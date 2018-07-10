package cards;

import cards.model.card.Card;
import cards.model.card.Rank;
import cards.model.card.Suit;
import cards.model.piles.BoardPile;
import cards.model.piles.Pile;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Iryna on 07.09.2017.
 */
public class Util {

    public static Pile pile(Card... cards) {
        return new Pile(cards);
    }

    public static Pile pile() {
        return new Pile();
    }

    public static BoardPile boardPile() {
        return new BoardPile();
    }

    public static BoardPile boardPile(Card... cards) {
        LinkedList<Card> invisible = new LinkedList<>(Arrays.asList(cards));
        Card visible = invisible.removeLast();
        return new BoardPile(invisible, visible);
    }
  
    public static Card card(Suit suit, Rank rank) {
        return new Card(suit, rank);
    }
    
}
