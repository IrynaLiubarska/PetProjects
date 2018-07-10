package cards.model;

import cards.model.piles.BoardPile;
import cards.model.piles.CollectingPile;
import cards.model.piles.DeckPile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iryna on 23.05.2018.
 */
@Data
public class Table {
    private List<BoardPile> boardPiles = new ArrayList<>();
    private List<CollectingPile> collectingPiles = new ArrayList<>();
    private DeckPile deckPile;
}
