package com.uni.roomfinder.reference;

import java.util.ArrayList;

public class GreedyByWeight implements Searchable {

    Graph myMap;

    public GreedyByWeight(Graph g) {
        this.myMap = g;
    }

    @Override
    public boolean search(String startName, String endName) {
        if (!myMap.containsNode(startName) || !myMap.containsNode(endName)) {
            return false;
        }

        Node startNode = myMap.getNode(startName);
        ArrayList<Node> list = new ArrayList<>();
        list.add(startNode);
        startNode.depth = 0;

        Node temp;

        while (!list.isEmpty()) {
            temp = list.get(0);
            System.out.println("Temp node is: " + temp.name
                    + " and depth is: " + temp.depth);

            if (temp.name.equals(endName)) {
                myMap.printPath(temp);
                return true;
            }
            myMap.setDepths(temp.name);
            temp.isTested = true;
            list.remove(0);

            for (Node node : myMap.getLinkedNodes(temp.name)) {
                if (!node.isTested && !list.contains(node)) {
                    list.add(node);
                }
            }
            myMap.sortByWeight(list);
            temp.isExpanded = true;

        }//end while

        return false;
    }

}

