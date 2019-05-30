package com.uni.roomfinder.obj;

import java.util.*;

public class Building {

    private HashMap<Integer, Room> myBuilding = new HashMap<>();

    private Comparator<Room> byRoomNumber = Comparator.comparingInt(Room::getRoomNumber);

    private Comparator<Room> byWeight = Comparator.comparingDouble(Room::getWeight);

    private Comparator<Room> byDistance = Comparator.comparingDouble(Room::getDistanceToGoal);

    private Comparator<Room> byFloor = Comparator.comparingInt(Room::getFloor);

    public void addRoom(Room room) {

        if (room == null || myBuilding.containsKey(room.getRoomNumber())) {
            System.err.println("Room with id: " + room.getRoomNumber() + " already exists");
            return;
        }
        myBuilding.put(room.getRoomNumber(), room);
    }

    public void addTransition(Integer startRoomNumber, Integer endRoomNumber, Type type, boolean isBiDirectional, double length) {
        if (myBuilding.containsKey(startRoomNumber) && myBuilding.containsKey(endRoomNumber)) {
            Room startRoom = myBuilding.get(startRoomNumber);
            Room endRoom = myBuilding.get(endRoomNumber);
            startRoom.getTransitions().add(new Transition(endRoomNumber, type, length));

            if (isBiDirectional) {
                endRoom.getTransitions().add(new Transition(startRoomNumber, type, length));
            }
        } else {
            System.err.println("Wrong or missing rooms");
        }
    }


    public Room getRoom(int roomNumber) {
        return myBuilding.get(roomNumber);
    }

    public boolean containsRoom(int roomNumber) {
        return myBuilding.containsKey(roomNumber);
    }


    public List<Room> getLinkedRooms(int roomNumber) {
        List<Room> linkedRooms = new ArrayList<>();
        Room room = myBuilding.get(roomNumber);
        for (Transition transition : room.getTransitions()) {
            linkedRooms.add(myBuilding.get(transition.getToRoomNumber()));
        }
        return linkedRooms;
    }

    public List<Room> getLinkedRoomsAvoid(int roomNumber, Type type) {
        List<Room> linkedRooms = new ArrayList<>();
        Room room = myBuilding.get(roomNumber);
        for (Transition transition : room.getTransitions()) {
            if (!transition.getTransitionType().equals(type)) {
                linkedRooms.add(myBuilding.get(transition.getToRoomNumber()));
            }
        }
        return linkedRooms;
    }

    public void resetAllRooms() {
        myBuilding.forEach((k, v) -> v.reset());
    }

    public void sortByRoomNumber(List<Room> list) {
        list.sort(byRoomNumber);
    }

    public void sortByDistance(List<Room> list) {

        list.sort(byDistance.thenComparing(byRoomNumber));
    }

    public void sortByWeight(List<Room> list) {
        list.sort(byWeight.thenComparing(byRoomNumber));
    }

    public void sortByFloor(List<Room> list, Priority priority) {

        list.sort(byFloor.thenComparing(byRoomNumber));

        if (priority.equals(Priority.UPWARDS)) {
            Collections.reverse(list);

        }
    }

    public void setDepths(int roomNumber) {
        Room room = getRoom(roomNumber);
        for (Room r : getLinkedRooms(roomNumber)) {
            if (r.getDepth() == -1) {
                r.setDepth(room.getDepth() + 1);
            }
        }
    }

    public void printPath(Room currentRoom) {
        StringBuilder path = new StringBuilder();

        while (currentRoom.getDepth() != 0) {
            path.append(currentRoom.getRoomNumber());

            for (Room room : getLinkedRooms(currentRoom.getRoomNumber())) {
                if (room.getDepth() == (currentRoom.getDepth() - 1)) {
                    currentRoom = room;
                    break;
                }
            }
        }

        path.append(currentRoom.getRoomNumber());

        System.out.print(path);
    }

    public double findLength(int roomOneNumber, int roomTwoNumber) {

        Room roomOne = getRoom(roomOneNumber);

        for (Transition transition : roomOne.getTransitions()) {
            if (transition.getToRoomNumber() == roomTwoNumber) {
//                System.out.println("Transition distance between room " + roomOneNumber + " and room " + roomTwoNumber + " is " + transition.getLength());
                return transition.getLength();
            }
        }

//        System.out.println("No transition between room " + roomOneNumber + " and room " + roomTwoNumber + " has been found");
        return 0.00;
    }

    public double findDistance(int roomOneNumber, int roomTwoNumber) {

        Room roomOne = getRoom(roomOneNumber);
        Room roomTwo = getRoom(roomTwoNumber);

        double distance = Math.sqrt(Math.pow(roomOne.getX() - roomTwo.getX(), 2)
                + Math.pow(roomOne.getY() - roomTwo.getY(), 2));

        System.out.println("Distance between room " + roomOneNumber + " and room " + roomTwoNumber + " is " + distance);
        return distance;
    }

}
