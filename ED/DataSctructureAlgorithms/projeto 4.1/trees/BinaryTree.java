package trees;

import exceptions.AvisoException;
import nodes.TreeNode;


//Classe abstrata que representa a ídeia básica de uma árvore
// binária

public abstract class BinaryTree<T> {

	protected TreeNode<T> root;
	protected int size;
	
	public BinaryTree(TreeNode<T> node) {
		this.root = node;
		this.size = 1;
	}

	public abstract void insert(Comparable<T> element);

	public abstract TreeNode<T> find(T element) throws AvisoException;

	public abstract void remove(T element) throws AvisoException;

	
	private void inOrder(TreeNode<T> node) {
		if(node != null) {
			inOrder(node.getChildLeft());
			System.out.println(node.getElement());
			inOrder(node.getChildRight());
		}
	}
	
	private void preOrder(TreeNode<T> node) {
		if(node != null) {
			System.out.println( node.getElement());
			preOrder(node.getChildLeft());
			preOrder(node.getChildRight());
		}
	}
	
	
	private void postOrder(TreeNode<T> node) {
		if(node != null) {
			postOrder(node.getChildLeft());
			postOrder(node.getChildRight());
			System.out.println(node.getElement());
		}
	}
	

	public int depth(TreeNode<T> node) {
		if(node.equals(root))
			return 0;
		return 1 + depth(node.getFather());
	}

	public int height(TreeNode<T> node) {
		int h;
		if(node == null)
			return 0;
		else
			h = getGreater(height(node.getChildLeft()), height(node.getChildRight()));
		return 1 + h;
	}
    
	private int getGreater(int h1,int h2)
	{
		return h1 > h2 ? h1 : h2; 
	}
	
	
	
	//Faz os caminhames da árvore
	public void inOrder() {
		this.inOrder(this.root);
	}
	
	public void preOrder() {
		this.preOrder(this.root);
	}
	
	public void postOrder(){
		this.postOrder(this.root);
	}

	

	
	protected TreeNode<T> root() {
		return this.root;
	}

	protected void setRoot(TreeNode<T> root) {
		this.root = root;
	}

	private boolean isLeftChild(TreeNode<T> child, TreeNode<T> parent) {
		if(parent.getChildLeft()!= null && parent.getChildLeft().equals(child))
			return true;
		return false;
	}

	private boolean isRightChild(TreeNode<T> child, TreeNode<T> parent) {
		if(parent.getChildLeft()!= null && parent.getChildRight().equals(child))
			return true;
		return false;
	}

	
}
