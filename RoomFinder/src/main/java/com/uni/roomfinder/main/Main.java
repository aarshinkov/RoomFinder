package com.uni.roomfinder.main;

import com.uni.roomfinder.obj.Building;
import com.uni.roomfinder.obj.Room;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private Building building = new Building();

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.init();


    }

    public void init() throws Exception {
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

    private void createRoomFromLine(String line) {
        String[] components = line.split(", ");

        building.addRoom(new Room(Integer.parseInt(components[0]),
                Integer.parseInt(components[1]),
                Integer.parseInt(components[2]),
                Integer.parseInt(components[3]),
                components[4].substring(0, components[4].length() - 1))); // For removing semicolon (;)
    }

    private void createTransitionFromLine(String line) {
        String[] components = line.split(", ");

        boolean isBidirectional = (components[4].substring(0, components[4].length() - 1).equalsIgnoreCase("yes"));

        building.addTransition(Integer.parseInt(components[0]),
                Integer.parseInt(components[1]),
                components[2],
                isBidirectional,
                Integer.parseInt(components[3]));
    }

    public Building getBuilding() {
        return building;
    }
}
