package clock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Iryna on 22.01.2020.
 */
public class BerlinerClockTest {

    @Test
    public void shouldLightYellowTopLampIfSecondsArePairNumber() throws Exception {
        List<TimeBlock> expectedSecondsRepresentation = new ArrayList<TimeBlock>();
        TimeBlock timeBlock30Seconds = new TimeBlock(1, true, "Yellow");
        expectedSecondsRepresentation.add(timeBlock30Seconds);
        assertEquals(expectedSecondsRepresentation, BerlinerClock.representSeconds(30));
    }

    @Test
    public void shouldNotLightYellowTopLampIfSecondsAreNotPairNumber() throws Exception {
        List<TimeBlock> expectedSecondsRepresentation = new ArrayList<TimeBlock>();
        TimeBlock timeBlock29Seconds = new TimeBlock(1, false, "Yellow");
        expectedSecondsRepresentation.add(timeBlock29Seconds);
        assertEquals(expectedSecondsRepresentation, BerlinerClock.representSeconds(29));
    }

    @Test
    public void shouldReturnNullIfSecondsMinusNumber() throws Exception {
        assertNull(BerlinerClock.representSeconds(-1));
    }

    @Test
    public void shouldReturnNullIfSecondsMoreThan60() throws Exception {
        assertNull(BerlinerClock.representSeconds(64));
    }

    @Test
    public void shouldRepresent10Hours() throws Exception {
        // 10h representation
        List<TimeBlock> expectedHoursRepresentation = new ArrayList<TimeBlock>();
        TimeBlock timeBlock1st5h = new TimeBlock(2, true, "Red");
        TimeBlock timeBlock2nd5h = new TimeBlock(2, true, "Red");
        TimeBlock timeBlock3rd5h = new TimeBlock(2, false, "Red");
        TimeBlock timeBlock4th5h = new TimeBlock(2, false, "Red");
        expectedHoursRepresentation.add(timeBlock1st5h);
        expectedHoursRepresentation.add(timeBlock2nd5h);
        expectedHoursRepresentation.add(timeBlock3rd5h);
        expectedHoursRepresentation.add(timeBlock4th5h);
        assertEquals(expectedHoursRepresentation, BerlinerClock.representEach5hHours(10));
    }

    @Test
    public void shouldRepresent14Hours() throws Exception {
        // 14h representation
        List<TimeBlock> expectedHoursRepresentation = new ArrayList<TimeBlock>();
        TimeBlock timeBlock1st5h = new TimeBlock(2, true, "Red");
        TimeBlock timeBlock2nd5h = new TimeBlock(2, true, "Red");
        TimeBlock timeBlock3rd5h = new TimeBlock(2, false, "Red");
        TimeBlock timeBlock4th5h = new TimeBlock(2, false, "Red");
        expectedHoursRepresentation.add(timeBlock1st5h);
        expectedHoursRepresentation.add(timeBlock2nd5h);
        expectedHoursRepresentation.add(timeBlock3rd5h);
        expectedHoursRepresentation.add(timeBlock4th5h);
        assertEquals(expectedHoursRepresentation, BerlinerClock.representEach5hHours(14));
    }

    @Test
    public void shouldRepresent15Minutes() throws Exception {
        // 14minutes representation in 5 minutes interval
        List<TimeBlock> expectedHoursRepresentation = BerlinerClock.createTimeBlocksFor5MinutesInterval();
        expectedHoursRepresentation.get(0).setLighted(true);
        expectedHoursRepresentation.get(1).setLighted(true);
        assertEquals(expectedHoursRepresentation, BerlinerClock.representEach5Minutes(14));
    }

    @Test
    public void shouldCreateTimeBlocksFor1SecondInterval() throws Exception {
        TimeBlock actual = BerlinerClock.createTimeBlockFor1Second();
        assertEquals(new TimeBlock(1, false, "Yellow"), actual);
    }

    @Test
    public void shouldCreateTimeBlocksFor5MinutesInterval() throws Exception {
        List<TimeBlock> actual = BerlinerClock.createTimeBlocksFor5MinutesInterval();
        assertEquals(11, actual.size());
    }

    @Test
    public void shouldCreateTimeBlocksFor1MinuteInterval() throws Exception {
        List<TimeBlock> actual = BerlinerClock.createTimeBlocksFor1MinuteInterval();
        assertEquals(4, actual.size());
    }

    @Test
    public void shouldShouldShowTime() throws Exception {
        BerlinerClock.representLocalTime(13, 17, 2);
        System.out.println(BerlinerClock.clock.toString());
    }
}