package cards;

import cards.model.piles.BoardPile;
import cards.model.piles.Deck;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Iryna on 25.05.2018.
 */
public class DeckTest {
    
    private Deck deck = Deck.generate();

    @Test
    public void shouldGenerateDeck() {
        assertEquals(52, deck.getInvisible().size());
    }

    @Test
    public void shouldGetBoardingPileWithPredeterminedNumberOfCards() {
        BoardPile pile1 = deck.getBoardingPile(1);
        assertEquals(1, pile1.getVisible().size());
        assertEquals(0, pile1.getInvisible().size());

        BoardPile pile7 = deck.getBoardingPile(7);
        assertEquals(1, pile7.getVisible().size());
        assertEquals(6, pile7.getInvisible().size());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGetBoardingPileWithNotAppropriateNumbersOfCards() {
        deck.getBoardingPile(0);
        thrown.expect(IllegalArgumentException.class);
        deck.getBoardingPile(8);
        thrown.expect(IllegalArgumentException.class);
    }

    @Test
    public void shouldShuffleDeck() {
    //TODO
    }
}
