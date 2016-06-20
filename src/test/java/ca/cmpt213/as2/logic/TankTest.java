package ca.cmpt213.as2.logic;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;


public class TankTest {
    private static final int[] testLocation = new int[] {0, 0, 0, 1, 0, 2, 0, 3};
    private static final int[] damagedHealth = new int[] {1, 1, 0, 1};
    private static final int[] fullHealth = new int[] {1, 1, 1, 1};

    @Test
    public void testUpdateHealthWithValidLocation() {
        Tank tank = new Tank(testLocation);
        tank.updateHealth(0, 2, 0);
        assertArrayEquals(tank.getHealth(), damagedHealth);
    }

    @Test
    public void testUpdateHealthWithInvalidLocation() {
        Tank tank = new Tank(testLocation);
        tank.updateHealth(1, 0, 0);
        assertArrayEquals(tank.getHealth(), fullHealth);
    }


}
