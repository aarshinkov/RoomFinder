package com.uni.roomfinder.main;

import com.uni.roomfinder.obj.Room;
import com.uni.roomfinder.obj.Transition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    private static List<Room> rooms = new ArrayList<>();

    private static List<Transition> transitions = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        init();

        System.out.println("Rooms: ");

        for (Room room : rooms) {
            System.out.println(room);
        }

        System.out.println("-----------------------");

        System.out.println("Transitions: ");

        for (Transition transition : transitions) {
            System.out.println(transition);
        }

        System.out.println("-----------------------");

    }

    private static void init() throws Exception {
        // File path in src/main/resources/...
        String fileName = "files/structure.txt";

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        // Finding file starting from root of application (src folder) then takes the resources (resource directory)
        // and from fileName takes the file as an object
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

        // Reading from file
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;

            boolean isRooms = false;
            boolean isTransitions = false;

            // While there is lines to read
            while ((line = br.readLine()) != null) {
                if (line.equals("ROOMS")) {
                    isTransitions = false;
                    isRooms = true;
                    continue;
                } else if (line.equals("TRANSITIONS")) {
                    isRooms = false;
                    isTransitions = true;
                    continue;
                } else if (line.isEmpty()) { // Blank line
                    isRooms = false;
                    isTransitions = false;
                }

                if (isRooms) {
                    createObjectFromLine(line, new Room());
                } else if (isTransitions) {
                    createObjectFromLine(line, new Transition());
                }
            }
        } catch (Exception ignored) {
        }
    }

    private static void createObjectFromLine(String line, Object object) {
        String[] components = line.split(", ");

        if (object instanceof Room) {
            Room room = new Room();

            room.setRoomId(Integer.parseInt(components[0]));
            room.setX(Integer.parseInt(components[1]));
            room.setY(Integer.parseInt(components[2]));
            room.setFloor(Integer.parseInt(components[3]));
            room.setType(components[4].substring(0, components[4].length() - 1)); // For removing semicolon (;)

            rooms.add(room);

        } else if (object instanceof Transition) {
            Transition transition = new Transition();

            transition.setFromRoomNumber(Integer.parseInt(components[0]));
            transition.setToRoomNumber(Integer.parseInt(components[1]));
            transition.setTransitionType(components[2]);
            transition.setDistance(Integer.parseInt(components[3]));

            if (components[4].substring(0, components[4].length() - 1).equalsIgnoreCase("yes")) {
                transition.setBidirectional(true);
            } else {
                transition.setBidirectional(false);
            }

            transitions.add(transition);
        }
    }

}
