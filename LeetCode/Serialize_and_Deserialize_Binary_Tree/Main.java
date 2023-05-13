package LeetCode.Serialize_and_Deserialize_Binary_Tree;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);

        root.left = new TreeNode(1);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);


        Codec ser = new Codec();
        String neki = ser.serialize(root);
        System.out.println(neki);
    }    
}
