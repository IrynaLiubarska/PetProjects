package cards.model.piles;

import cards.model.card.Card;
import cards.model.card.Rank;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * Created by Iryna on 06.09.2017.
 */
@EqualsAndHashCode
public class CollectingPile extends Pile {

    public CollectingPile(ArrayList<Card> cards) {
        super(cards);
    }

    public CollectingPile(Pile pile) {
        super(pile.cardPile);
    }

    public CollectingPile(Card card) {
        super(card);
    }

    public boolean put(Card card) {
        if (this.isEmpty()) return putIfACE(card);
        else {
            Card aimCard = getLastCardInPile();
            return putIfAppropriateSuitAndRank(card, aimCard);
        }
    }

    private boolean putIfAppropriateSuitAndRank(Card card, Card aimCard) {
        return card.sameSuit(aimCard) && putIfAppropriateRank(card, aimCard);
    }

    private boolean putIfACE(Card card) {
        return card.getRank() == Rank.ACE && cardPile.add(card);
    }

    private boolean putIfAppropriateRank(Card card, Card aimCard) {
        return aimCard.isNextRank(card) && cardPile.add(card);
    }

}
