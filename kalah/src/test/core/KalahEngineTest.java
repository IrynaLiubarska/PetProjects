package core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class KalahEngineTest {

    private KalahEngine kalahEngine;

    @Before
    public void setUp() {
        kalahEngine = new KalahEngine();
    }

    @Test
    public void shouldDistributeFirstHole() {
        kalahEngine.move(0);
        assertArrayEquals(new int[]{0, 7, 7, 7, 7, 7, 1}, kalahEngine.getStones().getHoles(true));
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 6, 0}, kalahEngine.getStones().getHoles(false));
    }

    @Test
    public void shouldMoveStonesFromFirstAndSecondHoleNoUserChange() {
        kalahEngine.move(0);
        assertArrayEquals(new int[]{0, 7, 7, 7, 7, 7, 1}, kalahEngine.getStones().getHoles(true));
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 6, 0}, kalahEngine.getStones().getHoles(false));
        kalahEngine.move(1);
        assertArrayEquals(new int[]{0, 0, 8, 8, 8, 8, 2}, kalahEngine.getStones().getHoles(true));
        assertArrayEquals(new int[]{7, 7, 6, 6, 6, 6, 0}, kalahEngine.getStones().getHoles(false));
    }

    @Test
    public void shouldMoveStonesFromLastHoleFirstUser() {
        kalahEngine.move(5);
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 0, 1}, kalahEngine.getStones().getHoles(true));
        assertArrayEquals(new int[]{7, 7, 7, 7, 7, 6, 0}, kalahEngine.getStones().getHoles(false));
    }

    @Test
    public void shouldBePositiveGameIsOver() {
        int[] stonesUser1 = kalahEngine.getStones().getHoles(true);
        System.arraycopy(new int[]{0, 3, 2, 0, 0, 0, 32}, 0, stonesUser1, 0, 7);
        int[] stonesUser2 = kalahEngine.getStones().getHoles(false);
        System.arraycopy(new int[]{0, 0, 0, 0, 0, 0, 35}, 0, stonesUser2, 0, 7);
        boolean result = kalahEngine.isGameOver();
        assertEquals(true, result);
    }

    @Test
    public void secondUserShouldBeAWinner() {
        int[] stonesUser1 = kalahEngine.getStones().getHoles(true);
        int[] stonesUser2 = kalahEngine.getStones().getHoles(false);
        stonesUser1[6] = 32;
        stonesUser2[6] = 35;
        boolean result = kalahEngine.getWinner();
        assertEquals(false, result);
    }

    @Test
    public void firstUserShouldBeAWinner() {
        int[] stonesUser1 = kalahEngine.getStones().getHoles(true);
        int[] stonesUser2 = kalahEngine.getStones().getHoles(false);
        stonesUser1[6] = 35;
        stonesUser2[6] = 32;
        boolean result = kalahEngine.getWinner();
        assertEquals(true, result);
    }

    @Test
    public void shouldBePositiveNoAWinner() {
        int[] stonesUser1 = kalahEngine.getStones().getHoles(true);
        int[] stonesUser2 = kalahEngine.getStones().getHoles(false);
        stonesUser1[6] = 35;
        stonesUser2[6] = 35;
        boolean result = kalahEngine.isNoWinner();
        assertEquals(true, result);
    }

    @Test
    public void shouldBeNegativeNoAWinner() {
        int[] stonesUser1 = kalahEngine.getStones().getHoles(true);
        int[] stonesUser2 = kalahEngine.getStones().getHoles(false);
        stonesUser1[6] = 35;
        stonesUser2[6] = 36;
        boolean result = kalahEngine.isNoWinner();
        assertEquals(false, result);
    }
}
