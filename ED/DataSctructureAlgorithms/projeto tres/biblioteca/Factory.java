package biblioteca;

import java.util.Random;




public class Factory {

	private Comparable[] array;
    
	public static  enum Opcoes {ORDENADO,DESORDENADO,ALEATORIO};
    
    //contantes usadas para determinar o tamanho da entrada
	private  final int dez = 10;
    private  final int cen = 100;
    private  final int quinhetos = 500;
    private  final int doisMil = 2000;
    
    public Factory() {
    	this.array = new Comparable[2001];
    }
    
    //preenche o array aleatoriamente
    public void preencherAleatorio(int n) throws AvisoException{
        Random random = new Random();
        int aleatorio;
        int indice;

        for(indice = 1; indice <=n; indice++){
                
        	    aleatorio = 100 + random.nextInt(900);
        	    Comparable numero = new Integer(aleatorio);
                this.array[indice] = numero;
        }
        
       
    }
    
    //preenche o array ordenadamente
    public void preencherOrdenado(int n) throws AvisoException{
        int indice = 0;
        
       
        for(indice=0; indice < this.array.length; indice++){
            Integer numero = new Integer(indice);
            this.array[indice] = numero;
                }
        
               
      
    }
    
    //preenche desordenado
    public void preencherDesordenado(int n) throws AvisoException{
        int indice;
        
        for(indice= this.array.length-1; indice>=1; indice--){
            Integer numero = new Integer(indice + 100);
            this.array[indice] = (numero);
        }
        
       
    }
    
    
    //Métodos relacionados aos algoritmos de classificação
    
    public String[] insertion(Opcoes op) throws AvisoException{
        
    	String [] times = null;
        switch(op)
        {
        case ORDENADO: times = this.insertionOrdenado();break;
        	
        case ALEATORIO: times = this.insertionAleatorio();break;
        	
        case DESORDENADO: times = this.insertionDesordenado();break;	
        }
        
        return times;
    }
    
    private String[] insertionAleatorio() throws AvisoException
    {
    	long a1,a2,a3,a4,b1,b2,b3,b4;
        long ref;
         
        
        
        this.preencherAleatorio(this.doisMil);
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        b4 = a4-ref;
        

        
        this.preencherAleatorio(this.quinhetos);
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        b3 = a3-ref;
        
        
        this.preencherAleatorio(this.dez);
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        

        this.preencherAleatorio(this.cen);
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        long[] resultado = {(b1/1000), (b2/1000), (b3/1000), (b4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+ " us";
        return resp;
    }
    
     
    private String[] insertionOrdenado() throws AvisoException
    {
    	long a1,a2,a3,a4,b1 = 0,b2=0,b3=0,b4=0;
        long ref;
         
        
       
        this.preencherOrdenado(this.doisMil);
       
       
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        b4 = a4-ref;
        
       
        
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        b3 = a3-ref;
        
       
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        

       
        
        long[] resultado = {(b1/1000), (b2/1000), (b3/1000), (b4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+ " us";
        return resp;
    }
    
    private String[] insertionDesordenado() throws AvisoException
    {
    	long a1,a2,a3,a4,b1,b2,b3,b4;
        long ref;
         
        
        
        this.preencherDesordenado(this.doisMil);
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        b4 = a4-ref;
        

        
        this.preencherDesordenado(this.quinhetos);
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        b3 = a3-ref;
        
        
        
        this.preencherDesordenado(this.cen);
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        
        this.preencherDesordenado(this.dez);
        ref = System.nanoTime();
        Sort.insertionSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        

        
        
        long[] resultado = {(b1/1000), (b2/1000), (b3/1000), (b4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
 //Fim do Insertion
    
    
    private String[] selectionAleatorio() throws AvisoException{
        long a1,a2,a3,a4,b1,b2;
        long ref;
       
    
        
        this.preencherAleatorio(this.doisMil);
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.doisMil);
        a4 = System.nanoTime() - ref;
        
       
        
        this.preencherAleatorio(this.quinhetos);
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.quinhetos);
        a3 = System.nanoTime()-ref;
       
        this.preencherAleatorio(this.cen);
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        
       
        this.preencherAleatorio(this.dez);
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        
       
        long[] resultado = {(b1/1000), (b2/1000), (a3/1000), (a4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i]) + " us";
        return resp;
    }
    
    public String[] selection(Opcoes op) throws AvisoException{
    	
    	String [] times = null;
        switch(op)
        {
        case ORDENADO: times = this.selectionOrdenado();break;
        	
        case ALEATORIO: times = this.selectionAleatorio();break;
        	
        case DESORDENADO: times = this.selectionDesodernado();break;	
        }
        
        return times;
    }
    
    private String[] selectionOrdenado() throws AvisoException{
        long a1,a2,a3,a4,b1,b2;
        long ref;
       
    
        
        this.preencherOrdenado(this.doisMil);
        
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.doisMil);
        a4 = System.nanoTime() - ref;
        
       
       
        
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.quinhetos);
        a3 = System.nanoTime()-ref;
       
        
        
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        
        
        
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        
       
        long[] resultado = {(b1/1000), (b2/1000), (a3/1000), (a4/1000)};
         String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    
    private String[] selectionDesodernado() throws AvisoException{
        long a1,a2,a3,a4,b1,b2;
        long ref;
       
    
        
        this.preencherDesordenado(this.doisMil);
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.doisMil);
        a4 = System.nanoTime() - ref;
        
       
        
        this.preencherDesordenado(this.quinhetos);
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.quinhetos);
        a3 = System.nanoTime()-ref;
       
        this.preencherDesordenado(this.cen);
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        
       
        this.preencherDesordenado(this.dez);
        ref = System.nanoTime();
        Sort.selectionSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        
       
        long[] resultado = {(b1/1000), (b2/1000), (a3/1000), (a4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    
    
    public String[] shell(Opcoes op) throws AvisoException{
     
    	String[] times = null;
        switch(op)
        {
        case ORDENADO: times = this.shellOrdenado();break;
        	
        case ALEATORIO: times = this.shellAleatorio();break;
        	
        case DESORDENADO: times = this.shellDesordenado();break;	
        }
        
        return times;
    }
    
    //Método que retorna um array de Strings que representam o tempo
    //em microsegundo
    private String[] shellAleatorio() throws AvisoException{
        long a1,a2,a3,a4;
        long ref;
        
        
        this.preencherAleatorio(this.doisMil);
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.doisMil);
        a4 =System.nanoTime();
        a4 = a4-ref;
       
        
        this.preencherAleatorio(this.quinhetos);
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        a3 = a3-ref;
       
        this.preencherAleatorio(this.cen);
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.cen);
        a2 =System.nanoTime();
        a2 = a2-ref;
        
        this.preencherAleatorio(this.dez);
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.dez);
        a1 =  System.nanoTime();
        a1 = a1-ref;
        //Divite por 1000
     
        long[] resultado = {(a1/1000), (a2/1000),(a3/1000),(a4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+ " us";
        return resp;
    }
    
    private String[] shellDesordenado() throws AvisoException{
        long a1,a2,a3,a4;
        long ref;
        
        
        this.preencherDesordenado(this.doisMil);
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.doisMil);
        a4 =System.nanoTime();
        a4 = a4-ref;
       
        this.preencherDesordenado(this.quinhetos);
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        a3 = a3-ref;
        
        this.preencherDesordenado(this.cen);
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.cen);
        a2 =System.nanoTime();
        a2 = a2-ref;
        
        this.preencherDesordenado(this.dez);
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.dez);
        a1 =  System.nanoTime();
        a1 = a1-ref;
        
        long[] resultado = {(a1/1000), (a2/1000),(a3/1000),(a4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    
    private String[] shellOrdenado() throws AvisoException{
        long a1,a2,a3,a4;
        long ref;
        
        
        this.preencherOrdenado(this.doisMil);
        
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.doisMil);
        a4 =System.nanoTime();
        a4 = a4-ref;
       
        
        
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        a3 = a3-ref;
       
        
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.cen);
        a2 =System.nanoTime();
        a2 = a2-ref;
        
        
        ref = System.nanoTime();
        Sort.shellSort(this.array,this.dez);
        a1 =  System.nanoTime();
        a1 = a1-ref;
        
        long[] resultado = {(a1/1000), (a2/1000),(a3/1000),(a4/1000)};
       String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    
    
    public String[] heap(Opcoes op) throws AvisoException{
        
    	String [] times = null;
        switch(op)
        {
        case ORDENADO: times = this.heapOrdenado();break;
        	
        case ALEATORIO: times = this.heapAleatorio();break;
        	
        case DESORDENADO: times = this.heapDesordenado();break;	
        }
        
        return times;
    }
    
    
    private String[] heapAleatorio() throws AvisoException{
        long a1 = 1,a2 = 1,a3 = 1,a4 = 1;
        long ref =1;
        
       
        this.preencherAleatorio(this.doisMil);
        ref = System.nanoTime();
        Sort.heapSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        a4 = a4-ref;
        
        
        this.preencherAleatorio(this.quinhetos);
        ref = System.nanoTime();
        Sort.heapSort(this.array,this.quinhetos);
        a3 =System.nanoTime();
        a3 = a3-ref;
        
        
        this.preencherAleatorio(this.cen);
        ref =System.nanoTime();
        Sort.heapSort(this.array,this.cen);
        a2 = System.nanoTime();
        a2 = a2-ref;
        
        
        this.preencherAleatorio(this.dez);
        ref = System.nanoTime();
        Sort.heapSort(this.array,this.dez);
        a1 = System.nanoTime();
        a1 = a1-ref;
        
        
        long[] resultado = {(a1/1000),(a2/1000),(a3/1000),(a4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    
    private String[] heapDesordenado() throws AvisoException{
        long a1 = 1,a2 = 1,a3 = 1,a4 = 1;
        long ref =1;
        
       
        this.preencherDesordenado(this.doisMil);
        ref = System.nanoTime();
        Sort.heapSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        a4 = a4-ref;
        
        
        this.preencherDesordenado(this.quinhetos);
        ref = System.nanoTime();
        Sort.heapSort(this.array,this.quinhetos);
        a3 =System.nanoTime();
        a3 = a3-ref;
        
        
        this.preencherDesordenado(this.cen);
        ref =System.nanoTime();
        Sort.heapSort(this.array,this.cen);
        a2 = System.nanoTime();
        a2 = a2-ref;
        
        
        this.preencherDesordenado(this.dez);
        ref = System.nanoTime();
        Sort.heapSort(this.array,this.dez);
        a1 = System.nanoTime();
        a1 = a1-ref;
        
        
        long[] resultado = {(a1/1000),(a2/1000),(a3/1000),(a4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    
    private String[] heapOrdenado() throws AvisoException{
        long a1 = 1,a2 = 1,a3 = 1,a4 = 1;
        long ref =1;
        
       
        this.preencherOrdenado(this.doisMil);
        
        ref = System.nanoTime();
        Sort.heapSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        a4 = a4-ref;
        
        
        
        ref = System.nanoTime();
        Sort.heapSort(this.array,this.quinhetos);
        a3 =System.nanoTime();
        a3 = a3-ref;
        
        
        
        ref =System.nanoTime();
        Sort.heapSort(this.array,this.cen);
        a2 = System.nanoTime();
        a2 = a2-ref;
        
        
        
        ref = System.nanoTime();
        Sort.heapSort(this.array,this.dez);
        a1 = System.nanoTime();
        a1 = a1-ref;
        
        
        long[] resultado = {(a1/1000),(a2/1000),(a3/1000),(a4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    
    public String[] quick(Opcoes op) throws AvisoException{
        
    	String [] times = null;
        switch(op)
        {
        case ORDENADO: times = this.quickOrdenado();break;
        	
        case ALEATORIO: times = this.quickAleatorio();break;
        	
        case DESORDENADO: times = this.quickDesordenado();break;	
        }
        
        return times;
    }
    
private String[] quickDesordenado() throws AvisoException{
        
    	long a1,a2,a3,a4,b1,b2,b3,b4;
        long ref;
        
        this.preencherDesordenado(this.doisMil);
        ref = System.nanoTime();
        Sort.quickSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        b4 = a4-ref;
        
        this.preencherDesordenado(this.quinhetos);
        ref = System.nanoTime();
        Sort.quickSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        b3 = a3-ref;
        
        this.preencherDesordenado(this.cen);
        ref = System.nanoTime();
        Sort.quickSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        this.preencherDesordenado(this.dez);
        ref = System.nanoTime();
        Sort.quickSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        
        
        long[] resultado = {(b1/1000),(b2/1000),(b3/1000),(b4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
  

 private String[] quickOrdenado() throws AvisoException{
    
	long a1,a2,a3,a4,b1,b2,b3,b4;
    long ref;
    
    this.preencherOrdenado(this.doisMil);
    
    ref = System.nanoTime();
    Sort.quickSort(this.array,this.doisMil);
    a4 = System.nanoTime();
    b4 = a4-ref;
    
    
    ref = System.nanoTime();
    Sort.quickSort(this.array,this.quinhetos);
    a3 = System.nanoTime();
    b3 = a3-ref;
    
    
    ref = System.nanoTime();
    Sort.quickSort(this.array,this.cen);
    a2 = System.nanoTime();
    b2 = a2-ref;
    
    
    ref = System.nanoTime();
    Sort.quickSort(this.array,this.dez);
    a1 = System.nanoTime();
    b1 = a1-ref;
    
    
    long[] resultado = {(b1/1000),(b2/1000),(b3/1000),(b4/1000)};
    String[] resp = new String[4];
    
    for(int i = 0;i < 4;i++)
    	resp[i] = String.valueOf(resultado[i])+" us";
    return resp;
}
private String[] quickAleatorio() throws AvisoException{
    
	long a1,a2,a3,a4,b1,b2,b3,b4;
    long ref;
    
    this.preencherAleatorio(this.doisMil);
    ref = System.nanoTime();
    Sort.quickSort(this.array,this.doisMil);
    a4 = System.nanoTime();
    b4 = a4-ref;
    
    this.preencherAleatorio(this.quinhetos);
    ref = System.nanoTime();
    Sort.quickSort(this.array,this.quinhetos);
    a3 = System.nanoTime();
    b3 = a3-ref;
    
    this.preencherAleatorio(this.cen);
    ref = System.nanoTime();
    Sort.quickSort(this.array,this.cen);
    a2 = System.nanoTime();
    b2 = a2-ref;
    
    this.preencherAleatorio(this.dez);
    ref = System.nanoTime();
    Sort.quickSort(this.array,this.dez);
    a1 = System.nanoTime();
    b1 = a1-ref;
    
    
    long[] resultado = {(b1/1000),(b2/1000),(b3/1000),(b4/1000)};
    String[] resp = new String[4];
    
    for(int i = 0;i < 4;i++)
    	resp[i] = String.valueOf(resultado[i])+" us";
    return resp;
}
    
    public String[] merge(Opcoes op) throws AvisoException{
    	
    	String[] times = null;
        switch(op)
        {
        case ORDENADO: times = this.mergeOrdenado();break;
        	
        case ALEATORIO: times = this.mergeAleatorio();break;
        	
        case DESORDENADO: times = this.mergeDesordenado();break;	
        }
        
        return times;
    }
    private String[] mergeAleatorio() throws AvisoException{
        long a1,a2,a3,a4,b1,b2,b3,b4;
        long ref;
        
        this.preencherAleatorio(this.doisMil);
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        b4 = a4-ref;
        
        this.preencherAleatorio(this.quinhetos);
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        b3 = a3-ref;
        
        this.preencherAleatorio(this.cen);
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        this.preencherAleatorio(this.dez);
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        
        
        long[] resultado = {(b1/1000), (b2/1000), (b3/1000), (b4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    private String[] mergeDesordenado() throws AvisoException{
        long a1,a2,a3,a4,b1,b2,b3,b4;
        long ref;
        
        this.preencherDesordenado(this.doisMil);
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        b4 = a4-ref;
        
        this.preencherDesordenado(this.quinhetos);
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        b3 = a3-ref;
        
        this.preencherDesordenado(this.cen);
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        this.preencherDesordenado(this.dez);
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        
        
        long[] resultado = {(b1/1000), (b2/1000), (b3/1000), (b4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    private String[] mergeOrdenado() throws AvisoException{
        long a1,a2,a3,a4,b1,b2,b3,b4;
        long ref;
        
        this.preencherOrdenado(this.doisMil);
        
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.doisMil);
        a4 = System.nanoTime();
        b4 = a4-ref;
        
        
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.quinhetos);
        a3 = System.nanoTime();
        b3 = a3-ref;
        
        
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.cen);
        a2 = System.nanoTime();
        b2 = a2-ref;
        
        
        ref = System.nanoTime();
        Sort.mergeSort(this.array,this.dez);
        a1 = System.nanoTime();
        b1 = a1-ref;
        
        
        long[] resultado = {(b1/1000), (b2/1000), (b3/1000), (b4/1000)};
        String[] resp = new String[4];
        
        for(int i = 0;i < 4;i++)
        	resp[i] = String.valueOf(resultado[i])+" us";
        return resp;
    }
    
  

}
