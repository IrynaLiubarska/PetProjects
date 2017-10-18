package core;

public class HoleNavigator {

    public Hole getNextOf(Hole hole) { 
        int nextIndex = hole.getIndex() + 1;
        boolean nextUser = hole.getUser();
        if (nextIndex == 7) {
            nextIndex = 0;
            nextUser = !nextUser;
        }
        return new Hole(nextIndex, nextUser);
    }

    public Hole getOppositeOf(Hole hole) {
        return new Hole(5 - hole.getIndex(), !hole.getUser());
    }
}
