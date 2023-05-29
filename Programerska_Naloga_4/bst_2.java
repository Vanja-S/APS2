public class bst {
    static Element root;
    static int compareCount = 0;
    static boolean kretnica;

    public bst() {
        bst.root = null;
        bst.compareCount = 0;
        bst.kretnica = false;
    }

    public void insert(int value) {
        if(bst.root == null) {
            bst.root = new Element(value);
        } else {
            bst.root.insert(value);
        }
    }

    public void find(int value) {
        if(bst.root == null) {
            System.out.println("false");
        } else {
            System.out.println(bst.root.find(value));
        }
    }

    public void delete(int value) {
        if(root == null) {
            System.out.println("false");
        } else {
            System.out.println(bst.root.delete(value, null));
        }
    }

    public void printPreOrder() {
        if(bst.root == null) {
            System.out.println("empty");
        } else {
            bst.root.printPreOrder();
            System.out.println();
        }
    }

    public void printInOrder() {
        if(bst.root == null) {
            System.out.println("empty");
        } else {
            bst.root.printInOrder();
            System.out.println();
        }
    }

    public void printPostOrder() {
        if(bst.root == null) {
            System.out.println("empty");
        } else {
            bst.root.printPostOrder();
            System.out.println();
        }
    }

    public void printComparisons() {
        System.out.println("COMPARISONS: " + bst.compareCount);
    }
}

class Element {
    private int value;
    public int count;
    public Element left, right;

    public Element(int value) {
        this(value, null, null);
    }

    public Element(int value, Element left, Element right) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.count = 1;
    }

    public int value() {
        return value;
    }

    public boolean find(int value) {
        bst.compareCount++;
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
        bst.compareCount++;
        if(value > this.value) {
            if(this.right == null) {
                this.right = new Element(value);
            } else {
                this.right.insert(value);
            }

        } else if(value < this.value) {
            if(this.left == null) {
                this.left = new Element(value);
            } else {
                this.left.insert(value);
            }

        } else {
            this.count++;
        }
    }

    public Element getMaxElement(Element parent) {
        if(this.right != null) {
            return this.right.getMaxElement(this);
        } else {
            return parent;
        }
    }

    public Element getMinElement(Element parent) {
        if(this.left != null) {
            return this.left.getMinElement(this);
        } else {
            return parent;
        }
    }

    private void setParent(int currValue, Element parent, Element newValue) {
        if(parent == null) {
            bst.root = newValue;
        } else {
            if(currValue > parent.value()) {
                parent.right = newValue;
            } else {
                parent.left = newValue;
            }
        }
    }

    public boolean delete(int value, Element parent) {
        bst.compareCount++;
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
                    if(bst.kretnica == false) {
                        Element maxElParent = this.left.getMaxElement(this);

                        if(maxElParent == this) {
                            this.left.right = this.right;
                            setParent(value, parent, maxElParent.left);
                        } else {
                            this.value = maxElParent.right.value;
                            this.count = maxElParent.right.count;

                            maxElParent.right = maxElParent.right.left;
                        }
                                                
                        bst.kretnica = true;
                    } else {

                        Element maxElParent = this.right.getMinElement(this);
                        if(maxElParent == this) {
                            this.right.left = this.left;
                            setParent(value, parent, maxElParent.right);
                        } else {
                            this.value = maxElParent.left.value;
                            this.count = maxElParent.left.count;

                            maxElParent.left = maxElParent.left.right;
                        }
                        bst.kretnica = false;
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

