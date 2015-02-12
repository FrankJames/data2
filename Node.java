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