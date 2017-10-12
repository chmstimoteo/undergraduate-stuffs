package biblioteca;

import java.util.Random;



public class MergeSort {

	private Comparable[] data; 
	private int n;
	
	
	public MergeSort(Comparable[] data,int n)
	{
		this.data = data;
		this.n = n;
	}
	
	public void sort()
	{
		
		this.sortArray(1,this.n);
	}   
    
	
   // Método que implementa o algorítmo do mergesort	
	private void sortArray(int low,int high)
	{
	    if((high-low) >= 1)
	    {
	    	int middle1 = (low + high)/2;//Calcula a primeira metade
	    	int middle2 = middle1 + 1;//Calcula a segunda metade
	    	
	    	this.sortArray(low,middle1);
	    	this.sortArray(middle2,high);
	    	this.merge(low,middle1,middle2,high);
	    }
	}
	
	//Junta os dois subarray ordenados
	private void merge(int left,int middle1,int middle2,int right)
	{
		int leftIndex = left;
		int rightIndex = middle2;
		int combinadIndex = left;
		Comparable[] combinad = new Comparable[this.n + 1];
		
		while(leftIndex <=middle1 && rightIndex <=right )
		{
			if(this.data[leftIndex].compareTo(this.data[rightIndex]) < 0)
				combinad[combinadIndex++] = this.data[leftIndex++];
			else
				combinad[combinadIndex++] = this.data[rightIndex++];
		}
		
		if(leftIndex == middle2)
			while(rightIndex <=right)
				combinad[combinadIndex++] = this.data[rightIndex++];
		else
			while(leftIndex <= middle1)
				combinad[combinadIndex++] = this.data[leftIndex++];
		
		for(int i=left;i<=right;i++)
			this.data[i] = combinad[i];
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
