package com.example.wrap.algorithm;

/**
 * see http://www.importnew.com/26482.html error
 */
public class Binary_Search {

    public static final int binary_search(int[] arr,int item) {
        int high = arr.length;
        int low = 0;
        int count = 0;
        while (true) {
            count++;
            int mid = (high + low) / 2;
            int v = arr[mid];
            if(v == item){
                System.out.println("执行次数"+count+" 下角标："+mid);
                return mid;
            }else if (v > item){
                high = mid ;
            }else if (v < item){
                low = mid ;
            }else if (count > arr.length){
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        // 5   :2
        //low : 0 ;high : 2

        int [] arr  = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        for (int i = 0; i <19 ; i++) {
            binary_search(arr,i+1);
        }
    }

}
