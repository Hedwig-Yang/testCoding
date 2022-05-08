package LeeCode;

/**
 * @Author:Z
 * @Date:2021/12/31 17:27
 * @Version:1.0
 */
public class SimpleClassAlgorithm {

    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i= 0;i<nums.length;i++){
            for(int j=i+1;j < nums.length;j++){
                if((nums[i]+nums[j]) == target){
                    result[0] = i;
                    result[1] = j;
                 }
            }
        }
        return result;
    }
}
