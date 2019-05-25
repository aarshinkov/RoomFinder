package com.uni.roomfinder.reference;


public class Link {

    public String toNodeName;
    public double length;

    public Link(String name) {
        this.toNodeName = name;
    }

    public Link(String name, double l) {
        this.toNodeName = name;
        this.length = l;
    }

}
