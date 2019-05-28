package com.uni.roomfinder.obj;

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

        System.out.println(building.getRoom(startRoomNumber).getTransitions().size());

        for (Transition transition : building.getRoom(startRoomNumber).getTransitions()) {
            System.out.println(transition.getToRoomNumber());
        }


        return true;
    }
}
