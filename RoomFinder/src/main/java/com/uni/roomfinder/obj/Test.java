package com.uni.roomfinder.obj;

import java.util.ArrayList;
import java.util.List;

public class Test implements Searchable {

    private Building myBuilding;
    private List<Room> testedRooms = new ArrayList<Room>();
    private List<Room> path = new ArrayList<Room>();
    private Type typeToFollow = Type.CLIMB;

    public Test(Building myBuilding) {
        this.myBuilding = myBuilding;
    }

    @Override
    public boolean search(int startRoomNumber, int endRoomNumber) {

        if (!myBuilding.containsRoom(startRoomNumber) || !myBuilding.containsRoom(endRoomNumber)) {
            return false;
        }

        Room currentRoom = myBuilding.getRoom(startRoomNumber);
        Room lastRoom = null;

        testedRooms.add(currentRoom);
        path.add(currentRoom);

        while (!path.isEmpty()) {

            if (currentRoom == null) {
                return false;
            }

            if (currentRoom.getRoomNumber() == endRoomNumber) {
                printPath();
                return true;
            }

            currentRoom = getNextRoom(currentRoom, lastRoom, typeToFollow, endRoomNumber);

            if (currentRoom == null) {
                currentRoom = lastRoom;

                if (path.size() > 1) {
                    int beforeLastElementIndex = path.indexOf(currentRoom) - 1;
                    path.remove(lastRoom);
                    lastRoom = path.get(beforeLastElementIndex);
                    currentRoom = path.get(beforeLastElementIndex);
                } else {
                    lastRoom = null;
                }
            } else {
                lastRoom = currentRoom;
            }
        }
        return false;
    }

    private Room getNextRoom(Room currentRoom, Room lastRoom, Type typeToFollow, int toRoom) {

        List<Room> rooms = myBuilding.getLinkedRooms(currentRoom.getRoomNumber());

        for (Room room : rooms) {
            for (Transition transition : room.getTransitions()) {

                if (transition.getToRoomNumber() == currentRoom.getRoomNumber()) {
                    if (!testedRooms.contains(room) && (room != lastRoom)) {

                        testedRooms.add(room);
                        path.add(room);
                        return room;
                    }
                }
            }
        }
        return null;
    }

    private void printPath() {
        for (Room room : path) {
            System.out.println("Current room: " + room.getRoomNumber() + " x=" + room.getX() + ", y=" + room.getY() + ", floor="
                    + room.getFloor() + ", type=" + room.getType().name());
        }
    }

//    @Override
//    public void getResult() {
//        System.out.print("The path is: ");
//        int i = 0;
//        for (Room room : path) {
//            System.out.print("Room " + room.roomId);
//            if ((i + 1) != path.size()) {
//                System.out.print(" => ");
//            }
//            i++;
//        }
//        System.out.println();
//        getTypeTransitions(path);
//    }

//    private void getTypeTransitions(ArrayList<Room> path) {
//        int i = 0;
//        String type;
//        int currentD = 0;
//        int sumClimbTransitions = 0;
//        int sumWalkTransitions = 0;
//        int sumLiftTransitions = 0;
//
//        for (Room room : path) {
//            if (i == path.size() - 1)
//                break;
//
//            for (Transition transition : room.getTransitions()) {
//                Transition trans = room.getTransitions().stream().filter(x -> x.transitionType.equals("lift")).findFirst().orElse(null);
//                if (trans != null && transition.transitionType.equals("climb")) {
//                    continue;
//                }
//
//                if (transition.toRoom.equals(path.get(i + 1).roomId)) {
//                    if (transition.transitionType.equals("climb")) {
//                        currentD += (transition.distance * 2);
//                    } else {
//                        currentD += transition.distance;
//                    }
//                    type = transition.transitionType;
//
//                    switch (type.toUpperCase()) {
//                        case "CLIMB":
//                            sumClimbTransitions++;
//                            break;
//                        case "LIFT":
//                            sumLiftTransitions++;
//                            break;
//                        case "WALK":
//                            sumWalkTransitions++;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//
//            i++;
//        }
//
//        System.out.println("The sum of the distance is: " + currentD);
//        System.out.println("The sum of the climb transitions is: " + sumClimbTransitions);
//        System.out.println("The sum of the walk transitions is: " + sumWalkTransitions);
//        System.out.println("The sum of the lift transitions is: " + sumLiftTransitions);
//    }

//    @Override
//    public boolean search(String fromRoom, String toRoom, String typeToAvoid) {
//        // TODO Auto-generated method stub
//        return false;
//    }
}
