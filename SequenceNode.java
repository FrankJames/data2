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