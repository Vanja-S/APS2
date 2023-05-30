package Programerska_Naloga_5;

public class Main {
    public static void main(String[] args) {
        GRPH g = new GRPH(5);
        g.addEdge(0, 1, -3);
        g.addEdge(0, 3, -7);
        g.addEdge(0, 4, -8);
        g.addEdge(1, 3, -4);
        g.addEdge(2, 1, -1);
        g.addEdge(2, 3, -2);
        g.addEdge(4, 3, -3);
        
        for (int i = 0; i < 5; i++) {
            g.printShortestDistsFrom(i);
            System.out.println();
        }

        // GRPH g = new GRPH(4);
        // g.addEdge(0, 10, 1);
        // g.addEdge(0, 3, 2);
        // g.addEdge(1, 2, 3);
        // g.addEdge(3, 2, 1);
        // g.printShortestDistsFrom(0);
    }
}
