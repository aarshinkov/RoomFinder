package com.uni.roomfinder.reference;

import java.util.ArrayList;

public class BreadthSearch implements Searchable {

    Graph myMap;

    public BreadthSearch(Graph g) {
        this.myMap = g;
    }

    @Override
    public boolean search(String startName, String endName) {

        if (!myMap.containsNode(startName) || !myMap.containsNode(endName)) {
            return false;
        }

        Node startNode = myMap.getNode(startName);
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(startNode);

        Node temp;

        while (!queue.isEmpty()) {
            temp = queue.get(0);
            System.out.println("Temp node is: " + temp.name);

            if (temp.name.equals(endName)) {
                return true;
            }

            temp.isTested = true;
            queue.remove(0);

            for (Node node : myMap.getLinkedNodes(temp.name)) {
                if (!node.isTested && !queue.contains(node)) {
                    queue.add(node);
                }
            }

            temp.isExpanded = true;

        }//end while

        return false;
    }

}

