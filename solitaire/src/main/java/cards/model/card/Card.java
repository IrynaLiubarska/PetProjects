package cards.model.card;

import lombok.Data;
@Data
public class Card implements Comparable<Card> {

    private Rank rank;
    private Suit suit;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean oppositeSuits(Card that) {
        return this.getSuit().getColor() != that.getSuit().getColor();
    }

    public boolean sameSuit(Card that) {
        return this.getSuit() == that.getSuit();
    }

    public boolean isNextRank(Card aimCard) {
        return this.getRank().getNext() == aimCard.getRank();
    }

    @Override
    public int compareTo(Card that) {
        return this.getRank().ordinal() - that.getRank().ordinal();
    }

}
