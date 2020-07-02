package com.example.wrap.algorithm;

import com.beust.jcommander.internal.Lists;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 字符串回文判断 链表结构
 * @author 12232
 */
public class Palindrome {
    /**
     * 判断给定的字符串链表是否是回文
     * @param strings
     * @return
     */
    public boolean isPalindrome(LinkedList<String> strings){
        int size = strings.size();
        for (int i = 0; i < size ; i++) {
            if(i>(size/2+1)){
                break;
            }
            if (!strings.get(i).equals(strings.get(--size))) {
                return false;
            }
        }
        return true;
    }
    @Test
    public void testIsPalindrome(){
        LinkedList<String> strings =  Lists.newLinkedList();
        String string = "上海自来水来自海上";
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            strings.add(String.valueOf(aChar));
        }
        System.out.println(isPalindrome(strings));
    }
}
