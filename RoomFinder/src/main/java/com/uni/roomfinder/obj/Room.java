package com.uni.roomfinder.obj;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int roomId;

    private int x;

    private int y;

    private int floor;

    private String type;

    private List<Transition> transitions = new ArrayList<>();

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{roomId=" + roomId + ", x=" + x + ", y=" + y + ", floor=" + floor + ", type='" + type + "', transitions='" + transitions + "'}";
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}
