package ca.cmpt213.as2.logic;


import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;


public class TankTest {
    private static final Integer[] piece1 = new Integer[] {0, 0};
    private static final Integer[] piece2 = new Integer[] {0, 1};
    private static final Integer[] piece3 = new Integer[] {0, 2};
    private static final Integer[] piece4 = new Integer[] {0, 3};
    private static final List<Integer[]> testLocation = new ArrayList<>();
    private static final int[] damagedHealth = new int[] {1, 1, 0, 1};
    private static final int[] fullHealth = new int[] {1, 1, 1, 1};
//    private static final int[] testLocation = new int[] {0, 0, 0, 1, 0, 2, 0, 3};
//    private static final int[] damagedHealth = new int[] {1, 1, 0, 1};
//    private static final int[] fullHealth = new int[] {1, 1, 1, 1};

    public void initializeLocation() {
        testLocation.add(piece1);
        testLocation.add(piece2);
        testLocation.add(piece3);
        testLocation.add(piece4);
    }

    @Test
    public void testUpdateHealthWithValidLocation() {
        initializeLocation();
        Tank tank = new Tank(testLocation);
        tank.updateHealth(0, 2, 0);
        assertArrayEquals(tank.getHealth(), damagedHealth);
    }

    @Test
    public void testUpdateHealthWithInvalidLocation() {
        initializeLocation();
        Tank tank = new Tank(testLocation);
        tank.updateHealth(1, 0, 0);
        assertArrayEquals(tank.getHealth(), fullHealth);
    }


}