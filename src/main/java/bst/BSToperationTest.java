package bst;

public class BSToperationTest {

	public static void main(String[] args) {

		int[] testData = new int[] { 10, 4, 7, 0, 2, 53, 15, 62,  76, 1, 5,
				3, 8 };

		BSTree mytree = new BSTree();

		for (int i : testData) {
			mytree.addNode(i);
		}

		mytree.printSelf();

		TreeNode treeNode = mytree.search(62);
		System.out.println("treeNode:" + treeNode.payload);

		System.out.println("minValue:" + mytree.min(mytree.root).payload);

		System.out.println("maxValue:" + mytree.max(mytree.root).payload);
		
		System.out.println(mytree.findNext(15).payload);
		System.out.println(mytree.findPre(15).payload);
	}
}
