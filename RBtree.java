

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