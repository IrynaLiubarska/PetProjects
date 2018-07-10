package cards.model.piles;

import cards.model.card.Card;
import cards.model.card.Rank;
import cards.model.card.Suit;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Getter
public class Deck extends Pile {

    private Stack<Card> invisible = new Stack<>();

    public Deck(List<Card> cardPile) {
        invisible.addAll(cardPile);
    }

    public static Deck generate() {
        List<Card> cardsDeck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                cardsDeck.add(card);
            }
        }
        return new Deck(cardsDeck);
    }

    public BoardPile getBoardingPile(Integer numberOfCards) {
        checkIfAppropriateNumberOfCards(numberOfCards);
        Stack<Card> invisibleBordCards = new Stack<>();
        for (int i = 0; i < numberOfCards; i++) {
            invisibleBordCards.push(invisible.pop());
        }
        Card visible = invisibleBordCards.pop();
        return new BoardPile(invisibleBordCards, visible);
    }

    private boolean checkIfAppropriateNumberOfCards(Integer numberOfCards) {
        if (numberOfCards > 0 && numberOfCards < 8) return true;
        throw new IllegalArgumentException();
    }

    public void shuffle() {
        Collections.shuffle(invisible);
    }
}





