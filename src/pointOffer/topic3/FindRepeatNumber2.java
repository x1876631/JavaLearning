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
        System.out.print("方法2，找到的array1的重复数字是：" + number2.byMySelf2(array) + "\n");
        System.out.print("方法best，找到的array1的重复数字是：" + number2.best(array, 1, array.length - 1) + "\n");

        int[] array2 = {1, 2, 3};
        System.out.print("方法best，找到的array2的重复数字是：" + number2.best(array2, 1, array2.length - 1) + "\n");
    }

    /**
     * 第一时间想到的解法，时间复杂度是O(n)，空间复杂度是O(n)
     */
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


    /**
     * 第2想到的算法，不用额外空间，只使用原本的数组。
     * 时间复杂度是O(n^2)，因为内循环比较n-1+n-2+..+1 = n(n-1)/2 约= O(n^2)
     * 空间复杂度是O(1)，因为无论n怎么变，空间都是个常量，是不变的
     */
    private int byMySelf2(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int cur = array[i];
            for (int j = i + 1; j <= array.length; j++) {
                if (cur == array[j]) {
                    return cur;
                }
            }
        }
        return -1;
    }


    /**
     * 用二分法，按1~m m+1~n的划分法，遍历数组检查所有数字，
     * 是否落在某一范围（1~m or m+1~n）的数字的个数超过m，超过了的就说明那个范围内有重复数字，然后再把那个范围折半
     * 直到最后范围内只有一个数字，这个数字就是重复的数字
     * <p>
     * 时间复杂度是O(n * log n)，因为二分查找的时间复杂度是O(log2^n)简写为O(log n)，
     * 而每次二分后都需要遍历检查一遍数组O(n)，所以最后时间复杂度是O(n * log n)
     * 空间复杂度是O(1)，因为所用空间值是固定的，跟n没关系
     */
    private int best(int[] array, int start, int end) {
        if (array == null || array.length == 0 || end <= start || start < 1 || end > array.length - 1) {
            return -1;
        }
        //找中间值
        int m = (start + end) / 2;
        System.out.print("start = " + start + " , end = " + end + ", m=" + m + "\n");
        //1~m范围值个数
        int left = 0;
        //m+1范围内的个数
        int right = 0;

        //遍历一遍数组，跟中位值m比较，找到每个范围内的数字个数
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= m) {
                left++;
            } else if (array[i] > m && array[i] <= end) {
                right++;
            }
        }

        System.out.print("left(" + start + "~" + m + ") = " + left + " , right(" + (m + 1) + "~" + end + ") = " + right + " ,m= " + m + "\n");

        if (left > m) {
            System.out.print("left count > m" + "\n\n");
            //如果1~m范围值个数超了，说明这个范围有重复数字，范围重新折半，start不变，end变为m
            end = m;
        } else if (right > m) {
            System.out.print("right count > m" + "\n\n");
            //如果m+1~n范围值个数超了，说明这个范围有重复数字，范围重新折半，end不变，start变为m+1
            start = m + 1;
        } else {
            //左范围和右范围都没超，说明没有重复数字
            return -1;
        }

        //如果折半后，范围首位相同，说明就是这个数字重复了
        if (start == end) {
            System.out.print("get result = " + start + "\n");
            return start;
        }

        //重复查找
        return best(array, start, end);
    }
}
