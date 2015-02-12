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