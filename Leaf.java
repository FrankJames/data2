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