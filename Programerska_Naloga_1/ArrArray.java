package Programerska_Naloga_1;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrArray {
    // Tabela tabel
    private ArrayElement[][] table;
    private int[][] deleted;
    static private int k = 1;
    private int[] subtable_index;

    public ArrArray() {
        this.table = new ArrayElement[k][];
        this.deleted = new int[k][];
        this.subtable_index = new int[k];
    }

    public void insert(int e) {
        // Temp table
        ArrayElement[] temp = new ArrayElement[1];
        temp[0] = new ArrayElement(e);

        // Insert the element into the table
        for (int i = 0; i < k; i++) {
            if (table[i] == null) {
                table[i] = new ArrayElement[(int) Math.pow(2, i)];
                deleted[i] = new int[(int) Math.pow(2, i)];
            }
            // If the element already exists
            int[] exist = exists(temp[0]);
            if (exist.length != 0) {
                table[exist[0]][exist[1]].count++;
                return;
            }
            // If the subtable is empty merge temp and all previous subtables and sort
            else if (subtable_index[i] == 0) {
                emptyMerge(i, temp);
                return;
            }
            // If the subtable if full and has any elements deleted just insert where the
            // element was deleted
            else if (subtable_index[i] == (int) Math.pow(2, i)) {
                if (Arrays.stream(deleted[i]).sum() > 0) {
                    int insert_place = findIndex(deleted[i], 1);
                    table[i][insert_place] = temp[0];
                    deleted[i][insert_place] = 0;
                    return;
                }

                else if (i == k - 1) {
                    resize();
                    if (table[i + 1] == null) {
                        table[i + 1] = new ArrayElement[(int) Math.pow(2, i + 1)];
                        deleted[i + 1] = new int[(int) Math.pow(2, i + 1)];
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

    public static int findIndex(int[] arr, int e) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == e) {
                return i;
            }
        }
        return -1;
    }

    private void nonEmptyMerge(int i, ArrayElement[] temp) {
        table[i] = concatArrayElements(table[i], temp);
        subtable_index[i]++;
        Arrays.sort(table[i]);
    }

    private void emptyMerge(int i, ArrayElement[] temp) {
        table[i][0] = temp[0];
        subtable_index[i]++;
        for (int j = 0; j < i; j++) {
            table[i] = concatArrayElements(Arrays.copyOfRange(table[i], 0, subtable_index[i]), table[j]);
            subtable_index[i] += subtable_index[j];
            subtable_index[j] = 0;
            Arrays.sort(table[i]);
        }
    }

    public ArrayElement[] concatArrayElements(ArrayElement[] array1, ArrayElement[] array2) {
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2)).toArray(ArrayElement[]::new);
    }

    private int[] exists(ArrayElement e) {
        for (int i = 0; i < k; i++) {
            if (subtable_index[i] == 0) {
                continue;
            } else {
                // If e is the min of the subtable
                if (e.compareTo(table[i][0]) == 0 && deleted[i][0] != 1) {
                    return new int[] { i, 0 };
                }
                // If e is the max of the subtable
                else if (e.compareTo(table[i][subtable_index[i] - 1]) == 0 && deleted[i][subtable_index[i] - 1] != 1) {
                    return new int[] { i, subtable_index[i] - 1 };
                }
                // If e is not in the sorted subtable
                else if (e.compareTo(table[i][0]) < 0 || e.compareTo(table[i][subtable_index[i] - 1]) > 0) {
                    continue;
                }
                // Else e must be in the subtable and we perform the binary search to find it
                else {
                    int j = binarySearch(table[i], e);
                    if (deleted[i][j] != 1) {
                        return new int[] { i, j };
                    }
                }
            }
        }
        return new int[0];
    }

    public boolean find(int e) {
        int[] exist = exists(new ArrayElement(e));
        if (exist.length == 0) {
            System.out.println("false");
            return false;
        }
        System.out.println("true");
        return true;
    }

    private int binarySearch(ArrayElement[] table, ArrayElement e) {
        int i = 0;
        int j = table.length - 1;
        while (e.compareTo(table[(i + j) / 2]) != 0) {
            if (e.compareTo(table[(i + j) / 2]) > 0) {
                i = (i + j) / 2;
            } else {
                j = (i + j) / 2;
            }
        }

        return (i + j) / 2;
    }

    public void delete(int e) {
        int[] index = exists(new ArrayElement(e));
        if (index.length == 0) {
            System.out.println("false");
            return;
        }
        if (--table[index[0]][index[1]].count == 0)
            deleted[index[0]][index[1]] = 1;
        System.out.println("true");
    }

    public void printOut() {
        if (Arrays.stream(subtable_index).sum() == 0) {
            System.out.println("empty");
            return;
        }

        for (int i = 0; i < k; i++) {
            if (subtable_index[i] == 0) {
                System.out.println("A_" + i + ": ...");
            } else {
                System.out.print("A_" + i + ":");

                for (int j = 0; j < table[i].length; j++) {
                    if (deleted[i][j] == 1) {
                        if (j == table[i].length - 1)
                            System.out.print(" " + "x");
                        else
                            System.out.print(" " + "x,");
                    } else {
                        if (j == table[i].length - 1)
                            System.out.print(" " + table[i][j].integer + "/" + table[i][j].count);
                        else
                            System.out.print(" " + table[i][j].integer + "/" + table[i][j].count + ",");
                    }
                }

                System.out.println();
            }
        }
    }

    // public void printOut() {
    // IntStream stream = Arrays.stream(subtable_index);
    // if (stream.sum() == 0) {
    // System.out.println("empty");
    // return;
    // }

    // for (int i = 0; i < k; i++) {
    // if (subtable_index[i] == 0) {
    // System.out.println("A_" + i + ": ...");
    // } else {
    // System.out.print("A_" + i + ":");
    // stream = Arrays.stream(table[i]);
    // int[] deletedRow = deleted[i];
    // Map<Integer, Long> count = stream.filter(e -> deletedRow[e] == 0)
    // .boxed()
    // .collect(Collectors.groupingBy(Function.identity(), TreeMap::new,
    // Collectors.counting()));

    // final int[] index = { 0 };
    // int last = count.size() - 1;
    // count.forEach((key, value) -> {
    // if (deletedRow[key] == 1) {
    // if (index[0] == last) {
    // System.out.print(" x");

    // } else {
    // System.out.print(" x,");
    // return;
    // }
    // }
    // if (index[0] == last) {
    // System.out.print(" " + key + "/" + value);
    // } else {
    // System.out.print(" " + key + "/" + value + ",");
    // }
    // index[0]++;
    // });
    // System.out.println();
    // }
    // }
    // }

    private void resize() {
        int newK = k + 1; // Compute new size of first dimension
        ArrayElement[][] newTable = new ArrayElement[newK][]; // Create new array with the new size
        int[][] newDeleted = new int[newK][];
        int[] new_subtable_index = new int[newK];

        for (int i = 0; i < k; i++) {
            newTable[i] = Arrays.copyOf(table[i], table[i].length);
            newDeleted[i] = Arrays.copyOf(deleted[i], deleted[i].length);
            new_subtable_index[i] = subtable_index[i];
        }

        this.table = newTable;
        this.subtable_index = new_subtable_index;
        this.deleted = newDeleted;
        k = newK;
    }
}

class ArrayElement implements Comparable<ArrayElement> {
    public int integer;
    public long count;

    public ArrayElement(int e) {
        this.integer = e;
        this.count = 1;
    }

    public int compareTo(ArrayElement a) {
        if (this.integer < a.integer)
            return -1;
        else if (this.integer == a.integer)
            return 0;
        else
            return 1;
    }
}