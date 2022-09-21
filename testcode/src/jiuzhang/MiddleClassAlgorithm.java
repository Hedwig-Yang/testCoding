package jiuzhang;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author:Z
 * @Date:2022/1/4 13:41
 * @Description: 中难度算法
 * @Version:1.0
 */
public class MiddleClassAlgorithm {


    //-------------------------------------------------双指针 & 回文子串-------------------------------------------------
    /**
     * 获取字符串中的回文子串（palindromic substring） 方法一
     * 时间复杂度：n的三次方
     * 循环嵌套暴力破解法：采用对字符串长度逆序遍历方式检查字 串是否是回文子串，再做遍历。
     * @param inputString
     * @return
     */
    public static String getPalindromic1(String  inputString){
        if(inputString == null || "".equals(inputString)){
            return "";
        }

        for(int length = inputString.length(); length >0; length--){
            for(int start = 0; start + length <= inputString.length(); start++){
                if(isPalindromic(start, start+length-1, inputString)){
                    return inputString.substring(start, start+length);
                }
            }
        }
        return "";
    }

    public static boolean isPalindromic(int left, int right, String inputSring){
        boolean flag = false;
        while(inputSring.charAt(left) == inputSring.charAt(right)){
            left++;
            right--;
            if(left >= right){
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 获取字符串中的回文子串 方法二
     * 时间复杂度：n*n
     * @param inputString
     * @return
     */
    public static String getPalindromic2(String  inputString){
        String longest = "";
        if(inputString == null || "".equals(inputString)){
            return "";
        }

        for(int start = 0; start < inputString.length(); start++){
            //odd
            int left = start;
            int  right = start;
            String tmpString = getTmpLongest(left, right, inputString);
            longest = longest.length() >tmpString.length() ? longest : tmpString;

            //even
            left = start;
            right = start+1;
            tmpString = getTmpLongest(left, right, inputString);
            longest = longest.length() >tmpString.length() ? longest : tmpString;
        }
        return longest;
    }

    public static String getTmpLongest(int left, int right, String inputString){
        String longest = "";
        while(left >= 0 && right < inputString.length() && inputString.charAt(right) == inputString.charAt(left)){
            longest = inputString.substring(left,right+1);
            right++;
            left--;
        }
        return longest;
    }

    /**
     * 动态规划获取回文子串
     * @param inputString
     * @return
     */
    public static String getPalindromic3(String inputString){
        if(inputString == null || "".equals(inputString)){
            return "";
        }

        int n = inputString.length();
        boolean[][] isPalindrome = new boolean[n][n];

        int longest = 1,start = 0;
        for(int i = 0; i < n; i++){
             isPalindrome[i][i] = true;
        }

        for(int i = 0; i < n-1; i++){
            isPalindrome[i][i+1] = inputString.charAt(i) == inputString.charAt(i+1);
            if(isPalindrome[i][i+1]){
                start = i;
                longest = 2;
            }

        }

        for(int i = n-1; i >= 0; i--){
            for(int j = i+2; j < n; j++){
                isPalindrome[i][j] = isPalindrome[i+1][j-1] && inputString.charAt(i) == inputString.charAt(j);
                if(isPalindrome[i][j] && j - i + 1 > longest){
                    start = i;
                    longest = j-i+1;
                }
            }
        }

        return inputString.substring(start,start+longest);

    }

    /**
     * Implement strStr(略)
     * 在一个字符串中查询另外一个字符串第一次出现的位置
     * method_01:暴力破解，for循环依次截取targetString长度的字符串作比较。
     * method_02:Rabin-Karp,将字符串使用hash算法转换成整数，再做比较。
     *  关键是练习进制转换法实现hash函数
     */


    /**
     * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
     * 判断一个字符串删除一个字符后还是不是回文子串
     * 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
     */
    public static boolean validPalindrome(String s) {
        if(s == null || "".equals(s)){
            return false;
        }

        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                break;
            }
            left++;
            right--;
        }
        return isPalindrome(s, left, right-1) || isPalindrome(s, left+1, right);
    }

    public static boolean isPalindrome(String inputString, int left, int right){
        while(left < right){
            if(inputString.charAt(left) != inputString.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //----------------------------------------快速排序 & 快速查找 & 归并排序----------------------------------------------

    /**
     * 快速排序Qucik Sort
     * 思想：先整体有序再局部有序  根据中间数整体排序左右分区 -> 拆开后再各自排序
     * 平均时间复杂度：O(nlogn)
     * @return
     */
    public int[] sortArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }

        quickSort(nums, 0, nums.length-1);
        return nums;
    }

    public void quickSort(int[] nums, int start, int end){
        if(start >= end){
            return;
        }

        int left = start;
        int right = end;
        //1、privot、num[start]、nums[end] get value not index;
        int pivot = (nums[start] + nums[end])/2;

        while(left <= right){
            //2、标号比较都带等于号：<= 或者 >=
            //3、数值比较都不带等号
            while(left <= right && nums[left] < pivot){
                left++;
            }
            while(left <= right && nums[right] >pivot){
                right--;
            }
            if(left <= right){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        //4、从while循环出来后，left > right,在右边，所以；递归时注意下标。
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }

    /**
     * quickSearch 快速查找
     * 从数组中获取第K个大的数，先使用一轮快速排序，将数组分成左右两个部分,再确定需要找的数在左右哪一个数组
     * 平均时间复杂度： O(n)
     */
    public static int quickSearch(int[] nums, int start, int end, int k){
        if(start == end){
            return nums[start];
        }
        int left = start;
        int right = end;
        int pivot  = (nums[start] + nums[end])/2;
        while(left <= right){
            while(left <= right && nums[left] > pivot){
                left++;
            }
            while(left <= right && nums[right] < pivot){
                right--;
            }
            if(left <= right){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        if(start + k - 1 <= right){
            return quickSearch(nums, start, right, k);
        }
        if(start + k -1 >= left){
            //left - start表示左边有几位数
            return quickSearch(nums, left, end, k - (left - start));
        }
        return nums[right + 1];
    }

    /**
     * Merge Sort
     * 归并排序
     * 时间复杂度O(nlogn)
     * 过程： 拆开排序 -> 合并
     */
    public int[] arraySort(int[] nums){
        if(nums == null || nums.length == 0){
            return null;
        }
        int start = 0;
        int end = nums.length - 1;
        return mergeSort(nums, start, end);
    }

    public int[] mergeSort(int[] nums, int start, int end){
        if(start == end){
            return new int[]{nums[start]};
        }
        int[] leftArray = mergeSort(nums, start, (start + end)/2);
        int[] rightArray = mergeSort(nums, (start + end)/2+1, end);
        return merge(leftArray,rightArray);
    }

    public int[] merge(int[] leftArray, int[] rightArray){
        int[] tmp = new int[leftArray.length + rightArray.length];
        int lPointer = 0;
        int rPointer = 0;
        int index = 0;
        while(lPointer <= leftArray.length-1 && rPointer <= rightArray.length-1){
            if(lPointer <= leftArray.length-1 && rPointer <= rightArray.length-1 && leftArray[lPointer] <= rightArray[rPointer]){
                tmp[index] = leftArray[lPointer];
                lPointer++;
                index++;
            }
            if(lPointer <= leftArray.length-1 && rPointer <= rightArray.length-1 && leftArray[lPointer] > rightArray[rPointer]){
                tmp[index] = rightArray[rPointer];
                rPointer++;
                index++;
            }
        }
        if(lPointer >= leftArray.length){
            while(rPointer <= rightArray.length-1){
                tmp[index] = rightArray[rPointer];
                rPointer++;
                index++;
            }
        }
        if(rPointer >= rightArray.length){
            while(lPointer <= leftArray.length-1){
                tmp[index] = leftArray[lPointer];
                lPointer++;
                index++;
            }
        }
        return tmp;
    }

    /**
     * Merge Sort
     * 归并排序 (优化版：减少空间复杂度，简化数组合并)
     * 时间复杂度O(nlogn)
     * 过程： 拆开排序 -> 合并
     */
    public static int[] arraySortImprove(int[] nums){
        if(nums == null || nums.length == 0){
            return null;
        }
        int start = 0;
        int end = nums.length - 1;
        //1、每次递归调用都使用同一个临时数组，减少空间复杂度
        int[] tmpArr = new int[nums.length];
        mergeSortImprove(nums, start, end, tmpArr);
        return nums;
    }

    public static void mergeSortImprove(int[] nums, int start, int end, int[] tmpArr){
        if(start >= end){
            return ;
        }
        int mid = start + (end - start) / 2;
        mergeSortImprove(nums, start, mid, tmpArr);
        mergeSortImprove(nums, mid + 1, end,tmpArr);
        mergeImprove(nums, start, mid,end, tmpArr);
    }

    public static void mergeImprove(int[] arr, int start, int mid, int end, int[] tmp){
        int leftIndex = start;
        int rightIndex = mid + 1;
        int index = leftIndex;
        while(leftIndex <= mid && rightIndex <= end){
            if(arr[leftIndex] < arr[rightIndex]){
                tmp[index++] = arr[leftIndex++];
            }else{
                tmp[index++] = arr[rightIndex++];
            }
        }
        while(leftIndex <= mid){
            tmp[index++] = arr[leftIndex++];
        }
        while(rightIndex <= end){
            tmp[index++] = arr[rightIndex++];
        }
        for(int i = start; i <= end; i++){
            arr[i] = tmp[i];
        }
    }

    //---------------------------------------------------二分法---------------------------------------------------------

    /**
     * 基于递归实现二分法查找目标值，返回其下标（默认升序数组）
     * 递归三要素：1、定义函数功能；2、控制出口；3、递归拆分
     * 时间复杂的：O(logn)
     */
    public int binarySearch1(int[] nums, int start, int end, int target){

        int pivot = (start + end)/2;
        if(target == nums[pivot]){
            return pivot;
        }
        //需要有star > end的判断，不然会一直进入递归，造成栈溢出
        if(start > end){
            return -1;
        }
        if(target > nums[pivot]){
            return binarySearch1(nums, pivot+1, end, target);
        }
        if(target < nums[pivot]){
            return binarySearch1(nums, start, pivot - 1, target);
        }
        return -1;
    }


    /**
     * 非递归的方式实现二分法找目标值，返回其下标（默认升序数组）
     * 时间复杂的：O(logn)
     * @param nums 数组
     * @param target 目标数
     * @return 目标数下标
     */
    public int binarySearch2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while(start <= end){
            int pivot = (start + end)/2;
            if(nums[pivot] == target){
                return pivot;
            }
            if(nums[pivot] < target){
                start = pivot + 1;
                continue;
            }
            if(nums[pivot] > target){
                end = pivot - 1;
            }
        }
        return -1;
    }


    /**
     * 非递归二分法查询有序数组中目标值第一次 / 最后一次出现的下标（二分法通用模板）
     * 时间复杂度：O(logn)
     * @param nums 指定有序整数数列
     * @param target 目标值
     * @return
     */
    public static int getLastBybBnarySearch(int[] nums, int target) {

        if(nums == null || nums.length == 0){
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        //1、start+1的目的是在start+1 = end的时候就退出，避免因为[1,1]的情况还进入循环，造成死循环。
        while(start + 1 < end){
            //2、避免int溢出
            //int pivot = (start + end)/2;
            int pivot = start + (end - start) /2;
            if(nums[pivot] == target){
                start = pivot;  //在右半区域寻找target值最后一个出现的位置
               //end = pivot;   //在左半区域寻找target值第一个出现的位置
            }
            if(nums[pivot] < target){
                //3、可以去掉+1、-1，不影响结果
                //start = pivot + 1;
                start = pivot;
            }
            if(nums[pivot] > target){
                //end = pivot - 1;
                end = pivot;
            }
        }
        //4、start + 1 = end时候就退出循环了，此时start和end对应的两个值需要做判断。
        if(nums[end] == target){   //找最后一个target位置就先检查end
            return end;
        }
        if(nums[start] == target){ //找第一个target位置就先检查start
            return start;
        }

        return -1;
    }

    /*
     * 二分查找：Find K Closest Elements在排序数组中找最接近的K个数
     *      给一个目标数 target, 一个非负整数 k, 一个按照升序排列的数组 A。在A中找与target最接近的
     * k个整数。返回这k个数并按照与target的接近程度从小到大排序，如果接近程度相当，那么小的数排在前面。
     * 思路：
     *      先采用二分查找算法，获取目标值插入的位置，即大于等于目标值的最小值的右边，或小于等于
     * 目标值的最大值的右边；然后以插入位置为中心采用反向双指针算法，依次获取接近的K个值。
     */
    public int getNearestBiggerIndex(int[] arr, int target){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        int pivot = start + (end - start) / 2;
        while(start + 1 < end){
            if(target == arr[pivot]){
                start = pivot;
            }
            if(target < arr[pivot]){
                end = pivot;
            }
            if(target > arr[pivot]){
                start = pivot;
            }
        }
        return (target - arr[start] < arr[end] - target) ? start : end;
    }

    public int[] getNearestKNums(int index, int[] arr, int k, int target){
        if(arr.length < k){
            return null;
        }
        int left = index - 1;
        int right = index;
        int[] resultArr = new int[k];
        for(int i = 0; i < k; i++){
            if(left > 0 && right < arr.length){
                if( target - arr[left] < arr[right] - target){
                    resultArr[i] = arr[left];
                    left --;
                }else{
                    resultArr[i] = arr[right];
                    right++;
                }
            }
            if(left < 0){
                resultArr[i] = arr[right];
                right++;
            }
            if(right >= arr.length){
                resultArr[i] = arr[left];
                left--;
            }
        }
        return resultArr;
    }

    //---------------------------------------------二叉树遍历：BFS------------------------------------------------------

    /*
     * BFS使用场景：
     *      （能用BFS，一定不要用DFS，除非面试官要求）
     *      1、连通块问题（Connected Component）：
     *          ● 通过一个点找到途中连接的所有的点.
     *          ● 非递归方式找所有方案【20章】
     *      2、分层遍历(Level Order Traversal)：
     *          ● 图的层次遍历。
     *          ● 简单图最短路径。
     *      3、拓扑排序(Topological Sorting)：
     *          ● 求任意拓扑序。
     *          ● 求是否有拓扑序。
     *          ● 求字典最小拓扑序。
     *          ● 求是否唯一拓扑序。
     * BFS是否需要分层？怎样分层？
     *      当要求遍历结果按照分层输出的时候，需要分层。
     *      当要求求接最短路劲长度时候，可以每层使用length++，来记录长度，使用HashSet去重，
     *          也可以直接使用HashMap直接去重，并记录长度
     *      直接在while(!queue.isEmpty())循环以及for(Node node : neighbors)循环中间加一层
     *          for(int i = 0; i < levelLength; i++)循环。
     */

    /**
     * 分层遍历二叉树--单队列的方式实现BFS（Breath First Search）
     * 利用队列先进先出的特性，在一个队列存储上下两层节点。
     * 上层节点一边遍历，添加下层节点到队列，一边输出值到当前层List，一边弹出节点
     * @param root 根节点
     * @return 自上而下层遍历结果
     */
    public List<List<Integer>> levelOrderByOneQueue(TreeNode root) {
        ArrayList result = new ArrayList();
        if(root ==  null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //1、将第一层的节点放入队列
        queue.offer(root);
        //2、队列非空的时候向下拓展
        while(!queue.isEmpty()){
            //每个level用来存这一层的所有点的数据
            ArrayList<Integer> level = new ArrayList<>();
            //取出当前层节点的数量
            int size = queue.size();
            //根据上层节点，拓展出下层节点
            for(int i = 0; i < size; i++){
                //返回队列首结点
                TreeNode head = queue.poll();
                //记录当前节点值。
                level.add(head.val);
                if(head.left != null){
                    queue.offer(head.left);
                }
                if(head.right != null){
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    /**
     * 分层遍历从上倒下遍历二叉树--双队列的方式实现BFS（Breath First Search）
     * 相比单单列，思路更清晰，此时不需要在一个队列存放两层节点，也就不需要先进先出，所以可以不用队列来写
     * @param root 根节点
     * @return 自上而下层遍历结果
     */
    public List<List<Integer>> levelOrderByTwoQueue(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> upperList = new ArrayList<>();
        if(root == null){
            return result;
        }
        upperList.add(root);
        while(!upperList.isEmpty()){
            result.add(toIntegerList(upperList));
            List<TreeNode> lowerQueue = new ArrayList<>();
            for(TreeNode node : upperList){
                if(node.left != null){
                    lowerQueue.add(node.left);
                }
                if(node.right != null){
                    lowerQueue.add(node.right);
                }
            }
            upperList = lowerQueue;
        }
        return result;
    }

    public List<Integer> toIntegerList(List<TreeNode> nodeList){
        List<Integer> resultList = new ArrayList<>();
        for(TreeNode node : nodeList){
            resultList.add(node.val);
        }
        return resultList;
    }

    /**
     * 使用哨兵节点的方式实现BFS
     * 哨兵节点永远指向头节点，在队列中将各层节点用空节点分开，队列头poll到空节点就在队列尾加上一个空节点
     * 哨兵模式相比单队列的优势是减少一层for循环的嵌套，通过是否遇到null值判断当前这一层是否遍历结束。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderByDummyNode(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null){
            return result;
        }
        queue.add(root);
        queue.offer(null);
        ArrayList<Integer> levelVal = new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            //遇到空说明这一层结束了。
            if(node == null){
                //添加这一层的所有结果到result
                result.add(levelVal);
                //队列为空说明遍历完了。
                if(queue.size() == 0){
                    break;
                }
                levelVal = new ArrayList<>();
                queue.offer(null);
                continue;
            }
            //遇到非空节点，正常添加
            levelVal.add(node.val);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return result;
    }

    //------------------------------------------图遍历：BFS遍历与查询-----------------------------------------------------

    /*
     * 树是没有环的图
     * 图 >= 树 >= 二叉树
     * 简单图：没有方向、没有权重、两点间最多只有一条路径、一个点没有一条边直接连着自己
     *
     * 图可能存在环，导致同一节点重复进入队列，但是重复BFS是没有意义的：
     *      1、对于连通块问题，不能带来新的节点
     *      2、对于最短路径问题，不可能产生最短路径
     *      为了避免重复BFS，可使用HashMap、HashSet去重
     */
    /**
     * 图BFS模板
     * ArrayDeque类型的 queue:存放两层节点用于实现while循环
     * HashMap类型的 distance用于存放所有遍历到的节点（不重不漏），以及到根节点的距离
     */
    public List<Node> GraphBfs(Node root){
        /*-------1、初始化-------*/
        //双端队列；Java中建议使用ArrayDeque，用于保存所有点，不重不喽
        // 相比LinkedList，数组比链表更快。
        Queue<Node> queue = new ArrayDeque();
        //distance作用：1、判断节点是否已经访问过；2、记录离起始位置的距离。
        HashMap<Node, Integer> distance = new HashMap<>();
        distance.put(root,0);
        queue.offer(root);
        /*-------2、循环遍历队列-------*/
        while(!queue.isEmpty()){
            Node node = queue.poll();
            for(Node neighbor : node.getNeighbours()){
                if(distance.containsKey(neighbor)){
                    continue;
                }
                queue.offer(neighbor);
                distance.put(neighbor, distance.get(node) + 1);
            }
        }
        return new ArrayList<>(distance.keySet());
    }


    /*
     * Clone Graph 克隆图
     * 找到所有点 -> 复制所有点 -> 复制所有边
     * 思路：
     *    1、BFS获取所有节点：
     *       使用ArrayDeque创建队列添加层节点遍历，使用HashMap或者HashSet去重，返回一个List。
     *    2、复制所有节点：
     *       使用Map创建旧节点与新节点的映射，map.put(node, new Node(node.val))，返回一个HashMap
     *    3、复制所有边：
     *       遍历方法一的旧结点list，获取每一个旧节点的neighbors，根据方法2的map找到旧节点对应的
     *       新节点，再遍历旧节点的neighbors，通过映射找到对应的新节点，然后作为neighbor添加到，
     *       新节点的neighbors中，相当于两层for循环：
     *          遍历旧结点获取对应的neighbors。
     *          遍历neighbors，通过map映射到新的节点后，添加到新节点的neighbors中。
     */

    /*
     * 单词接龙（Word Ladder）
     * 给出两个单词（start和end）和一个字典，找出从start到end的最短转换序列，输出最短序列的长度。
     * 规则：
     *      每次只能改变一个字母。变换过程中的中间单词必须在字典中出现。(起始单词和结束单词可以不
     *      出现在字典中)
     * 思路：创建树，使用BFS宽度优先搜索最短路径（记得将end节点添加到dict）
     *      1、将start节点添加到队列，开始非空条件下的while循环
     *      2、循环内获取节点的neighbors节点（也就是当前节点单词可转换的下一个单词）
     *         循环遍历neighbors，添加（neighbor, lenght）到HashMap添加到队列,HashMap的作用是
     *         记录节点路径长度，通过Key唯一的属性，避免遍历重复节点。
     *         当遍历某个节点value为end单词时，结束遍历返回这个neighbor的路径长度length。
     *      3、（关键）怎样获取到节点的neighbors，也就是某个单词改变一个字母后，成为的能在字典找到的单词集合
     *          ● 方法一：双层for循环，第一层遍历字典N个单词，第二层遍当前节点单词的长度L，从而获取n个
     *                   单词中长度为L且，仅有一个字母与目标单词不同的单词作为neighbors，时间复杂度O(N*L)
     *          ● 方法二：双层for循环，第一层根据目标节点对应的单词转成字符数组的长度遍历，第二层对26个小写
     *                   英文字母遍历，依次替换字符数组中的一个字符，然后根据替换后的字符数组产生新的单词，
     *                   判断产生的新的单词是否在字典内，如果在就添加到neighbors。对于需要替换的字母与单词
     *                   当前位置字母相同的情况，可以直接跳过。由于替换单个字符然后产生新的字符串时间复杂度
     *                   为字符长度L，所以总的时间复杂度为O(L*L)
     *          综上：单词长度较长的话，用方法一；字典单词较多的情况用方法二。
     */

    /*
     * 数字岛（Number of Lslands）矩阵BFS
     * 规则：
     *      给一个 01 矩阵，求不同的岛屿的个数。0 代表海，1 代表岛，如果两个 1 相邻，
     *      那么这两个 1 属于同一个岛。我们只考虑上下左右为相邻。
     * 思路：
     *      1、双层for循环对横纵坐标的点逐行遍历。
     *      2、如果找到一个1，岛屿数量增1，把所有跟这个1相连的1都找出来。所有这些相连的1代表一个岛。
     *      3、使用矩阵坐标变换数组deltaX，deltaY记录坐标偏移量。
     *      关键：(每次发现一个新岛屿都是一个完整的BFS)
     *          ● 每次发现第一个为1的点后，以此为root开始BFS，节点的上下左右都是neighbors，斜着不算。
     *          ● 此时无法使用HashMap去重，所以采用同样的N*N的二维boolean数组，访问过且为1的节点为ture；
     *          ● 使用isVaild()子函数避免遍历,数组下标越界以及坐标对应的boolean值为true的都不需要再遍历。
     *          ● 双层For循环过程中只需要根据节点对应boolean值判定是否需要遍历。
     *          ● BFS过程中根据子函数isVail()判断是否需要遍历。
     */

    /*
     * Knight Shortest Path
     * 国际象棋中的骑士最短路径问题（矩阵BFS）
     * 规则：
     *      给定骑士在棋盘上的初始位置(一个2进制矩阵0表示空，1表示有障碍物)，找到到达终点的最短路线，
     *      返回路线的长度。如果骑士不能到达则返回 -1。骑士只能在没有障碍的情况下走2x3的矩形对角线。
     * 思路：
     *      常规的BFS，类似于数字岛。
     *      1、创建偏移常量数组int[] X_OFFSET存储骑士单次跳跃在8个方向上的x轴偏移量,同理创建Y_OFFSET
     *         存储y轴偏移量。
     *      2、需要返回从骑士最短跳跃次数，需要使用HashMap<Point, Integer>存储节点的路径长度，同时去重。
     *      3、两层循环遍历：第一层while队列不为空，第二层遍历跳跃后的8个方向上的neighbor节点，去除下标
     *         越界或者节点对应二维数组值为1（障碍）的节点，再根据HashMap去除重复遍历的节点。
     *      4、将合格节点存入queue，将节点以及路径长度存入HashMap
     *      5、遍历节点与终点的x、y轴坐标一致的情况下，返回路径长度，终点判定要放在while循环内，for循环外
     *         才能兼容起始节点与终点一致的情况。
     *      优化：将二维坐标转化成一维坐标，作为HashMap的key值，减少空间复杂度
     */

    /**
     * 图BFS总结：
     * 1、双层循环：while（队列非空）嵌套for（Node node： neighbors）
     * 2、根据实际情况neighbors可能存在由节点的属性中，也可能需要自己计算（如坐标的情况）
     * 3、根据是否用到路径长度选择HashMap或者HashSet
     * 4、节点必须去重并满足其他边缘条件才能参与遍历，否则“continue”
     * 5、节点必须同时添加到Queue以及HashMap/HashSet,避免出现重复添加。
     * 6、BFS查询或返回最小路径长度的问题，对于结果的if()判定放在while()以及for()中间最稳，
     *    直接放在for()里面会忽略start == end的情况。
     */


//-------------------------------------------------拓扑排(BFS)-----------------------------------------------------------
/*
 * 拓扑排序(Topological Sorting):
 *      反应的是一种依赖关系大小的的排序,查找过程就是将途中离散的依赖关系，转换成连续的依赖关系。
 * 入度(In-degree):
 *      有向图（Directed Graph）中指向当前节点的点的个数（或指向当前节点的边的条数）
 * 算法描述：
 *      1. 统计每个点的入度
 *      2. 将每个入度为 0 的点放入队列（Queue）中作为起始节点
 *      3. 不断从队列中拿出一个点，去掉这个点的所有连边（指向其他点的边），其他点的相应的入度 - 1
 *      4. 一旦发现新的入度为 0 的点，丢回队列中
 * 拓扑排序并不是传统的排序算法
 *      一个图可能存在多个拓扑序（Topological Order），也可能不存在任何拓扑序
 * 拓扑顺序是否唯一判定：
 *      只需要从Queue队列中取数时判定，队列内节点数 > 1。
 * 图结构 + 有向/有依赖关系 + 无环 => 拓扑结构
 */

    /**
     * 课程表 Course Schedule II (问是否存在拓扑排序)
     * 描述：
     *    你需要去上n门九章的课才能获得offer，这些课被标号为 0 到 n-1，有一些课程需要“前置课程”，
     *    比如如果你要上课程0，你需要先学课程1，我们用一个匹配来表示他们： [0,1]，给你课程的
     *    总数量和一些前置课程的需求，返回你为了学完所有课程所安排的学习顺序。可能会有多个正确
     *    的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
     * 输入:
     *    n = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     * 输出:
     *    [0,1,2,3] or [0,2,1,3]
     * 思路：
     *    1、统计每个点的入度。
     *    2、将每个入度为0的点放入队列(Queue)中作为起始节点。
     *    3、不断从队列，拿出一个点，去掉这个点的所有连边(指向其他点的边)，其他点的入度-1。
     *    4、一旦发现新的入度为 0 的点，就丢回到队列中。
     */
    public int[] findorder(int numCourses, int[][] prerequisites){
        //构件图: List数组graph代表 [先修课->多个后修课] 的映射关系; int[]数组inDegree记录各点入度信息
        List<Integer>[] graph = new ArrayList[numCourses];

        //图的初始化：每个先修课->空的后修课List
        for(int i = 0; i < numCourses; i++){
             graph[i] = new ArrayList<Integer>();
        }

        //1、统计每个点的入度，并构建图
        int[] inDegree = new int[numCourses];
        for(int[] edge : prerequisites){
            //先上edge[1]，然后才能上edge[0]
            graph[edge[1]].add(edge[0]);
            inDegree[edge[0]]++;
        }

        //2、将每个入度为0的点方到队列(Queue)中作为起始点
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < inDegree.length; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        //记录已修课程的数量
        int numChoose = 0;
        //记录拓扑的顺序
        int[] topoorder = new int[numCourses];

        //3.不断从队列中拿出一个点，逻辑上去掉这个点的所有连边（指向其他点的边）即其他点相应的入度-1
        while(!queue.isEmpty()){
            int nowPos = queue.poll();
            topoorder[numChoose] = nowPos;
            numChoose++;
            for(int i = 0; i < graph[nowPos].size(); i++){
                int nextPos = graph[nowPos].get(i);
                //当前点的邻居入度-1，表示表示每个后修课的先修课已经完成了
                inDegree[nextPos]--;
                //4、一旦发现新的入度为0的点，就放回队列中
                //表示有一门后修课的所有先修课已经完成了，可以被修了。
                if(inDegree[nextPos] == 0){
                    queue.add(nextPos);
                }
            }
        }
        //如果所有的课程已经被修过，那么返回拓扑顺序，否则返回空。
        return (numChoose == numCourses) ? topoorder : new int[0];
    }

    /**
     * BFS总结
     * 二叉树BFS
     *      节点已知、neighbors2个，单层 while(!queue.isEmpty())循环
     * 图BFS
     *      节点已知，neighbors个数未知，需多一层循环遍历。一层对队列的while()循环，一层对neighbors的for-each()循环遍历,
     *      使用HashMap或者HashSet去重，需要记录长度时候，用HashMap<Node,Integer>()
     * 矩阵BFS
     *      节点未知，neighbors个数未知，需要根据坐标定义节点类class，neighbors也需要自己根据不同方向的x、y轴的坐标偏移
     *      计算保留满足条件的neighbor节点。最后在是while循环--for循环，注意坐标边界与节点去重。
     * 拓扑BFS
     *      拓扑，也就是有向图的BFS，因为节点有向，所以需要单独记录节点有向数据，同时需要在for循环中修改入向数据。
     *      经典案例：选课（先修课->后修课），
     *          1、使用使用List[]或Map<Node,Set<Node>> graph记录当前节点和指向的neighbors节点,
     *             使用int[]]或Map<Node,Integer> inDegree记录每个节点的入向的数量。
     *          2、根据提供的参数，遍历获取无向节点添加到queue。
     *          3、开始while(!queue.isEmpty())循环，再for-each()循环每个neighbor，修改遍历到的这个neighbor在inDegree
     *             中的记录的“入向”数量，对修改后入向数量为0的neighbor，添加到queue。
     *          4、添加结果的操作放在for循环内的话会忽略根节点，所以结果在while与for中间将节点添加到结果。最后返回结果。
     *          待优化：创建节点Class对象，将入向数量作为数据添加到类中。省略了inDegree这个参数，简化代码(增加了空间复杂度)
     */







//-----------------------------------------------二叉树遍历：DFS---------------------------------------------------------

    /* 深度优先搜索算法就是回溯法
     * 什么叫回溯操作：
     *      递归函数在回到上一层递归调用处的时候，一些参数需要改回到调用前的值，这个操作就是回溯。
     *      注意：基本变量在函数间传值自动回溯；引用变量函数间传递引用，需要手动回溯，恢复调用前的状态！
     * 回溯分类：
     *      1、遍历法：一个小人拿着一个记事本走遍所有的节点，通常会用到一个全局变量或者是共享参数。
     *            每一个方法都接收结果集引用，添加新的结果到结果集中。
     *      2、分治法：分配小弟去做子任务，自己进行结果汇总，通常将利用 return value 记录子问题结果。
     *            每一个方法自己创建结果集，自己返回结果集（子任务和总任务内容上相同，只是大小规模不同）。
     *             典型应用：归并排序、快速排序
     */
    /**
     * DFS遍历获取所有节点（深度优先搜索算法就是回溯法）
     * @param localNode 当前节点
     * @param nodes 当前节点以前记录的所有节点
     * @return
     */
    public List<TreeNode> findNodes(TreeNode localNode, List<TreeNode> nodes){
        if(localNode == null){
            return nodes;
        }
        nodes.add(localNode);
        //注意引用类型除了String外，都是传引用；基本类型都是传值。
        // 所以此处可以不接收返回的索引指，因为索引值不变；
        findNodes(localNode.left,nodes);
        findNodes(localNode.right,nodes);
        return nodes;
    }

    /**
     * DFS遍历获取所有路径（深度优先搜索算法就是回溯法）
     * @param localNode 当前节点
     * @param path 包含当前节点后，已走过的路径。
     * @param paths 统计过的所有路径。
     * @return
     */
    public List<String> findPaths(TreeNode localNode, String path, List<String> paths){
        if(localNode == null){
            return paths;
        }
        if(localNode.left == null && localNode.right == null ){
            paths.add(path);
            return paths;
        }
        if(localNode.left != null){
            findPaths(localNode.left, path +"->"+localNode.left.val, paths);
        }
        if(localNode.right != null){
            findPaths(localNode.right, path +"->"+localNode.right.val, paths);
        }
        return  paths;
    }

    /**
     * DFS遍历获取所有路径（优化版）
     * 函数间传递String是传值，相当于复制一边，需要浪费大量空间,可使用StringBulider或者ArrayList存储path进行优化。
     * @param localNode 当前节点
     * @param path 包含当前节点后，已走过的路径。
     * @param paths 统计过的所有路径。
     * @return
     */
    public List<String> findPathsImprovement(TreeNode localNode, StringBuilder path, List<String> paths){
        //控制出口
        if(localNode == null){
            return paths;
        }
        if(localNode.left == null && localNode.right == null ){
            //叶子节点根据实际情况判断是否需要控制出口，否则的话就会丢失一个添加path操作
            paths.add(path.toString());
            return paths;
        }

        //递归条件
        if(localNode.left != null){
            int length = path.length();
            path.append("->").append(localNode.left.val);
            findPathsImprovement(localNode.left, path, paths);
            //调用后回溯
            path.delete(length,path.length());
        }
        if(localNode.right != null){
            int length = path.length();
            path.append("->").append(localNode.right.val);
            findPathsImprovement(localNode.right, path, paths);
            path.delete(length,path.length());
        }
        return  paths;
    }


    /**
     * 判断二叉树是否为平衡二叉树（任意节点，的左右节点高度差不超过1）
     * 任意节点的高度 = max(左节点高度、右节点高度) + 1；
     * 使用分治法的优点：可以无脑拆分递归，重点关注在递归出口以及合并结果集
     * @param root 根节点
     * @return
     */
    public Result binaryTreeIsBalance(TreeNode root){
        //控制递归出口
        if(root == null){
            //此时不单独处理叶子节点，结果不变，所以不单独处理。
            return new Result(true, 0);
        }
        //递归拆分
        Result leftResult = binaryTreeIsBalance(root.left);
        Result rightResult = binaryTreeIsBalance(root.right);
        //分治法的关键：合并结果集
        return getResult(leftResult,rightResult);
    }

    public Result getResult(Result leftResult, Result rightResult){
        if(leftResult.isBalance && rightResult.isBalance){
            if(Math.abs(leftResult.hight - rightResult.hight) <=1){
                int hight = Math.max(leftResult.hight, rightResult.hight) + 1;
                return new Result(true, hight);
            }
        }
        return new Result(false, -1);
    }

//---------------------------------------------二叉树遍历：中序遍历-------------------------------------------------------

   /**
     * 二叉树非递归方式实现中序遍历：
     *      如：手动实现二分查找树迭代器（Binary Search Tree Iterator）
     *      注意：二分查找树的中序遍历是有序的从小到大排好序的，因此对于二分查找树来说，只有中序遍历是有意义的。
     * 中序遍历：
     * 迭代器（Iterator）关键有2个方法：hasNext()和next()方法。
     * 递归 → 非递归，意味着自己需要控制原来由操作系统控制的“栈”的进进出出，所以需要手动实现栈用于记录根节点到当前节点的路径
    * @return
    */
   public List inorderTraversal(TreeNode root) {
       BSTIterator bi = new BSTIterator(root);
       List result = new ArrayList<Integer>();
       while(bi.hasNext()){
           result.add(bi.next().val);
       }
       return result;
   }

    /**
     * 递归方式实现中序遍历
     * @return
     */
   public List<Integer> recursionInorderTraversal(TreeNode root, List<Integer> resultList){
       if(root == null){
           return resultList;
       }
       if(root.left != null){
           recursionInorderTraversal(root.left, resultList);
       }
       resultList.add(root.val);
       if(root.right != null){
           recursionInorderTraversal(root.right, resultList);
       }
       return resultList;
   }


//--------------------------------------------------组合类 DFS-----------------------------------------------------------

    /*
     * 给定一个具有不重复数字的列表，返回其所有可能的不重复子集，要求任意子集从小到大排序输出。
     * 思路：
     *      要求“找到所有可能的xxx”，一般99%以上的可能采用DFS解决，考点在于写递归
     */
    @Test
    public void test(){
        int[] nums = {1,2};
        System.out.println(subsets(nums));
    }

    /**
     * 方法1：BFS；广度优先搜索，求所有子集
     * 1、遍历结果集的各个集合并深度拷贝到新的集合中
     * 2、依次添加下标index对应的整数到各个新的集合中，
     * 3、最后再添加新的集合到结果集
     */
    public List<List<Integer>> subsetMy(int[] nums){
        if(nums == null){
            return null;
        }
        Arrays.sort(nums);
        List<List<Integer>> resultSet = new LinkedList<>();
        //只要nums非空，空集一定是子集之一
        resultSet.add(new LinkedList<>());
        getSubsets(nums, 0, resultSet);
        return resultSet;
    }

    /*
     * 递归的定义
     * @param nums  数组
     * @param currentIndex  遍历到的下标
     * @param resultSet 结果集
     */
    public void getSubsets(int[] nums, int currentIndex, List<List<Integer>> resultSet){
        if(currentIndex >= nums.length){
            return;
        }
        int size = resultSet.size();
        //遍历结果集所有的元素，依次添加index下标对应的数值。
        for(int i = 0; i < size; i++){
            LinkedList<Integer> miniList = (LinkedList<Integer>) resultSet.get(i);
            //深拷贝
            LinkedList<Integer> newMiniList = new LinkedList<>(miniList);
            newMiniList.add(nums[currentIndex]);
            resultSet.add(newMiniList);
        }
        getSubsets(nums, currentIndex + 1, resultSet);
    }

    /**
     * 方法2：DFS:深度优先搜索算法，求所有子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> results = new ArrayList<>();
        if(nums == null){
            return results;
        }
        Arrays.sort(nums);
        dfsOne(nums, 0, new ArrayList<Integer>(), results);
        return results;
    }

    /*
     * 递归定义：
     *      nums数组的长度+1就是树的层数，每个节点的值都是一个集合，根据是否添加下标index对应整数到集合这种决策，分为左节点
     * 和右节点，直到最底层所有节点对应集合合集就是不重复结果集，底层节点代表的集合就是当前节点到根节点的路径上所有决策添加的数值
     * 的集合。
     * @param nums  数组
     * @param index  遍历到的数组下标
     * @param subset 包含当前节点的路径种做出决策后得到的数值集合
     * @param result  保存结果的结果集类型是集合的集合
     */
    private void dfsOne(int[] nums,
                        int index,
                        List<Integer> subset,
                        List<List<Integer>> result){
        if(index == nums.length){
            result.add(new ArrayList<Integer>(subset));
            return;
        }
        subset.add(nums[index]);
        dfsOne(nums, index + 1, subset, result);
        //注意一定要先“加上”，然后再去掉，这样才满足回溯算法回到初始状态的要求。
        subset.remove(subset.size() - 1);
        dfsOne(nums, index + 1, subset, result);
    }

    /**
     * 方法3：DFS:深度优先搜索算法，求所有子集，这种方法更通用（默认数组中的整数不重复）
     * 递归定义：
     *      以当前集合为根节点，和数组下标index及以后的所有整数作为元素，查询能够组成的所有不重复子集，并添加到结果集result中。
     * 例如以空集[]为根节点，[1,2,3]为数组，元素个数一个的子集：[1],[2],[3]; 以[1]为根节点，(2,3)为可选元素，可构成2个数的
     * 不重复子集：[1,2], [1,3]; 以[2]为根节点，(3)为可选元素，可构成1个数的不重复子集：[2,3]; .....
     * 注意：
     *      为什么以[2]为根节点，可选元素只有(3)？ 因为除了2以外，只剩下1、3可选，但是选1的话会与以[1]为根节点的子集[1,2]重复，
     * 由此可见以任意一个子集作为节点（[]空子集是根节点），以index + 1 到 nums.length-1对应的数组元素作为可选元素，依次添加到
     * 原节点对应的子集中，由此产生新的节点，直到节点对应的子集中包含nums数组最后一位数为止，不再继续（递归）探索子节点。由以上
     * 步骤产生的所有节点对应的子集，就是数组购成的所有不重复子集。
     */

    //递归定义：给定数组、下标、当前节点子集、结果集，获取以当前节点的子集为基点构成的所有不重复子集，并添加到结果集
    //写递归的时候思维子需要停留在当前层，不要向下深入，默认递归拆解后的是已完成的！
    private void dfsTwo(int[] nums,
                        int index,
                        List<Integer> subset,
                        List<List<Integer>> results){
        //深度拷贝
        results.add(new ArrayList<Integer>(subset));
        //递归出口(省略这一步也行，相等情况也会直接被for循环排除)
        if(index == nums.length){
            return;
        }
        //递归的拆解
        for(int i = index; i < nums.length; i++){
            subset.add(nums[i]);
            dfsTwo(nums, i+1, subset, results);
            //回溯backtracking
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * DFS:深度优先搜索算法，求所有子集，这种方法更通用（数组中存在重复整数的情况）
     * 递归定义：
     *      以当前集合为根节点，和数组下标index及以后的所有整数作为元素，查询能够组成的所有不重复子集，并添加到结果集result中。
     * 原理：
     *      以数组[1, 2', 2"]为例，按照方法三DFS方法在纸上推理遍历，可得到[1,2"]与[1,2']重复，[2"]与[2]重复，推理可知递归过程
     * 中，遍历到index对应的数组整数nums[index]与数组前一个整数nums[index-1]相同，但整数nums[index-1]不在当前子集subset中，如：
     * [1,2"]中的2"与2'相同，但2'不在[1,2"]，此时可判定当前subest : [1,2"]为重复子集，可以舍弃。
     * 注意：
     *      传入参数nums数组一定是经过排序的，Arrays.sort(nums); 否则就会出现[1,2,3,2',0,2"]的情况不满足上述去重原理。只有
     *      排序后，相同的整数才会放在一起。
     */
    public void dfsWithRepeat(int[] nums, int index, List<List> result, List<Integer> subset){
        result.add(new ArrayList(subset));
        for(int i = index; i < nums.length; i++){
            //结论：相邻且相同且不在子集中的整数，都是重复；
            //做法：遍历到相邻且相同的整数，前一个数在子集时保留，前一个数不在子集就舍弃；
            //     因此关键就在于遍历到一个数与前一个数相等时，判断前一个数在不在subset中！
            //     如果一个数已经在subset中，那么这个数的下一个数一定是下一轮递归的第一个数，即i== index，
            //     逆反：i != index时这个数前一个数一定不在子集subset中。
            if(i != 0 && nums[i] == nums[i - 1] && i > index){
                continue;
            }
            subset.add(nums[i]);
            dfsWithRepeat(nums, index+1, result, subset);
            subset.remove(subset.size() -1);
        }

    }
//---------------------------------------------------排列的搜索树--------------------------------------------------------
    /*
     * 给定一个数字列表，返回其所有可能的排列。
     * permutation : [ˌpɜːmjuˈteɪʃn] n. 排列(方式); 组合(方式);
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return permutations;
        }
        boolean[] visited = new boolean[nums.length];
        ArrayList<Integer> permutation = new ArrayList<>();
        dfsWithPermutation(nums, visited, permutation, permutations);
        return permutations;
    }

    //递归的定义：找到以子集permutation开头的所有排列
    private void dfsWithPermutation(int[] nums,
                     boolean[] visited,
                     ArrayList<Integer> permutation,
                     List<List<Integer>> permutations){
        //递归的出口
        if(permutation.size() == nums.length){
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        //递归的拆解
        for(int i = 0; i < nums.length; i++){
            if(visited[i]){
                continue;
            }
            visited[i] = true;
            permutation.add(nums[i]);
            dfsWithPermutation(nums, visited, permutation, permutations);
            permutation.remove(permutation.size() - 1);   //backtracking
            visited[i] = false;   //backtracking
        }
    }


//-----------------------------------------分治法解决99%二叉树问题的算法--------------------------------------------------

    /*
     * 分治法 Divide & Conquer
     *     将大规模问题拆分为若干个小规模的同类型问题去处理的算法思想。把一个复杂的问题分成两个
     * 或更多的相同或相似的子问题，再把子问题分成更小的子问题……直到最后子问题可以简单的直接求解，
     * 原问题的解即子问题的解的合并。
     *
     * 什么样的数据结构适合分治法？
     *      1、二叉树：整棵树的左子树和右子树都是二叉树。二叉树的大部分题都可以使用分治法解决
     *         遇到二叉树的问题，就想想整棵树在该问题上的结果和左右孩子在该问题上的结果之间有
     *         什么联系，一开始不要关注小树的问题，只需要关注大树的问题解决了。这棵大树问题
     *         解决了，小树的问题就迎刃而解了。
     *      2、数组：一个大数组可以拆分为若干个不相交的子数组归并排序，快速排序，都是基于数组的分治法。
     *
     * 二叉树考点：
     *      1、考察形态：二叉树上求值，求路径（考点本质：深度优先搜索）
     *      2、考察形态：二叉树结构变化（考点本质：深度优先搜索）
     *      3、考察形态：二叉查找树（Binary Search Tree）（考点本质：深度优先搜索）
     */



    /*
     * 考察形态一：二叉树上求值(Maximum / Minimum / Average / Sum)，求路径(Paths)
     *
     * 求最小子树  Minimum Subtree
     *     给一棵二叉树, 找到和为最小的子树, 返回其根节点（不是根节点的和）。输入输出数据范围都在int内。
     * 注意节点值可以为负数，保证只有一棵和最小的子树，并且给出的二叉树不是一棵空树。
     * 思路：
     *      遇到二叉树的问题，就想想整棵树在该问题上的结果和左右孩子在该问题上的结果之间有什么联系，
     * 树的和 = 根节点值 + 左子树和 + 右子树和。
     */

    class Solution{
        //最小值初始为负无穷
        private int minSum;
        //最小和子树节点
        private TreeNode minRoot;

        public TreeNode findMiniSumSubTree(TreeNode root){
            minSum = Integer.MAX_VALUE;
            minRoot = null;
            //递归三要素1：递归定义：确定递归函数的作用，以及输入输出参数。
            getTreeSum(root);
            return minRoot;
        }

        public int getTreeSum(TreeNode root){
            //递归三要素3：递归出口
            if(root == null){
                return 0;
            }
            //递归三要素2：递归拆解
            //左子树和
            int leftSum = getTreeSum(root.left);
            //右子树和
            int rightSum = getTreeSum(root.right);
            //得到root为根的二叉树所有节点之和
            int rootSum = leftSum + rightSum + root.val;
            //打擂台确定最新的minSum
            if(rootSum < minSum){
                minSum = rootSum;
                minRoot = root;
            }
            //返回当前和
            return rootSum;
        }
    }

    /*
     * 考察形态一：查最近公共祖先 II (Lowest Common Ancestor II)
     *     给一棵二叉树和二叉树中的两个节点，找到这两个节点的最近公共祖先LCA。两个节点的最近公共祖先，
     * 是指两个节点的所有父亲节点中（包括这两个节点），离这两个节点最近的公共的节点。每个节点除了左右
     * 儿子指针以外，还包含一个父亲指针parent，指向自己的父亲。
     * 注意：
     *      这里输入的两个点是node objects，不是数字；自己可以是自己的祖先；
     * 思路：
     *      1、顺藤摸瓜遍历获取节点A的所有父节点，放入HashSet；然后依次顺藤摸瓜
     *         遍历B节点的所有父类，第一个出现在HashSet中的父类节点就是最近公共节点。
     *      2、分别依次获取A节点和B节点的所有父节点，存入List，然后从root节点开始
     *         单指针遍历，aParentList和bParentList中最后一个相同的节点就是最近公共节点。
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                                 ParentTreeNode A, ParentTreeNode B){
        HashSet<ParentTreeNode> parentSet = new HashSet<>();
        //顺藤摸瓜把A的所有父节点放入哈希表中
        ParentTreeNode curr = A;
        while(curr != null){
            parentSet.add(curr);
            curr = curr.parent;
        }
        //顺藤摸瓜遍历B的父节点，第一个出现在哈希表中的就是答案
        curr = B;
        while(curr != null){
            if(parentSet.contains(curr)){
                return curr;
            }
            curr = curr.parent;
        }
        return null;
    }

    /*
     * 考察形态一：查最近公共祖先 (Lowest Common Ancestor of a Binary Tree )
     *      给定二叉树的根节点和两个子节点，找到两个节点的最近公共父节点(LCA)。最近公共祖先是
     * 两个节点的公共的祖先节点且具有最大深度。“假设给出的两个节点一定都在树中存在”。
     * 注意：
     *      这里输入的两个点是node objects，不是数字；自己可以是自己的祖先；
     * 思路：
     *      遇到二叉树的问题，就想想整棵树在该问题上的结果和左右孩子在该问题上的结果之间有什么联系
     * 树存在LCA与左右子树存在LCA的关系。依次判断：1、（root节点 == A节点 || root节点 == B节点）
     * 那么这时候root节点一定是LCA；2、root左子树存在A/B节点 && root右子树存在A/B节点，此时root
     * 节点一定是LCA；3、如果root节点不是A/B节点，同时AB节点不分布在左右子树两侧，那么只可能AB节点
     * 都在root左子树或者root右子树，则LCA == root左子树LCA || 右子树LCA。
     * 由此完成递归三要素：
     * 递归定义：
     *      给定root节点、A节点、B节点作为入参，返回root节点下的LCA节点，或者找到的A/B节点本身；
     *      当root为根节点的树下存在A、B 2个点时，返回的是最近公共父节点，子树下只有A/B节点时返回的是节点本身
     * 递归的出口：
     *      root == null时返回null、其次见思路1、思路2、思路3。
     * 递归的拆分：
     *      以左右子树作为root节点进一步递归。
     */

    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode nodeA,TreeNode nodeB){
        if(root == null){
            return null;
        }
        //1、采用DFS的前序遍历的方式查找A/B节点，默认找到了就直接返回作为当前子树的LCA，返回到上一层，无需继续向下查找
        if(root == nodeA || root == nodeB){
            return root;
        }
        //分别获取默认的左右子树的LCA（即找到A/B节点就作为LCA返回）
        TreeNode leftResult = lowestCommonAncestor(root.left, nodeA, nodeB);
        TreeNode rightResult = lowestCommonAncestor(root.right, nodeA, nodeB);
        //2、存在特殊情况如果左右子树分别找到了自己默认的LCA，此时各自的LCA失效，将root作为当前子树的LCA返回上一层继续比较
        if(leftResult != null && rightResult != null){
            return root;
        }
        //3、走到这一个判断，表明左右子树仅有一个子树找到了默认的LCA，此时LCA继续生效，返回到上一层继续参与比较
        if(leftResult != null){
            return leftResult;
        }
        if(rightResult != null){
            return rightResult;
        }
        //4、左右子树都没找到的情况下直接返回空。
        return null;
    }

    /*
     * 考察形态二：Flatten Binary Tree to Linked List 将二叉树拆成链表(二叉树结构变化)
     *      将一棵二叉树按照前序遍历拆解成为一个假链表。所谓的假链表是说，用二叉树的 right 指针，
     * 来表示链表中的 next 指针。把这道题目翻译成人话：DFS前序遍历这棵树，然后把结果一路向右串联起来。
     * 思路：
     *      遇到二叉树的问题，就想想整棵树在该问题上的结果和左右孩子在该问题上的结果之间有什么联系树的
     * 链表 = 树的根节点 + 左子树链表 + 右子树链表
     */
    public void flatten(TreeNode root){
        flattenAndReturnLastNode(root);
    }

    //1、递归定义：将root这棵树摊平（形成一路向右的假列表）并返回摊平的树的尾部节点
    public TreeNode flattenAndReturnLastNode(TreeNode root){
        //2、递归结束：root为空时直接返回；
        if(root == null){
            return null;
        }
        //3、递归拆解：分别获取左右子树转换成“链表”后的末尾节点
        TreeNode leftLastNode = flattenAndReturnLastNode(root.left);
        TreeNode rightLastNode = flattenAndReturnLastNode(root.right);
        //左右子树链表都不为空
        if(leftLastNode != null && rightLastNode != null){
            leftLastNode.right = root.right;
            root.right = root.left;
            root.left = null;
            return rightLastNode;
        }
        //左子树链表不为空
        if(leftLastNode != null){
            root.right = root.left;
            root.left = null;
            return leftLastNode;
        }
        //右子树链表不为空
        if(rightLastNode != null){
            return rightLastNode;
        }
        //左右子树链表都为空
        return root;
    }


    /*
     * 考察形态三：二叉查找树 Binary Search Tree
     * 二叉查找树定义：
     *      左子树节点的值 < 根节点的值，右子树节点的值 >= 根节点的值
     * 相等的情况
     *      值相等的点可能在右子树，或者可能在左子树，需要跟面试官确认。
     * BST特点：
     *      中序遍历结果有序（不下降的顺序，有些相邻点可能相等）
     *      ● 如果二叉树的中序遍历不是“不下降”序列，则一定不是BST
     *      ● 如果二叉树的中序遍历是不下降，也不一定是BST（如：所有节点值相同）
     * BST基本操作：
     *      1、有序数列转为二叉查找树
     *      2、在二叉查找树中插入节点
     *      3、二叉查找树中搜索相关节点
     *      4、二叉查找树中删除节点后拼接成新的二叉查找树
     *      5、二叉查找树的中序遍历（在一个有序数列上找一个target一定要想到二叉查找树和二分查找）
     */

    /*
     * Kth Smallest Element in a BST, BST中第K小的元素
     * 给一棵二叉查找树，写一个 KthSmallest 函数来找到其中第 K 小的元素
     * 思路：
     *      1、中序遍历（遍历到第K个就行）；
     *      2、中序遍历（非递归实现），DFS的递归方式实现太简单了都是三行代码，于是实际
     *         interview时可能会要求用非递归方式实现；（用到栈stack）
     */
    public int kthSmallest(TreeNode root, int k){
        //使用stack进行非递归算法的数据存取
        Stack<TreeNode> stack = new Stack<>();
        //一路向左，把左边缘的点全部入栈
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        //每次从栈顶弹出一个节点，需要k-1次弹出，才能让第k小的元素出现在栈顶
        for(int i = 0; i <k-1; i++){
            //栈顶节点出栈
            TreeNode node = stack.pop();
            //如果栈顶节点的右节点存在，以栈顶节点的右节点为起始节点一路向左，依次添加到栈内
            //原因是以右节点一路向左找到的节点一定是整个右子树中的最小值节点，由此保证对二叉
            //查找树从小到大的依次遍历。
            if(node.right != null){
                TreeNode pushNode = node.right;
                while(pushNode != null){
                    stack.push(pushNode);
                    pushNode = pushNode.left;
                }
            }
        }
        return stack.peek().val;
    }

    /*
     * 考察形态三：二叉查找树 Binary Search Tree
     * Closest Binary Search Tree Value 二叉搜索树中最接近的值
     * 思路：
     *      分别找到 >= target的minNum和 <= target的maxNum，然后比较minNum和maxNum获取最接近的值。
     */
    public int closestValue(TreeNode root,double target){
        if(root == null){
            return 0;
        }
        TreeNode lowerBound = lowerBound(root, target);
        TreeNode upperBound = upperBound(root, target);
        int upVal = upperBound.val;
        int lowVal = lowerBound.val;
        if(lowerBound == null){
            return upVal;
        }
        if (upperBound == null){
            return lowVal;
        }
        return (upVal - target > target -lowVal)? lowVal : upVal;

    }
    //查找目标值下限
    private TreeNode lowerBound(TreeNode root, double target){
        if(root == null){
            return null;
        }
        if(target < root.val){
            //此时下限一定在root的左子树中
            return lowerBound(root.left, target);
        }
        //如果root.vakl <= target,那么此时root就是一个下限，继续在右子树查找更接近的下限
        TreeNode lowerBoundNode = lowerBound(root.right, target);
        return (lowerBoundNode == null)? root : lowerBoundNode;
    }
    //查找目标值上限
    private TreeNode upperBound(TreeNode root, double target){
        if(root == null){
            return null;
        }

        if(target > root.val){
            //此时上限一定在root的右子树中
            return upperBound(root.right, target);
        }
        //如果target <= root.val, 那么此时root就是一个上限继续在左子树查找更接近的上限
        TreeNode upperBoundNode = upperBound(root.left, target);
        //如果存在值更小的上限则直接返回，否则返回root；
        return (upperBoundNode == null) ? root : upperBoundNode;
    }



    /*
     * 红黑树：是一种平衡的二叉查找树 Balanced BST
     * 应用：
     *      O(logN) 的时间内实现增删查改
     *      O(logN) 的时间内实现找最大找最小
     *      O(logN) 的时间内实现找比某个数小的最大值(upperBound)和比某个数大的最小值(lowerBound)
     * 拓展：
     *      Java 1.8 中的 HashMap 的实现里同时用到了 TreeMap 和 LinkedList
     *      只可能考红黑树的应用，不会考红黑树的实现！
     */



















//----------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        int[] nums = {5,2,3,1};
        System.out.println(Arrays.toString(arraySortImprove(nums)));

    }
}





//二叉树节点
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//包含指向父节点的指针的二叉树节点。
class ParentTreeNode{
    public ParentTreeNode parent, left, right;
}

//二叉树非递归中序遍历迭代器
class BSTIterator{
    //创建栈用于存储当前节点的路径
    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(){};
    public BSTIterator(TreeNode root){
        //获取最小节点压入栈
        findMostLeft(root, stack);
    }
    public boolean hasNext(){
        return !stack.isEmpty();
    }

    //关键在于前序遍历中，一个结点的下一个节点一定在右下角，或者最近的通过左子树找到当前节点的点。
    public TreeNode next(){
        TreeNode curt = stack.peek();
        TreeNode node = curt;
        //如果当前节点存在右子树，那么下一节点为右子树的最小节点
        if(node.right != null){
            findMostLeft(node.right, stack);
        //当前节点不存在右子树的情况下，下一节点为路径中最近一个通过左子树包含当前点的点
        }else{
            node = stack.pop();
            while(!stack.isEmpty() && stack.peek().right == node){
                node = stack.pop();
            }
        }
        //依次返回栈顶节点
        return curt;
    }
    public void findMostLeft(TreeNode node, Stack<TreeNode> stack){
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }
}

//（无向）树节点
class Node{
    int val;
    List<Node> neighbours;
    public Node(int x){
        this.val = x;
        this.neighbours = new ArrayList<>();
    }
    public void setVal(int val) {
        this.val = val;
    }
    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }
    public int getVal() {
        return val;
    }
    public List<Node> getNeighbours() {
        return neighbours;
    }
}




//结果类：返回是否是平衡二叉树，及其树的高度
class Result{
    public boolean isBalance;
    public int hight;
    public Result(){};
    public Result(boolean isBalance, int hight){
        this.isBalance = isBalance;
        this.hight = hight;
    }
}
