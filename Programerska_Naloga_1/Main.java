package Programerska_Naloga_1;

public class Main {

    public static void main(String[] args) {
        ArrArray tbl = new ArrArray();
        tbl.insert(7);
        tbl.insert(5);
        tbl.insert(9);
        tbl.insert(3);
        tbl.insert(15);
        tbl.insert(11);
        tbl.insert(17);
        tbl.delete(15);
        tbl.insert(11);
        tbl.delete(5);
        tbl.printOut();
        tbl.find(7);
        tbl.find(5);
        tbl.find(42);
    }
}