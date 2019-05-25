package com.uni.roomfinder.reference;


public class MainClass {

    static Graph graph = new Graph();

    public static void findPath(String startName, String endName,
                                Searchable searcher) {
        graph.resetAllNodes();
        if (searcher.search(startName, endName)) {
            System.out.println("HAVE A PATH");
        } else {
            System.out.println("THERE IS NO PATH");
        }
    }

    public static void init() {
        graph.addNode(new Node("A", 2, 4));
        graph.addNode(new Node("B", 1, 2));
        graph.addNode(new Node("C", 4, 2));
        graph.addNode(new Node("D", 0, 0));
        graph.addNode(new Node("E", 2, 0));
        graph.addNode(new Node("F", 5, 0));

        graph.addLink("A", "B", true, 2);
        graph.addLink("A", "C", true, 1);
        graph.addLink("D", "B", true, 3);
        graph.addLink("E", "B", true, 10);
        graph.addLink("C", "E", true, 4);
        graph.addLink("C", "F", true, 17);
        graph.addLink("E", "F", true, 3);
        graph.addLink("B", "C", true, 1);
        graph.addLink("E", "D", true, 4);
    }

    public static void main(String[] args) {
        init();
        findPath("D", "A", new DijkstraSearch(graph));
    }

}

