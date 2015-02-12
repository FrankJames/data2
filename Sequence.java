interface Sequence<Obj> {

	public Obj getMine();
	public boolean hasMine();
	public Sequence<Obj> getNext();
	public String toString();
}