package Programerska_Naloga_3;

import java.util.Arrays;

public class binheap {

    int size;
    int[] heap;
    int comparisons;

    public binheap() {
        this.size = 0;
        this.heap = new int[10];
        this.comparisons = 0;
    }

    public void insert(int key) {
        /* Insert into the heap */
        heap[size] = key;

        /* Siftup */
        siftUp(size);
        size++;
        if (size == heap.length)
            resize();
    }

    private void siftUp(int index) {
        if (index == 0 || heap[index] == heap[(index - 1) / 2])
            return;
        comparisons++;
        if (heap[index] < heap[(index - 1) / 2]) {
            int temp = heap[index];
            heap[index] = heap[(index - 1) / 2];
            heap[(index - 1) / 2] = temp;
            siftUp((index - 1) / 2);
        }
    }

    private boolean compareMoreThan3(int left, int right) {
        return heap[left] <= heap[right];
    }

    private int compareOther(int leftChildIndex, int rightChildIndex, int minIndex) {
        if(leftChildIndex < size) {
            comparisons++;
            if (heap[leftChildIndex] < heap[minIndex]) {
                return leftChildIndex;
            }
        }

        if(rightChildIndex < size) {
            comparisons++;
            if (heap[rightChildIndex] < heap[minIndex]) {
                return rightChildIndex;
            }
        }

        return minIndex;
    }

    private void siftDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int minIndex = index;

        if (index == 0 && size >= 3) {
            comparisons++;
            if (compareMoreThan3(leftChildIndex, rightChildIndex))
                minIndex = leftChildIndex;
            else
                minIndex = rightChildIndex;
        } else {
            minIndex = compareOther(leftChildIndex, rightChildIndex, minIndex);
        }

        if (minIndex != index) {
            int temp = heap[index];
            heap[index] = heap[minIndex];
            heap[minIndex] = temp;
            siftDown(minIndex);
        }
    }

    public void deleteMin() {
        if (size == 0) {
            System.out.println("false");
            return;
        }
        /* Delete the root */
        System.out.println("true: " + heap[0]);
        heap[0] = heap[size - 1];
        size--;

        /* Siftdown */
        siftDown(0);
    }

    public void printElements() {
        if (size == 0) {
            System.out.println("empty");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (i == size - 1)
                System.out.print(heap[i]);
            else
                System.out.print(heap[i] + ", ");
        }
        System.out.println();
    }

    public void printMin() {
        if (size == 0) {
            System.out.println("MIN: none");
            return;
        }
        System.out.println("MIN: " + heap[0]);
    }

    public void printComparisons() {
        System.out.println("COMPARISONS: " + comparisons);
    }

    private void resize() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }

}