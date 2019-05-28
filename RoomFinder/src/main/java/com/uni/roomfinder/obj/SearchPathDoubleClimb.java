package com.uni.roomfinder.obj;

import com.uni.roomfinder.reference.Node;

import java.util.ArrayList;
import java.util.List;

public class SearchPathDoubleClimb implements Searchable {

    private Building building;

    public SearchPathDoubleClimb(Building building) {
        this.building = building;
    }

    @Override
    public boolean search(int startRoomNumber, int endRoomNumber) {

        if (!building.containsRoom(startRoomNumber) || !building.containsRoom(endRoomNumber)) {
            return false;
        }

        Room startRoom = building.getRoom(startRoomNumber);
        List<Room> list = new ArrayList<>();
        list.add(startRoom);
        startRoom.setDepth(0);

        Room temp;

        while (!list.isEmpty()) {
            temp = list.get(0);
            System.out.println("Temp room is: " + temp.getRoomNumber()
                    + " and depth is: " + temp.getDepth());

            if (temp.getRoomNumber() == endRoomNumber) {
                building.printPath(temp);
                return true;
            }
            building.setDepths(temp.getRoomNumber());
            temp.setTested(true);
            list.remove(0);

            for (Room room : building.getLinkedRooms(temp.getRoomNumber())) {
                if (!room.isTested() && !list.contains(room)) {
                    list.add(room);
                }
            }
            building.sortByWeight(list);
            temp.setExpanded(true);

        }//end while

        return false;
    }
}
