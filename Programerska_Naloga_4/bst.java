package Programerska_Naloga_4;

public class bst {

    int size;
    int comparisons;
    boolean deleted;
    BstElement root;

    public bst() {
        size = 0;
        comparisons = 0;
        root = null;
        deleted = false;
    }

    public void insert(int key) {
        if (root == null) {
            root = new BstElement(key);
            return;
        }

        insertRecursive(key, root);
    }

    private void insertRecursive(int newKey, BstElement node) {
        if (newKey < node.data) {
            comparisons++;
            if (node.left != null) {
                insertRecursive(newKey, node.left);
                return;
            }
            node.left = new BstElement(newKey);
            return;
        } else if (newKey > node.data) {
            comparisons++;
            if (node.right != null) {
                insertRecursive(newKey, node.right);
                return;
            }
            node.right = new BstElement(newKey);
            return;
        } else if (newKey == node.data) {
            comparisons++;
            node.frequency++;
            return;
        }
    }

    public void find(int key) {
        if (findRecursive(key, root))
            System.out.println("true");
        else
            System.out.println("false");
    }

    private boolean findRecursive(int key, BstElement node) {
        if (node == null)
            return false;
        if (key < node.data) {
            comparisons++;
            return findRecursive(key, node.left);
        } else if (key > node.data) {
            comparisons++;
            return findRecursive(key, node.right);
        } else {
            comparisons++;
            // key == node.data
            return true;
        }
    }

    public void delete(int key) {
        deleted = false;
        if (root == null) {
            System.out.println("false");
            return;
        }

        deleteRecursive(key, root, null, false);
    }

    private void deleteRecursive(int key, BstElement node, BstElement parentNode, boolean dir) {
        if (node == null) {
            if (!deleted)
                System.out.println("false");
            return;
        }

        if (!deleted)
            comparisons++;
        if (key == node.data) {
            if (!deleted) {
                System.out.println("true");
            }
            deleted = true;

            if (--node.frequency != 0) {
                return;
            }

            if (node == root) {
                BstElement max;
                if(node.left == null && node.right == null) {
                    root = null;
                    return;
                }
                else if (node.left != null) {
                    max = maxElement(node.left);
                    node.data = max.data;
                    node.frequency = max.frequency;
                    deleteRecursive(max.data, node.left, node, false);
                    return;
                } else {
                    BstElement min = minElement(node.right);
                    node.data = min.data;
                    node.frequency = min.frequency;
                    deleteRecursive(min.data, node.right, node, true);
                    return;
                }
            }
            // If node is a leaf
            else if (node.left == null & node.right == null) {
                if(!dir)
                    parentNode.left = null;
                else 
                    parentNode.right = null;
            }
            // If node has only left child
            else if (node.left != null & node.right == null) {
                if(!dir)
                    parentNode.left = node.left;
                else 
                    parentNode.right = node.left;
            }
            // If node has only right child
            else if (node.left == null & node.right != null) {
                if(!dir)
                    parentNode.left = node.right;
                else 
                    parentNode.right = node.right;
            }
            // Else the node has two children
            else {
                BstElement max = maxElement(node.left);
                node.data = max.data;
                node.frequency = max.frequency;
                deleteRecursive(max.data, node.left, node, false);
            }
        } else if (key < node.data) {
            deleteRecursive(key, node.left, node, false);
        } else if (key > node.data) {
            deleteRecursive(key, node.right, node, true);
        }
    }

    private BstElement maxElement(BstElement node) {
        if (node.right == null)
            return node;
        return maxElement(node.right);
    }

    public BstElement minElement(BstElement node) {
        if(node.left != null) 
            return node;
        return minElement(node.left);
    }

    private void preOrder(BstElement node) {
        if (node == null)
            return;
        System.out.print(node.data + "/" + node.frequency + " | ");
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(BstElement node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.print(node.data + "/" + node.frequency + " | ");
        inOrder(node.right);
    }

    private void postOrder(BstElement node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + "/" + node.frequency + " | ");
    }

    public void printPreOrder() {
        if (root == null) {
            System.out.println("empty");
            return;
        }
        preOrder(root);
        System.out.println();
    }

    public void printInOrder() {
        if (root == null) {
            System.out.println("empty");
            return;
        }
        inOrder(root);
        System.out.println();
    }

    public void printPostOrder() {
        if (root == null) {
            System.out.println("empty");
            return;
        }
        postOrder(root);
        System.out.println();
    }

    public void printComparisons() {
        System.out.println("COMPARISONS: " + comparisons);
    }
}

class BstElement {
    int data;
    int frequency;
    BstElement left, right;

    public BstElement(int x) {
        data = x;
        frequency = 1;
        left = right = null;
    }
}