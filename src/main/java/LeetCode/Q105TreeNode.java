package LeetCode;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.max;

/**
 * @DateTime: 2022/4/22 上午10:20
 * @Description: 树
 * 1、前序、中序、后序遍历
 * 2、leetcode：100、101、104
 * leetcode105 前序+中序；递归：出口和循环体；
 * leetcode106 中序+后序;真正的大坑是下标索引；左节点的边界用左边界和headindex找；右节点的边界用右边界和headindex找
 * leetcode107 二叉树的层序遍历
 * leetcode98 搜索二叉树
 *
 */
public class Q105TreeNode {
    //treenode的数据结构
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }
    //平衡二叉树
    public static class infoTree {
        public static boolean isBl;
        public static int height;

        public infoTree(boolean isBl, int height) {
            this.isBl = isBl;
            this.height = height;
        }
    }
    //搜索二叉树
    public static class InfoTreeNode {
        boolean isValid;
        int max;
        int min;

        InfoTreeNode(boolean isVa, int max, int min) {
            this.isValid = isVa;
            this.max = max;
            this.min = min;
        }
    }

    //前序遍历
    public static void prePrint(TreeNode tree) {
        if (tree == null) {
            return;
        }
        System.out.println(tree.val);
        prePrint(tree.left);
        prePrint(tree.right);
    }

    //中序遍历
    public static void midPrint(TreeNode tree) {
        if (tree == null) {
            return;
        }
        midPrint(tree.left);
        System.out.println(tree.val);
        midPrint(tree.right);
    }

    //后序遍历
    public static void laterPrint(TreeNode tree) {
        if (tree == null) {
            return;
        }
        laterPrint(tree.left);
        laterPrint(tree.right);
        System.out.println(tree.val);
    }


    //leetcode100
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    //leetcode101
    public static boolean isSymmetric(TreeNode root) {
        return isMinor(root, root);
    }

    public static boolean isMinor(TreeNode h1, TreeNode h2) {
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }
        return h1.val == h2.val && isMinor(h1.left, h2.right) && isMinor(h1.right, h2.left);
    }


    //leetcode104
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    //leetcode105前序+中序；递归：出口和循环体；
    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length || preorder == null || inorder == null) {
            return null;
        }
        HashMap<Integer, Integer> valueIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valueIndex.put(inorder[i], i);
        }
        return bPreorder(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, valueIndex);
    }

    public static TreeNode bPreorder(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2, HashMap<Integer, Integer> valueIndex) {
        if (l1 > r1) {
            return null;
        }
        TreeNode head = new TreeNode(preorder[l1]);
        if (l1 == r1) {
            return head;
        }
        int find = valueIndex.get(preorder[l1]);
        //
        head.left = bPreorder(preorder, l1 + 1, find - l2 + l1, inorder, l2, find - 1, valueIndex);
        head.right = bPreorder(preorder, find - l2 + l1 + 1, r1, inorder, find + 1, r2, valueIndex);
        return head;
    }

    //leetcode106 中序+后序;真正的大坑是下标索引；左节点的边界用左边界和headindex找；右节点的边界用右边界和headindex找
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        HashMap<Integer, Integer> valueIndex = new HashMap<>();
        for (int i = 0; i <= inorder.length - 1; i++) {
            valueIndex.put(inorder[i], i);
        }
        return buildPostorder(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, valueIndex);
    }

    public static TreeNode buildPostorder(int[] inorder, int l1, int r1, int[] postorder, int l2, int r2, HashMap<Integer, Integer> valueIndex) {
        if (l1 > r1) {
            return null;
        }
        TreeNode head = new TreeNode(postorder[r2]);
        if (l1 == r1) {
            return head;
        }
        int headIndex = valueIndex.get(postorder[r2]);
        int ll1 = l1;
        int lr1 = headIndex - 1;
        int ll2 = l2;
        int lr2 = headIndex - l1 + l2 - 1;
        int rl1 = headIndex + 1;
        int rr1 = r1;
        int rl2 = r2 - (r1 - headIndex);
        int rr2 = r2 - 1;
        head.left = buildPostorder(inorder, ll1, lr1, postorder, ll2, lr2, valueIndex);
        head.right = buildPostorder(inorder, rl1, rr1, postorder, rl2, rr2, valueIndex);
        return head;
    }

    //leetcode107 二叉树的层序遍历
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        //最终返回的结果
        List<List<Integer>> finalAns = new LinkedList<>();
        //先判断传入是否为空，为空直接返回
        if (root == null) {
            return finalAns;
        }
        //使用队列来存当前的头结点，一个个从头poll
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //判断头结点是否为空，为空停止
        while (!queue.isEmpty()) {
            //给个循环的次数，循环到这停止
            int size = queue.size();
            //当前的头结点值
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                //将队列中的首节点弹出
                TreeNode tr = queue.poll();
                curAns.add(tr.val);
                //判断左右节点是否为空，不为空加到队列中，为下一次做准备
                if (tr.left != null) {
                    queue.add(tr.left);
                }
                if (tr.right != null) {
                    queue.add(tr.right);
                }
            }
            finalAns.add(0, curAns);
        }
        return finalAns;
    }


    //判断是否为二叉树
    public static boolean isBlance(TreeNode treeNode) {
        infoTree infoTree = recurBlance(treeNode);
        return infoTree.isBl;
    }
    //递归调用
    public static infoTree recurBlance(TreeNode tree) {
        if (tree == null) {
            return new infoTree(true, 0);
        }
        infoTree leftInfo = recurBlance(tree.left);
        infoTree rightInfo = recurBlance(tree.right);
        boolean blance = leftInfo.isBl && rightInfo.isBl && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        int height = Math.max(leftInfo.height, rightInfo.height)+1;
        return new infoTree(blance,height);
    }



    //leetcode98 搜索二叉树
    public static boolean isValidBST(TreeNode root) {
        return recuBST(root).isValid;
    }
    public static InfoTreeNode recuBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 先拿到左右子树的最终结果的倒数第二层
        InfoTreeNode leftInfo = recuBST(root.left);
        InfoTreeNode rightInfo = recuBST(root.right);

        // 拿所有子树的最大值和最小值
        int max = root.val;
        int min = root.val;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }

        // 拿是否为搜索树的boolean值
        boolean isValBs=true;
        if(leftInfo != null && !leftInfo.isValid){
            isValBs = false;
        }
        if(rightInfo != null && !rightInfo.isValid){
            isValBs = false;
        }
        boolean leftMaxLessValue = (leftInfo == null) ? true : (leftInfo.max < root.val);
        boolean rightMinMoreValue = (rightInfo == null) ? true : (root.val < rightInfo.min);
        isValBs=isValBs && leftMaxLessValue && rightMinMoreValue;

        //最终返回info
        return new InfoTreeNode(isValBs,max,min);
    }


    public static void main(String[] args) {
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(9);
        tree.right = new TreeNode(20);
        tree.right.left = new TreeNode(15);
        tree.right.right = new TreeNode(7);

        System.out.println(isBlance(tree));


    }
}
