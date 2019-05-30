package com.uni.roomfinder.obj;

public interface Searchable {

    boolean search(int startRoomNumber, int endRoomNumber);

    void getResult();
}
