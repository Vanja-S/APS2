package Programerska_Naloga_5;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

public class LBR {
    int[][] cells;
    int rows;
    int cols;
    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    String[][] pathGrid;

    public LBR(int[][] cells) {
        this.cells = cells;
        rows = cells.length;
        cols = cells[0].length;
        pathGrid = new String[rows][cols];
    }

    public void printPath(int from, int to) {
        int fromRow = (from - 1) / cols;
        int fromCol = (from - 1) % cols;
        int toRow = (to - 1) / cols;
        int toCol = (to - 1) % cols;

        if (fromRow < 0 || fromRow >= rows || fromCol < 0 || fromCol >= cols ||
                toRow < 0 || toRow >= rows || toCol < 0 || toCol >= cols) {
            System.out.println("None");
            return;
        }

        boolean found = bfs(fromRow, fromCol, toRow, toCol);

        if (!found) {
            System.out.println("None");
            return;
        }

        int pathLength = Integer.parseInt(pathGrid[toRow][toCol]);
        System.out.println("Length " + pathLength + ":");

        List<Integer> path = reconstructPath(fromRow, fromCol, toRow, toCol);
        for (int cell : path) {
            System.out.println(cell + " ");
        }
        System.out.println();
    }

    private boolean bfs(int fromRow, int fromCol, int toRow, int toCol) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        queue.offer(new int[] { fromRow, fromCol });
        visited[fromRow][fromCol] = true;
        pathGrid[fromRow][fromCol] = "0";

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            if (row == toRow && col == toCol) {
                return true;
            }

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isValidMove(newRow, newCol) && !visited[newRow][newCol]) {
                    queue.offer(new int[] { newRow, newCol });
                    visited[newRow][newCol] = true;
                    int pathLength = Integer.parseInt(pathGrid[row][col]) + 1;
                    pathGrid[newRow][newCol] = String.valueOf(pathLength);
                }
            }
        }

        return false;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && cells[row][col] == 0;
    }

    private List<Integer> reconstructPath(int fromRow, int fromCol, int toRow, int toCol) {
        List<Integer> path = new ArrayList<>();
        int row = toRow;
        int col = toCol;

        while (row != fromRow || col != fromCol) {
            path.add(row * cols + col + 1);
            int pathLength = Integer.parseInt(pathGrid[row][col]);

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isValidMove(newRow, newCol) && Integer.parseInt(pathGrid[newRow][newCol]) == pathLength - 1) {
                    row = newRow;
                    col = newCol;
                    break;
                }
            }
        }

        path.add(fromRow * cols + fromCol + 1);
        Collections.reverse(path);

        return path;
    }
}