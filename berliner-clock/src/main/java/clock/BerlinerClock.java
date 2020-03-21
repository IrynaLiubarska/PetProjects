package clock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iryna on 22.01.2020.
 */
public class BerlinerClock {

    public static Map<Integer, List<TimeBlock>> clock = new HashMap<Integer, List<TimeBlock>>();

    public static Map<Integer, List<TimeBlock>> representLocalTime(int hours, int minutes, int seconds) {
        representSeconds(seconds);
        representEach5hHours(hours);
        representEach5Minutes(minutes);
        return clock;
    }

    public static List<TimeBlock> representSeconds(int seconds) {
        if (seconds >= 0 && seconds <= 60) {
            TimeBlock timeBlockSeconds = createTimeBlockFor1Second();
            if (seconds % 2 == 0) {
                timeBlockSeconds.setLighted(true);
            } else {
                timeBlockSeconds.setLighted(false);
            }
            List<TimeBlock> secondsRepresentation = new ArrayList<TimeBlock>();
            secondsRepresentation.add(timeBlockSeconds);
            clock.put(1, secondsRepresentation);
            return secondsRepresentation;
        }
        return null;
    }

    public static List<TimeBlock> representEach5hHours(int hours) {
        List<TimeBlock> representation5HoursIntervals = createTimeBlocksFor5hInterval();
        int lessThan5HoursTime = hours % 5;
        int intervalsWith5Hours = hours / 5;
        if (lessThan5HoursTime != 0) {
            representEach1hHours(lessThan5HoursTime);
        }
        if (intervalsWith5Hours != 0) {
            for (int i = 0; i < intervalsWith5Hours; i++) {
                representation5HoursIntervals.get(i).setLighted(true);
            }
        }
        clock.put(2, representation5HoursIntervals);
        return representation5HoursIntervals;
    }


    public static List<TimeBlock> representEach1hHours(int hours) {
        List<TimeBlock> hoursRepresentation = createTimeBlocksFor1hInterval();
        for (int i = 0; i < hours; i++) {
            hoursRepresentation.get(i).setLighted(true);
        }
        clock.put(3, hoursRepresentation);
        return hoursRepresentation;
    }

    public static List<TimeBlock> representEach5Minutes(int minutes) {
        List<TimeBlock> representation5MinutesIntervals = createTimeBlocksFor5MinutesInterval();
        int lessThan5MinutesTime = minutes % 5;
        int intervalsWith5Minutes = minutes / 5;
        if (lessThan5MinutesTime != 0) {
            representEach1Minutes(lessThan5MinutesTime);
        }
        if (intervalsWith5Minutes != 0) {
            for (int i = 0; i < intervalsWith5Minutes; i++) {
                representation5MinutesIntervals.get(i).setLighted(true);
            }
        }
        clock.put(4, representation5MinutesIntervals);
        return representation5MinutesIntervals;
    }

    public static List<TimeBlock> representEach1Minutes(int minutes) {
        List<TimeBlock> minutesRepresentation = createTimeBlocksFor1MinuteInterval();
        for (int i = 0; i < minutes; i++) {
            minutesRepresentation.get(i).setLighted(true);
        }
        clock.put(5, minutesRepresentation);
        return minutesRepresentation;
    }

    private static List<TimeBlock> createTimeBlocksFor5hInterval() {
        List<TimeBlock> representation5HoursIntervals = new ArrayList<TimeBlock>();
        for (int number5HoursIntervals = 0; number5HoursIntervals < 4; number5HoursIntervals++) {
            TimeBlock timeBlock1st = new TimeBlock(2, false, "Red");
            representation5HoursIntervals.add(timeBlock1st);
        }
        return representation5HoursIntervals;
    }

    public static List<TimeBlock> createTimeBlocksFor1hInterval() {
        List<TimeBlock> representation1HoursIntervals = new ArrayList<TimeBlock>();
        for (int number1hourIntervals = 0; number1hourIntervals < 4; number1hourIntervals++) {
            TimeBlock timeBlock1st = new TimeBlock(3, false, "Red");
            representation1HoursIntervals.add(timeBlock1st);
        }
        return representation1HoursIntervals;
    }

    public static List<TimeBlock> createTimeBlocksFor5MinutesInterval() {
        List<TimeBlock> representation5MinutesIntervals = new ArrayList<TimeBlock>();
        for (int number5MinIntervals = 0; number5MinIntervals < 11; number5MinIntervals++) {
            if (number5MinIntervals == 2 || number5MinIntervals == 5 || number5MinIntervals == 8) {
                TimeBlock timeBlock1st = new TimeBlock(4, false, "Red");
                representation5MinutesIntervals.add(timeBlock1st);
            } else {
                TimeBlock timeBlock1st = new TimeBlock(4, false, "Yellow");
                representation5MinutesIntervals.add(timeBlock1st);
            }
        }
        return representation5MinutesIntervals;
    }

    public static List<TimeBlock> createTimeBlocksFor1MinuteInterval() {
        List<TimeBlock> representation1MinuteIntervals = new ArrayList<TimeBlock>();
        for (int number1MinIntervals = 0; number1MinIntervals < 4; number1MinIntervals++) {
            TimeBlock timeBlock1st = new TimeBlock(5, false, "Yellow");
            representation1MinuteIntervals.add(timeBlock1st);
        }
        return representation1MinuteIntervals;
    }

    static TimeBlock createTimeBlockFor1Second() {
        TimeBlock timeBlockSeconds = new TimeBlock();
        timeBlockSeconds.setLine(1);
        timeBlockSeconds.setColor("Yellow");
        return timeBlockSeconds;
    }
}
