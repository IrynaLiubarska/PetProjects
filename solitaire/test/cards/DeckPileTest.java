package cards;

import cards.model.piles.DeckPile;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static cards.Util.card;
import static cards.Util.pile;
import static cards.model.card.Rank.FOUR;
import static cards.model.card.Rank.THREE;
import static cards.model.card.Suit.DIAMOND;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThat;

public class DeckPileTest {

    private DeckPile deckPile;

    @Before
    public void before() {
        deckPile = new DeckPile(pile(card(DIAMOND, FOUR), card(DIAMOND, THREE)));
    }

    @Test
    public void testCurrentCard() {
        assertThat(deckPile.getCurrentCard(), Is.is(card(DIAMOND, FOUR)));
    }

    @Test
    public void shouldRemoveCurrentCard() {
        deckPile.remove();
        assertEquals(new DeckPile(pile(card(DIAMOND, THREE))), deckPile);
    }

    @Test
    public void shouldMoveToNextCardInPile() {
        deckPile.scrollToNextCard();
        assertEquals(new DeckPile(pile(card(DIAMOND, THREE), card(DIAMOND, FOUR))), deckPile);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenPileIsEmpty() {
        deckPile.remove();
        deckPile.remove();
        deckPile.remove();
        thrown.expect(IllegalStateException.class);
    }
    
}
