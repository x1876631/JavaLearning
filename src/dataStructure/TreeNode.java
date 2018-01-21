package dataStructure;

/**
 * Created by xuye on 2017年12月13日
 * <p>
 * 二叉树专用节点，有个数据域和左右子节点
 */
public class TreeNode {

	public int value;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int value) {
		this(value, null, null);
	}

	public TreeNode(int value, TreeNode left, TreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public void display() {
		System.out.print((value == 0 ? "null" : value) + " ");
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
