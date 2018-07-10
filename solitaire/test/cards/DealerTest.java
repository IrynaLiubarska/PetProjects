package cards;

import cards.model.Dealer;
import cards.model.Table;
import cards.model.piles.Deck;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Iryna on 25.05.2018.
 */
public class DealerTest {
    private Table table;
    private Dealer dealer;

    @Before
    public void before() {
        table = new Table();
        dealer = new Dealer(table, Deck.generate());
    }

    @Test
    public void shouldGenerateAllPiles() {
        dealer.generateAllPiles();
        assertEquals(7, table.getBoardPiles().size());
        
        assertNotNull(table.getCollectingPiles());
    }

}
