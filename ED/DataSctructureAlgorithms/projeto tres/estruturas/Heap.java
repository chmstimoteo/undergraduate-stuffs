package estruturas;

import biblioteca.AvisoException;

public class Heap {

	private Comparable data[];
	private int n;
	
	public Heap(Comparable v[])
	{
		this.data = v;
		this.n = v.length-1;
	}
	
	private void exchange(Comparable first,Comparable second)
	{
		Comparable temp;
		temp = first;
		first = second;
		second = temp;
	}
	
	public void refaz(int position)
	{
	   int maior = 0;	//armazena o maior
	   int left = 2 * position;
	   int right = 2 * position + 1;
	   
	   if(left <= this.n && this.data[left].compareTo(this.data[position]) > 0)
		   maior = left;
	   else
		   maior = position;
	   if(right <= this.n && this.data[right].compareTo(this.data[maior])> 0)
		   maior = right;
	  
	   if(position != maior){
		    
		    Comparable temp;
			
		    temp = this.data[position];
			this.data[position] = this.data[maior];
			this.data[maior] = temp;
			
		   refaz(maior);
	   }
		
	}
	
	
	public void constroi()
	{
		for(int i = this.n/2;i>0;i--)
			this.refaz(i);
	}
	
	public Comparable max()
	{
		return data[1];
	}

    public Comparable retiraMax() throws AvisoException
    {
    	if(!isEmpty()){
    	Comparable maximo = this.data[1];
    	this.data[1] = data[this.n--];
    	this.refaz(1);
    	
    	return maximo;
    	}
    	throw new AvisoException("Heap Vazia"); 
    }
    
    public boolean isEmpty()
    {
    	return this.n == 0;
    }
    
    
    public void aumentaChave(int i,Comparable newElement)
    {
    	this.data[i] = newElement;
    	
    	while( i>1 && newElement.compareTo(this.data[i/2]) >= 0)
    	{
    		this.exchange(this.data[i],this.data[i/2]);
    		i /=2; 
    	}
    }

}


