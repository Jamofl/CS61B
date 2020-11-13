public class IsBST {

    class TreeNode { int val;
        TreeNode left;
        TreeNode right;
    }

    // my solution: tooooooooo complicated, fake recursion
//    public static boolean isBST(TreeNode T){
//        if(T == null)
//            return true;
//        else if(T.left != null){
//            return findBiggest(T.left) < T.val;
//        }
//        else if(T.right != null){
//            return findSmallest(T.right) > T.val;
//        }
//        return isBST(T.left) && isBST(T.right);
//
//    }
//
//    public static int findBiggest(TreeNode T){
//        int biggest = T.val;
//        return findBHelper(T, biggest);
//    }
//
//    private static int findBHelper(TreeNode T, int biggest){
//        if(T.left.val > biggest)
//            biggest = T.left.val;
//        if(T.right.val > biggest)
//            biggest = T.right.val;
//        int a = findBHelper(T.left, biggest);
//        int b = findBHelper(T.right,biggest);
//        if(a > b)
//            return a;
//        else
//            return b;
//    }
//
//    public static int findSmallest(TreeNode T){
//        return -1;
//    }

    //official solution : using recursion to find the smallest and biggest
    public static boolean isBST(TreeNode T){
        return isBSTHelper(T, -1, Double.POSITIVE_INFINITY);
    }

    public static boolean isBSTHelper(TreeNode T, int min, double max){
        if(T == null)
            return true;
        if(T.val > max || T.val < min)
            return false;
        return isBSTHelper(T.right, T.val, max) && isBSTHelper(T.left, min, T.val);

    }
}
