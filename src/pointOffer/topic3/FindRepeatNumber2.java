package pointOffer.topic3;

import java.util.HashMap;

/**
 * 不修改数组，找出重复的数字
 * 在一个长度为n+1的数组里，所有数字都在1~n的范围内，所以数组中至少有一个数字是重复的。
 * 请找出数组中任意一个重复的数字，但不能修改数组。
 * 例如，输入的数组是{2,3,5,4,3,2,6,7}，那么对于的输出是重复的数组2或者3
 */
public class FindRepeatNumber2 {

    public static void main(String[] args) {
        FindRepeatNumber2 number2 = new FindRepeatNumber2();
        int[] array = {2, 3, 5, 4, 3, 2, 6, 7};
        System.out.print("方法1，找到的array1的重复数字是：" + number2.byMySelf(array) + "\n");
    }

    private int byMySelf(int[] array) {
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
}
