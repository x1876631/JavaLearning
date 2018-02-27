package algorithm;

/**
 * Created by xuye on 2018年2月26日
 * <p>
 * n*n矩阵，顺时针旋转90度<br/>
 * 规律：1、新矩阵的列，是原本矩阵的行。2、新矩阵的行，是n-原矩阵行
 */
public class MatrixRotateImpl {

	/**
	 * 最简单的方法，但是空间复杂度很高，需要一个额外的二维数组，时间复杂度是O(n^2)，当然哪种算法都是这个数
	 */
	public static int[][] matrixRotate90Simple(int[][] matrix) {
		int col = matrix.length;// 列
		int row = matrix.length;// 行
		int[][] result = new int[row][col];// 新矩阵
		int lastColIndex = col - 1;// 新矩阵的列index，填数据时，从最后一列向前依次输入
		for (int i = 0; i < row; i++) {
			// 从原矩阵按行取数据，放入新数组的列
			for (int j = 0; j < col; j++) {
				// 从最后一列向前依次输入，所以新矩阵列值为lastColIndex不变，变的是行的index
				// 行的index其实就是原数组的列index，所以就是j
				result[j][lastColIndex] = matrix[i][j];
			}
			// 没填完1列，就将列向前1列
			lastColIndex--;
		}
		return result;
	}

	/**
	 * 参考：http://blog.csdn.net/michellechouu/article/details/46794323
	 */
	public static int[][] matrixRotate90Best(int[][] matrix) {
		return matrix;
	}

	public static void dispalyMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			int[] array = matrix[i];
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[j] + " ");
			}
		}
		System.out.println("----矩阵展示完成----");
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		dispalyMatrix(matrix);
		matrix = matrixRotate90Simple(matrix);
		dispalyMatrix(matrix);
	}
}
