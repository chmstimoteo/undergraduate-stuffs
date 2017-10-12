package biblioteca;

import java.util.Random;

import estruturas.Heap;

public class HeapSort {

	
	private Comparable[] data; 
	private int n;
	
	
	public HeapSort(Comparable[] data,int n)
	{
		this.data = data;
		this.n = n;
	}
	
	public  void sort()
	{
//		cria um subarray de n elementos a partir do array original
		Comparable v[] = this.subarray(0,this.n);
		Heap heap = new Heap(v);//Cria uma heap para esse subarray
	    heap.constroi();
	    
	    
	    //Organiza esse subarray e passa oo seus valores para
	    //o array original.Organizando os this.n primeiors elementos
	    // do array original em ordem crescente.
	    try{ 
	    while(this.n > 0)
	    {
	    	data[this.n] = heap.retiraMax();//Coloca o maior elemento na n-ésima 
	    	 //posição do array original
	    	
	    	this.n--;//dimunui o tamanho do array a organizar                      
	    } 
	       }
	     catch(AvisoException e){
	    	 System.err.println("Heap Vazia");
	     }
	}

	
	private Comparable[] subarray(int low,int high)
	{  
		Comparable v[] = new Comparable[high -low +1];
		for(int i=low;i<=high;i++)
			v[i] = this.data[i];
			
		return v;
	}
	
	
}

/**************************************************************************
 * Elliackin Messias do Nascimento Figueiredo              				  *
 * Carlos Henrique												          *
 *                                                                        *
 * Disciplina: Estruturas de Dados                                        *
 *      												 				  *
 *    									   								  *
 *  											 						  *
 * 																		  *
 *    								  									  *
 *     																	  *
 * 								              						      *
 *************************************************************************/
