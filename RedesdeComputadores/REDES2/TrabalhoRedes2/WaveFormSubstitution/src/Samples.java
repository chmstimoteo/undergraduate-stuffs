package src;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class Samples {

	private ArrayList samples;
	
	public Samples(){
		this.samples = new ArrayList();
	}
	
	public Samples(ArrayList samples){
		this.samples = samples;
	}
	
	public Samples(byte[] bytes, int bytesPerSample){
		this.samples = new ArrayList();
		int qtdeSamples = bytes.length/bytesPerSample;
		
		Sample sample = new Sample(bytesPerSample);
		byte b;
		for(int i=0; i<qtdeSamples; i++){
			for(int j=0; j<bytesPerSample; j++){
				int p = (bytesPerSample*i)+j;
				b = bytes[p];
				sample.addByte(b,j);
			}
			this.addSample(sample);
		}
	}
	
	public void addSample(Sample sample){
		this.samples.add(sample);
	}
	
	public boolean removeSample(Sample sample){
		return this.samples.remove(sample);
	}
	
	public boolean containSample(Sample sample){
		return this.samples.contains(sample);
	}
	
	public int getQtdeSamples(){
		return this.samples.size();
	}
	
	public Sample getSampleAt(int position){
		return ((Sample)this.samples.get(position));
	}
	
	public Iterator getIterator(){
		return this.samples.iterator();
	}
	
	public String toString(){
		String str ="";
		Iterator it = this.samples.iterator();
		while(it.hasNext()){
			str += ((Sample)it.next()).toString();
		}
		return str;
	}
	
	public BigInteger toBinary(){
		return new BigInteger(this.toString());
	}
	
	public String toIntString(){
		String str ="";
		Iterator it = this.samples.iterator();
		while(it.hasNext()){
			str += ((Sample)it.next()).toIntString();
		}
		return str;
	}
	
	public int toInteger(){
		return Integer.parseInt(this.toIntString());
	}
	
	
	
	
	/*public static void main(String[] args){
		byte b = 8;
		//byte[] a = {1,12};
		//Byte bt = new Byte(b);

		byte[] b2 = {2,4};
		Sample s1 = new Sample(b2);
		
		System.out.println(s1.toInteger());
		System.out.println(s1.toBinary());
		
		byte[] b1 = {2,8};
		Sample s2 = new Sample(b1);
		
		
		
		Samples sm = new Samples();

		sm.addSample(s1);
		sm.addSample(s2);
		BigInteger lng = new BigInteger(sm.toString());
		
		//System.out.println(sm.getSampleAt(0).toInteger());
		System.out.println("Binary" + sm.toBinary());
		System.out.println("String:" + sm.toString());
		System.out.println("Integer:" + sm.toInteger());
		
		//System.out.println(Integer.toBinaryString(b));
	}*/
}
