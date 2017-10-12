package nodes;


//Classe para o nós das árvores com os métodos
//devidos


public class TreeNode<T> {

	private TreeNode<T> father;
	private TreeNode<T> childLeft;
	private TreeNode<T> childRight;
	private Comparable<T> element;

	public TreeNode(TreeNode<T> parent, TreeNode<T> left, TreeNode<T> right, Comparable<T> element) {
		this.setFather(parent);
		this.setElement(element);
		this.setChildLeft(left);
		this.setChildRight(right);
	}
	
	public TreeNode<T> getFather() {
		return this.father;
	} 

	public TreeNode<T> getChildLeft() {
		return this.childLeft;
	}

	public TreeNode<T> getChildRight() {
		return this.childRight;
	}

	public Comparable<T> getElement() {
		return this.element;
	}

	public void setFather(TreeNode<T> parent) {
		this.father = parent;
	}

	public void setChildLeft(TreeNode<T> left) {
		this.childLeft = left;
	}

	public void setChildRight(TreeNode<T> right) {
		this.childRight = right;
	}

	public void setElement(Comparable<T> element) {
		this.element = element;
	}
	
	public boolean hasAllChildren()
	{
		return this.getChildLeft() != null && this.getChildRight() != null;
	}
	
	public boolean isLeaf()
	{
		return this.getChildLeft() == null
		            && this.getChildRight() == null;
	}
	
	public boolean isInternal()
	{
		return !this.isLeaf();
	}

}
