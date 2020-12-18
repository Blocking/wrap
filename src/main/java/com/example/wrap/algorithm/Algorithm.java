package com.example.wrap.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangxiaoyu
 */
public class Algorithm {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(1);
        integers.add(2);
        Collections.sort(integers);
        int i = Collections.binarySearch(integers, 3);
        System.out.println(i);
        System.out.println(5 / 2);
    }

    @Test
    public void test_binary_search() {
        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(1);
        integers.add(2);
        System.out.println(integers);
        System.out.println(binary_search(integers, 1));
    }

    public <T extends Comparable<? super T>> int binary_search(List<T> list, T item) {
        Collections.sort(list);
        int high = list.size();
        for (int low = 0; low <= high; ) {
            int mid = (high + low) >>> 1;
            T t = list.get(mid);
            int compareTo = item.compareTo(t);
            if (compareTo < 0) {
                // item < t
                high = mid - 1;

            } else if (compareTo > 0) {
                //item > t
                low = mid + 1;
            } else {
                // item 与 t 相等
                return mid;
            }
        }
        return -1;
    }
}
