package biblioteca;

import java.util.Random;

public class InsertionSort {

	
	private Comparable[] data;
	private int n;
	
	public InsertionSort(Comparable[] data,int n)
	{
		this.data = data;
		this.n = n;
	}
	
	public void sort()
	{
		Comparable insert;
		
		for( int next = 2; next <= n;next++)
		{
			insert = data[ next ];
			
			int moveItem = next;
  		    
			while(moveItem > 1 && data[moveItem - 1].compareTo(insert) > 0)
			{
				data[ moveItem ] = data[moveItem -1];
				moveItem--;
			}
			
			data[ moveItem ] = insert;
			
		}
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
