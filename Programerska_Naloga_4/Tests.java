package Programerska_Naloga_4;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Tests {
    static bst bst = new bst();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void testDeleteBasic1() {
        System.setOut(new PrintStream(outContent));

        for (int i = 0; i < 5; i++)
            bst.insert(i);

        bst.printInOrder();
        bst.printPostOrder();
        bst.printPreOrder();
        // Comparisons = 10
        assertEquals("0/1 | 1/1 | 2/1 | 3/1 | 4/1 | \n4/1 | 3/1 | 2/1 | 1/1 | 0/1 | \n0/1 | 1/1 | 2/1 | 3/1 | 4/1 | \n",
                outContent.toString());

        bst.delete(0);
        bst.printComparisons();
        assertEquals(
                "0/1 | 1/1 | 2/1 | 3/1 | 4/1 | \n4/1 | 3/1 | 2/1 | 1/1 | 0/1 | \n0/1 | 1/1 | 2/1 | 3/1 | 4/1 | \ntrue\nCOMPARISONS: 11\n",
                outContent.toString());
    }

    @Test
    public void testDeleteBasic2() {
        System.setOut(new PrintStream(outContent));

        bst.insert(15);
        bst.insert(13);
        bst.insert(1);
        bst.insert(0);

        bst.insert(12);
        bst.insert(16);
        bst.insert(20);
        // Comparisons = 12
        // bst.printComparisons();

        // Tuki more bit comparisons samo za delete 4
        bst.delete(0);
        bst.printComparisons();
        assertEquals("true\nCOMPARISONS: 16\n", outContent.toString());
    }

    @Test
    public void testDeleteBasic3() {
        System.setOut(new PrintStream(outContent));

        bst.insert(15);
        bst.insert(13);
        bst.insert(1);
        bst.insert(0);

        bst.insert(12);
        bst.insert(16);
        bst.insert(20);

        bst.insert(40);
        bst.insert(34);
        bst.insert(25);
        bst.insert(19);
        bst.insert(35);

        // Comparisons = 32
        // bst.printComparisons();

        // Samo +3
        bst.delete(20);
        bst.printComparisons();
        assertEquals("true\nCOMPARISONS: 35\n", outContent.toString());
    }

    @Test
    public void testDeleteBasic4() {
        System.setOut(new PrintStream(outContent));

        bst.insert(15);
        bst.insert(13);
        bst.insert(1);
        bst.insert(0);

        bst.insert(12);
        bst.insert(16);
        bst.insert(20);

        bst.insert(40);
        bst.insert(34);
        bst.insert(25);
        bst.insert(19);
        bst.insert(35);

        // Comparisons = 32
        // bst.printComparisons();

        // Samo +3
        bst.delete(20);
        bst.printComparisons();

        // Samo +4
        bst.delete(40);
        bst.printComparisons();
        assertEquals("true\nCOMPARISONS: 35\ntrue\nCOMPARISONS: 39\n", outContent.toString());
    }

    @Test
    public void testDeleteBasic5() {
        System.setOut(new PrintStream(outContent));

        bst.insert(10);
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(32);
        bst.insert(1);
        bst.insert(0);
        bst.insert(36);
        bst.insert(42);
        bst.insert(34);
        bst.insert(25);
        bst.insert(19);
        bst.insert(9);
        bst.insert(3);
        bst.insert(46);
        bst.insert(12);
        bst.insert(55);
        bst.insert(54);

        // Comparisons = 55
        // bst.printComparisons();

        // +3
        bst.delete(9);
        bst.printComparisons();

        // +4
        bst.delete(20);
        bst.printComparisons();

        // +2
        bst.delete(50);
        bst.printComparisons();

        // +4
        bst.delete(32);
        bst.printComparisons();

        // +4
        bst.delete(19);
        bst.printComparisons();

        assertEquals(
                "true\nCOMPARISONS: 58\ntrue\nCOMPARISONS: 62\ntrue\nCOMPARISONS: 64\ntrue\nCOMPARISONS: 68\ntrue\nCOMPARISONS: 72\n",
                outContent.toString());
    }

    @Test
    public void testDeleteFail() {
        System.setOut(new PrintStream(outContent));

        bst.insert(10);
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(32);
        bst.insert(1);
        bst.insert(0);
        bst.insert(36);
        bst.insert(42);
        bst.insert(34);
        bst.insert(25);
        bst.insert(19);
        bst.insert(9);
        bst.insert(3);
        bst.insert(46);
        bst.insert(12);
        bst.insert(55);
        bst.insert(54);

        // Comparisons = 55
        // bst.printComparisons();

        // +6
        bst.delete(11);
        bst.printComparisons();

        // +3
        bst.delete(100);
        bst.printComparisons();

        // +7
        bst.delete(47);
        bst.printComparisons();

        assertEquals("false\nCOMPARISONS: 61\nfalse\nCOMPARISONS: 64\nfalse\nCOMPARISONS: 71\n", outContent.toString());
    }

    @Test
    public void testDeleteRoot1() {
        System.setOut(new PrintStream(outContent));

        bst.insert(10);
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(32);
        bst.insert(1);
        bst.insert(0);
        bst.insert(36);
        bst.insert(42);
        bst.insert(34);
        bst.insert(25);
        bst.insert(19);
        bst.insert(9);
        bst.insert(3);
        bst.insert(46);
        bst.insert(12);
        bst.insert(55);
        bst.insert(54);

        // Comparisons = 55
        // bst.printComparisons();

        // +1
        bst.delete(10);
        bst.printComparisons();

        // +1
        bst.delete(9);
        bst.printComparisons();
        
        // +1
        bst.delete(3);
        bst.printComparisons();

        assertEquals("true\nCOMPARISONS: 56\ntrue\nCOMPARISONS: 57\ntrue\nCOMPARISONS: 58\n", outContent.toString());
    }

    @Test
    public void testDeleteRoot2() {
        System.setOut(new PrintStream(outContent));

        bst.insert(4);
        bst.insert(5);
        bst.insert(2);

        // Comparisons = 2
        // bst.printComparisons();

        // +1
        bst.delete(4);
        bst.printComparisons();

        // +1
        bst.delete(2);
        bst.printComparisons();

        assertEquals("true\nCOMPARISONS: 3\ntrue\nCOMPARISONS: 4\n", outContent.toString());
    }

}
