package core;

import lombok.EqualsAndHashCode;

/**
 * Created by Iryna on 11.10.2017.
 */
@EqualsAndHashCode
public class Stones {

    private int[] holes1 = {6, 6, 6, 6, 6, 6, 0};
    private int[] holes2 = {6, 6, 6, 6, 6, 6, 0};

    public int getStones(int hole, boolean user) {
        return getHoles(user)[hole];
    }

    public int getStones(Hole hole) {
        return getStones(hole.getIndex(), hole.getUser());
    }

    private void setStones(Hole hole, int value) {
        getHoles(hole.getUser())[hole.getIndex()] = value;
    }

    public int removeStones(Hole hole) {
        int stones = getStones(hole);
        setStones(hole, 0);
        return stones;
    }

    public void addStones(Hole hole, int amountOfStones) {
        setStones(hole, getStones(hole) + amountOfStones);
    }

    public int[] getHoles(boolean user) {
        return user ? holes1 : holes2;
    }

    public int countStones(boolean user) {
        int amountOfStones = 0;
        for (int i = 0; i < getHoles(user).length - 1; i++) {
            amountOfStones += getHoles(user)[i];
        }
        return amountOfStones;
    }
}
