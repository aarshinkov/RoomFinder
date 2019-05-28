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

        Room checkpoint = building.getRoom(startRoomNumber);

        return true;
    }
}
