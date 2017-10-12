package nodes;


//Classe nó para implementação da árvore AVL
//conte um campo novo,no caso a altura

public class AVLNode<T> extends TreeNode<T> {

	private int height;

	public AVLNode(int height, TreeNode<T> parent, TreeNode<T> left, TreeNode<T> right, Comparable<T> element) {
		super(parent, left, right, element);
		this.height = height;
	}

	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public AVLNode<T> getFather() {
		return ((AVLNode<T>)super.getFather());
	}

	public AVLNode<T> getChildLeft() {
		return ((AVLNode<T>)super.getChildLeft());
	}

	public AVLNode<T> getChildRight() {
		return ((AVLNode<T>)super.getChildRight());
	}

}
