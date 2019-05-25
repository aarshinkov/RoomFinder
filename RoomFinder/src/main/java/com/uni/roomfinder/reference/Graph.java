package com.uni.roomfinder.reference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Graph {

    public HashMap<String, Node> myGraph = new HashMap<>();
    Comparator<Node> byWeight = (Node n1, Node n2)
            -> Double.compare(n1.weight, n2.weight);
    Comparator<Node> byName = (Node n1, Node n2)
            -> n1.name.compareTo(n2.name);
    Comparator<Node> byDistance = (Node n1, Node n2)
            -> Double.compare(n1.distanceToGoal, n2.distanceToGoal);

    public void addNode(Node node) {

        if (node == null || myGraph.containsKey(node.name)) {
            System.err.println("Node name already exists");
            return;
        }
        myGraph.put(node.name, node);
    }

    public void addLink(String startName, String endName, boolean isBiDirectional, double l) {
        if (myGraph.containsKey(startName) && myGraph.containsKey(endName)) {
            Node startNode = myGraph.get(startName);
            Node endNode = myGraph.get(endName);
            startNode.links.add(new Link(endName, l));

            if (isBiDirectional) {
                endNode.links.add(new Link(startName, l));
            }
        } else {
            System.err.println("Wrong or missing node names");
        }
    }

    public Node getNode(String name) {
        return myGraph.get(name);
    }

    public boolean containsNode(String name) {
        return myGraph.containsKey(name);
    }

    public ArrayList<Node> getLinkedNodes(String name) {
        ArrayList<Node> linkedNodes = new ArrayList<>();
        Node node = myGraph.get(name);
        for (Link l : node.links) {
            linkedNodes.add(myGraph.get(l.toNodeName));
        }
        return linkedNodes;
    }

    public void resetAllNodes() {
        myGraph.forEach((k, v) -> v.reset());
    }

    public void sortByWeight(ArrayList<Node> list) {

        list.sort(byWeight.thenComparing(byName));
    }

    public void sortByDistance(ArrayList<Node> list) {

        list.sort(byDistance.thenComparing(byName));
    }

    public void setDepths(String name) {
        Node node = getNode(name);
        for (Node n : getLinkedNodes(name)) {
            if (n.depth == -1) {
                n.depth = node.depth + 1;
            }
        }
    }

    public void printPath(Node current) {
        StringBuilder path = new StringBuilder();

        while (current.depth != 0) {
            path.append(current.name);

            for (Node node : getLinkedNodes(current.name)) {
                if (node.depth == current.depth - 1) {
                    current = node;
                    break;
                }
            }
        }

        path.append(current.name);


        System.out.println(path.reverse());
    }

    public double findDistance(String nameOne, String nameTwo) {

        Node nodeOne = getNode(nameOne);
        Node nodeTwo = getNode(nameTwo);

        double distance = Math.sqrt(Math.pow(nodeOne.x - nodeTwo.x, 2)
                + Math.pow(nodeOne.y - nodeTwo.y, 2));
        return distance;
    }


}

