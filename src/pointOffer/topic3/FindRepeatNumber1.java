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
        int[] array = {2, 3, 1, 0, 2, 5, 3};
        System.out.print("重复数字是：" + number1.checkMySelf(array));
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
}
