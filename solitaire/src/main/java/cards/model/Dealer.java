package cards.model;

import cards.model.card.Card;
import cards.model.piles.BoardPile;
import cards.model.piles.CollectingPile;
import cards.model.piles.Deck;
import cards.model.piles.DeckPile;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iryna on 23.05.2018.
 */
@AllArgsConstructor
public class Dealer {
    private Table table;
    private Deck deck;
    
    public void generateAllPiles() {
        deck.shuffle();
        generateBoardPiles(deck);
        generateDeckPile(deck);
        generateCollectionPiles();
    }

    private void generateDeckPile(Deck deck) {
        table.setDeckPile(new DeckPile(deck));
    }

    private void generateBoardPiles(Deck deck) {
        List<BoardPile> boardPiles = new ArrayList<>();
        for (int numberOfCards = 1; numberOfCards < 8; numberOfCards++) {
            BoardPile boardPile = deck.getBoardingPile(numberOfCards);
            boardPiles.add(boardPile);
        }
        table.setBoardPiles(boardPiles);
    }

    private void generateCollectionPiles() {
        for (int i = 0; i < 4; i++) {
            table.getCollectingPiles().add(new CollectingPile(new ArrayList<Card>()));
        }
    }
}
