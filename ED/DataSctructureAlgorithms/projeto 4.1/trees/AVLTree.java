package trees;


import java.util.Random;

import nodes.AVLNode;
import exceptions.AvisoException;

public class AVLTree<T> extends BinarySearchTree<T> {

	public AVLTree() {
		super();
	}

	public void insert(Comparable<T> element) {
		super.insert(element);
		this.balance(this.actionPosition().getFather());
	}

	//Mesmo sem necessidade, o método é redefinido
	//para retornar explicitamente um tipo AVLTreeNode<T>
	public AVLNode<T> find(T element) throws AvisoException{
		return ((AVLNode<T>)super.find(element));
	}

	public void remove(T element) throws AvisoException {
		super.remove(element);
		this.balance(this.actionPosition());
	}

	
	protected AVLNode<T> newNode(Comparable<T> element) {
		return new AVLNode<T> (1, null, null, null, element);
	}

	
	protected AVLNode<T> actionPosition() {
		return ((AVLNode<T>)super.actionPosition());
	}

	protected AVLNode<T> root() {
		return ((AVLNode<T>)super.root());
	}

	
	private int height(AVLNode<T> node) {
		if(node == null)
			return 0;	
		return node.getHeight();
	}

	
	private void setHeight(AVLNode<T> node) {
		if(node != null)
			node.setHeight(1 + Math.max( height(node.getChildLeft()), height(node.getChildRight())));
	}

	
	private AVLNode<T> tallerChild(AVLNode<T> node) {
		if(height(node.getChildLeft()) >= height(node.getChildRight()))
			return node.getChildLeft();
		return node.getChildRight();
	}

	
	private boolean isLeftChild(AVLNode<T> child, AVLNode<T> parent) {
		if(parent.getChildLeft()!= null && parent.getChildLeft().equals(child))
			return true;
		return false;
	}

	private boolean isRightChild(AVLNode<T> child, AVLNode<T> parent) {
		if(parent.getChildRight()!= null && parent.getChildRight().equals(child))
			return true;
		return false;
	}

	private boolean isChild(AVLNode<T> child, AVLNode<T> parent) {
		if( (isLeftChild(child, parent) || isRightChild(child, parent)) && !(isLeftChild(child, parent) && isRightChild(child, parent)) )
			return true;
		return false;
	}

	private boolean isParentReferenceCorrect(AVLNode<T> child) {
		if(isChild(child, child.getFather()))
			return true;
		return false;
	}
	
	private int bFactor(AVLNode<T> node) {
		return height(node.getChildLeft()) - height(node.getChildRight());
	}

	
	private boolean isBalanced(AVLNode<T> node) {
		
		return ((-1<=bFactor(node)) && (bFactor(node)<=1));
	}

	
	private void balance(AVLNode<T> position) {
		while(position != null) {
			setHeight(position);
			AVLNode<T> parent = position.getFather();
			if(!isBalanced(position)) {
				AVLNode<T> botton = tallerChild(tallerChild(position)); //botton é o nó inferior do "trinodo" que precisa ser rotacionado...
				if(parent != null) {
					if(isLeftChild(position, parent))
						parent.setChildLeft(rotacao(botton));
					else
						parent.setChildRight(rotacao(botton));
				} else {
					super.setRoot(rotacao(botton));
				}
			}
			position = parent;
		}
	}

	private AVLNode<T> rotacao(AVLNode<T> x) {
		
		AVLNode<T> y = x.getFather();
		AVLNode<T> z = y.getFather(); 
		AVLNode<T> newSubTreeRoot = null;
		AVLNode<T> newSubTreeRootParent = z.getFather();
		
		if(!isLeftChild(x, y) && !isLeftChild(y, z))
			newSubTreeRoot = restructure(z, y, x, z.getChildLeft(), y.getChildLeft(), x.getChildLeft(), x.getChildRight());
		else if(isLeftChild(x, y) && isLeftChild(y, z))
			newSubTreeRoot = restructure(x, y, z, x.getChildLeft(), x.getChildRight(), y.getChildRight(), z.getChildRight());
		else if(isLeftChild(x, y) && !isLeftChild(y, z))
			newSubTreeRoot = restructure(z, x, y, z.getChildLeft(), x.getChildLeft(), x.getChildRight(), y.getChildRight());
		else
			newSubTreeRoot = restructure(y, x, z, y.getChildLeft(), x.getChildLeft(), x.getChildRight(), z.getChildRight());
		newSubTreeRoot.setFather(newSubTreeRootParent);	
		setHeight(newSubTreeRoot.getChildRight());
		setHeight(newSubTreeRoot.getChildLeft());
		setHeight(newSubTreeRoot);
		
		return newSubTreeRoot;
	
	}

	private AVLNode<T> restructure(AVLNode<T> a, AVLNode<T> b, AVLNode<T> c,
				AVLNode<T> t0,
					AVLNode<T> t1,
						AVLNode<T> t2,
							AVLNode<T> t3) {
		b.setChildLeft(a);
		b.setChildRight(c);
		a.setFather(b);
		c.setFather(b);
		a.setChildLeft(t0);
		a.setChildRight(t1);
		c.setChildLeft(t2);
		c.setChildRight(t3);
		if(t0 != null)
			t0.setFather(a);
		if(t1 != null)
			t1.setFather(a);
		if(t2 != null)
			t2.setFather(c);
		if(t3 != null)
			t3.setFather(c);
		return b;
	}

	
	
	public AVLNode<T> publicRoot() {
		return this.root();
	}

	
	
	
	/*public void teste()
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

      
    
      
      for(indice = 0; indice <1000; indice++)
      {
              
      	    aleatorio = 100 + random.nextInt(900);
      	    Comparable numero = new Integer(aleatorio);
              arrayInsetion[indice] = numero;
      }
      
      
      
	       

	       for(int i=0;i<1000;i++)
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
		
		  
	       
		  AVLTree tree= new AVLTree();
		
	       System.out.println("****");
	       tree.teste();
	       //tree.printTree();
	       
	       tree.inOrder();
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
	       tree.printTree();

	}
*/

}
