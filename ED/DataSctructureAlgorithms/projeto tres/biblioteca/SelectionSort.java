package biblioteca;


import java.util.Random;

public class SelectionSort
{
   private Comparable[] data; 
   private int n;

   
   public SelectionSort( Comparable[] data,int n)
   {
	   this.data = data;
	   this.n = n;
   } 

  
   public void sort()                
   {
      int smallest; // �ndice do menor elemento

      // faz um loop sobre this.n - 1 elementos      
      for ( int i = 1; i <= this.n - 1; i++ )
      {
         smallest = i; // primeiro �ndice do array remanescente

         // faz um loop para localizar o �ndice do menor elemento              
         for ( int index = i + 1; index <= this.n; index++ )
            if ( data[ index ].compareTo(data[ smallest ]) < 0 )             
               smallest = index;                                

         swap( i, smallest ); // permuta o menor elemento na posi��o
         
      } // fim do for externo
   } // fim do m�todo sort                                             

   // m�todo auxiliar para permutar valores em dois elementos
   public void swap( int first, int second )
   {
      Comparable temporary = data[ first ]; // armazena o primeiro no tempor�rio
      data[ first ] = data[ second ]; // substitui o primeiro pelo segundo
      data[ second ] = temporary; // coloca o tempor�rio no segundo
   } // fim do m�todo swap

   // imprime uma passagem do algoritmo
   
   
   
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