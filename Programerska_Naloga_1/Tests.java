package Programerska_Naloga_1;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Tests {
    ArrArray tbl = new ArrArray();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @Test
    public void testInsertBasic1() {
        System.setOut(new PrintStream(outContent));
        tbl.insert(0);
        tbl.insert(1);

        tbl.printOut();

        assertEquals("A_0: ...\nA_1: 0/1, 1/1\n", outContent.toString());
    }

    @Test
    public void testInsertBasic2() {
        System.setOut(new PrintStream(outContent));

        tbl.insert(0);
        tbl.insert(1);

        tbl.delete(0);

        tbl.printOut();

        assertEquals("true\nA_0: ...\nA_1: x, 1/1\n", outContent.toString());
    }

    @Test
    public void testInsertBasic3() {
        System.setOut(new PrintStream(outContent));

        tbl.insert(0);
        tbl.insert(1);
        tbl.insert(1);
        tbl.insert(1);
        tbl.insert(1);
        tbl.insert(1);

        tbl.delete(0);

        tbl.printOut();

        assertEquals("true\nA_0: ...\nA_1: x, 1/5\n", outContent.toString());
    }

    @Test
    public void testInsertBasic4() {
        System.setOut(new PrintStream(outContent));

        tbl.insert(0);
        tbl.insert(1);
        tbl.insert(1);
        tbl.insert(1);

        tbl.insert(3);
        tbl.insert(2);

        tbl.printOut();

        assertEquals("A_0: ...\nA_1: ...\nA_2: 0/1, 1/3, 2/1, 3/1\n", outContent.toString());
    }

    @Test
    public void testInsertDeleteBasic1() {
        System.setOut(new PrintStream(outContent));

        tbl.insert(0);
        tbl.insert(1);
        tbl.insert(1);
        tbl.insert(1);

        tbl.insert(3);
        tbl.insert(2);

        tbl.delete(1);
        tbl.delete(1);

        tbl.printOut();

        assertEquals("true\ntrue\nA_0: ...\nA_1: ...\nA_2: 0/1, 1/1, 2/1, 3/1\n", outContent.toString());
    }

    @Test
    public void testInsertDeleteBasic2() {
        System.setOut(new PrintStream(outContent));

        tbl.insert(0);
        tbl.insert(1);
        tbl.insert(1);
        tbl.insert(1);

        tbl.insert(3);
        tbl.insert(2);

        tbl.delete(1);
        tbl.delete(1);
        tbl.delete(1);

        tbl.find(1);

        tbl.printOut();

        assertEquals("true\ntrue\ntrue\nfalse\nA_0: ...\nA_1: ...\nA_2: 0/1, x, 2/1, 3/1\n", outContent.toString());
    }

    @Test
    public void testInsertDeleteBasic3() {
        System.setOut(new PrintStream(outContent));

        tbl.insert(0);
        tbl.insert(1);
        tbl.insert(1);
        tbl.insert(1);

        tbl.insert(3);
        tbl.insert(2);

        tbl.delete(1);
        tbl.delete(1);
        tbl.delete(1);

        tbl.insert(1);

        tbl.find(1);

        tbl.printOut();

        assertEquals("true\ntrue\ntrue\ntrue\nA_0: 1/1\nA_1: ...\nA_2: 0/1, x, 2/1, 3/1\n", outContent.toString());
    }

    @Test
    public void testInsertDeleteBasic4() {
        System.setOut(new PrintStream(outContent));

        tbl.insert(0);
        tbl.insert(1);
        tbl.insert(1);
        tbl.insert(1);

        tbl.insert(2);

        tbl.delete(1);
        tbl.delete(1);
        tbl.delete(1);
        tbl.delete(0);

        tbl.printOut();

        assertEquals("true\ntrue\ntrue\ntrue\nA_0: 2/1\n", outContent.toString());
    }

    @Test
    public void testInsertDeleteBasic5() {
        System.setOut(new PrintStream(outContent));

        tbl.insert(0);
        tbl.insert(1);
        tbl.insert(2);
        tbl.insert(3);

        tbl.delete(1);
        tbl.delete(2);

        tbl.printOut();

        assertEquals("true\ntrue\nA_0: ...\nA_1: ...\nA_2: 0/1, x, x, 3/1\n", outContent.toString());
    }

    @Test
    public void testDelete1() {
        System.setOut(new PrintStream(outContent));
        
        tbl.insert(1);
        tbl.insert(2);
        tbl.insert(2);
        tbl.insert(3);

        tbl.insert(4);

        tbl.delete(4);
        tbl.delete(3);
        tbl.delete(1);
        tbl.delete(2);
        tbl.delete(2);

        tbl.printOut();

        assertEquals("true\ntrue\ntrue\ntrue\ntrue\nA_0: ...\nA_1: ...\nA_2: x, 2/1, x, x\n", outContent.toString());
    }
}
