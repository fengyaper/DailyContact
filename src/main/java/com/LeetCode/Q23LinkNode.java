package com.LeetCode;


public class Q23LinkNode {

    //单链表
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //双链表
    public static class DoubleNode{
        public int val;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data){
            val=data;
        };
    }

    //单链表的反转
    public static ListNode reverseListNode(ListNode head){
        ListNode next = null;
        ListNode pre = null;
        while(head!=null){
            //记录原节点断开的位置
            next= head.next;
            //打断链表，修改当前节点的next指针，指向断开位置前节点
            head.next=pre;
            //记录最新断开位置前节点
            pre=head;
            //将head替换成断开位置后节点
            head=next;
        }
        return pre;
    }

    //双链表的反转
    public static DoubleNode reverceDoubleNode(DoubleNode head){
        DoubleNode next = null;
        DoubleNode pre = null;

        while (head != null){
            next=head.next;
            head.next=pre;
            head.last=next;
            pre=head;
            head=next;
        }
        return pre;
    }


    //    public ListNode mergeKLists(ListNode[] lists) {
//
//    }


    public static void main(String[] args) {

//        //单链表的创建和赋值
//        ListNode n1 = new ListNode(1);
//        ListNode n2 = new ListNode(2);
//        ListNode n3 = new ListNode(3);
//        ListNode n4 = new ListNode(4);
//        n1.next=n2;
//        n2.next=n3;
//        n3.next=n4;
//
//        System.out.println("=========reverse==========");
//        n1 = reverseListNode(n1);
//
//        while (n1 !=null ){
//            System.out.println(n1.val);
//            n1=n1.next;
//        }

        //双链表的创建和赋值
        DoubleNode n1 = new DoubleNode(1);
        DoubleNode n2 = new DoubleNode(2);
        DoubleNode n3 = new DoubleNode(3);
        DoubleNode n4 = new DoubleNode(4);
        n1.next=n2;
        n1.last=null;
        n2.next=n3;
        n2.last=n1;
        n3.next=n4;
        n3.last=n2;
        n4.next=null;

        System.out.println("=========reverse==========");

        while (n1 !=null ){
            System.out.println(n1.val);
            n1=n1.next;
        }








    }

}


