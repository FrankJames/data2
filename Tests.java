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