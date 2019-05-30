package com.uni.roomfinder.obj;

import java.util.ArrayList;
import java.util.List;

public class SearchMinRooms implements Searchable {

    private Building building;
    private int startRoomNumber;
    private int endFloor;
    private List<Room> path;
    private Priority priority = Priority.NONE;

    public SearchMinRooms(Building building) {
        this.building = building;
    }

    @Override
    public boolean search(int startRoomNumber, int endRoomNumber) {
        if (!building.containsRoom(startRoomNumber) || !building.containsRoom(endRoomNumber)) {
            return false;
        }

        Room startRoom = building.getRoom(startRoomNumber);

        int startFloor = startRoom.getFloor();
        endFloor = building.getRoom(endRoomNumber).getFloor();

        if (startFloor < endFloor) {
            priority = Priority.UPWARDS;
        } else if (startFloor > endFloor) {
            priority = Priority.DOWNWARDS;
        }

        List<Room> queue = new ArrayList<>();
        path = new ArrayList<>();
        queue.add(startRoom);
        path.add(startRoom);

        Room temp;

        while (!queue.isEmpty()) {
            queue = clearUnwantedRooms(queue);
            temp = queue.get(0);
            int currentRoom = temp.getFloor();
            System.out.println("Temp room is: " + temp.getRoomNumber());

            if (temp.getRoomNumber() == endRoomNumber) {
                return true;
            }

            temp.setTested(true);
            queue.remove(0);

            for (Room room : building.getLinkedRooms(temp.getRoomNumber())) {
                if (!room.isTested() && !queue.contains(room)) {
                    if (room.getFloor() != currentRoom) {
                        building.sortByFloor(queue, priority);
                    }
                    queue.add(room);
                }
            }

            temp.setExpanded(true);
        }

        return false;

    }

    private void printPath(List<Room> path) {
        System.out.print("The path is: ");
        for (Room room : path) {
            System.out.print(room.getRoomNumber() + " -> ");
        }
        System.out.println();
    }

//    public boolean search2(int startRoomNumber, int endRoomNumber) {
//        if (!building.containsRoom(startRoomNumber) || !building.containsRoom(endRoomNumber)) {
//            return false;
//        }
//
//        Room startRoom = building.getRoom(startRoomNumber);
//
//        int startFloor = startRoom.getFloor();
//        endFloor = building.getRoom(endRoomNumber).getFloor();
//
//        if (startFloor < endFloor) {
//            priority = Priority.UPWARDS;
//        } else if (startFloor > endFloor) {
//            priority = Priority.DOWNWARDS;
//        }
//
//        List<Room> queue = new ArrayList<>();
//        queue.add(startRoom);
//
//        Room temp;
//
//        while (!queue.isEmpty()) {
//            queue = clearUnwantedRooms(queue);
//            temp = queue.get(0);
//            System.out.println("Temp room is: " + temp.getRoomNumber());
//
//            if (temp.getRoomNumber() == endRoomNumber) {
//                return true;
//            }
//
//            temp.setTested(true);
//            queue.remove(0);
//
//            for (Room room : building.getLinkedRooms(temp.getRoomNumber())) {
//                if (!room.isTested() && !queue.contains(room)) {
//                    queue.add(room);
//                    building.sortByFloor(queue, priority);
//
//                }
//            }
//
//            temp.setExpanded(true);
//
//        }//end while
//
//        return false;
//    }

    private List<Room> clearUnwantedRooms(List<Room> roomList) {
        if (roomList == null) {
            return roomList;
        }

        int currentFloor = roomList.get(0).getFloor();
        boolean isOtherFloorExist = false;

        for (Room room : roomList) {
            isOtherFloorExist = room.getFloor() != currentFloor;
            if (isOtherFloorExist) {
                break;
            }
        }

        if (isOtherFloorExist) {
            for (Room room : roomList) {
                if (room.getFloor() == currentFloor) {
                    roomList.remove(room);
                }
            }
        }

        return roomList;
    }

    private List<Room> deleteRoomsFromFloor(List<Room> roomList, int floor) {
        for (Room room : roomList) {
            if (room.getFloor() != floor) {
                roomList.remove(room);
            }
        }

        return roomList;
    }

    @Override
    public void getResult() {

    }
}
