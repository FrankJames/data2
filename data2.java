/* 
@author Frank James
data2 assignment
October - November 2014
*/


/*

	NOTES:

using a red-black tree:

1. like a BST, but has an extra field-- true or false (red or black)

	Empty trees are always black
	
	Filled trees are either red or black

		the root is always black
			--if the set is empty, the empty tree 
				is black and there's no need to worry

		If a tree is red, then it has two black branches
			Thus the tree seems to alternate between red and black nodes

		All paths to any child leaves must contain the same amount of black nodes


2. Needs to be constructed such that these rules apply:

	retroactively change the colors of trees, because whenever a node is created
		it will be black first, then with one branch is still black. 
		However, with two children, the node becomes red (except for the root)

	Thus we need to be able to detect if there are two filled braches (if both are Trees),
		and then change the color of the parent tree accordingly. 


3. Iteration abstraction:

	Using iterable:
		gives forEach function
			maps a function to each element of a set

		gives splitorator()
			splitorators: 
				can iterate in parallel, like through trees whereas an iterator could go through a list
				trySplit() <-- creates another splitorator in partitioning elements to traverse branches

				forEachRemaining(Object o, method) <-- probably important for member / other methods

				How to get this to work correctly with binary trees?
				
				Apparently, these work most efficiently with approximately balanced trees, as RB-trees are


4. Using generics: 

	How to optimize the member function such that it recognizes 
		on which branch an object might be?

	Getting syntax correct

5. Multisets 

	can contain more than one of the same element

	How will this affect remove / intersection / difference?
		We must check 


6. Testing

	TESTING PROPERTIES
	GET SET PROPERTIES
	TEST SET PROPERTIES

*/

interface RBtree {

/*
methods to add:
member
add
union
remove
intersection
difference
equal
subset
*/

}

class Leaf implements RBtree {
	boolean color;

	Leaf() {
		this.color = false;  // false for black, because all leaves are black
	}

}

class Node implements RBtree {
	boolean color;
	Object o;
	RBtree left;
	RBtree right;

	Node(RBtree left, Object o, RBtree right) {
		this.left = new Leaf();
		this.o = o;
		this.right = new Leaf();
		this.color = false; 
	}

}


class data2 {

	public static void main(String[] args) {

		System.out.println("---------END OF CODE--------");
	}
}