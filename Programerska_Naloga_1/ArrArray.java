package Programerska_Naloga_1;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrArray {
    // Tabela tabel
    private int[][] table;
    static private int k = 1;
    private int[] subtable_index;

    public ArrArray() {
        this.table = new int[k][];
        this.subtable_index = new int[k];
        for (int i = 0; i < k; i++) {

        }
    }

    public void insert(int e) {
        // Temp table
        int[] temp = new int[1];
        temp[0] = e;

        // Insert the element into the table
        for (int i = 0; i < k; i++) {
            if (table[i] == null) {
                table[i] = new int[(int) Math.pow(2, i)];
            }
            // If the subtable is empty merge temp and all previous subtables and sort
            if (subtable_index[i] == 0) {
                emptyMerge(i, temp);
                return;
            } else if (subtable_index[i] == (int) Math.pow(2, i)) {
                if (i == k - 1) {
                    resize();
                    if (table[i + 1] == null) {
                        table[i + 1] = new int[(int) Math.pow(2, i + 1)];
                    }
                    emptyMerge(i + 1, temp);
                    return;
                }
                continue;
            }
            // else just merge the current subtable and temp
            else {
                nonEmptyMerge(i, temp);
                return;
            }
        }
    }

    private void nonEmptyMerge(int i, int[] temp) {
        table[i] = concatIntArr(table[i], temp);
        subtable_index[i]++;
        Arrays.sort(table[i]);
    }

    private void emptyMerge(int i, int[] temp) {
        table[i][0] = temp[0];
        subtable_index[i]++;
        for (int j = 0; j < i; j++) {
            table[i] = concatIntArr(Arrays.copyOfRange(table[i], 0, subtable_index[i]), table[j]);
            subtable_index[i] += subtable_index[j];
            subtable_index[j] = 0;
            Arrays.sort(table[i]);
        }
    }

    private int[] concatIntArr(int[] array1, int[] array2) {
        return IntStream.concat(Arrays.stream(array1), Arrays.stream(array2)).toArray();
    }

    public boolean find(int e) {
        return false;
    }

    public boolean delete(int e) {
        return false;
    }

    public void printOut() {
        IntStream stream = Arrays.stream(subtable_index);
        if (stream.sum() == 0) {
            System.out.println("empty");
            return;
        }

        for (int i = 0; i < k; i++) {
            if (subtable_index[i] == 0) {
                System.out.println("A_" + i + ": ...");
            } else {
                System.out.print("A_" + i + ":");
                stream = Arrays.stream(table[i]);
                Map<Integer, Long> count = stream.boxed()
                        .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));

                final int[] index = { 0 };
                int last = count.size() - 1;
                count.forEach((key, value) -> {
                    if (index[0] == last) {
                        System.out.print(" " + key + "/" + value);
                    } else {
                        System.out.print(" " + key + "/" + value + ",");
                    }
                    index[0]++;
                });
                System.out.println();
            }
        }
    }

    private void resize() {
        int newK = k + 1; // Compute new size of first dimension
        int[][] newTable = new int[newK][]; // Create new array with the new size
        int[] new_subtable_index = new int[newK];
        // Copy existing data into the new array
        for (int i = 0; i < k; i++) {
            newTable[i] = Arrays.copyOf(table[i], table[i].length);
            new_subtable_index[i] = subtable_index[i];
        }

        // Update the reference to the old array with the new one, and the k value
        this.table = newTable;
        this.subtable_index = new_subtable_index;
        k = newK;
    }
}
