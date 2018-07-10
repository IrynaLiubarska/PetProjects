package cards.model.piles;

import cards.model.card.Card;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Data
public class Pile {

    public List<Card> cardPile = new ArrayList<>();

    Pile(List<Card> cardPile) {
        this.cardPile = cardPile;
    }

    public Pile(Card... cardPile) {
        this.cardPile = new ArrayList<>(Arrays.asList(cardPile));
    }

    public Card getFirstCardInPile() {
        return cardPile.get(0);
    }

    public Card getLastCardInPile() {
        return cardPile.get(cardPile.size() - 1);
    }

    public boolean isEmpty() {
        return cardPile.isEmpty();
    }
}
