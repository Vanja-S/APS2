package Programerska_Naloga_4;

public class bst_2 {
    static BstElement root;
    static int compareCount = 0;
    static boolean kretnica;

    public bst_2() {
        bst_2.root = null;
        bst_2.compareCount = 0;
        bst_2.kretnica = false;
    }

    public void insert(int value) {
        if(bst_2.root == null) {
            bst_2.root = new BstElement(value);
        } else {
            bst_2.root.insert(value);
        }
    }

    public void find(int value) {
        if(bst_2.root == null) {
            System.out.println("false");
        } else {
            System.out.println(bst_2.root.find(value));
        }
    }

    public void delete(int value) {
        if(root == null) {
            System.out.println("false");
        } else {
            System.out.println(bst_2.root.delete(value, null));
        }
    }

    public void printPreOrder() {
        if(bst_2.root == null) {
            System.out.println("empty");
        } else {
            bst_2.root.printPreOrder();
            System.out.println();
        }
    }

    public void printInOrder() {
        if(bst_2.root == null) {
            System.out.println("empty");
        } else {
            bst_2.root.printInOrder();
            System.out.println();
        }
    }

    public void printPostOrder() {
        if(bst_2.root == null) {
            System.out.println("empty");
        } else {
            bst_2.root.printPostOrder();
            System.out.println();
        }
    }

    public void printComparisons() {
        System.out.println("COMPARISONS: " + bst_2.compareCount);
    }
}

class BstElement {
    private int value;
    public int count;
    public BstElement left, right;

    public BstElement(int value) {
        this(value, null, null);
    }

    public BstElement(int value, BstElement left, BstElement right) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.count = 1;
    }

    public int value() {
        return value;
    }

    public boolean find(int value) {
        bst_2.compareCount++;
        if(this.value == value) {
            return true;
        } else {
            if(value < this.value) {
                return (this.left == null ? false : this.left.find(value));
            } else {
                return (this.right == null ? false : this.right.find(value));
            }
        }
    }

    public void insert(int value) {
        bst_2.compareCount++;
        if(value > this.value) {
            if(this.right == null) {
                this.right = new BstElement(value);
            } else {
                this.right.insert(value);
            }

        } else if(value < this.value) {
            if(this.left == null) {
                this.left = new BstElement(value);
            } else {
                this.left.insert(value);
            }

        } else {
            this.count++;
        }
    }

    public BstElement getMaxElement(BstElement parent) {
        if(this.right != null) {
            return this.right.getMaxElement(this);
        } else {
            return parent;
        }
    }

    public BstElement getMinElement(BstElement parent) {
        if(this.left != null) {
            return this.left.getMinElement(this);
        } else {
            return parent;
        }
    }

    private void setParent(int currValue, BstElement parent, BstElement newValue) {
        if(parent == null) {
            bst_2.root = newValue;
        } else {
            if(currValue > parent.value()) {
                parent.right = newValue;
            } else {
                parent.left = newValue;
            }
        }
    }

    public boolean delete(int value, BstElement parent) {
        bst_2.compareCount++;
        if(value == this.value) {
            if(this.count > 1) {
                this.count--;
                return true;
            }

            switch(getChildrenCount()) {
                case 0:
                    setParent(value, parent, null);
                    break;
                    
                case 1:
                    setParent(value, parent, (this.right != null ? this.right : this.left));
                    break;

                case 2:
                    if(bst_2.kretnica == false) {
                        BstElement maxElParent = this.left.getMaxElement(this);

                        if(maxElParent == this) {
                            this.left.right = this.right;
                            setParent(value, parent, maxElParent.left);
                        } else {
                            this.value = maxElParent.right.value;
                            this.count = maxElParent.right.count;

                            maxElParent.right = maxElParent.right.left;
                        }
                                                
                        bst_2.kretnica = true;
                    } else {

                        BstElement maxElParent = this.right.getMinElement(this);
                        if(maxElParent == this) {
                            this.right.left = this.left;
                            setParent(value, parent, maxElParent.right);
                        } else {
                            this.value = maxElParent.left.value;
                            this.count = maxElParent.left.count;

                            maxElParent.left = maxElParent.left.right;
                        }
                        bst_2.kretnica = false;
                    }
            }
            return true;

        } else if(value < this.value && this.left != null) {
            return this.left.delete(value,this);

        } else if(value > this.value && this.right != null) {
            return this.right.delete(value,this);
            
        } else {
            return false;
        }
    }

    public int getChildrenCount() {
        int i = 0;
        if(this.left != null)
            i++;

        if(this.right != null)
            i++;
        
        return i;
    }

    public String toString() {
        return this.value + "/" + this.count;
    }

    public void printPreOrder() {
        System.out.print(this + " | ");
        if(this.left != null)
            this.left.printPreOrder();


        if(this.right != null)
            this.right.printPreOrder();
    }

    public void printInOrder() {
        if(this.left != null)
            this.left.printInOrder();

        System.out.print(this + " | ");

        if(this.right != null)
            this.right.printInOrder();
    }

    public void printPostOrder() {
        if(this.left != null)
            this.left.printPostOrder();
  
        if(this.right != null)
            this.right.printPostOrder();

        System.out.print(this + " | ");
    }

}

