package Programerska_Naloga_2;

public class Main {
    public static void main(String[] args) {
        HTB ht = new HTB(7, 3, 5, 7);

        ht.insert(19);
        ht.insert(11);
        ht.insert(23);
        ht.insert(19);

        ht.printKeys();
        System.out.println("--");
        ht.printCollisions();
    }
}
