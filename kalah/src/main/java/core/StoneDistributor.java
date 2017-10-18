package core;

public class StoneDistributor {

    private Stones stones;
    private HoleNavigator holeNavigator = new HoleNavigator();

    public StoneDistributor(Stones stones) {
        this.stones = stones;
    }

    boolean distributeStones(Hole hole) {
        boolean user = hole.getUser();
        int amountOfStones = stones.removeStones(hole);
        if (amountOfStones == 0) {
            return user;
        } else {
            hole = distributeWhileStonesExists(hole, amountOfStones);
            grabOppositeStonesIfNeeded(hole, user);
            return getNextUser(hole, user);
        }
    }

    private Hole distributeWhileStonesExists(Hole hole, int amountOfStones) {
        while (amountOfStones > 0) {
            hole = holeNavigator.getNextOf(hole);
            stones.addStones(hole, 1);
            amountOfStones--;
        }
        return hole;
    }

    private void grabOppositeStonesIfNeeded(Hole hole, boolean user) {
        if (hole.getUser() == user && !hole.isKalah() && stones.getStones(hole) == 1) {
            Hole oppositeHole = holeNavigator.getOppositeOf(hole);
            Hole kalah = new Hole(6, user);
            int grabbedOppositeStones = stones.removeStones(oppositeHole);
            int currentStones = stones.removeStones(hole);
            stones.addStones(kalah, grabbedOppositeStones + currentStones);
        }
    }

    public boolean getNextUser(Hole lastVisitedHole, boolean currentUser) {
        if (lastVisitedHole.isKalah()) {
            return lastVisitedHole.getUser() == currentUser ? currentUser : !currentUser;
        }
        return !currentUser;
    }
}
