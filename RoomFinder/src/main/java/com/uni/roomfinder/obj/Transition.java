package com.uni.roomfinder.obj;

public class Transition {

    private int toRoomNumber;
    private String transitionType;
    private double length;

    public Transition(int toRoomId, String transitionType) {
        this.toRoomNumber = toRoomId;
        this.transitionType = transitionType;
    }

    public Transition(int toRoomId, String transitionType, double length) {
        this(toRoomId, transitionType);
        this.length = length;
    }

    public int getToRoomNumber() {
        return toRoomNumber;
    }

    public void setToRoomNumber(int toRoomNumber) {
        this.toRoomNumber = toRoomNumber;
    }

    public String getTransitionType() {
        return transitionType;
    }

    public void setTransitionType(String transitionType) {
        this.transitionType = transitionType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
