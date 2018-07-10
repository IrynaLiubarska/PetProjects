package cards.model.piles;

import cards.model.card.Card;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Iryna on 06.09.2017.
 */
@EqualsAndHashCode
@Getter
public class DeckPile {

    private Queue<Card> deckPile = new LinkedList<>();

    public DeckPile(Pile deck) {
        deckPile.addAll(deck.cardPile);
    }

    public Card getCurrentCard() {
        return deckPile.peek();
    }

    public void scrollToNextCard() {
        deckPile.add(deckPile.remove());
    }

    public boolean isEmpty() {
        return deckPile.isEmpty();
    }

    public Card remove() {
        if(isEmpty()) throw new IllegalStateException();
        return deckPile.poll();
    }
}
