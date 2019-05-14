package pointOffer.topic3;

import java.util.HashMap;

/**
 * 题目1：找出数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0~n-1的范围内，数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次，请找出数组中任意一个重复的数字。
 * 例如：如何输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3。
 */
public class FindRepeatNumber1 {
    public static void main(String[] args) {
        FindRepeatNumber1 number1 = new FindRepeatNumber1();
        int[] array = {2, 3, 1, 0, 3, 5, 2};
        int[] array2 = {2, 3, 1, 0, 4, 6, 5};
        System.out.print("方法1，找到的array1的重复数字是：" + number1.checkMySelf(array) + "\n");
        System.out.print("方法2，找到的array1的重复数字是：" + number1.best(array) + "\n");
        System.out.print("方法2，找到的array2的重复数字是：" + number1.best(array2) + "\n");
    }


    /*
     * 我自己第一时间想到的方法。
     * 时间复杂度：最坏要判断n次，才能找到重复的数，所以是O(n)
     * 空间复杂度：额外用了一个长度为n的数组，所以是O(n)
     */
    private int checkMySelf(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>(array.length);
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                return array[i];
            } else {
                map.put(array[i], i);
            }
        }
        return -1;
    }

    /**
     * 空间复杂度最小的解法，一直用的都是一个额外的空间来承载交换的数据，和n没有关系。所以复杂度是O(1)
     * 同时也是时间复杂度最小的算法，由于n个元素每个元素最多只需要比较2次就能找到自己的位置，最坏的情况是所有元素都比较过，所以是O(an)，即O(n)
     */
    private int best(int[] array) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] != index) {
                if (array[index] != array[array[index]]) {
                    //index与元素值m不相等，a[m]的位置上不是元素值m，则把元素值m放在a[m]处（即排序了），原a[m]值放到前面
                    int temp = array[array[index]];
                    array[array[index]] = array[index];
                    array[index] = temp;
                    best(array);
                } else {
                    //如果输入的数组元素是不重复的话，元素值m应该在a[m]的位置，如果现在发现a[m]是值m，说明值m就是重复的
                    return array[index];
                }
            }
            //index==元素值m，则继续向后查询
        }
        return -1;
    }
}
