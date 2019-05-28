/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickquiz;

/**
 *
 * @author student
 */
//Source:  http://www.newthinktank.com/2013/03/binary-tree-in-java/
// New Think Tank


public class BinaryTree 
{
        int qQuestionNumber;
        String traversalString;
	bTNode root;

	public void addNode(int key, String name) 
        {

		// Create a new bTNode and initialize it

		bTNode newNode = new bTNode(key, name);

		// If there is no root this becomes root

		if (root == null) 
                {

			root = newNode;

		} 
                
                else 
                {

			// Set root as the bTNode we will start
			// with as we traverse the tree

			bTNode focusNode = root;

			// Future parent for our new bTNode

			bTNode parent;

			while (true) 
                        {

				// root is the top parent so we start
				// there

				parent = focusNode;

				// Check if the new node should go on
				// the left side of the parent node

				if (key < focusNode.key) 
                                {

					// Switch focus to the left child

					focusNode = focusNode.leftChild;

					// If the left child has no children

					if (focusNode == null) 
                                        {

						// then place the new node on the left of it

						parent.leftChild = newNode;
						return; // All Done

					}

				} 
                                else 
                                { // If we get here put the node on the right

					focusNode = focusNode.rightChild;

					// If the right child has no children

					if (focusNode == null) 
                                        {

						// then place the new node on the right of it

						parent.rightChild = newNode;
						return; // All Done

					}

				}

			}
		}

	}

	// All nodes are visited in ascending order
	// Recursion is used to go to one node and
	// then go to its child nodes and so forth

	public void inOrderTraverseTree(bTNode focusNode) 
        {

		if (focusNode != null) 
                {

			// Traverse the left node

			inOrderTraverseTree(focusNode.leftChild);

			// Visit the currently focused on node

			System.out.println(focusNode);
                        traversalString = traversalString + focusNode.toString();

			// Traverse the right node

			inOrderTraverseTree(focusNode.rightChild);

		}

	}

	public void preorderTraverseTree(bTNode focusNode) 
        {

		if (focusNode != null) 
                {
                        
                    
			System.out.println(focusNode);
                        traversalString = traversalString + focusNode.toString();
			preorderTraverseTree(focusNode.leftChild);
			preorderTraverseTree(focusNode.rightChild);
                        

		}

	}

	public void postOrderTraverseTree(bTNode focusNode) 
        {

		if (focusNode != null) 
                {

			postOrderTraverseTree(focusNode.leftChild);
			postOrderTraverseTree(focusNode.rightChild);
			System.out.println(focusNode);
                        traversalString =  traversalString + ",  " + focusNode.toString();
		}

	}

	public bTNode findNode(int key) 
        {

		// Start at the top of the tree

		bTNode focusNode = root;

		// While we haven't found the bTNode
		// keep looking

		while (focusNode.key != key) 
                {

			// If we should search to the left

			if (key < focusNode.key) 
                        {

				// Shift the focus bTNode to the left child

				focusNode = focusNode.leftChild;

			} 
                        else 
                        {

				// Shift the focus bTNode to the right child

				focusNode = focusNode.rightChild;

			}

			// The node wasn't found

			if (focusNode == null)
				return null;

		}

		return focusNode;

	}

public static void main(String[] args) 
{

		BinaryTree theTree = new BinaryTree();
                


		// Different ways to traverse binary trees

		// theTree.inOrderTraverseTree(theTree.root);

		// theTree.preorderTraverseTree(theTree.root);

		// theTree.postOrderTraverseTree(theTree.root);

		// Find the node with key 75



}
}

class bTNode 
{

	int key;
	String name;

	bTNode leftChild;
	bTNode rightChild;

	bTNode(int key, String name) 
        {

		this.key = key;
		this.name = name;

	}

	public String toString() 
        {

		return name + " has the key " + key;

		/*
		 * return name + " has the key " + key + "\nLeft Child: " + leftChild +
		 * "\nRight Child: " + rightChild + "\n";
		 */

	}

}
