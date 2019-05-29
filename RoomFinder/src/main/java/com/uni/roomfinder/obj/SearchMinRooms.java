package com.uni.roomfinder.obj;

import java.util.ArrayList;
import java.util.List;

public class SearchMinRooms implements Searchable {

    private Building building;
    private int startRoomNumber;
    private int endFloor;

    public SearchMinRooms(Building building) {
        this.building = building;
    }

    @Override
    public boolean search(int startRoomNumber, int endRoomNumber) {
        if (!building.containsRoom(startRoomNumber) || !building.containsRoom(endRoomNumber)) {
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
}
