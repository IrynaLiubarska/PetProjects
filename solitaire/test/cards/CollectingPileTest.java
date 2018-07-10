package cards;

import cards.model.card.Card;
import cards.model.piles.CollectingPile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static cards.Util.card;
import static cards.Util.pile;
import static cards.model.card.Rank.*;
import static cards.model.card.Suit.CLUB;
import static cards.model.card.Suit.SPADE;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Iryna on 06.09.2017.
 */
public class CollectingPileTest {
    
    private CollectingPile emptyCollectingPile;
    private CollectingPile notFullCollectingPile;

    @Before
    public void setup() {
        emptyCollectingPile = new CollectingPile(new ArrayList<Card>());
        notFullCollectingPile = new CollectingPile(card(SPADE, ACE));
    }

    @Test
    public void shouldCheckIfCollectingPileIsEmpty() {
        assertEquals(emptyCollectingPile.isEmpty(), (true));
    }

    @Test
    public void shouldCheckIfCollectingPileIsNotEmpty() {
        assertEquals(notFullCollectingPile.isEmpty(), (false));
    }

    @Test
    public void shouldPutCardInEmptyCollectingPile() {
        emptyCollectingPile.put(card(SPADE, ACE));
        assertEquals(new CollectingPile(pile(card(SPADE, ACE))), emptyCollectingPile);
    }

    @Test
    public void shouldNotPutCardInEmptyCollectingPile() {
        emptyCollectingPile.put(card(SPADE, KING));
        assertEquals(new CollectingPile(new ArrayList<Card>()), emptyCollectingPile);
    }


    @Test
    public void shouldPutCardInFullCollectingPile() {
        notFullCollectingPile.put(card(SPADE, DEUCE));
        assertEquals(new CollectingPile(pile(card(SPADE, ACE), card(SPADE, DEUCE))), notFullCollectingPile);
    }

    @Test
    public void shouldNotPutCardInFullCollectingPileBecauseOfRank() {
        notFullCollectingPile.put(card(SPADE, KING));
        assertEquals(new CollectingPile(pile(card(SPADE, ACE))), notFullCollectingPile);
    }

    @Test
    public void shouldNotPutCardInFullCollectingPileBecauseOfSuit() {
        notFullCollectingPile.put(card(CLUB, DEUCE));
        assertEquals(new CollectingPile(pile(card(SPADE, ACE))), notFullCollectingPile);
    }

    @Test
    public void shouldNotPutCardINFullCollectingPileBecauseOfVisibility() {
        notFullCollectingPile.put(card(SPADE, DEUCE));
        assertEquals(new CollectingPile(pile(card(SPADE, ACE))), notFullCollectingPile);
    }
}
