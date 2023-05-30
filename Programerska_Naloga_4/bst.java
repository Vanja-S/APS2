package Programerska_Naloga_4;

public class bst {

    int size;
    int comparisons;
    boolean deleteDir, deleted;
    BstElement root;

    public bst() {
        size = 0;
        comparisons = 0;
        root = null;
        deleteDir = false;
        deleted = false;
    }

    public void insert(int key) {
        if (root == null) {
            root = new BstElement(key);
            return;
        }
        insertR(key, root);
    }

    public void insertR(int key, BstElement node) {
        comparisons++;
        if (key > node.data) {
            if (node.right == null) {
                node.right = new BstElement(key);
            } else {
                insertR(key, node.right);
            }
        } else if (key < node.data) {
            if (node.left == null) {
                node.left = new BstElement(key);
            } else {
                insertR(key, node.left);
            }
        } else {
            node.frequency++;
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
            return true;
        }
    }

    public void delete(int key) {
        deleted = false;
        if (root == null) {
            System.out.println("false");
            return;
        }
        System.out.println(delete(key, root, null));
    }

    // private boolean deleteRecursive(int key, BstElement node, BstElement
    // parentNode, boolean dir) {
    // if (node == null) {
    // return false;
    // }

    // comparisons++;
    // if (key == node.data) {
    // if (!deleted) {
    // System.out.println("true");
    // }

    // if (--node.frequency != 0) {
    // return true;
    // }

    // if (node == root) {
    // if (node.left == null && node.right == null) {
    // root = null;
    // }

    // else if (!deleteDir) {
    // BstElement max = maxElement(node);
    // node.data = max.data;
    // node.frequency = max.frequency;
    // deleteRecursive(max.data, node.left, node, false);
    // deleteDir = true;
    // } else {
    // BstElement min = minElement(node);
    // node.data = min.data;
    // node.frequency = min.frequency;
    // deleteRecursive(min.data, node.right, node, false);
    // deleteDir = false;
    // }
    // }
    // // If node is a leaf
    // else if (node.left == null & node.right == null) {
    // if (!dir)
    // parentNode.left = null;
    // else
    // parentNode.right = null;
    // }
    // // If node has only left child
    // else if (node.left != null & node.right == null) {
    // if (!dir)
    // parentNode.left = node.left;
    // else
    // parentNode.right = node.left;
    // }
    // // If node has only right child
    // else if (node.left == null & node.right != null) {
    // if (!dir)
    // parentNode.left = node.right;
    // else
    // parentNode.right = node.right;
    // }
    // // Else the node has two children
    // else {
    // if (!deleteDir) {
    // BstElement max = maxElement(node.left);
    // node.data = max.data;
    // node.frequency = max.frequency;
    // deleteRecursive(max.data, node.left, node, false);
    // deleteDir = !deleteDir;
    // } else {
    // BstElement min = minElement(node.right);
    // node.data = min.data;
    // node.frequency = min.frequency;
    // deleteRecursive(min.data, node.right, node, false);
    // deleteDir = !deleteDir;
    // }
    // }
    // return true;
    // } else if (key < node.data) {
    // return deleteRecursive(key, node.left, node, false);
    // } else if (key > node.data) {
    // return deleteRecursive(key, node.right, node, true);
    // } else
    // return false;
    // }

    public boolean delete(int key, BstElement node, BstElement parent) {
        comparisons++;
        if (key == node.data) {
            if (node.frequency > 1) {
                node.frequency--;
                return true;
            }

            switch (getChildrenCount(node)) {
                case 0:
                    setParent(key, parent, null);
                    break;

                case 1:
                    setParent(key, parent, (node.right != null ? node.right : node.left));
                    break;

                case 2:
                    if (deleteDir == false) {
                        BstElement maxElParent = maxElement(node.left, node);

                        if (maxElParent == node) {
                            node.left.right = node.right;
                            setParent(key, parent, maxElParent.left);
                        } else {
                            node.data = maxElParent.right.data;
                            node.frequency = maxElParent.right.frequency;

                            maxElParent.right = maxElParent.right.left;
                        }

                        deleteDir = true;
                    } else {

                        BstElement maxElParent = minElement(node.right, node);
                        if (maxElParent == node) {
                            node.right.left = node.left;
                            setParent(key, parent, maxElParent.right);
                        } else {
                            node.data = maxElParent.left.data;
                            node.frequency = maxElParent.left.frequency;

                            maxElParent.left = maxElParent.left.right;
                        }
                        deleteDir = false;
                    }
            }
            return true;

        } else if (key < node.data && node.left != null) {
            return delete(key, node.left, node);
        } else if (key > node.data && node.right != null) {
            return delete(key, node.right, node);
        } else {
            return false;
        }
    }

    private void setParent(int key, BstElement parent, BstElement newNode) {
        if (parent == null) {
            root = newNode;
        } else {
            if (key > parent.data) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
    }

    public int getChildrenCount(BstElement node) {
        int i = 0;
        if (node.left != null)
            i++;

        if (node.right != null)
            i++;

        return i;
    }

    private BstElement maxElement(BstElement node, BstElement parent) {
        if (node.right == null)
            return parent;
        return maxElement(node.right, node);
    }

    public BstElement minElement(BstElement node, BstElement parent) {
        if (node.left == null)
            return parent;
        return minElement(node.left, node);
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
        left = null;
        right = null;
    }
}