package com.uni.roomfinder.reference;

import java.util.ArrayList;

public class DijkstraSearch implements Searchable {
    Graph myMap;
    String startName;

    public DijkstraSearch(Graph g) {
        this.myMap = g;
    }

    @Override
    public boolean search(String startName, String endName) {

        if (!myMap.containsNode(startName) || !myMap.containsNode(endName)) {
            return false;
        }

        this.startName = startName;
        Node startNode = myMap.getNode(startName);
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(startNode);

        Node temp;

        while (!queue.isEmpty()) {
            temp = queue.get(0);
            setParentAndCost(temp);

            if (temp.parent != null)
                System.out.println("Temp node is: " + temp.name
                        + " , parent " + temp.parent.name
                        + " , distance " + temp.distanceToGoal);

            if (temp.name.equals(endName)) {
                printPath(endName);
            }

            temp.isTested = true;
            queue.remove(0);

            for (Node node : myMap.getLinkedNodes(temp.name)) {
                if (!node.isExpanded && !queue.contains(node)) {
                    queue.add(node);
                }
            }
            myMap.sortByDistance(queue);
            temp.isExpanded = true;

        }//end while

        printPath(endName);
        return (myMap.getNode(endName).parent != null);
    }

    private void setParentAndCost(Node node) {

        Node temp;
        for (Link l : node.links) {
            if (l.toNodeName.equals(startName)) continue;
            temp = myMap.getNode(l.toNodeName);
            if ((temp.parent == null) || (temp.distanceToGoal > node.distanceToGoal + l.length)) {
                temp.parent = node;
                temp.distanceToGoal = node.distanceToGoal + l.length;
            }
        }
    }

    public void printPath(String name) {
        Node node = myMap.getNode(name);
        StringBuilder path = new StringBuilder();
        do {
            path.append(node.name);
            node = node.parent;
        } while (node != null);
        path.reverse();
        System.out.println(path + " distance: " + myMap.getNode(name).distanceToGoal);
    }
}

