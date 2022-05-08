package LeeCode;

import org.junit.Test;

/**
 * @Author:Z
 * @Date:2022/1/4 23:24
 * @Description: hard级算法
 * @Version:1.0
 */
public class HardClassAlgorithm {

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 方法：归并排序
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //合并两个正序数列
        int leftLength = nums1.length;
        int rightLength = nums2.length;
        //定义指针
        int p1 = 0;
        int p2 = 0;
        //合成数组下标
        int i = 0;
        int[] resultArray = new int[leftLength+rightLength];
        while(p1 <= leftLength-1 && p2 <= rightLength-1 ){
            resultArray[i++] = nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        }
        while(p1 <= leftLength-1){
            resultArray[i++] = nums1[p1++];
        }
        while(p2 <= rightLength-1){
            resultArray[i++] = nums2[p1++];
        }
        if((leftLength+rightLength)%2 == 1){
            return resultArray[(leftLength+rightLength)/2+1];
        }else if((leftLength+rightLength)%2 == 0){
            return (resultArray[(leftLength+rightLength)/2]+resultArray[(leftLength+rightLength)/2-1])/2.0;
        }
        return 0;
    }




    @Test
    public void test(){
        //二进制右移一位，等同于除以二取整
        int x = 5 >>1;
        System.out.println(x);
    }
}
