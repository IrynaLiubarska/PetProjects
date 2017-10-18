package core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Iryna on 09.08.2017.
 */
public class HoleNavigatorTest {

    private HoleNavigator holeNavigator;

    @Before
    public void setUp() {
        holeNavigator = new HoleNavigator();
    }

    @Test
    public void nextHoleForFirstHoleShouldBeSecondHoleFirstUser() {
        Hole firstHoleFirstUser = new Hole(0, true);
        assertEquals(new Hole(1, true), holeNavigator.getNextOf(firstHoleFirstUser));
    }

    @Test
    public void nextHoleForFirstHoleShouldBeSecondHoleSecondUser() {
        Hole firstHoleSecondUser = new Hole(0, false);
        assertEquals(new Hole(1, false), holeNavigator.getNextOf(firstHoleSecondUser));
    }

    @Test
    public void nextHoleForLastHoleOfFirstUserShouldBeKalah() {
        Hole lastHoleFirstUser = new Hole(5, true);
        assertEquals(new Hole(6, true), holeNavigator.getNextOf(lastHoleFirstUser));
    }

    @Test
    public void nextHoleForLastHoleOfSecondUserShouldBeKalah() {
        Hole lastHoleSecondUser = new Hole(5, false);
        assertEquals(new Hole(6, false), holeNavigator.getNextOf(lastHoleSecondUser));
    }

    @Test
    public void nextHoleForFirstUserKalahShouldBeFirstHoleOfSecondUser() {
        Hole kalahFirstUser = new Hole(6, true);
        assertEquals(new Hole(0, false), holeNavigator.getNextOf(kalahFirstUser));
    }

    @Test
    public void nextHoleForSecondUserKalahShouldBeFirstHoleOfFirstUser() {
        Hole kalahSecondUser = new Hole(6, true);
        assertEquals(new Hole(0, false), holeNavigator.getNextOf(kalahSecondUser));
    }

    @Test
    public void oppositeHoleForFirstHoleFirstUserShouldBeLastHoleOfSecondUser() {
        Hole firstHoleFirstUser = new Hole(0, true);
        assertEquals(new Hole(5, false), holeNavigator.getOppositeOf(firstHoleFirstUser));
    }

    @Test
    public void oppositeHoleForFirstHoleSecondUserShouldBeLastHoleOfFirstUser() {
        Hole firstHoleSecondUser = new Hole(0, false);
        assertEquals(new Hole(5, true), holeNavigator.getOppositeOf(firstHoleSecondUser));
    }

    @Test
    public void oppositeHoleForLastHoleFirstUserShouldBeFirstHoleOfSecondUser() {
        Hole firstHoleFirstUser = new Hole(5, true);
        assertEquals(new Hole(0, false), holeNavigator.getOppositeOf(firstHoleFirstUser));
    }

    @Test
    public void oppositeHoleForLastHoleSecondUserShouldBeFirstHoleOfFirstUser() {
        Hole firstHoleSecondUser = new Hole(5, false);
        assertEquals(new Hole(0, true), holeNavigator.getOppositeOf(firstHoleSecondUser));
    }

}
