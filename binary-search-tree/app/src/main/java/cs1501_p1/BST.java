package cs1501_p1;


import java.util.Stack;


/**
 * BST class
 * contains implementation of class named BST that implements BST_Inter 
 * uses BST_Node objects as its nodes, 
 * stores generic keys that extend Comparable 
 */

public class BST<T extends Comparable<T> > implements BST_Inter<T> {

	// root is private attribute of BST object
	//every BST has its own BTNode<T> root 
	//BTNode<T> class is an outer class, generic, within package cs1501_p1 

	private BTNode<T> root; 

	// BTNode<T> class has private T key, private BTNode<T> left and right
	// BTNode(T key) constructor
	// getKey()
	// getLeft()
	// getRight()
	// setLeft(BTNode<T> l)
	// setRight(BTNode<T> r)

	// default constructor, the <T> is implicity there 
	public BST(){

		// sets root to null
		root = null; 
	}

	private boolean isEmpty() {
		return root == null; 
	}

    /**
	 * Add a new key to the BST
	 *
	 * @param 	key Generic type value to be added to the BST
	 * @throws IllegalArgumentException
	 */
	public void put(T key)
	{
		if (key==null) throw new IllegalArgumentException("argument to put() is null");
		// if valid key, call private recursive put method and store result in root
		else root = put(root, key);
	}

	
	/**
	 * A private recursive helper method for put()
	 * 
	 * @param curNode the root of the current subtree where we will try to insert a new node with key 
	 * @param key Generic type value to be added to the BST
	 */
	private BTNode<T> put(BTNode<T> curNode, T key)
	{
		// empty BST or reached leaf - create new node and return
		// base case 
		if (curNode==null) return new BTNode<>(key); 

		// BST not empty 
		int cmp = key.compareTo(curNode.getKey());

		if (cmp<0) {
			// if key is less than current node's key, try to insert into curNode's left subtree
			BTNode<T> left = put(curNode.getLeft(), key);
			curNode.setLeft( left ); 
		} else if (cmp>0) {
			// if key is greater than current node's key, try to insert into curNode's right subtree
			BTNode<T> right = put(curNode.getRight(), key);
			curNode.setRight( right );
		}

		return curNode; 
	}


	/**
	 * Check if the BST contains a key
	 *
	 * @param	key Generic type value to look for in the BST
	 *
	 * @return	true if key is in the tree, false otherwise
	 * @throws IllegalArgumentException 
	 */
	public boolean contains(T key) {
		if (key==null) throw new IllegalArgumentException("argument to contains() is null");
		// call recursive helper method for contains
		else return contains(root, key);

	}

	//recursive helper method for contains
	private boolean contains(BTNode<T> curNode, T key) {

		// BST is empty, or have reached leaf and key not found 
		// base case
		if (curNode==null) return false; 

		// BST is not empty 
		int cmp = key.compareTo(curNode.getKey()); 

		if (cmp<0) return contains(curNode.getLeft(), key); // search left subtree
		else if (cmp>0) return contains(curNode.getRight(), key); // search right subtree
		else return true; // cmp==0, key found in tree 

	}

	/**
	 * Remove a key from the BST, if key is present
	 * 
	 * @param	key Generic type value to remove from the BST
	 * @throws IllegalArgumentException
	 */
	public void delete(T key) {
		if (key==null) throw new IllegalArgumentException("argument to delete() is null");
		// call recursive helper method for delete
		else root = delete(root, key); 
	}

	// recursive helper method for delete
	private BTNode<T> delete(BTNode<T> curNode, T key) {

		// BST is empty, or have reached leaf (key not found) - return curNode
		// base case
		if (curNode==null) return curNode;

		int cmp = key.compareTo(curNode.getKey());

		if (cmp<0) {
			BTNode<T> left = delete(curNode.getLeft(), key); // delete from left subtree
			curNode.setLeft(left); 
		} else if (cmp>0) {
			BTNode<T> right = delete(curNode.getRight(), key); // delete from right subtree
			curNode.setRight(right); 
		} else {
			// cmp==0, key found
			if (curNode.getRight()==null) {
				return curNode.getLeft(); 
			}
			if (curNode.getLeft()==null) {
				return curNode.getRight(); 
			}

			BTNode<T> temp = curNode; 
			curNode = min( temp.getRight() ); 
			BTNode<T> rightSubtreeOfTemp = deleteMin(temp.getRight());
			curNode.setRight( rightSubtreeOfTemp ); 
			curNode.setLeft( temp.getLeft() ); 

		} //end if 

		return curNode; 
	} //end private delete 


	/**
	 * Returns smallest key in BST
	 * 
	 * @return the smallest key in the BST 
	 */
	private T min() {
		if (root==null) return null; // BST empty 
		else return min(root).getKey(); 
	}

	// recursive method to find min
	// returns node with min key in BST 
	private BTNode<T> min(BTNode<T> curNode) {
		if (curNode.getLeft()==null) return curNode; //base case
		else return min(curNode.getLeft()); //find min in left subtree 
	}

	/**
	 * Returns largest key in BST
	 * 
	 * @return the largest key in the BST 
	 */
	private T max() {
		if (root==null) return null; // BST empty 
		else return max(root).getKey(); 
	}

	//recursive method to find max
	private BTNode<T> max(BTNode<T> curNode) {
		if (curNode.getRight() == null) return curNode; //base case
		else return max(curNode.getRight()); //search for max in right subtree 
	}


	//recursive method to delete the node with min key 
	private BTNode<T> deleteMin(BTNode<T> curNode) {
		if (curNode.getLeft() == null) return curNode.getRight(); //base case

		BTNode<T> temp = deleteMin( curNode.getLeft() ); //delete min from left subtree 
		curNode.setLeft(temp); 
		return curNode; 
	}


	/**
	 * Determine the height of the BST
	 *
	 * <p>
	 * A single node tree has a height of 1, an empty tree has a height of 0.
	 *
	 * @return	int value indicating the height of the BST
	 */
	public int height() {
		return height(root);
	}

	// helper method to find height of subtree rooted at curNode
	private int height(BTNode<T> curNode) {
		
		//if BST is empty or have reached leaf
		if (curNode == null) return 0; //base case 

		// get height of left subtree and height of right subtree and take larger of the two and add 1 
		return 1 + Math.max( height(curNode.getLeft()), height(curNode.getRight()));
	}

	/**
	 * Determine if the BST is height-balanced
	 *
	 * <p>
	 * A height balanced binary tree is one where the left and right subtrees
	 * of all nodes differ in height by no more than 1.
	 *
	 * @return	true if the BST is height-balanced, false if it is not
	 */
	public boolean isBalanced(){

		if ( isEmpty() ) return true; // empty BST is balanced
		else return isBalanced(root); 

	}

	// private recursive helper method for isBalanced()
	private boolean isBalanced(BTNode<T> curNode){

		if (curNode==null) return true; //base case 

		int heightDif; 

		// find dif in height btwn left and right subtrees
		// balanced if dif in height is <=1  
		heightDif = Math.abs( height(curNode.getLeft()) - height(curNode.getRight()) );

		if (heightDif<=1 && isBalanced(curNode.getLeft()) && isBalanced(curNode.getRight())){
			return true; 
		}

		// subtree height dif > 1, thus not height balanced
		return false; 

	}

	/**
	 * Produce a ':' separated String of all keys in ascending order
	 *
	 * <p>
	 * Perform an in-order traversal of the tree and produce a String
	 * containing the keys in ascending order, separated by ':'s.
	 * 
	 * @return	String containing the keys in ascending order, ':' separated
	 */
	public String inOrderTraversal(){

		return inOrderTraversal(root);

	}
	
	// private recursive helper method for inOrderTraversal
	// iot (left subtree, root, right subtree)
	private String inOrderTraversal(BTNode<T> curNode) {

		String result = ""; 

		if (curNode != null) {

			result += inOrderTraversal(curNode.getLeft()); //traverse left subtree and concatenate to result

			if (curNode == max(root)){
				// to prevent ':' from printing for last node
				result += curNode.getKey();
			} else {
				// not max key thus ':' should be concatenated too 
				result += curNode.getKey() + ":"; 
			}

			result += inOrderTraversal(curNode.getRight()); //traverse right subtree and concatenate to result
		}

		return result; //if BST is empty, the result would be empty string 
	}

	/**
	 * Produce String representation of the BST
	 * 
	 * <p>
	 * Perform a pre-order traversal of the BST in order to produce a String
	 * representation of the BST. The reprsentation should be a comma separated
	 * list where each entry represents a single node. Each entry should take
	 * the form: *type*(*key*). You should track 4 node types:
	 *     `R`: The root of the tree
	 *     `I`: An interior node of the tree (e.g., not the root, not a leaf)
	 *     `L`: A leaf of the tree
	 *     `X`: A stand-in for a null reference
	 * For each node, you should list its left child first, then its right
	 * child. You do not need to list children of leaves. The `X` type is only
	 * for nodes that have one valid child.
	 * 
	 * @return	String representation of the BST
	 */
	public String serialize() {

		//return serialize(root, null); 

		return serialize(root); 

	}


	// iterative private serialize method 
	private String serialize(BTNode<T> rootNode){

		// pre order traversal
		// String representation of BST 
		// root , then left subtree, then right subtree
		// 4 node types

		// L for leaf of tree
		// R for root of tree 
		// I for interior node (not root, not leaf)
		// X for null node 

		// for each node, list left child first, then right child 
		// do not list children of leaves
		// X type, only for nodes that have one valid child 
		// key for X node is NULL 

		String result = ""; 

		// if BST is empty, return empty string 
		if (isEmpty()) return result; 

		// stack to store preOrderTraversal of nodes
		Stack<BTNode<T>> preOrderStack = new Stack<>(); 

		// stack to keep track node types traversed 
		Stack<String> nodeTypes = new Stack<>(); 

		// push root onto stack
		// loop while stack ! empty 
		// pop last node, append to result
		// push right node if !null, push left node if !null

		preOrderStack.push(root); 
		nodeTypes.push("R");

		// loop while stack not empty 
		while ( !preOrderStack.isEmpty() ) {

			// get node type of cur node and pop off stack 
			String curNodeType = nodeTypes.pop(); 

			if (curNodeType.equals("X")){
				result += "X(NULL)";
				curNodeType = nodeTypes.pop(); 

				if(!preOrderStack.isEmpty()){
					result+=","; 
				}
			}

			// get ref to current node and pop off stack
			BTNode<T> curNode = preOrderStack.pop(); 

			// check what needs to be appended to the string, result

			if (curNode == root){
				result += "R(" + curNode.getKey() + ")"; 
			} else if ( isLeaf(curNode) ) {
				result += "L(" + curNode.getKey() + ")";
			} else {
				// interior node 
				result += "I(" + curNode.getKey() + ")"; 
			}

			if (curNode.getRight() != null) {
				preOrderStack.push(curNode.getRight()); 

				// figure out type of node that we just pushed onto preOrderStack
				if ( isLeaf(curNode.getRight()) ){
					// leaf node
					nodeTypes.push("L");
				} else {
					// interior node 
					nodeTypes.push("I"); 
				}

			}else{
				// right child is null

				if (curNodeType.equals("I") || curNodeType.equals("R")){
					//previously handled node was interior node, (or root) and it has no right child 
					// interior node - must have left child
					// signal something to my stack of nodes, thus push X

					nodeTypes.push("X"); 
				}

			}

			if (curNode.getLeft() != null) {
				// left child not null 
				preOrderStack.push(curNode.getLeft()); 
				
				if ( isLeaf(curNode.getLeft()) ){
					// leaf node
					nodeTypes.push("L");
				} else {
					// interior node 
					nodeTypes.push("I"); 
				}

			}else{
				// left child null 
				// previously handled node was interior node (or root) and is missing left child 
				if (curNodeType.equals("I") || curNodeType.equals("R")){
					nodeTypes.push("X"); 
				}

			}

			// if not working with last node, append comma
			if(!preOrderStack.isEmpty()){
				result+=","; 
			}

		} // end while 

		while (!nodeTypes.isEmpty()){
			// node type stack has X

			if(nodeTypes.pop().equals("X")){
				result += ",X(NULL)";
			}

		}

		return result; 

	}


	private boolean isRoot(BTNode<T> curNode){
		return curNode == root;
	}

	private boolean isLeaf(BTNode<T> curNode){
		return curNode.getLeft()==null && curNode.getRight()==null; 
	}

	/**
	 * Produce a deep copy of the BST that is reversed (i.e., left children
	 * hold keys greater than the current key, right children hold keys less
	 * than the current key).
	 *
	 * @return	Deep copy of the BST reversed
	 */
	public BST_Inter<T> reverse(){

		// try recursive approach without using stack 

		BST<T> reversedTree = new BST<>(); //empty BST 

		reversedTree.root = reverse(root); // call recursive helper method for reverse
		
		return reversedTree; 

	}

	// recursive helper method for reverse 
	private BTNode<T> reverse(BTNode<T> cur){

		// base case
		// reached leaf or empty BST 
		if (cur == null){
			return cur; 
		}

		BTNode<T> leftSubtree = reverse(cur.getLeft()); //reverse left subtree
		BTNode<T> rightSubtree = reverse(cur.getRight()); //reverse right subtree 

		cur.setLeft( rightSubtree ); // swap left and right subtree
		cur.setRight( leftSubtree ); // swap left and right subtree

		return cur;

	}

}
