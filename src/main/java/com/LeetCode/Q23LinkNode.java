package com.LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @DateTime: 2022/4/14 下午3:21
 * @Description: 多个有序链表合并成一个有序链表
 *  有点类似归并排序啊
 *       1、使用小根堆做一个存数的数组，重写排序器，改成升序排序
 *       2、剩下的就是弹出一个数，存入一个数
 *       3、比较难的就是listnode，比较特殊，至少需要2个指针，加上返回的head是三个
 *  注意：
 *      1、优先队列就是小根堆PriorityQueue
 *      2、优先队列是队列了，就不用数组方式建立了
 */

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

    //自定义排序器
    public static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        //判断lists是否为空，为空直接终止返回
        if (lists == null) {
            return null;
        }
        //PriorityQueue小根堆本身就是队列了，不用数组了
        //优先队列也就是我们常说的小根堆或者大根堆，它至少支持两种操作：插入add，删除poll最小者
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        //把数据加载到优先队列(小根堆)
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }
        //判断下，添加完之后的heap是否为空
        if (heap.isEmpty()) {
            return null;
        }
        //弹出一个
        ListNode head = heap.poll();
        //使用pre来拼接要返回的Listnode
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }
        //判断条件就是优先队列(小根堆)不为空
        while (!heap.isEmpty()) {
            //弹出一个listnode
            ListNode cur = heap.poll();
            //记录下返回的listnode
            pre.next = cur;
            pre = cur;
            //判断被弹出的那个Listnode是否还有下一个，有添加到小根堆中，有点像归并排序啊
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }





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


