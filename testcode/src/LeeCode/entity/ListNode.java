package LeeCode.entity;

/**
 * @Author:Z
 * @Date:2022/1/4 13:43
 * @Description:  单链表
 * @Version:1.0
 */

  //Definition for singly-linked list.
  public class ListNode {
      public int val;
      public ListNode next;
      public ListNode() {}
      public ListNode(int val) { this.val = val; }
      public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
