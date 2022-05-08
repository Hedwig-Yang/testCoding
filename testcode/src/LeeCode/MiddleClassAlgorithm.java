package LeeCode;

import LeeCode.entity.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author:Z
 * @Date:2022/1/4 13:41
 * @Description: 中难度算法
 * @Version:1.0
 */
public class MiddleClassAlgorithm {

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 递归算法
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode resultList = new ListNode((l1.val+l2.val)%10);
        addNode(l1,l2,carry,resultList);
        return resultList;

    }
    public void addNode(ListNode a,ListNode b,int carry,ListNode currentResult){
        if(a.next != null || b.next != null){
            ListNode aNext = a.next == null ? new ListNode():a.next;
            ListNode bNext = b.next == null ? new ListNode():b.next;
            carry = (a.val+b.val+carry)>9 ? 1:0;
            int sum = (aNext.val+bNext.val+carry)%10;
            ListNode nextResultNode = new ListNode(sum);
            currentResult.next = nextResultNode;
            addNode(aNext,bNext,carry,nextResultNode);
        }else{
            if(((a.val+b.val+carry)>9 ? 1:0) == 1){
                ListNode nextResultNode = new ListNode(1);
                currentResult.next = nextResultNode;
            }
        }
    }

    /**
     * 给定一个字符串 s，请你找出其中不含有重复字符的最长子串的长度。
     * 存入hashmap，利用key值不重复，递归算法：控制出口、控制条件
     */
    @Test
    public void lengthOfLongestSubstring() {
        System.out.println(checkString("aab",1));
    }
    public int checkString(String s,int max){
        if(max >= s.length()){
            return max;
        }else{
            int i ;
            HashMap map = new HashMap();
            for( i = 0;i < s.length();i++){
                String substring = s.substring(i, i+1);
                if(!map.keySet().contains(substring)){
                    map.put(substring,"");
                }else{
                    s = s.substring(s.indexOf(substring)+1);
                    break;
                }
            }
            int size = map.keySet().size();
            max = max > size ? max:size;
            return checkString(s,max);
        }
    }

    /**
     * 获取一个字符串的最长回文子串
     * 暴力破解：两层循环，挨个判断，但是超时
     * @param s
     */
    public String longestPalindrome(String s) {
        if(s.length() == 1){
            return s;
        }
        int maxLength = 1;
        int startP = 0;
        int endP = 0;
        for(int i = 0; i<s.length();i++){
            for(int j = i+1;j< s.length();j++){
                if(j-i+1 > maxLength && palindromicCheck(s,i,j)){
                    maxLength = j-i+1;
                    startP = i;
                    endP = j;
                }
            }
        }
        return s.substring(startP,endP+1);
    }
    //双指针判断是否为回文子串并返回长度
    public boolean palindromicCheck(String s,int i,int j){
        char[] chars = s.toCharArray();
        boolean flag = true;
        while(i<=j){
            if(chars[i] == chars[j]){
                i++;
                j--;
            }else{
                flag = false;
                break;
            }
        }
        return flag;
    }



    @Test
    public void test(){
        System.out.println(longestPalindrome("ac"));
    }


    public static void main(String[] args) {
        String s = "cde";
        System.out.println(s.indexOf('d'));
        System.out.println(s.substring(2,3));
    }

}
