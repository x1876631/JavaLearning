package pointOffer.topic4;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排列，每一列都按照从上到下递增的顺序排列。
 * 请完成这样一个函数，输入这样一个二维数组和一个整数，判断数组中是否含有这个整数
 * 例如：
 * 1 2 8 9
 * 2 4 9 12
 * 4 7 10 13
 * 6 8 11 15
 * 如果查找7，则返回true，如果查找5，则返回false
 */
public class TwoDimensionalArray {
    public static void main(String[] args) {
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
//        printArray(array);
        int findNumber1 = 7;
        int findNumber2 = 5;
        System.out.print("my1, find findNumber = "+findNumber1+", result = "+my1(array,findNumber1)+"\n");
        System.out.print("my1, find findNumber = "+findNumber2+", result = "+my1(array,findNumber2)+"\n");

    }

    private static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            int[] array2 = array[i];
            for (int j = 0; j < array2.length; j++) {
                System.out.print(array2[j] + " , ");
            }
        }
    }

    /**
     * 最无脑的方法，循环打印，时间复杂度
     */
    private static boolean my1(int[][] array, int number){
        for (int i = 0; i < array.length; i++) {
            int[] array2 = array[i];
            for (int j = 0; j < array2.length; j++) {
//                System.out.print(array2[j] + " , ");
                if(number == array2[j]){
                    return true;
                }
            }
        }
        return false;
    }
}
