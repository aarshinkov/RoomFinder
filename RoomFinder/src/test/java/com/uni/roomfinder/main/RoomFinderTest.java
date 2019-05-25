package com.uni.roomfinder.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoomFinderTest {

    private Main main;

    @Before
    public void setUp() throws Exception {
        main = new Main();
        main.init();
    }

    @Test
    public void testLengthBetweenRooms() {
        assertEquals(3, main.getBuilding().findLength(303, 403), 0);
        assertEquals(1, main.getBuilding().findLength(500, 527), 0);
        assertEquals(5, main.getBuilding().findLength(527, 528), 0);
        assertEquals(0, main.getBuilding().findLength(500, 401), 0);
    }
}
