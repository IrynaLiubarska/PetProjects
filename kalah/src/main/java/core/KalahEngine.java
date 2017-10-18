package core;

/**
 * Created by Iryna on 20.07.2017.
 */
public class KalahEngine {

    public static final boolean USER_1 = true;
    public static final boolean USER_2 = false;

    private Stones stones = new Stones();
    private StoneDistributor stoneDistributor = new StoneDistributor(stones);
    private boolean currentUser = USER_1;

    public void move(int hole) {
        boolean nextUser = stoneDistributor.distributeStones(new Hole(hole, currentUser));
        currentUser = nextUser;
    }

    public boolean getCurrentUser() {
        return currentUser;
    }

    public boolean isGameOver() {
        return stones.countStones(USER_1) == 0 || stones.countStones(USER_2) == 0;
    }

    public boolean isNoWinner() {
        return stones.getStones(6, USER_1) == stones.getStones(6, USER_2);
    }

    public boolean getWinner() { // TODO return null in case of tie
        return stones.getStones(6, USER_1) > stones.getStones(6, USER_2) ? USER_1 : USER_2;
    }

    public Stones getStones() {
        return stones;
    }
}
