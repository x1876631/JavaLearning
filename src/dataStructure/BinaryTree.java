package dataStructure;

/**
 * Created by xuye on 2017年12月13日
 * <p>
 * 二叉树<br/>
 * 参考：<br/>
 * 树结构基础知识： http://blog.csdn.net/xiaoquantouer/article/details/65631708<br/>
 * 基础操作的实现：http://m.jb51.net/article/61021.htm<br/>
 * 基础操作的java实现：http://blog.csdn.net/fengrunche/article/details/52305748<br/>
 */
public class BinaryTree {
	private BinaryNode mRootNode;

	public BinaryTree(int data) {
		mRootNode = new BinaryNode(data);
		mRootNode.left = null;
		mRootNode.right = null;
	}

	/**
	 * 向二叉树插入新节点
	 * 
	 * @param value
	 *            要插入的值
	 * @return true表示插入成功
	 */
	public boolean insert(int value) {
		BinaryNode newNode = new BinaryNode(value);
		if (mRootNode == null) {
			mRootNode = newNode;
			return true;
		} else {
			BinaryNode current = mRootNode;
			BinaryNode parent = null;
			while (true) {
				if (value < current.value) {
					// 比根节点小的值都放在左子树里
					parent = current;
					current = current.left;
					if (current == null) {
						// 直到找到最后的叶子节点，才赋值，然后退出本次插入操作
						parent.left = newNode;
						break;
					}
				} else if (value > current.value) {
					// 比根节点大的都放在右子树里
					parent = current;
					current = parent.right;
					if (current == null) {
						parent.right = newNode;
						break;
					}
				} else {
					System.out.println("当前二叉树里已经有要添加的节点值了，添加失败");
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 在当前树里找和指定的值相等的节点
	 * 
	 * @param value
	 *            待查询的值
	 * @return 和指定的值相等的节点
	 */
	public BinaryNode find(int value) {
		BinaryNode current = mRootNode;
		while (true) {
			if (value == current.value) {
				System.out.println("find " + value + ": success ");
				return current;
			} else if (value < current.value) {
				// value比当前节点值小，说明在左子树里
				current = current.left;
			} else {
				current = current.right;
			}
			// 当前节点==null了，说明已经遍历到最后了
			if (current == null) {
				System.out.println("find " + value + ": fail ");
				return null;
			}
		}
	}

	/**
	 * 递归实现的中序遍历，顺序为：先访问左子树，访问根，再访问右子树
	 * 
	 * @param node
	 *            开始遍历的节点
	 */
	public void inOrderTraverse(BinaryNode root) {
		if (root == null) {
			// 如果root为null，说明root的父节点就是叶子节点了，返回
			return;
		}
		// 如果有左子树，去访问左子树
		inOrderTraverse(root.left);
		// 递归到最底层的叶子节点，才会走下面的访问根语句
		root.display();
		// 访问完当前root的左子树和根后，再访问右子树
		inOrderTraverse(root.right);
	}

	/**
	 * 递归实现的前序遍历，顺序为：根左右
	 * 
	 * @param root
	 */
	public void preOrderTraverse(BinaryNode root) {
		if (root == null) {
			return;
		}
		root.display();
		preOrderTraverse(root.left);
		preOrderTraverse(root.right);
	}

	/**
	 * 递归实现的后序遍历，顺序为：左右根
	 * 
	 * @param root
	 */
	public void postOrderTraverse(BinaryNode root) {
		if (root == null) {
			return;
		}
		postOrderTraverse(root.left);
		postOrderTraverse(root.right);
		root.display();
	}

	public int getMinValue() {
		BinaryNode current = mRootNode;
		while (true) {
			if (current.left == null) {
				return current.value;
			} else {
				current = current.left;
			}
		}
	}

	public int getMaxValue() {
		BinaryNode current = mRootNode;
		while (true) {
			if (current.right == null) {
				return current.value;
			} else {
				current = current.right;
			}
		}
	}

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree(52);
		bt.insert(580);
		bt.insert(12);
		bt.insert(50);
		bt.insert(58);
		bt.insert(9);
		bt.insert(888);
		bt.insert(248);
		bt.insert(32);
		bt.insert(666);
		bt.insert(455);
		bt.insert(777);
		bt.insert(999);
		System.out.println("\n----中序遍历----");
		bt.inOrderTraverse(bt.mRootNode);

		System.out.println("\n----前序遍历----");
		bt.preOrderTraverse(bt.mRootNode);

		System.out.println("\n----后序遍历----");
		bt.postOrderTraverse(bt.mRootNode);

		System.out.println("\n");
		bt.find(32);
		bt.find(81);

		System.out.println("获取当前树的最小值:" + bt.getMinValue());
		System.out.println("获取当前树的最大值:" + bt.getMaxValue());
	}
}
