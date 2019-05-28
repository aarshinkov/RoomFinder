package com.uni.roomfinder.main;

import com.uni.roomfinder.obj.*;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static Building building = new Building();

    public static void main(String[] args) throws Exception {
        init();

        findPath(232, 330, new SearchPathAvoiding(building, Type.LIFT));

    }

    private static void findPath(int startRoomNumber, int endRoomNumber, Searchable searcher) {
        building.resetAllRooms();
        if (searcher.search(startRoomNumber, endRoomNumber)) {
            System.out.println("HAVE A PATH");
        } else {
            System.out.println("THERE IS NO PATH");
        }
    }

    public static void init() throws Exception {
        // File path in src/main/resources/...
        String fileName = "files/structure.txt";

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        // Finding file starting from root of application (src folder) then takes the resources (resource directory)
        // and from fileName takes the file as an object
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

        Scanner sc = new Scanner(file);

        // Reading from file
        try {

            boolean isRooms = true;

            // While there is lines to read
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.isEmpty()) {
                    isRooms = false;
                    continue;
                }

                if (isRooms) {
                    createRoomFromLine(line);
                } else {
                    createTransitionFromLine(line);
                }
            }

        } catch (Exception ignored) {
        }
    }

    private static void createRoomFromLine(String line) {
        String[] components = line.split(", ");

        Type type = getTypeFromString(components[4].substring(0, components[4].length() - 1));

        building.addRoom(new Room(Integer.parseInt(components[0]),
                Integer.parseInt(components[1]),
                Integer.parseInt(components[2]),
                Integer.parseInt(components[3]),
                type)); // For removing semicolon (;)
    }

    private static void createTransitionFromLine(String line) {
        String[] components = line.split(", ");

        boolean isBidirectional = (components[4].substring(0, components[4].length() - 1).equalsIgnoreCase("yes"));

        Type type = getTypeFromString(components[2]);

        building.addTransition(Integer.parseInt(components[0]),
                Integer.parseInt(components[1]),
                type,
                isBidirectional,
                Integer.parseInt(components[3]));
    }

    private static Type getTypeFromString(String text) {

        switch (text.toLowerCase()) {
            case "room":
                return Type.ROOM;
            case "transit":
                return Type.TRANSIT;
            case "walk":
                return Type.WALK;
            case "climb":
                return Type.CLIMB;
            case "lift":
                return Type.LIFT;
            default:
                throw new IllegalArgumentException("The parameter " + text + " is not a valid parameter.");
        }
    }

    public Building getBuilding() {
        return building;
    }
}
