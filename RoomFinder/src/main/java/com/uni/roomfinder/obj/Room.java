package com.uni.roomfinder.obj;

import java.util.ArrayList;

public class Room {

    private int roomNumber;
    private int x;
    private int y;
    private int floor;
    private Type type;

    private boolean isTested = false;
    private boolean isExpanded = false;
    private int depth = -1;
    private double distanceToGoal = 0.0;
    private Room parent = null;

    private ArrayList<Transition> transitions = new ArrayList<>();
//    public double weight;


    public Room(int roomNumber, int floor, Type type) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.type = type;
    }

    public Room(int roomNumber, int x, int y, int floor, Type type) {
        this(roomNumber, floor, type);
        this.x = x;
        this.y = y;
    }

    public void reset() {
        this.isExpanded = false;
        this.isTested = false;
        this.depth = -1;
        this.distanceToGoal = 0.0;
        this.parent = null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isTested() {
        return isTested;
    }

    public void setTested(boolean tested) {
        isTested = tested;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getDistanceToGoal() {
        return distanceToGoal;
    }

    public void setDistanceToGoal(double distanceToGoal) {
        this.distanceToGoal = distanceToGoal;
    }

    public Room getParent() {
        return parent;
    }

    public void setParent(Room parent) {
        this.parent = parent;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }
}
