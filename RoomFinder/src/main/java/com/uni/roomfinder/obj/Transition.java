package com.uni.roomfinder.obj;

public class Transition {

    private int fromRoomNumber;
    private int toRoomNumber;

    private String transitionType;
    private int distance;
    private boolean isBidirectional;

    @Override
    public String toString() {
        return "Transition{" +
                "fromRoomNumber=" + fromRoomNumber +
                ", toRoomNumber=" + toRoomNumber +
                ", transitionType='" + transitionType + '\'' +
                ", distance=" + distance +
                ", isBidirectional=" + isBidirectional +
                '}';
    }

    public int getFromRoomNumber() {
        return fromRoomNumber;
    }

    public void setFromRoomNumber(int fromRoomNumber) {
        this.fromRoomNumber = fromRoomNumber;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isBidirectional() {
        return isBidirectional;
    }

    public void setBidirectional(boolean bidirectional) {
        isBidirectional = bidirectional;
    }
}
