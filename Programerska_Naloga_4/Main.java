package Programerska_Naloga_4;


public class Main {
    public static void main(String[] args) {
        bst b = new bst();

        b.insert(12);
        b.insert(22);
        b.insert(33);
        b.insert(4);
        b.insert(14);
        b.insert(6);
        b.insert(24);
        b.printPreOrder();
        b.printInOrder();
        b.printPostOrder();
        b.printComparisons();
    }
}
