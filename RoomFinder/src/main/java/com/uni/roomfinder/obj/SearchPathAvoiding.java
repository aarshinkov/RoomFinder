package com.uni.roomfinder.obj;

import java.util.ArrayList;
import java.util.List;

public class SearchPathAvoiding implements Searchable {

    private Building building;
    private Type avoidedType;
    private int startRoomNumber;

    public SearchPathAvoiding(Building building, Type avoidedType) {
        this.building = building;
        this.avoidedType = avoidedType;
    }

    @Override
    public boolean search(int startRoomNumber, int endRoomNumber) {
        if (!building.containsRoom(startRoomNumber) || !building.containsRoom(endRoomNumber)) {
            System.out.println("One or both of the rooms does not exist");
            return false;
        }

        Room startRoom = building.getRoom(startRoomNumber);
        List<Room> queue = new ArrayList<>();
        queue.add(startRoom);

        Room temp;

        while (!queue.isEmpty()) {
            temp = queue.get(0);
            System.out.println("Temp room is: " + temp.getRoomNumber());

            if (temp.getRoomNumber() == endRoomNumber) {
                return true;
            }

            temp.setTested(true);
            queue.remove(0);

            for (Room room : building.getLinkedRooms(temp.getRoomNumber())) {
                if (!room.isTested() && !queue.contains(room)) {
                    queue.add(room);
                }
            }

            temp.setExpanded(true);

        }//end while

        return false;
    }

    public boolean search3(int startRoomNumber, int endRoomNumber) {
        if (!building.containsRoom(startRoomNumber) || !building.containsRoom(endRoomNumber)) {
            System.out.println("One or both of the rooms does not exist");
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
                printPath(endRoomNumber);
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

//        printPath(endName);
//        return (myMap.getNode(endName).parent != null);

        return true;
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
        StringBuilder path = new StringBuilder();
        do {
            path.append(room.getRoomNumber());
            room = room.getParent();
        } while (room != null);
        path.reverse();
        System.out.println(path + " distance: " + building.getRoom(roomNumber).getDistanceToGoal());
    }
}
