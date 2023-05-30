package Programerska_Naloga_5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GRPH {
    int verticesCount;
    HashMap<Integer, Vertex> vertices;

    public GRPH(int verticesCount) {
        this.verticesCount = verticesCount;
        vertices = new HashMap<Integer, Vertex>();
        for (int i = 0; i < verticesCount; i++) {
            vertices.put(i, new Vertex(i));
        }
    }

    public void addEdge(int from, int to, int cost) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            return;
        }
        Vertex fromVertex = vertices.get(from);
        Vertex toVertex = vertices.get(to);
        fromVertex.addEdge(toVertex, cost);
    }

    public void printShortestDistsFrom(int from) {
        System.out.println("V .. Cena");
        if (from < 0 || from >= verticesCount) {
            System.out.println("None");
            return;
        }
        for (int i = 0; i < verticesCount; i++) {
            Optional<Integer> cost = DFS(from, i);
            System.out.println(i + " .. " + (cost.isPresent() ? cost.get().toString() : "None"));
        }
    }

    private Optional<Integer> DFS(int from, int to) {
        Set<Integer> visited = new HashSet<>();
        return DFSHelper(vertices.get(from), vertices.get(to), 0, visited);
    }

    private Optional<Integer> DFSHelper(Vertex currentVertex, Vertex targetVertex, int cumulativeCost, Set<Integer> visited) {
        if (currentVertex == targetVertex) {
            return Optional.of(cumulativeCost);
        }

        if (visited.contains(currentVertex.id)) {
            return Optional.empty();
        }

        visited.add(currentVertex.id);

        Optional<Integer> lowestCost = Optional.empty();

        for (Edge edge : currentVertex.edges) {
            int newCumulativeCost = cumulativeCost + edge.cost;

            Optional<Integer> cost = DFSHelper(edge.vertexEnd, targetVertex, newCumulativeCost, visited);

            if (cost.isPresent() && (!lowestCost.isPresent() || cost.get() < lowestCost.get())) {
                lowestCost = cost;
            }
        }
        visited.remove(currentVertex.id);

        return lowestCost;
    }
}

class Vertex {
    int id;
    Set<Edge> edges;

    public Vertex(int id) {
        this.id = id;
        edges = new HashSet<Edge>();
    }

    public void addEdge(Vertex end, int cost) {
        this.edges.add(new Edge(end, cost));
    }
}

class Edge {
    public Vertex vertexEnd;
    int cost;

    public Edge(Vertex v1, int cost) {
        vertexEnd = v1;
        this.cost = cost;
    }
}