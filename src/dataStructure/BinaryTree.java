package dataStructure;

import java.util.LinkedList;
import java.util.Stack;

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
	private TreeNode mRootNode;

	public BinaryTree(int data) {
		mRootNode = new TreeNode(data);
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
		TreeNode newNode = new TreeNode(value);
		if (mRootNode == null) {
			mRootNode = newNode;
			return true;
		} else {
			TreeNode current = mRootNode;
			TreeNode parent = null;
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
	public TreeNode find(int value) {
		TreeNode current = mRootNode;
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
	 * 访问节点，输出数据
	 * 
	 * @param node
	 */
	public void visit(TreeNode node) {
		node.display();
	}

	/**
	 * 递归实现的中序遍历，顺序为：先访问左子树，访问根，再访问右子树
	 * 
	 * @param node
	 *            开始遍历的节点
	 */
	public void inOrderTraverse(TreeNode root) {
		if (root == null) {
			// 如果root为null，说明root的父节点就是叶子节点了，返回
			return;
		}
		// 如果有左子树，去访问左子树
		inOrderTraverse(root.left);
		// 递归到最底层的叶子节点，才会走下面的访问根语句
		visit(root);
		// 访问完当前root的左子树和根后，再访问右子树
		inOrderTraverse(root.right);
	}

	/**
	 * 非递归的中序遍历，顺序是左中右<br/>
	 * 参考：http://blog.csdn.net/u011514810/article/details/75907170
	 * 
	 * @param root
	 */
	public void inOrderLoop(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		while (p != null || !stack.empty()) {
			// 不断压根节点入栈，然后查找这个入栈节点是否有左节点，再把入栈节点的左节点依次压入
			while (p != null) {
				stack.push(p);
				// 直到p是叶子节点的left
				p = p.left;
			}
			// 到叶子节点了，开始拿节点遍历
			if (!stack.empty()) {
				// 访问的其实都算是根节点
				TreeNode node = stack.pop();
				visit(node);
				// 开始遍历这个节点的右子树
				p = node.right;
			}
		}
	}

	/**
	 * 递归实现的前序遍历，顺序为：根左右
	 * 
	 * @param root
	 */
	public void preOrderTraverse(TreeNode root) {
		if (root == null) {
			return;
		}
		visit(root);
		preOrderTraverse(root.left);
		preOrderTraverse(root.right);
	}

	/**
	 * 非递归，前序遍历，中左右
	 * 
	 * @param root
	 */
	public void preOrderLoop(TreeNode root) {
		TreeNode p = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (p != null || !stack.empty()) {
			// 当p==null时，可能是遍历到叶子节点的子节点了
			while (p != null) {
				// 访问根并压入，然后访问其左节点
				visit(p);
				stack.push(p);
				p = p.left;
			}

			if (!stack.empty()) {
				// 拿当前节点的右节点，此时根节点和左节点已经遍历完了
				TreeNode node = stack.pop();
				p = node.right;
			}
		}
	}

	/**
	 * 递归实现的后序遍历，顺序为：左右根
	 * 
	 * @param root
	 */
	public void postOrderTraverse(TreeNode root) {
		if (root == null) {
			return;
		}
		postOrderTraverse(root.left);
		postOrderTraverse(root.right);
		visit(root);
	}

	public int getMinValue() {
		TreeNode current = mRootNode;
		while (true) {
			if (current.left == null) {
				return current.value;
			} else {
				current = current.left;
			}
		}
	}

	public int getMaxValue() {
		TreeNode current = mRootNode;
		while (true) {
			if (current.right == null) {
				return current.value;
			} else {
				current = current.right;
			}
		}
	}

	/**
	 * 参考1：https://www.bysocket.com/?p=1209<br/>
	 * 参考2：http://www.cnblogs.com/MrListening/p/5782752.html<br/>
	 * 参考3：图片讲解比较清晰，http://blog.csdn.net/fengrunche/article/details/52305748 <br/>
	 * 删除指定值对应的节点，分4步：<br/>
	 * 1、找到要删除的节点<br/>
	 * 2、判断是有没有左右子节点，如果没有就简单修改其父节点的左或者右节点为null<br/>
	 * 3、判断是有仅有一个左右子节点，如果仅有一个，就将该单节点变成父节点的左右子节点<br/>
	 * 4、判断是否有2个子节点，如果有2个子节点，先找到后续节点，代替要删除的节点(通过修改删除节点的父节点的子节点关系)，
	 * 然后调整该后续节点的左右子节点
	 * 
	 * @param value
	 *            要删除的节点里的值
	 * @return 删除的节点对象
	 */
	public TreeNode delete(int value) {
		if (mRootNode == null) {
			return null;
		}
		TreeNode parent = mRootNode;
		TreeNode current = mRootNode;
		boolean isLeftChild = false;// 要删除的节点在左还是在右

		// 先遍历一遍，找到要删除的节点，current就是要删除的节点，如果找不到就返回null了
		while (current.value != value) {
			// 当前节点不是要删除的节点，则下移parent指针
			parent = current;
			if (value < current.value) {
				// 说明要删除的节点在左子树
				isLeftChild = true;
				current = current.left;
			} else {
				// 要删除的节点在右子树
				isLeftChild = false;
				current = current.right;
			}

			// current==null说明已经遍历到叶子节点了，还没有找到要删除的值对应的节点
			if (current == null) {
				return current;
			}
		}

		// 上边的逻辑走完没返回，说明找到了要删除的节点current，再做删除判断
		// 1、要删除的节点是叶子节点，无左右子树，很简单，直接删掉current就行
		if (current.left == null && current.right == null) {
			if (current == mRootNode) {
				// 如果当前节点是root节点(没有父节点，单独设置)
				mRootNode = null;
			} else {
				// 根据在左还是右，给父节点的引用置空，就相当于删除了
				if (isLeftChild) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			}
		}

		// 2、如果要删除的节点不是叶子节点，但只有一个子树
		// 做每种判断里，都应该判断下当前要删除的节点current是不是根节点，
		// 如果是要删除根节点的话，就直接让根节点指针指向当前根节点的左孩子或右孩子，然后删除这个根节点
		else if (current.right == null) {
			// 如果要删除的节点只有左孩子，那么就让该节点的父亲结点指向该节点的左孩子，然后删除该节点，返回true；
			if (current == mRootNode) {
				mRootNode = current.left;
			} else if (isLeftChild) {
				// 要删除的节点在父节点的左子树，将父节点的左子树指向该节点的左孩子
				parent.left = current.left;
			} else {
				// 要删除的节点在父节点的右子树，将父节点的右子树指向该节点的左孩子
				parent.right = current.left;
			}
		} else if (current.left == null) {
			// 如果要删除的节点只有右孩子，那么就让该节点的父亲结点指向该节点的右孩子，然后删除该节点，返回true
			if (current == mRootNode) {
				mRootNode = current.right;
			} else if (isLeftChild) {
				parent.right = current.right;
			} else {
				parent.left = current.right;
			}
		}

		// 3、最复杂的情况，要删除的节点左右子树都不为空
		else if (current.left != null && current.right != null) {
			// 需要找到后续节点，后继节点就是比要删除的节点的value值要大的节点集合中的最小值，所以就是右子树里的最小值
			// 找后续节点的时候，就已经调整过子树了，所以接下来就更改一下parent的左右子树就好了
			TreeNode successor = getDeleteSuccessor(current);
			if (current == mRootNode) {
				mRootNode = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}

			// 把删除节点的左子树指向后续节点的左节点
			successor.left = current.left;
		}
		return current;
	}

	/**
	 * 获取替代删除节点的后续节点，后续节点就是比要删除节点的value值大的所有节点里value值最小的一个
	 * 
	 * @param deleteNode
	 *            要删除的节点
	 * @return 后续节点
	 */
	public TreeNode getDeleteSuccessor(TreeNode deleteNode) {
		TreeNode current = deleteNode.right;
		TreeNode successor = null;
		TreeNode successorParent = null;
		// 遍历待删除节点的右子树，找到最小值
		while (current != null) {
			successorParent = successor;
			successor = current;// 记录一下current，如果后面current==null，这个current就是后续节点
			current = current.left;// 移动到左子树，继续查询最小值
		}

		// 找到了后续节点，且后续节点有右子树
		if (successor != deleteNode.right) {
			// 把后续节点的右子树给后续节点的父左子树
			successorParent.left = successor.right;

			// 然后让后续节点代替删除的节点，并把原来删除节点的右子树指向该节点的右子树
			successor.right = deleteNode.right;
		}

		// 没有右子树就直接返回(此时已经是最小值了，所以也不可能有左子树)
		return successor;
	}

	/**
	 * s型，按层遍历<br/>
	 * 靠队列实现，每次遍历的某节点是，都将左右子节点插入队列，这样访问顺序必定是先左再右，且是同一层
	 */
	public void leverIterator(TreeNode node) {
		if (node == null) {
			return;
		} else {
			LinkedList<TreeNode> linkedList = new LinkedList<>();
			linkedList.add(node);
			TreeNode current = null;
			while (!linkedList.isEmpty()) {
				current = linkedList.poll();
				// 循环遍历队列
				System.out.print(current.value + " ");
				if (current.left != null) {
					linkedList.add(current.left);
				}
				if (current.right != null) {
					linkedList.add(current.right);
				}
			}
		}
	}

	/**
	 * 获取树的深度
	 * 
	 * @param root
	 *            树的根节点
	 * @return 树的深度
	 */
	public int getDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		// 获取左子树深度
		int left = getDepth(root.left);
		System.out.println("当前节点值为：" + root.value + " ,左子树深度：" + left);
		// 获取右子树深度
		int right = getDepth(root.right);
		System.out.println("当前节点值为：" + root.value + " ,右子树深度：" + right);
		// 比较左右子树深度，更深的那个值+1返回
		return left > right ? left + 1 : right + 1;
	}

	/**
	 * 求每一层的最大值<br/>
	 * 参考：http://www.tk4479.net/mine_song/article/details/70213201<br/>
	 * 
	 * @param root
	 *            根节点
	 */
	public static void bfs(TreeNode root) {
		if (root == null) {
			return;
		}
		// 用一个队列存储待遍历的节点
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.add(root);
		int currentLevelNum = 1;// 当前层的节点数
		int nextLevelNum = 0;// 下一层的节点数
		int levelMaxValue = Integer.MIN_VALUE;// 本层的最大值
		while (!list.isEmpty()) {
			// 不断的从队列里，拿本层元素
			TreeNode node = list.poll();
			currentLevelNum--;
			// 比较当前节点和之前获取的本层最大值，更新最大值
			levelMaxValue = Math.max(node.value, levelMaxValue);

			// 添加该节点的子节点到队列里，同时记录一下，下一层的节点个数，好在循环中判断，拿本层的节点数据
			if (node.left != null) {
				list.add(node.left);
				nextLevelNum++;
			}
			if (node.right != null) {
				list.add(node.right);
				nextLevelNum++;
			}

			// 如果遍历完本层所有数据了，输出一下最大值
			if (currentLevelNum == 0) {
				System.out.println("本层所有节点已经遍历完了，找到的最大值：" + levelMaxValue);
				// 重置所有值
				currentLevelNum = nextLevelNum;
				nextLevelNum = 0;
				levelMaxValue = Integer.MIN_VALUE;
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
		/***
		 * 执行完以后，这个二叉树有5层，样子如下：<br/>
		 *                52<br/>
		 *        	12 				580<br/>
		 *    	9      50   	58					888<br/>
		 * 			 32	          	248     	 666      	999<br/>  
		 * 								455     	777<br/>
		 */
		
		

		System.out.println("\n----获取树的深度----");
		System.out.println("树的深度：" + bt.getDepth(bt.mRootNode));

		System.out.println("\n----按层遍历----");
		bt.leverIterator(bt.mRootNode);

		System.out.println("\n----中序遍历，递归----");
		bt.inOrderTraverse(bt.mRootNode);

		System.out.println("\n----中序遍历，非递归----");
		bt.inOrderLoop(bt.mRootNode);

		System.out.println("\n----前序遍历，递归----");
		bt.preOrderTraverse(bt.mRootNode);

		System.out.println("\n----前序遍历，非递归----");
		bt.preOrderLoop(bt.mRootNode);

		System.out.println("\n----后序遍历----");
		bt.postOrderTraverse(bt.mRootNode);

		System.out.println("\n");
		bt.find(32);
		bt.find(81);

		System.out.println("获取当前树的最小值:" + bt.getMinValue());
		System.out.println("获取当前树的最大值:" + bt.getMaxValue());

		System.out.println("\n----删除一个节点 52----");
		bt.delete(52); // 删除有两个子节点的节点，且删除节点为根节点
		System.out.println("\n----中序遍历----");
		bt.inOrderTraverse(bt.mRootNode);

		System.out.println("\n----输出每层的最大值----");
		bfs(bt.mRootNode);
	}
}
