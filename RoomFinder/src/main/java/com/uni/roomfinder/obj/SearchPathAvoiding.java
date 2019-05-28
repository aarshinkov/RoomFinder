package com.uni.roomfinder.obj;

import java.util.ArrayList;
import java.util.List;

public class SearchPathAvoiding implements Searchable {

    private Building building;
    private Type avoidedType;

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

            for (Room room : building.getLinkedRoomsAvoid(temp.getRoomNumber(), avoidedType)) {
                if (!room.isTested() && !queue.contains(room)) {
                    queue.add(room);
                }
            }

            temp.setExpanded(true);

        }//end while

        return false;
    }
}
