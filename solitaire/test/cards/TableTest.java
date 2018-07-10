package cards;

import cards.model.Table;
import cards.model.card.Card;
import cards.model.piles.BoardPile;
import cards.model.piles.CollectingPile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static cards.Util.boardPile;
import static cards.Util.card;
import static cards.model.card.Rank.KING;
import static cards.model.card.Rank.QUEEN;
import static cards.model.card.Suit.CLUB;
import static cards.model.card.Suit.HEART;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Iryna on 25.05.2018.
 */
public class TableTest {
    
    Table table = new Table();

    @Test
    public void shouldSetBoardPiles() {
        List<BoardPile> piles = new ArrayList<>();
        piles.add(new BoardPile(card(CLUB, KING)));
        table.setBoardPiles(piles);
        assertEquals(1, table.getBoardPiles().size());

        piles.add(boardPile(card(HEART, KING), card(HEART, QUEEN)));
        table.setBoardPiles(piles);
        assertEquals(2, table.getBoardPiles().size());
    }

    @Test
    public void shouldSetCollectingPiles() {
        List<CollectingPile> piles = new ArrayList<>();
        piles.add(new CollectingPile(new ArrayList<Card>()));
        assertEquals(0, table.getCollectingPiles().size());
    }

    @Test
    public void shouldSetDeckPile() { 
        // TODO
    }

    @Test
    public void shouldGetBoardPiles() {
        // TODO
    }

    @Test
    public void shouldGetCollectingPiles() {
        //TODO
    }

    @Test
    public void shouldGetDeckPile() {
        //TODO
    }
}
