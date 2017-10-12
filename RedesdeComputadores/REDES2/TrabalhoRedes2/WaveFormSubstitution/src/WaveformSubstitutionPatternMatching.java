package src;

import java.util.Iterator;

public class WaveformSubstitutionPatternMatching {

	//merge Duration. Ex: _tm = 1 ms.
	private double _Tm;
	//packet duration. 
	private double _Tp;// Ex: 8 ms.
	//This is the samples per Packet => Loss 1 packet => 
	//_L lost samples that will be rebuild EX: 64 samples = 256 bytes
	//1 pacote tem 256 bytes
	private int _L;
	private byte[] lossesBits;
	//Template Samples before the missing packet.  
	//Will indicate the nexts bits that will be copyed to the losses bits.
	//The best samples that match the template(bits before lost packet)
	private int _M; // 32 samples = 0.5 packet = 128 bytes
	private byte[] templatebits;
	private Samples templateSamples = new Samples();
	//Search window of duration N samples to find the M samples that best
	//match the template
	
	//searchWindowSamples
	private int _N; //2 pacotes = 128 samples = 512  bytes
	private byte[] searchWindow;
	private Samples windowSamples;
	
	
	public WaveformSubstitutionPatternMatching(int _L, byte[] lossesBits, byte[] buffer, int lostPosition){
		this._L = _L;
		this.lossesBits = lossesBits;
		
		this._Tm = PatternMatchingConstants.TM;
		this._Tp = PatternMatchingConstants.TP;
		this._M = PatternMatchingConstants.M;
		this._N = PatternMatchingConstants.N;
		
		this.templatebits = new byte[this._M];
		this.searchWindow = new byte[this._N];

		//Carregando searchWindow e template. 
		this.chargeElements(buffer, lostPosition);
		
		this.templateSamples = new Samples(this.templatebits, PatternMatchingConstants.bytesPerSample);
		this.windowSamples = new Samples(this.searchWindow, PatternMatchingConstants.bytesPerSample);
		
		
	}
	
	public void chargeElements(byte[] buffer, int lostPosition){
		int initTemplate = (lostPosition - this._M);
		//Carregando o template
		for(int i=0; i<this._M; i++){
			this.templatebits[i] = buffer[(initTemplate)+i];
		}
		int initSearch = (initTemplate-this._N);
		//Carregando a searchWindow
		for(int j=0; j<this._N; j++){
			this.searchWindow[j] = buffer[(initSearch)+j];
		}
	}
	
	
	/*One of the packets is missing and the algorithm
	searches previous packets to find L samples that resemble
	the missing packet.
	At the end of the procedure, _L will receive the _M samples optimized*/
	
	/*We move the search window Tm ms to the left and append
	 Tm ms of received speech to each end of the replacement packet.*/
	//Eu não entendi essa técnica.
	public void mergingTechnique(){
		
	}
	
	
	public void patternMatching(){
		substitution(this.crossCorrelationSearch());
	}
	
	
	public void substitution(int index){
		Sample sampleSelected = null;
		int bytesPerPacket = PatternMatchingConstants.bytesPerPacket;
		int bytePerSample = PatternMatchingConstants.bytesPerSample;
		int samplesPerPacket = PatternMatchingConstants.samplesPerpacket;
		int initSubstitutionSample = index+this._M+1;
		Samples substitutionSamples = new Samples();
		//Criar um conjunto de amostras de onde serao extraidos os dados
		for(int i=0; i<samplesPerPacket; i++){
			sampleSelected = this.windowSamples.getSampleAt((initSubstitutionSample)+i);
			substitutionSamples.addSample(sampleSelected);
		}
		//bytes a serem substituidos
		Iterator it = substitutionSamples.getIterator();
		Sample s;
		s = ((Sample)it.next());
		byte[] first = s.getSampleBytes();
		byte[] sBytes = new byte[bytesPerPacket];
		int count = 0;
		//Preenchendo o array de bytes do espaco
		while(it.hasNext()){
			s = ((Sample)it.next());
			byte[] t = s.getSampleBytes();
			byte[] temp;
			temp = this.appendBytesEnd(first,t);
			count += temp.length;
			sBytes = this.putBytes(sBytes,temp,count);
		}
		//Repassando para os bytes perdidos
		for(int i=0; i<bytePerSample; i++){
			this.lossesBits[i] = sBytes[i];
		}
		
	}
	
	private byte[] appendBytesEnd(byte[] b1, byte[] b2){
		byte[] bResult = new byte[b1.length+b2.length];
		for (int i = 0; i < b1.length; i++) {
			bResult[i] = b1[i];
		}
		for (int i = 0; i < b2.length; i++) {
			bResult[b1.length+i] = b2[i];
		}
		
		return bResult;
	}
	
	private byte[] putBytes(byte[] b1, byte[] b2, int position){
		for (int i = 0; i < b2.length; i++) {
			b1[position+i] = b2[i];
		}
		return b1;
	}

	//Cross correlation of samples in the template and samples 
	//of the search window
	/*How this works: Ele vai realizando a operação AND de um a um
	 * sample para verificar se há quantidade iguais de samples
	 * do template M e da janela N+M, após isso normaliza com a própria janela.*/
	public int crossCorrelationSearch(){
		int n;
		int divisor = 0;
		int dividendo = 0;
		int Cn[] = new int[((int)this._N)-1];
		
		for(n=1; (n==this._N); n++){
			for(int m=1; (m==this._M); m++){
				Sample divis = (this.templateSamples.getSampleAt(m)).product(this.windowSamples.getSampleAt(n+m));
				Sample divid = (this.windowSamples.getSampleAt(n+m)).product(this.windowSamples.getSampleAt(n+m));
				divisor += divis.toInteger();
				dividendo += divid.toInteger();
			}
			Cn[n] = divisor/dividendo;
		}
		int nMatched = maximum(Cn);
		return nMatched;
		
	}
	
	public int signCorrelationSearch(){
		int[] Sn = new int[((int)this._N)-1];
		int sub = 0;
		int nMatched;
		for(int n=1; (n==this._N); n++){
			for(int m=1; (m==this._M); m++){
				sub += this.sign(this.templateSamples.getSampleAt(m))*sign(this.windowSamples.getSampleAt(n+m));
			}
			Sn[n] = sub;
		}
		
		nMatched = maximum(Sn);
		return nMatched;
	}
	
	private int maximum(int[] sn) {
		int result = sn[0];
		for(int i=0; i<sn.length; i++){
			if(result<sn[i])
				result = sn[i];
		}
		return result;
	}


	private int sign(Sample x){
		
		int _x = x.toInteger();
		if(_x>0)
			return 1;
		//if(_x<0)
		return -1;
		
	}
	
	/*Waveform Differences: The algorithm 
	*seeks the minimum sums of absolute differences
	*IMPORTANT:
	*Now, the result is sensitive to waveform shapes
	*rather than level changes. The speech segments 
	*are normalized first.
	*/
	
	/*Normalization Methods: One is to divide the samples
	 *of each segment by the square root of the energy of that
	 *segment*/
	
	
	public int differenceWaveformSearch_firstWay(){
		
		int D1n[] = new int[((int)this._N)-1];
		int subD1n = 0;
		int sub1 = 0;
		
		for(int n=1; (n==this._N); n++){
			for(int m=1; (m==this._M); m++){
				for(int j=1; (j==this._M); j++){
					sub1 += ((this.templateSamples.getSampleAt(m).toInteger()/
									Math.sqrt(Math.pow(this.templateSamples.getSampleAt(j).toInteger(),2)))
							-(this.windowSamples.getSampleAt(n+m).toInteger()/
									Math.sqrt(Math.pow(this.windowSamples.getSampleAt(n+j).toInteger(),2))));
				}
				subD1n += Math.abs(sub1);
			}
			D1n[n] = subD1n;
		}
		
		int nMatched = maximum(D1n);
		return nMatched;
	}
	
	public int differenceWaveformSearch_secondWay(){
		int D1n[] = new int[((int)this._N)-1];
		int subD1n = 0;
		int sub1 = 0;
		
		for(int n=1; (n==this._N); n++){
			for(int m=1; (m==this._M); m++){
				for(int j=1; (j==this._M); j++){
					sub1 += ((this.templateSamples.getSampleAt(m).toInteger()/
									Math.abs(this.templateSamples.getSampleAt(j).toInteger()))
							-(this.windowSamples.getSampleAt(n+m).toInteger()/
									Math.abs(this.windowSamples.getSampleAt(n+j).toInteger())));
				}
				subD1n += Math.abs(sub1);
			}
			D1n[n] = subD1n;
		}
		
		int nMatched = maximum(D1n);
		return nMatched;
	}
	
	public int differenceWaveformSearch_thirdWay(){
		
		int D1n[] = new int[((int)this._N)-1];
		int subD1n = 0;
		int sub1 = 0;
		
		for(int n=1; (n==this._N); n++){
			for(int m=1; (m==this._M); m++){
					sub1 += Math.abs(((this.templateSamples.getSampleAt(m).toInteger()/
									(maximumSample(this.templateSamples) - minimumSample(this.templateSamples))
							-(this.windowSamples.getSampleAt(n+m).toInteger()/
									(maximumSample(this.windowSamples) - minimumSample(this.windowSamples))))));
				
			}
			D1n[n] = subD1n;
		}
		
		int nMatched = maximum(D1n);
		return nMatched;
	}


	private int maximumSample(Samples samples) {
		Iterator it = samples.getIterator();
		Sample sam = (Sample)it.next();
		int res = sam.toInteger();
		while(it.hasNext()){
			int key = ((Sample)it.next()).toInteger();
			if(res < key)
				res = key;
		}
			
		return res;
	}


	private int minimumSample(Samples samples) {
		Iterator it = samples.getIterator();
		Sample sam = (Sample)it.next();
		int res = sam.toInteger();
		while(it.hasNext()){
			int key = ((Sample)it.next()).toInteger();
			if(res > key)
				res = key;
		}
			
		return res;
	}
	
	/*Falta implementar mergingTechnique e integra com a tecnica de busca selecionada.
	 * Por enquanto ele somente está encontrando o template otimizado. 
	 * Falta, pegar o pacote proximo a esse para fazer a substituição 
	 * e depois a merging technique.
	 * 
	 * The two-sidded approach é um método que vai fazer com que o paote gerado seja o
	 * melhor possível, já que é analisado com os pacotes anteriores à perda e posteri-
	 * ores à perda. No entanto, isso adiciona um atraso de processamento para a busca
	 * e possivelmente teremos que ter uma janela maior e um algoritmo mais complexo.
	 * Dessa forma, não é interessante a sua implementação, já que estamos tentando 
	 * recuperar patte do fonema.*/
}
