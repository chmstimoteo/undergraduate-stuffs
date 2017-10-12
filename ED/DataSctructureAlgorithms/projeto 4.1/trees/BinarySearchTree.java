package trees;

import exceptions.AvisoException;
import nodes.TreeNode;

public class BinarySearchTree<T> extends BinaryTree<T> {

	TreeNode<T> insertionPosition; 

	
	public BinarySearchTree()
	{
		super(null);
		this.insertionPosition = null;
	}

	public void insert(Comparable<T> element) {
		super.setRoot(insert(this.root(), element));
	}

	public TreeNode<T> find(T element) throws AvisoException {
		TreeNode<T> result = find(this.root(), element);
		if(result == null)
			throw new AvisoException("Elemento não encontrado findBST");
		return result;
	}

	public void remove(T element) throws AvisoException {
		super.setRoot(remove(this.root(), element));
	}

	
	protected TreeNode<T> newNode(Comparable<T> element) {
		return new TreeNode<T> (null, null, null, element);
	}

	
	protected TreeNode<T> actionPosition() {
		return this.insertionPosition;
	}

	
	
	
	private TreeNode<T> insert(TreeNode<T> node, Comparable<T> element) {
		if(node == null) {
			node = this.newNode(element);
			insertionPosition = node;
		} else if(node.getElement().compareTo(((T)element)) > 0) {
			node.setChildLeft(insert(node.getChildLeft(), element));
			TreeNode<T> left = node.getChildLeft();
			left.setFather(node);
		} else if(node.getElement().compareTo(((T)element)) <= 0) {
			node.setChildRight(insert(node.getChildRight(), element));
			TreeNode<T> right = node.getChildRight();
			right.setFather(node);
		
		} 
			
		
		return node;
	}

	private TreeNode<T> find(TreeNode<T> node, T element) {
		TreeNode<T> result = null;
		if(node != null) {
			if( node.getElement().compareTo(element) == 0 )
				return node;
			else if( node.getElement().compareTo(element) < 0 )
				return find(node.getChildRight(), element);
			else
				return find(node.getChildLeft(), element);
		}
		return result;
	}

	private TreeNode<T> remove(TreeNode<T> node, T element) throws AvisoException {
		TreeNode<T> parent = null;
		if(node == null)
			throw new AvisoException("Elemento não encontrado rBST");
		if(node.getElement().compareTo(element) > 0) {
			node.setChildLeft(remove(node.getChildLeft(), element));
		} else if(node.getElement().compareTo(element) < 0) {
			node.setChildRight(remove(node.getChildRight(), element));
		} else {
			parent = node.getFather();
			insertionPosition = parent;			
			if(node.getChildRight() == null) {		
				node = node.getChildLeft();			
			} else if(node.getChildLeft() == null) {	
				node = node.getChildRight();			
			} else {							
				node.setElement(lastInLeft(node.getChildRight()));
			}
			if(node != null) {
				node.setFather(parent);
			}
		}
		return node;
	}

	
	private Comparable<T> lastInLeft(TreeNode<T> node) {
		Comparable<T> lastInLeft = null;
		boolean isRightChild = (node.getChildLeft() == null)? true : false;
		while(node.getChildLeft() != null) {
			node = node.getChildLeft();
		}
		lastInLeft = node.getElement();
		insertionPosition = node.getFather();
		if(!isRightChild) {
			insertionPosition.setChildLeft(node.getChildRight());
			if(insertionPosition.getChildLeft() != null) {
				insertionPosition.getChildLeft().setFather(insertionPosition);
			}
		} else {
			insertionPosition.setChildRight(node.getChildRight());
			if(insertionPosition.getChildRight() != null) {
				insertionPosition.getChildRight().setFather(insertionPosition);
			}
		}
		return lastInLeft;
	}

}
