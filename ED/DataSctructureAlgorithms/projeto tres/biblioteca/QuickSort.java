package biblioteca;

import java.util.Random;


class LimiteParticao{
int i,j;	
}


public class QuickSort {

	
	public Comparable[] data;
	int n;
	
	public QuickSort(Comparable[] data,int n)
	{
		this.data = data;
		this.n = n;
	}
	
	public void sort()
	{
		this.quicksort(1,this.n);
	}
	
	
	
	private void quicksort(int esq,int dir)
	{   
		LimiteParticao p = this.partition(esq,dir);
		if(esq < p.j) this.quicksort(esq,p.j);
		if(p.i < dir) this.quicksort(p.i,dir);
	}
	
	
	private LimiteParticao partition(int esq,int dir)
	{
		LimiteParticao p =new LimiteParticao();
		
		p.i = esq;
		p.j = dir;
		Comparable x = this.data[(p.i + p.j + 1)/2];//Calcula o meio
		Comparable temp;
		do{
			while(x.compareTo(data[p.i]) > 0) p.i++;
			while(x.compareTo(data[p.j]) < 0) p.j--;
			if(p.i <= p.j)
			{
				temp = this.data[p.i];
			    this.data[p.i] = this.data[p.j];
			    this.data[p.j] = temp;
			    p.i++;
			    p.j--;	
			}
		  }while(p.i <= p.j);
		
		return p;
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