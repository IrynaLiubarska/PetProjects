package cards.model.card;

public enum Rank {

    ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    public Rank getNext() {
        return values()[(ordinal() + 1) % values().length];
    }

}
