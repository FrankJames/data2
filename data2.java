/* 
@author Frank James
data2 assignment
October - November 2014
*/

import java.util.*;

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

/*

NOTES ON RB-INSERT (ROTATE)

	insert always creates a new red node (with black leaf children), but then detects if rotation is necessary
	
	normal cases:

	1. if it is the first insertion (no parent node), then it will be painted black, as the root is always black

	2. if the inserted node has a parent but not a sibling, it will simply be inserted, as it is given to be red,
			which satisfies the properties. 

2. Needs to be constructed such that these rules apply:

	retroactively change the colors of trees, because whenever a node is created
		it will be black first, then with one branch is still black. 
		However, with two children, the node becomes red (except for the root)

	Thus we need to be able to detect if there are two filled braches (if both are Trees),
		and then change the color of the parent tree accordingly. 

5. Testing

	TESTING PROPERTIES
	GET SET PROPERTIES
	TEST SET PROPERTIES

*/

interface RBtree<Obj extends Comparable> {

	public int getCount( Obj o );
	public boolean isEmpty();
	public int cardinality();
	public boolean member( Obj o );
	public RBtree add( Obj o );
	// public RBtree addN( Obj o, int num );
	public RBtree blacken();
	public boolean isRed(); 
	public RBtree balance(); 
	public RBtree union( RBtree t );
	public RBtree remove( Obj o);
	public RBtree removeN( Obj o, int num );
	public RBtree removeALL( Obj o ); 
	public RBtree intersection( RBtree t );
	public RBtree difference( RBtree t );
	public boolean equal( RBtree t );
	public boolean subset( RBtree t );

}

interface Sequence<Obj> {
	public Obj getMine();
	public boolean hasMine();
	public Sequence<Obj> getNext();
	public String toString();
}

interface Sequenced<Obj extends Comparable> {
	public Sequence<Obj> seq();
}

class SequenceLeaf<Obj extends Comparable> implements Sequence<Obj> {

	public boolean hasMine() {
		return false;
	}

	public Obj getMine() {
		return null; 
	}

	public SequenceLeaf<Obj> getNext() {
		return this;
	}

	public String toString() {
		return "";
	}
}

class SequenceNode<Obj extends Comparable> implements Sequence<Obj>, Sequenced<Obj> {

	Obj mine;
	int count;
	Sequence<Obj> next;

	SequenceNode(Obj mine, int count, Sequence<Obj> next) {
		this.mine = mine;
		this.count = count;
		this.next = next;
	}

	public Sequence<Obj> seq() {
		return this;
	}

	public boolean hasMine() {
		return true;
	}

	public Obj getMine() {
		return this.mine;
	}

	public Sequence<Obj> getNext() {
		if ( count > 1 ) {
			return new SequenceNode(mine, count - 1, next);
		}
		else {
			return next;
		}
	}

	public String toString() {
		return "" + this.getMine();
	}
}

class SequenceConcat<Obj extends Comparable> implements Sequence<Obj> {
	Sequence<Obj> left;
	Sequence<Obj> right;

	SequenceConcat(Sequence<Obj> left, Sequence<Obj> right) {
		this.left = left;
		this.right = right;
	}

	public Obj getMine() {
		if ( this.left.hasMine() ) {
			return this.left.mine();
		} 
		else {
			return this.right.mine();
		}
	}

	public boolean hasMine() {
		return this.left.hasMine() || this.right.hasMine(); 
	}

	public Sequence<Obj> getNext() {
		if ( this.left.hasMine() ) {
			return this.left.getNext();
		}
		else {
			return this.right.getNext();
		}
	}

	public String toString {
		return "" + this.left.toString() + " " + this.right.toString();
	}

}

class Leaf<Obj extends Comparable> implements RBtree<Obj> {
	boolean color;

	Leaf() {
		this.color = false;  // false for black, because all leaves are black
	}

	public int getCount( Obj o ) {
		return 0;
	}

	public boolean isEmpty() { 
		return true;
	}

	public int cardinality() {
		return 0;
	}

	public boolean member( Obj o ) {
		return false;
	}

	public RBtree add( Obj o ) {
		return new Node(new Leaf(), o, new Leaf()); 
	}

	public RBtree addN( Obj o, int num ) {
		return new Node(new Leaf(), o, num, new Leaf());
	}

	public RBtree blacken( ) {
		return new Leaf(); 
	}

	public boolean isRed( ) {
		return this.color;
	}

	public RBtree balance( ) {
		return new Leaf(); 
	}

	public RBtree union( RBtree t ) {
		return t;
	}

	public RBtree remove( Obj o ) {
		return new Leaf();
	}

	public RBtree removeN( Obj o, int num ) {
		return new Leaf();
	}

	public RBtree removeALL( Obj o ) {
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

class Node<Obj extends Comparable> implements RBtree<Obj> {
	boolean color;
	Obj object;
	RBtree<Obj> left;
	RBtree<Obj> right;
	int count;

	Node(RBtree left, Obj object, int count, RBtree right, boolean color) {
		this.left = new Leaf();
		this.object = object;
		this.right = new Leaf();
		this.color = color; 
		this.count = count;
	}	

	Node(RBtree left, Obj object, int count, RBtree right) {
		this.left = new Leaf();
		this.object = object;
		this.right = new Leaf();
		this.color = true; 
		this.count = count;
	}

	Node(RBtree left, Obj object, RBtree right) {
		this.left = new Leaf();
		this.object = object;
		this.right = new Leaf();
		this.color = true; 
		this.count = 1;
	}

	// shorthand constructor
	Node(Obj object, int count) {
		this.left = new Leaf();
		this.object = object;
		this.right = new Leaf();
		this.color = true; 
		this.count = count;
	}

	public int getCount( Obj o ) {
		if (this.object.compareTo( o ) == 0) {
			return count;
		}

		else if (o.compareTo(this.object) > 0) {
			return this.right.getCount( o ); 
		}

		else {
			return this.left.getCount( o ); 
		}
	}

	public boolean isEmpty() {
		if (this.count == 0) {
		return true;
		}
		else {
			return false;
		}
	}

	public int cardinality() {
		return this.count + this.left.cardinality() + this.right.cardinality();
	}

	public boolean member( Obj o ) {
		if (this.object.compareTo( o ) == 0) {
			return this.count != 0;
		}

		else if (this.object.compareTo ( o ) > 0) {
			return this.right.member( o );
		}

		else {
			return this.left.member( o );
		}
	}


	public RBtree add( Obj o) {

		if (this.object.compareTo ( o ) > 0) {
			return this.right.add( o );
		}

		else {
			return this.left.add( o );
		}
	}

	public RBtree blacken() {
		return new Node(this.left, this.object, this.count, this.right, false);
	}

	public boolean isRed() {
		return this.color;
	}


	public RBtree balance() {

			// CASE 1:
		if ((this.left instanceof Node) && (this.isRed() == false) && ((Node) this.left).isRed() && ((Node) this.left).left.isRed()) {

				Node b = (Node) this.left;
				Node c = (Node) b.left;

				return new Node( new Node( c.left, c.object, c.count, c.right, false ),
									b.object, 
									b.count,
								 new Node( b.right, this.object, this.count, this.right, false),
							 		true);
		}
			// CASE 2:
		else if ((this.left instanceof Node) && (this.isRed() == false) && ((Node) this.left).isRed() && ((Node) this.left).right.isRed()) {

				Node b = (Node) this.left;
				Node c = (Node) b.right;

				return new Node( new Node( b.left, b.object, b.count, c.left, false ), 
									c.object,
									c.count,
								 new Node( c.right, this.object, this.count, this.right, false ),
								 	true);
			}

			// CASE 3:
		else if	((this.right instanceof Node) && (this.isRed() == false) && ((Node) this.right).isRed() && ((Node) this.right).left.isRed()) {

				Node b = (Node) this.right;
				Node c = (Node) b.left;

				return new Node( new Node( this.left, this.object, this.count, c.left, false ),
									c.object,
									c.count,
								  new Node( c.right, b.object, b.count, b.right, false ),
								  	true);
			}

			// CASE 4:
		else if ((this.right instanceof Node) && (this.isRed() == false) && ((Node) this.right).isRed() && ((Node) this.right).right.isRed()) {

				Node b = (Node) this.right;
				Node c = (Node) b.right;

				return new Node( new Node( this.left, this.object, this.count, b.left, false ),
									b.object,
									b.count,
								  new Node( c.left, c.object, c.count, c.right, false ),
								  	true);
		}
		return this;
	}


	public RBtree union( RBtree t ) {
		return this.left.union(this.right.union(t.add(this.object)));
	}

	public RBtree remove( Obj o) {
		return this.removeN( o, 1 );
	}

	public RBtree removeN( Obj o, int num) {
		if (this.object.compareTo( o ) == 0) {
			if (num > this.count) {
				return new Node(this.left, this.object, 0, this.right);
			}
			else {
				return new Node(this.left, this.object, this.count - num, this.right); 
			}
		}
		else if (this.object.compareTo( o ) > 0) {
			return new Node(this.left, this.object, this.count, this.right.removeN( o, num ));
		}
		else {
			return new Node(this.left.removeN( o, num ), this.object, this.count, this.right);
		}
	}

	public RBtree removeALL( Obj o ) {
		if (this.object.compareTo( o ) == 0) {
			return this.right.union(this.left);
		}
		else if (this.object.compareTo( o ) > 0) {
			return new Node(this.left, this.object, this.count, this.right.removeALL( o ));
		}
		else {
			return new Node(this.left.removeALL( o ), this.object, this.count, this.right);
		}
	}

	public RBtree intersection( RBtree t ) {
		if (t.member( this.object ) ) {
			if (t.getCount( this.object ) > this.count ) {
				return new Node(this.left.intersection( t ), this.object, t.getCount( this.object ), this.right.intersection( t ));
			}
			else {
				return new Node(this.left.intersection( t ), this.object, this.count, this.right.intersection( t ));
			}
		}
		else {
			return this.right.intersection( t ).union(this.left.intersection( t ));	
		}
	}

	public RBtree difference( RBtree t ) {
		RBtree temp = t.removeN( this.object, this.count );
		return this.right.union( this.left ).difference( temp );
	}

	public boolean subset( RBtree t ) {
		return (t.getCount( this.object ) >= this.getCount( this.object ))
			&& this.right.union(this.left).subset( t ); 
	}

	public boolean equal( RBtree t ) {
		if (this.subset( t ) && t.subset( this )) {
			return true;
		}
		else {
			return false;
		}
	}
}


class data2 {

	public static void main(String[] args) {

		System.out.println("---------END OF CODE--------");
	}
}