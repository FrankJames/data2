/*
@author Frank James
*/

import java.util.*;


interface RBtree<Obj extends Comparable> extends Sequenced<Obj> {

	public int getCount( Obj o );
	public boolean isEmpty();
	public int cardinality();
	public boolean member( Obj o );
	public RBtree<Obj> add( Obj o );
	public RBtree<Obj> add( Obj o, int num );
	public RBtree<Obj> blacken();
	public boolean isRed(); 
	public RBtree<Obj> balance(); 
	public RBtree<Obj> union( RBtree<Obj> t );
	public RBtree<Obj> remove( Obj o );
	public RBtree<Obj> remove( Obj o, int num );
	public RBtree<Obj> removeALL( Obj o ); 
	public RBtree<Obj> intersection( RBtree<Obj> t );
	public RBtree<Obj> difference( RBtree<Obj> t );
	public boolean equal( RBtree<Obj> t );
	public boolean subset( RBtree<Obj> t );
	public Sequence<Obj> makeSeq( );
	public int sumAll( );
}

class Leaf<Obj extends Comparable> implements RBtree<Obj> {
	boolean color;

	Leaf() {
		this.color = false; 
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

	public RBtree<Obj> add( Obj o ) {
		return add( o, 1 ).blacken(); 
	}

	public RBtree<Obj> add( Obj o, int num ) {
		return new Node(new Leaf(), o, num, new Leaf(), true);
	}

	public RBtree<Obj> blacken( ) {
		return new Leaf(); 
	}

	public boolean isRed( ) {
		return this.color;
	}

	public RBtree<Obj> balance( ) {
		return new Leaf(); 
	}

	public RBtree<Obj> union( RBtree<Obj> t ) {
		return t;
	}

	public RBtree<Obj> remove( Obj o ) {
		return new Leaf();
	}

	public RBtree<Obj> remove( Obj o, int num ) {
		return new Leaf();
	}

	public RBtree<Obj> removeALL( Obj o ) {
		return new Leaf();
	}

	public RBtree<Obj> intersection( RBtree<Obj> t ) {
		return new Leaf();
	}

	public RBtree<Obj> difference( RBtree<Obj> t ) {
		return t;
	}

	public boolean equal( RBtree<Obj> t ) {
		if (t.isEmpty())
			return true;
		else
			return false;
	}

	public boolean subset( RBtree<Obj> t ) {
		return true; 
	}

	public Sequence<Obj> makeSeq( ) {
		return new SequenceLeaf();
	}

	public int sumAll( ) {
		return 0;
	}
}

class Node<Obj extends Comparable> implements RBtree<Obj> {
	boolean color;
	Obj object;
	RBtree<Obj> left;
	RBtree<Obj>right;
	int count;

	Node(RBtree<Obj> left, Obj object, int count, RBtree<Obj> right, boolean color) {
		this.left = new Leaf();
		this.object = object;
		this.right = new Leaf();
		this.color = color; 
		this.count = count;
	}	

	Node(RBtree<Obj> left, Obj object, int count, RBtree<Obj> right) {
		this.left = new Leaf();
		this.object = object;
		this.right = new Leaf();
		this.color = true; 
		this.count = count;
	}

	Node(RBtree<Obj> left, Obj object, RBtree<Obj> right) {
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


	public RBtree<Obj> add( Obj o ) {

		return add( o, 1 ).blacken(); 
	}

	public RBtree<Obj> add( Obj o, int n ) {

		if (this.object.compareTo( o ) == 0) {
			return new Node(this.left, o, this.getCount( o ) + n, this.right, this.color).balance(); 
		}

		else if (this.object.compareTo ( o ) > 0) {
			return new Node(this.left, this.object, this.count, this.right.add( o, n ), this.color).balance();
		}

		else {
			return new Node(this.left.add( o, n ), this.object, this.count, this.right, this.color).balance();
		}

	}

	public RBtree<Obj> blacken() {
		return new Node(this.left, this.object, this.count, this.right, false);
	}

	public boolean isRed() {
		return this.color;
	}


	public RBtree<Obj> balance() {

			// CASE 1:
		if ((this.left instanceof Node) 
			&& (((Node) this.left).left instanceof Node)
			&& (this.isRed() == false) 
			&& ((Node) this.left).isRed() 
			&& ((Node) this.left).left.isRed()) {

				Node b = (Node) this.left;
				Node c = (Node) b.left;

				return new Node( new Node( c.left, c.object, c.count, c.right, false ),
									b.object, 
									b.count,
								 new Node( b.right, this.object, this.count, this.right, false),
							 		true);
		}
			// CASE 2:
		else if ((this.left instanceof Node) 
			&& (((Node) this.left).right instanceof Node)
			&& (this.isRed() == false) 
			&& ((Node) this.left).isRed() 
			&& ((Node) this.left).right.isRed()) {

				Node b = (Node) this.left;
				Node c = (Node) b.right;

				return new Node( new Node( b.left, b.object, b.count, c.left, false ), 
									c.object,
									c.count,
								 new Node( c.right, this.object, this.count, this.right, false ),
								 	true);
			}

			// CASE 3:
		else if	((this.right instanceof Node) 
			&& (((Node) this.right).left instanceof Node)
			&& (this.isRed() == false) 
			&& ((Node) this.right).isRed() 
			&& ((Node) this.right).left.isRed()) {

				Node b = (Node) this.right;
				Node c = (Node) b.left;

				return new Node( new Node( this.left, this.object, this.count, c.left, false ),
									c.object,
									c.count,
								  new Node( c.right, b.object, b.count, b.right, false ),
								  	true);
			}

			// CASE 4:
		else if ((this.right instanceof Node) 
			&& (((Node) this.right).right instanceof Node)
			&& (this.isRed() == false) 
			&& ((Node) this.right).isRed() 
			&& ((Node) this.right).right.isRed()) {

				Node b = (Node) this.right;
				Node c = (Node) b.right;

				return new Node( new Node( this.left, this.object, this.count, b.left, false ),
									b.object,
									b.count,
								  new Node( c.left, c.object, c.count, c.right, false ),
								  	true);
		}
		else {
			return this;
		}
	}


	public RBtree<Obj> union( RBtree<Obj> t ) {
		return left.union(right.union(t)).add(this.object, this.getCount( object ));
	}

	public RBtree<Obj> remove( Obj o) {
		return this.remove( o, 1 );
	}

	public RBtree<Obj> remove( Obj o, int num) {
		if (this.object.compareTo( o ) == 0) {
			if (num > this.count) {
				return new Node(this.left, this.object, 0, this.right, this.color);
			}
			else {
				return new Node(this.left, this.object, this.count - num, this.right, this.color); 
			}
		}
		else if (this.object.compareTo( o ) > 0) {
			return new Node(this.left, this.object, this.count, this.right.remove( o, num ), this.color);
		}
		else {
			return new Node(this.left.remove( o, num ), this.object, this.count, this.right, this.color);
		}
	}

	public RBtree<Obj> removeALL( Obj o ) {
		if (this.object.compareTo( o ) == 0) {
			return this.right.union(this.left);
		}
		else if (this.object.compareTo( o ) > 0) {
			return new Node(this.left, this.object, this.count, this.right.removeALL( o ), this.color);
		}
		else {
			return new Node(this.left.removeALL( o ), this.object, this.count, this.right, this.color);
		}
	}

	public RBtree<Obj> intersection( RBtree<Obj> t ) {
		if (t.member( this.object ) ) {
			int minimum = Math.min(t.getCount( object ), this.getCount( object ));
			return new Node(this.left.intersection( t ), this.object, minimum, this.right.intersection( t ), this.color);
		}
		else {
			return this.right.intersection( t ).union(this.left.intersection( t ));	
		}
	}

	public RBtree<Obj> difference( RBtree<Obj> t ) {
		RBtree<Obj> temp = t.remove( this.object, this.count );
		return this.right.union( this.left ).difference( temp );
	}

	public boolean subset( RBtree<Obj> t ) {
		return (t.getCount( this.object ) >= this.getCount( this.object )
			&& this.right.subset(t) && this.left.subset( t )); 
	}

	public boolean equal( RBtree<Obj> t ) {
		return (this.subset( t ) && t.subset( this ));
	}

	public Sequence<Obj> makeSeq( ) {
		return new SequenceNode(this.object, 
								this.count, 
								new SequenceConcat( this.left.makeSeq(), this.right.makeSeq() ));
	}

	public int sumAll( ) {

		Sequence seq = this.makeSeq();
		int sumAll = 0;
		while( seq.hasMine() ) {
			sumAll++;
			seq = seq.getNext();
		}

		return sumAll;
	}
}

interface Sequence<Obj> {

	public Obj getMine();
	public boolean hasMine();
	public Sequence<Obj> getNext();
	public String toString();
}

interface Sequenced<Obj extends Comparable> {
	public Sequence<Obj> makeSeq();
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

	public Sequence<Obj> makeSeq() {
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
			return this.left.getMine();
		} 
		else {
			return this.right.getMine();
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

	public String toString() {
		return "" + this.left.toString() + " " + this.right.toString();
	}
}

interface Randomy<Obj extends Comparable> {
	public Obj makeRandom();
}

class RandomInteger implements Randomy<Integer> {
	public Integer makeRandom() {
		Random r1 = new Random();
		return r1.nextInt((100 - 0) + 1);
	}
}

class RandomString implements Randomy<String> {
	public static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz1234567890";
	public static final int RAND_STRING_LENGTH = getRandomInt();

	public static int getRandomInt() {
		int grabInt = (int)(Math.random() * (20 - 1) + 1);
		return grabInt;
	}

	public String makeRandom() {
		StringBuffer grabString = new StringBuffer();

		for ( int i = 0; i < RAND_STRING_LENGTH; i++ ) {
			int num = getRandomInt();
			char c = CHAR_LIST.charAt(num);
			grabString.append(c);
		}

		return grabString.toString();
	}
}

class Tests<Obj extends Comparable> {

	Randomy<Obj> r1;

	public static int emptyLeafTest = 0;
	public static int emptyLeafTestCard = 0;
	public static int unionLeafTest = 0;
	public static int interLeafTest = 0;
	public static int differLeafTest = 0;
	public static int addingNodeTest = 0;
	public static int removingNodeTest = 0;
	public static int subsetUnionTest = 0;
	public static int equalSubsetIntersectionTest = 0;
	public static int cardinalitySequenceSumTest = 0;

	Tests(Randomy<Obj> r1) {
		this.r1 = r1;
	}


	public static RBtree empty() {
		return new Leaf();
	}

	public RBtree<Obj> randomRB(int num) {
		if (num <= 0) {
			return new Leaf();
		}
		else {
			int counter = (int)(Math.random() * (10 - 1) + 1);
			return randomRB(num - 1).add(r1.makeRandom(), counter); 
		}
	}

	public void testisEmptyLeaf(int count) throws Exception {

		for (int i = 0; i < 100; i++) {

			RBtree empt = empty();

			if (!empt.isEmpty()) {				
				throw new Exception("FAILURE EMPTY IS NOT EMPTY");
			}
			else {
				int len = (int)(Math.random() * (10 - 1) + 1);
				RBtree tree = randomRB( len );
				if (tree.isEmpty() && count != 0) {
					throw new Exception("FAILURE EMPTY TREE HAS AN OBJECT");
				}
			}
			emptyLeafTest++;
		}
	}

	public void testisEmptyLeafCard() throws Exception {

		for (int i = 0; i < 100; i++) {

			RBtree empt = empty();
			if ((!empt.isEmpty()) && (empt.cardinality() == 0)) {
				throw new Exception("A NON-EMPTY TREE HAS ZERO CARDINALITY");
			}

			if(empt.isEmpty() && (empt.cardinality() != 0)) {
				throw new Exception("AN EMPTY TREE HAS A CARDINALITY");
			}
			emptyLeafTestCard++;
		}
	}

	public void testUnionLeaf() throws Exception {

		for (int i = 0; i < 100; i++) {

			int len = (int)(Math.random() * (10 - 1) + 1);
			RBtree tree = randomRB( len );

			if (!tree.union(empty()).equal(tree)) {
				throw new Exception("FAILURE A TREE UNIONED WITH EMPTY IS NOT THE TREE");
			}
			unionLeafTest++;
		}
	}


	public void testInterLeaf() throws Exception {

		for (int i = 0; i < 100; i++) {

			int len = (int)(Math.random() * (10 - 1) + 1);
			RBtree tree = randomRB( len );

			if ( ! tree.intersection(empty()).equal(empty()) ) {
				throw new Exception("FAILURE IS NOT AN OPTION");
			}
			interLeafTest++;
		}
	}


	public void testDiffLeaf() throws Exception {

		for (int i = 0; i < 100; i++) {

			int len = (int)(Math.random() * (10 - 1) + 1);
			RBtree tree = randomRB( len );
			RBtree empt = empty();
			RBtree diff = tree.difference(empt);
			if ( !diff.equal(empt) ) {
				throw new Exception("FAILURE TO TAKE DIFFERENCE");
			}
			differLeafTest++;
		}
	}

	public void testAddingNode() throws Exception {

		for (int i = 0; i < 100; i++) {

			int len = (int)(Math.random() * (10 - 1) + 1);
			RBtree tree = randomRB( len );
			int card = tree.cardinality();
			int testcard = tree.add(r1.makeRandom()).cardinality();
			if ( testcard < card ) {
				throw new Exception("FAILURE WHILE TRYING TO ADD! " + card + " is original cardinality. "
					+ testcard + " is the cardinality after adding");
			}
				addingNodeTest++; 
		}
	}

	public void testRemovingNode() throws Exception {

		for (int i = 0; i < 100; i++) {

			int len = (int)(Math.random() * (10 - 1) + 1);
			RBtree tree = randomRB( len );
			int cardtree = tree.cardinality();

			RBtree removetree = tree.remove(((Node) tree).object);
			int cardremovetree = removetree.cardinality();

			if (cardtree < cardremovetree ) {
				throw new Exception("FAILURE TO REMOVE");
			}
		removingNodeTest++;
		}
	}

	public void testSubsetUnion() throws Exception {

		for (int i = 0; i < 100; i++) {

			int len = (int)(Math.random() * (10 - 1) + 1);
			RBtree a = randomRB( len );
			RBtree b = randomRB( len );
			RBtree ab = a.union( b );

			if (!(a.subset( ab )) && !(b.subset( ab )) ) {
				throw new Exception("FAILURE A SET WAS NOT IN ITS UNION'S SUBSET");
			}
			subsetUnionTest++;
		}
	}

	public void testEqualSubsetIntersection() throws Exception {

		for (int i = 0; i < 100; i++) {

			int len = (int)(Math.random() * (10 - 1) + 1);
			RBtree a = randomRB( len );	
			RBtree b = a; 
			if (!a.equal( b ) ) {
				throw new Exception("FAILURE THE TWO SETS ARE NOT EQUAL");
			}

			if (!a.subset( a.intersection( b )) && !b.subset( b.intersection( a ))) {
				throw new Exception("FAILURE THE TWO SETS ARE NOT SUBSETS OF THE INTERSECTION");
			}

			equalSubsetIntersectionTest++;
		}
	}

	public void testCardinalitySequenceSum() throws Exception {

		for (int i = 0; i < 100; i++) {

			int len = (int)(Math.random() * (10 - 1) + 1);
			RBtree a = randomRB( len );	

			if (!(a.cardinality() == a.sumAll() ) ) {
				throw new Exception("FAILURE THE CARDINALITY IS DIFFERENT FROM THE SEQUENCE SUM");
			}

			cardinalitySequenceSumTest++;
		}
	}
}

class data2 {
	public static void main(String[] args) throws Exception {

		Tests integerTest = new Tests(new RandomInteger());
		Tests stringTest = new Tests(new RandomString());


		System.out.println("//////////////////////");
		System.out.println("/  Property testing  /");
		System.out.println("/for strings and ints/");
		System.out.println("//////////////////////\n");


		integerTest.testisEmptyLeaf(0);
		stringTest.testisEmptyLeaf(0);
		System.out.println("Test for Integer isEmpty() succeeded " + integerTest.emptyLeafTest + " times.");
		System.out.println("Test for String isEmpty() succeeded " + stringTest.emptyLeafTest + " times.\n");


		integerTest.testisEmptyLeafCard();
		stringTest.testisEmptyLeafCard();
		System.out.println("Test for Integer isEmpty() and cardinality() succeeded " + integerTest.emptyLeafTestCard + " times.");
		System.out.println("Test for String isEmpty() and cardinality() succeeded " + stringTest.emptyLeafTestCard + " times.\n");


		integerTest.testUnionLeaf();
		stringTest.testUnionLeaf();
		System.out.println("Test for Integer union() succeeded " + integerTest.unionLeafTest + " times.");
		System.out.println("Test for String union() succeeded " + stringTest.unionLeafTest + " times.\n");


		integerTest.testAddingNode();
		stringTest.testAddingNode();
		System.out.println("Test for add() and cardinality() succeeded " + integerTest.addingNodeTest + " times.");
		System.out.println("Test for add() and cardinality() succeeded " + stringTest.addingNodeTest + " times.\n");


		integerTest.testInterLeaf();
		stringTest.testInterLeaf();
		System.out.println("Test of intersection() succeeded " + integerTest.interLeafTest + " times.");
		System.out.println("Test of intersection() succeeded " + stringTest.interLeafTest + " times.\n");

		integerTest.testDiffLeaf();
		stringTest.testDiffLeaf();
		System.out.println("Test of difference() succeeded " + integerTest.differLeafTest + " times.");
		System.out.println("Test of intersection() succeeded " + stringTest.differLeafTest + " times.\n");


		integerTest.testRemovingNode();
		stringTest.testRemovingNode();
		System.out.println("Test of remove() succeeded " + integerTest.removingNodeTest + " times.");
		System.out.println("Test of remove() succeeded " + stringTest.removingNodeTest + " times.\n");

		integerTest.testSubsetUnion();
		stringTest.testSubsetUnion();
		System.out.println("Test of subset() and union() succeeded " + integerTest.subsetUnionTest + " times.");
		System.out.println("Test of subset() and union() succeeded " + stringTest.subsetUnionTest + " times.\n");

		integerTest.testEqualSubsetIntersection();
		stringTest.testEqualSubsetIntersection();
		System.out.println("Test of equal(), subset(), intersection() succeeded " + integerTest.equalSubsetIntersectionTest + " times.");	
		System.out.println("Test of equal(), subset(), intersection() succeeded " + stringTest.equalSubsetIntersectionTest + " times.\n");

		integerTest.testCardinalitySequenceSum();
		System.out.println("Test of cardinality(), makeSeq(), sumAll() succeeded " + integerTest.cardinalitySequenceSumTest + " times.");

	}
}