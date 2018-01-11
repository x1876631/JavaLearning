package algorithm;

/**
 * created by xuye on 2018年1月11日
 * 
 * 矩阵相关算法
 */
public class MatrixTest {

	/**
	 * 给定M*N矩阵，每一行、每一列都按升序排列，查询某元素是否在里面<br/>
	 * 思路：从第一行的末尾开始找，如果>x，说明在该列的左边。如果<x，说明在这行的下面<br/>
	 * 这样不断缩小矩阵，从矩阵中的子矩阵中查找元素
	 */
	public static boolean findX(int[][] matrix, int x) {
		int rowIndex = 0;// 行坐标，从第一行开始
		int colIndex = matrix[0].length - 1;// 列坐标，从最后一列列开始

		while (rowIndex < matrix.length && colIndex >= 0) {
			if (matrix[rowIndex][colIndex] == x) {
				return true;
			} else if (matrix[rowIndex][colIndex] > x) {
				// 本行的最后一列元素>x，说明x在该列的左边，列-1
				colIndex--;
			} else {
				// 本行的最后一列元素<x，说明x在本行的下面，行+1
				rowIndex++;
			}
		}
		return false;
	}

	public static void displayMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.println("matrix[" + i + "][" + j + "]=" + matrix[i][j]);
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		int x = 11;
		System.out.println("矩阵中是否找到指定的数：" + x + " ？ result：" + findX(matrix, x));
	}
}
