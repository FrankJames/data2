/*
@author Frank James
*/

class data2 {
	public static void main(String[] args)  throws Exception {

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