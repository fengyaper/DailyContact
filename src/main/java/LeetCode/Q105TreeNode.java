package LeetCode;



/**
 * @author: putao
 * @date: 2022/4/26 20:49
 * @descriptions:
 */

public class Q105TreeNode {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

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

    //搜索二叉树
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
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(1);
        tree.right = new TreeNode(4);
        tree.right.left = new TreeNode(3);
        tree.right.right = new TreeNode(6);

        System.out.println(isValidBST(tree));


    }
}
