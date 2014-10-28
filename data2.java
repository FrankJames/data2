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

	public boolean isEmpty();
	public int cardinality();
	public RBtree add( Object o);
	public RBtree union( RBtree t );
	public RBtree remove( Object o);
	public RBtree intersection( RBtree t );
	public RBtree difference( RBtree t );
	public boolean equal( RBtree t );
	public boolean subset( RBtree t );

}

class Leaf implements RBtree {
	boolean color;

	Leaf() {
		this.color = false;  // false for black, because all leaves are black
	}

	public boolean isEmpty() { 
		return true;
	}

	public int cardinality() {
		return 0;
	}

	public RBtree add( Object o ) {
		return new Node(new Leaf(), o, new Leaf()); 
	}

	public RBtree union( RBtree t ) {
		return t;
	}

	public RBtree remove( Object o ) {
		return new Leaf();
	}

	public RBtree intersection( RBtree t ) {
		return new Leaf();
	}

	public RBtree difference( RBtree t ) {
		return t;
	}

	public boolean equal( RBtree t ) {
		return false;
	}

	public boolean subset( RBtree t ) {
		return true; 
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

	public boolean isEmpty() {
		return false;
	}

	public int cardinality() {
		return 1 + this.left.cardinality() + this.right.cardinality();
	}

// TO DO: ALL OF THESE FUNCTIONS UTILIZING ITERABLE AND CONFORMING TO THE RULES OF A RED-BLACK TREE
	public RBtree add( Object o) {

	}

	public RBtree union( RBtree t ) {

	}

	public RBtree remove( Object o) {

	}

	public RBtree intersection( RBtree t ) {

	}

	// difference is the set of all things in set b that are not in set a
	public RBtree difference( RBtree t ) {

	}

	public boolean equal( RBtree t ) {

	}

	public boolean subset( RBtree t ) {

	}
}


class data2 {

	public static void main(String[] args) {

		System.out.println("---------END OF CODE--------");
	}
}