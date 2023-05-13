package LeetCode.Serialize_and_Deserialize_Binary_Tree;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Codec {
    private Queue<TreeNode> queue;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        queue = new LinkedList<TreeNode>();
        queue.add(root);
        String string = levelOrder(queue.remove(), "").strip();
        string = "[" + string + "]";
        string = string.replaceAll(" ", ",");
        return string;
    }

    private String levelOrder(TreeNode node, String string) {
        if (node == null) {
            string += " null";
            if (queue.size() == 0)
                return string;
        }
        else {
            string += " " + node.val;
            queue.add(node.left);
            queue.add(node.right);
        }
        
        return levelOrder(queue.remove(), string);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return new TreeNode(1);
    }
}