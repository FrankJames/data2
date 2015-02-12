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