package Programerska_Naloga_4;

public class testi {
    Bi lahko kdo ki je dobil 10 tock 4 nalogo sprobal te teste :
public class Naloga4 {
    public static void main(String[] args) {

        // TEST 1
        System.out.println("TEST1");
        bst b = new bst();
        b.insert(19);
        b.insert(11);
        b.insert(23);
        b.insert(19);
        b.insert(29);
        System.out.println("Preorder --");
        b.printPreOrder();
        System.out.println("Inorder --");
        b.printInOrder();
        System.out.println("Postorder --");
        b.printPostOrder();
        b.printComparisons();

//TEST 2
        System.out.println();
        System.out.println("TEST2");
        bst b2 = new bst();
        b2.insert(19);
        b2.printComparisons();
        b2.insert(11);
        b2.insert(23);
        b2.insert(31);
        b2.insert(42);
        b2.insert(29);
        b.printComparisons();
        b2.find(29);
        b.printComparisons();
        b2.insert(23);
        b2.insert(29);
        b2.delete(31);
        b2.find(31);
        b2.find(23);
        System.out.println("Inorder --");
        b2.printInOrder();
        b2.printComparisons();
        System.out.println("TEST3");
//TEST 3
        System.out.println();
        bst b3 = new bst();
        b3.insert(19);
        b3.insert(19);
        b3.insert(19);
        b3.insert(11);
        b3.insert(9);
        b3.insert(11);
        System.out.println("Inorder --");
        b3.printInOrder();
        b3.delete(23);
        b3.insert(23);
        b3.insert(17);
        b3.insert(20);
        b3.insert(18);
        b3.insert(25);
        System.out.println("Inorder --");
        b3.printInOrder();
        b3.delete(19);
        b3.delete(19);
        b3.delete(11);
        b3.delete(11);
        b3.delete(23);
        b3.find(19);
        System.out.println("Preorder --");
        b3.printPreOrder();
        b.printComparisons();
        System.out.println();
        System.out.println("TEST4");
//TEST 4
        bst b4 = new bst();
        b4.insert(19);
        b4.insert(19);
        b4.find(19);
        b4.delete(19);
        b4.find(19);
        b4.delete(19);
        b4.find(19);
        b4.insert(19);
        b4.insert(9);
        b4.insert(23);
        b4.insert(11);
        b4.insert(21);
        b4.insert(31);
        b4.insert(11);
        System.out.println("Postorder --");
        b4.printPostOrder();
        b4.delete(19);
        b4.delete(11);
        b4.find(19);
        b4.find(11);
        b4.delete(11);
        System.out.println("Postorder --");
        b4.printPostOrder();
        b.printComparisons();
        System.out.println();
        System.out.println("TEST5");
//TEST 5
        bst b5 = new bst();
        b5.insert(29);
        b5.insert(15);
        b5.insert(43);
        b5.insert(7);
        b5.insert(23);
        b5.insert(35);
        b5.insert(51);
        b5.delete(29);
        System.out.println("Preorder --");
        b5.printPreOrder();
        b5.printComparisons();
        System.out.println();
        System.out.println("TEST6");
        // TEST 6
        bst b6 = new bst();
        b6.insert(29);
        b6.insert(15);
        b6.insert(43);
        b6.insert(5);
        b6.insert(23);
        b6.insert(35);
        b6.insert(51);
        b6.insert(3);
        b6.insert(7);
        b6.insert(19);
        b6.insert(27);
        b6.insert(31);
        b6.insert(37);
        b6.insert(47);
        b6.insert(59);
        b6.delete(15);
        b6.delete(43);
        System.out.println("Preorder --");
        b6.printPreOrder();
        System.out.println("Postorder --");
        b6.printPostOrder();
        b.printComparisons();
    }
}
}
