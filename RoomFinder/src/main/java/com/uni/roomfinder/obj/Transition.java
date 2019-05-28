package com.uni.roomfinder.obj;

public class Transition {

    private int toRoomNumber;
    private Type transitionType;
    private double length;

    public Transition(int toRoomId, Type transitionType) {
        this.toRoomNumber = toRoomId;
        this.transitionType = transitionType;
    }

    public Transition(int toRoomId, Type transitionType, double length) {
        this(toRoomId, transitionType);
        this.length = length;
    }

    public int getToRoomNumber() {
        return toRoomNumber;
    }

    public void setToRoomNumber(int toRoomNumber) {
        this.toRoomNumber = toRoomNumber;
    }

    public Type getTransitionType() {
        return transitionType;
    }

    public void setTransitionType(Type transitionType) {
        this.transitionType = transitionType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
