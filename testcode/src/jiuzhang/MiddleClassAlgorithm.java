package jiuzhang;

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
     * 循环嵌套暴力破解法：采用对字符串长度逆序遍历方式检查字串是否是回文子串，再做遍历。
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
        if(nums[start] == target){ //找第一个target位置就先检查start，（移动start判断到end判断钱）
            return start;
        }

        return -1;
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
     *          使用使用List[] graph = new ArrayList[n]记录当前节点和指向的neighbors节点,使用int[] inDegree记录每个节点
     *          的入向的数量。根据提供的参数，遍历获取无向节点添加到queue开始while(!queue.isEmpty())循环，再for-each()循
     *          环每个neighbor，并修改遍历到的这个neighbor在数组inDegree中的记录的入向数量，对修改后入向数量为0的neighbor，
     *          添加到queue。在while与for中间将节点添加到结果。验证结果数量与数组inDegree长度一致时可正常返回。
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
