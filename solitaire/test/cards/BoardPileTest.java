package cards;

import cards.model.piles.BoardPile;
import cards.model.card.Card;
import junit.framework.TestCase;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static cards.Util.*;
import static cards.model.card.Rank.KING;
import static cards.model.card.Rank.QUEEN;
import static cards.model.card.Suit.CLUB;
import static cards.model.card.Suit.HEART;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardPileTest {

    @Test
    public void putCardInFullBoardingPile() {
        BoardPile boardPile = new BoardPile(card(CLUB, KING));
        boardPile.put(pile(card(HEART, QUEEN)));
        assertEquals(boardPile, new BoardPile(card(CLUB, KING), card(HEART, QUEEN)));
    }

    @Test
    public void putSameColorCardInNotEmptyPileShouldBeNotSuccessful() {
        BoardPile boardPile = new BoardPile(card(CLUB, KING));
        boardPile.put(pile(card(CLUB, QUEEN)));
        assertEquals(boardPile, new BoardPile(card(CLUB, KING)));
    }

    @Test
    public void putSameRankCardInNotEmptyPileShouldBeNotSuccessful() {
        BoardPile boardPile = new BoardPile(card(CLUB, KING));
        boardPile.put(pile(card(HEART, KING)));
        assertEquals(boardPile, new BoardPile(card(CLUB, KING)));
    }

    @Test
    public void putKingInEmptyPileShouldBeSuccessful() {
        BoardPile emptyPile = new BoardPile();
        emptyPile.put(pile(card(HEART, KING)));
        TestCase.assertEquals(boardPile(card(HEART, KING)), emptyPile);
    }

    @Test
    public void putQueenInEmptyPileShouldBeNotSuccessful() {
        BoardPile emptyPile = boardPile();
        boolean operationResult = emptyPile.put(pile(card(HEART, QUEEN)));
        assertThat(operationResult, is(false));
        assertThat(emptyPile, Is.is(boardPile()));
    }
    
    @Test
    public void shouldReturnPilePart() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(card(HEART, KING));
        BoardPile boardPile = new BoardPile(cardList, card(HEART, QUEEN));
        BoardPile pile = boardPile.copyPilePart(1);
        TestCase.assertEquals(boardPile(card(HEART, QUEEN)), pile);
        TestCase.assertEquals(boardPile(card(HEART, KING), card(HEART, QUEEN)), boardPile);
    }


    @Test
    public void shouldRemovePilePart() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(card(HEART, KING));
        BoardPile boardPile = new BoardPile(cardList, card(HEART, QUEEN));
        boardPile.remove(1);
        TestCase.assertEquals(boardPile(card(HEART, KING)), boardPile);
        List<Card> result = new ArrayList<>();
        result.add(card(HEART, KING));
        assertEquals(result, boardPile.getVisible());
    }

    @Test
    public void shouldRemoveLastCardFromPile() {
        List<Card> cardList = new ArrayList<>();
        BoardPile boardPile = new BoardPile(cardList, card(HEART, QUEEN));
        boardPile.remove(1);
        TestCase.assertEquals(boardPile(), boardPile);
        List result = new ArrayList<>();
        assertEquals(result, boardPile.getVisible());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldNotCopyPilePartBecauseOfNotAppropriateAmountOfCards() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(card(HEART, KING));
        BoardPile boardPile = new BoardPile(cardList, card(HEART, QUEEN));
        boardPile.copyPilePart(2);
        TestCase.assertEquals(boardPile(card(HEART, KING), card(HEART, QUEEN)), boardPile);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldNotCopyEmptyPilePart() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(card(HEART, KING));
        BoardPile boardPile = new BoardPile(cardList, card(HEART, QUEEN));
        boardPile.copyPilePart(0);
        TestCase.assertEquals(boardPile(card(HEART, KING), card(HEART, QUEEN)), boardPile);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldNotCopyPileFromEmptyPile() {
        List<Card> cardListInvisible = new ArrayList<>();
        List<Card> cardListVisible = new ArrayList<>();
        BoardPile boardPile = new BoardPile(cardListInvisible, cardListVisible);
        boardPile.copyPilePart(0);
        TestCase.assertEquals(boardPile(), boardPile);
    }

}

