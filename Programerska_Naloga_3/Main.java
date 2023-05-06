package Programerska_Naloga_3;

public class Main {
    public static void main(String[] args) {
        binheap bh = new binheap();

        bh.printElements();
        bh.printMin();
        bh.printComparisons();
        bh.insert(33);
        bh.insert(23);
        bh.insert(13);
        bh.insert(9);
        bh.insert(27);
        bh.insert(7);
        bh.insert(17);
        bh.printElements();
        bh.printMin();
        bh.printComparisons();
        bh.deleteMin();
        bh.printElements();
        bh.printComparisons();
        bh.deleteMin();
        bh.printElements();
        bh.deleteMin();
        bh.printElements();
        bh.deleteMin();
        bh.deleteMin();
        bh.deleteMin();
        bh.printElements();
        bh.deleteMin();
        bh.printElements();
        bh.printComparisons();
    }
}
