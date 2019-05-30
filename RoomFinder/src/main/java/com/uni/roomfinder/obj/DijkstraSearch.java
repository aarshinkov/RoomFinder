package com.uni.roomfinder.obj;

import java.util.ArrayList;
import java.util.List;

public class DijkstraSearch implements Searchable {

    private Building building;
    private int startRoomNumber;

    public DijkstraSearch(Building building) {
        this.building = building;
    }

    @Override
    public boolean search(int startRoomNumber, int endRoomNumber) {
        if (!building.containsRoom(startRoomNumber) || !building.containsRoom(endRoomNumber)) {
            return false;
        }

        this.startRoomNumber = startRoomNumber;
        Room startRoom = building.getRoom(startRoomNumber);
        List<Room> queue = new ArrayList<>();
        queue.add(startRoom);

        Room temp;

        while (!queue.isEmpty()) {
            temp = queue.get(0);
            setParentAndCost(temp);

            if (temp.getParent() != null)
                System.out.println("Temp room is: " + temp.getRoomNumber()
                        + " , parent " + temp.getParent().getRoomNumber()
                        + " , distance " + temp.getDistanceToGoal());

            if (temp.getRoomNumber() == endRoomNumber) {
                break;
            }

            temp.setTested(true);
            queue.remove(0);

            for (Room room : building.getLinkedRooms(temp.getRoomNumber())) {
                if (!room.isExpanded() && !queue.contains(room)) {
                    queue.add(room);
                }
            }
            building.sortByDistance(queue);
            temp.setExpanded(true);

        }//end while

        printPath(endRoomNumber);
        return (building.getRoom(endRoomNumber).getParent() != null);
    }

    private void setParentAndCost(Room room) {

        Room temp;
        for (Transition transition : room.getTransitions()) {
            if (transition.getToRoomNumber() == startRoomNumber) continue;
            temp = building.getRoom(transition.getToRoomNumber());
            if ((temp.getParent() == null) || (temp.getDistanceToGoal() > room.getDistanceToGoal() + transition.getLength())) {
                temp.setParent(room);
                temp.setDistanceToGoal(room.getDistanceToGoal() + transition.getLength());
            }
        }
    }

    public void printPath(int roomNumber) {
        Room room = building.getRoom(roomNumber);
        List<Integer> queue = new ArrayList<>();
        do {
            queue.add(room.getRoomNumber());
            room = room.getParent();
        } while (room != null);
        printPathDisplay(queue);
        System.out.println("Overall distance: " + building.getRoom(roomNumber).getDistanceToGoal());
    }

    private void printPathDisplay(List<Integer> queue) {
        for (int i = queue.size() - 1; i >= 0; i--) {
            if (i == 0) {
                System.out.print(queue.get(i) + "! ");
            } else {
                System.out.print(queue.get(i) + " -> ");
            }
        }
    }

    @Override
    public void getResult() {

    }
}
