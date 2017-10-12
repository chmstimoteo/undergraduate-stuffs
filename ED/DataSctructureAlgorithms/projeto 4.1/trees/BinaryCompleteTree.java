package trees;

import java.util.Random;
import com.sun.jmx.remote.internal.ArrayQueue;
import exceptions.AvisoException;
import nodes.TreeNode;

public class BinaryCompleteTree<T> extends BinaryTree<T> {

	private int tamanho;
	
	
	
	public BinaryCompleteTree()
	{
		super(new TreeNode<T>(null, null, null, null));
		this.root = null;
		this.tamanho = 0;
		
	}

	public void insert(Comparable<T> element) {
		
		this.insertAux(element);
	}

	
	private void  insertAux(Comparable<T> data)
	{
		ArrayQueue queue = new ArrayQueue( this.size );
		TreeNode<T> node = null;
		
		if(this.root == null)
		{	
			this.root = new TreeNode<T>(null,null,null,data);
			super.size = super.size + 1;
			
		    return ;
		}
		
		queue.add(this.root);
		
		 
		while(!queue.isEmpty())
		{
			 node = (TreeNode<T>) queue.remove(0);
			
			if(node.hasAllChildren())
			{
				queue.add(node.getChildLeft());
			    queue.add(node.getChildRight());
			}
			else
			{
			     if(node.getChildLeft() == null)
			     {
			    	 node.setChildLeft(new TreeNode<T>(node,null,null,data));
			    	 this.size += 1;
			    	 return;
			     }
			     else
			     {
			       node.setChildRight(new TreeNode<T>(node,null,null,data)); 
			       this.size += 1;
			       return ;
			     }
			         
			 }
		}
		
	}
	
    public TreeNode<T> find(T element) throws AvisoException  {
		
		TreeNode<T> result = find(this.root, element);
		if(result == null)
			throw new AvisoException("Elemento não encontrado findBCT");
		return result;
	}
    
    
	private TreeNode<T> find(TreeNode<T> node, T element) {
		
		
		if( node != null ) {
			if( node.getElement().compareTo(element) == 0 ) {
				return node;
			} else {
				TreeNode<T> leftResult = find(node.getChildLeft(), element);
				if(leftResult != null) {
					return leftResult;
				} else {
					return find(node.getChildRight(), element);
				}
				
			}
		}
		return null;
	}
	
	public boolean isEmpty()
	{
		if(this.root==null && this.tamanho==0 )
			return true;
		return false;
	}
	
	
	
	
	
	
	public void remove(T element) throws AvisoException {
	
		//Faltou fazer, mas nao vai USAR.
		
	}

	
	
	
	
	public void teste()
	{
		 
	       Random random = new Random();
	       int v[] = {18,43,54,35,89,27,43,87,53,67,15,38,32,65,70,83,97
	    		   ,12,19,47,25,26,14,78,49};
	        Comparable[] arrayInsetion = new Comparable[2000];
	    	Comparable[] arraySearch;
	   	  long soma = 0,dt = 0,tempo_0 = 0,tempo = 0;
	       
	   	  
	   	Random random1 = new Random();
      int aleatorio;
      int indice;

      
    
      
      for(indice = 0; indice <25; indice++)
      {
              
      	    aleatorio = 100 + random.nextInt(900);
      	    Comparable numero = new Integer(v[indice]);
               arrayInsetion[indice] = numero;
      }
      
      
      
	       

	       for(int i=0;i<25;i++)
	    	{
	    		//tem um syso
	    		//System.out.println(i);
	    		tempo_0 = System.nanoTime();
	    		
	    		this.insert( arrayInsetion[i]);
	    		tempo = System.nanoTime();;
	    		dt = tempo - tempo_0;
	            soma+=dt;   	
	    	}
	  
	}
	
	
	
	public static void main(String[] args) {
		
		  
	       
		  BinaryCompleteTree tree= new BinaryCompleteTree();
		
	       System.out.println("****");
	       tree.teste();
	       //tree.printTree();
	       
	       tree.inOrder();
	       
	       try {
			tree.remove(27);
			tree.remove(27);
		} catch (AvisoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       //System.out.println("\n\nNovo Pai : " + tree.root);
	       
	       
	     
	       /*System.out.println("\n\nRemovendo : " + 65);
	       tree.remover(65);
	       tree.remover(27);
	       tree.remover(54);
	       tree.remover(32);
	       
	       tree.printTree();
	       /*System.out.println("Removendo " + 54);
	       
	       tree.remove(54);
	       System.out.println("****");
	       tree.printTree();*/

	}

	
	

}
