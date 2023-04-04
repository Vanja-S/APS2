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
        tbl.delete(11);
        tbl.insert(22);
        tbl.insert(33);
        tbl.insert(12);
        tbl.insert(9);
        tbl.printOut();
        tbl.find(11);
        tbl.find(9);
        tbl.find(42);
    }
}