package biblioteca;

import java.util.Random;

public class ShellSort {

	 private Comparable[] data; 
	 private int n;

	
	public ShellSort(Comparable[] data,int n) {
		
		this.data = data;
		this.n = n;
	}

	public void sort()
	{		
		 int h = 1;
		 
		 while(h < n)// obtém o valor do maior h
	     {
			 h = 3 * h + 1; 
	     }
		 h  = (h -1)/3 ;//despresa o último valor de h 
		 
		 do { 
			
			for (int i=h; i<=n; i++)
		    { 
		      Comparable x = this.data[i];
		      int j = i;
		      
		      while (j>h &&  x.compareTo(data[j-h])< 0 )
		      {
		        data[j] = data[ j-h ]; 
		        j -= h; 
		      } 
		      data[j]= x;
		    }
		
		  h  = (h -1)/3 ; 
		
		}while (h>=1);
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