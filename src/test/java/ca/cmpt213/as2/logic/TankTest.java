package ca.cmpt213.as2.logic;


import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;


public class TankTest {
    private static final Integer[] PIECE_1 = new Integer[] {0, 0};
    private static final Integer[] PIECE_2 = new Integer[] {0, 1};
    private static final Integer[] PIECE_3 = new Integer[] {0, 2};
    private static final Integer[] PIECE_4 = new Integer[] {0, 3};
    private static final List<Integer[]> TEST_LOCATION = new ArrayList<>();
    private static final int[] DAMAGED_HEALTH = new int[] {1, 1, 0, 1};
    private static final int[] FULL_HEALTH = new int[] {1, 1, 1, 1};

    public void initializeLocation() {
        TEST_LOCATION.add(PIECE_1);
        TEST_LOCATION.add(PIECE_2);
        TEST_LOCATION.add(PIECE_3);
        TEST_LOCATION.add(PIECE_4);
    }

    @Test
    public void testUpdateHealthWithValidLocation() {
        initializeLocation();
        Tank tank = new Tank(TEST_LOCATION);
        tank.updateHealth(0, 2);
        assertArrayEquals(DAMAGED_HEALTH, tank.getHealth());
    }

    @Test
    public void testUpdateHealthWithInvalidLocation() {
        initializeLocation();
        Tank tank = new Tank(TEST_LOCATION);
        tank.updateHealth(1, 0);
        assertArrayEquals(FULL_HEALTH, tank.getHealth());
    }


}