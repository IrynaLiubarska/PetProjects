package core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Iryna on 09.08.2017.
 */
public class StoneDistributorTest {

    private StoneDistributor stoneDistributor;
    private Stones stones;

    @Before
    public void setUp() {
        stones = new Stones();
        stoneDistributor = new StoneDistributor(stones);
    }

    @Test
    public void shouldDistributeStonesFromFirstHoleFirstUser() {
        Hole hole0 = new Hole(0, true);
        stoneDistributor.distributeStones(hole0);
        assertArrayEquals(new int[]{0, 7, 7, 7, 7, 7, 1}, stones.getHoles(true));
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 6, 0}, stones.getHoles(false));
    }

    @Test
    public void shouldDistributeStonesFromFirstHoleSecondUser() {
        Hole hole0 = new Hole(0, false);
        stoneDistributor.distributeStones(hole0);
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 6, 0}, stones.getHoles(true));
        assertArrayEquals(new int[]{0, 7, 7, 7, 7, 7, 1}, stones.getHoles(false));
    }

    @Test
    public void shouldDistributeStonesFromLastHoleFirstUser() {
        Hole hole5 = new Hole(5, true);
        stoneDistributor.distributeStones(hole5);
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 0, 1}, stones.getHoles(true));
        assertArrayEquals(new int[]{7, 7, 7, 7, 7, 6, 0}, stones.getHoles(false));
    }

    @Test
    public void shouldDistributeStonesFromLastHoleSecondUser() {
        Hole hole5 = new Hole(5, false);
        stoneDistributor.distributeStones(hole5);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7, 6, 0}, stones.getHoles(true));
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 0, 1}, stones.getHoles(false));
    }

    @Test
    public void shouldDistributeStonesFromMiddleHoleFirstUser() {
        Hole hole3 = new Hole(3, true);
        stoneDistributor.distributeStones(hole3);
        assertArrayEquals(new int[]{6, 6, 6, 0, 7, 7, 1}, stones.getHoles(true));
        assertArrayEquals(new int[]{7, 7, 7, 6, 6, 6, 0}, stones.getHoles(false));
    }

    @Test
    public void shouldDistributeStonesFromMiddleHoleSecondUser() {
        Hole hole3 = new Hole(3, false);
        stoneDistributor.distributeStones(hole3);
        assertArrayEquals(new int[]{7, 7, 7, 6, 6, 6, 0}, stones.getHoles(true));
        assertArrayEquals(new int[]{6, 6, 6, 0, 7, 7, 1}, stones.getHoles(false));
    }

    @Test
    public void shouldNotDistributeStonesFromEmptyHole() {
        Hole hole3 = new Hole(3, false);
        boolean result = stoneDistributor.distributeStones(hole3);
        assertEquals(true, result);
        boolean result2 = stoneDistributor.distributeStones(hole3);
        assertArrayEquals(new int[]{7, 7, 7, 6, 6, 6, 0}, stones.getHoles(true));
        assertArrayEquals(new int[]{6, 6, 6, 0, 7, 7, 1}, stones.getHoles(false));
        assertEquals(false, result2);
    }

    @Test
    public void shouldGetFirstUserAsNextUserIfLastHoleIsKalahOfFirstUser() {
        boolean result = stoneDistributor.getNextUser(new Hole(6,true), true);
        assertEquals(true, result);
    }
    
    @Test
    public void shouldGetSecondUserAsNextUser(){
        boolean result = stoneDistributor.getNextUser(new Hole(0, false), true);
        assertEquals(false, result);
    }
    @Test
    public void shouldGetFirstUserAsNextUser(){
        boolean result = stoneDistributor.getNextUser(new Hole(0, true), false);
        assertEquals(true, result);
    }

    @Test
    public void shouldGetFirstUserAsNextUserSunshineCase(){
        boolean result = stoneDistributor.getNextUser(new Hole(3, true), true);
        assertEquals(false, result);
    }
    @Test
    public void shouldGetSecondUserAsNextUserSunshineCase(){
        boolean result = stoneDistributor.getNextUser(new Hole(3, false), false);
        assertEquals(true, result);
    }
    
    @Test
    public void shouldDistributeOppositeStones() {
        int[] stonesUser1 = stones.getHoles(true);
        int[] stonesUser2 = stones.getHoles(false);
        System.arraycopy(new int[]{3, 6, 6, 0, 6, 0, 1}, 0, stonesUser1, 0, 7);
        System.arraycopy(new int[]{6, 6, 6, 5, 7, 7, 1}, 0, stonesUser2, 0, 7);
        Hole hole0 = new Hole(0, true);
        stoneDistributor.distributeStones(hole0);
        assertArrayEquals(new int[]{0, 7, 7, 0, 6, 0, 8}, stones.getHoles(true));
        assertArrayEquals(new int[]{6, 6, 0, 5, 7, 7, 1}, stones.getHoles(false));
    }
}
