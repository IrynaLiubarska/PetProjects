package cards.model;

import cards.model.card.Card;
import cards.model.piles.BoardPile;
import cards.model.piles.CollectingPile;
import cards.model.piles.DeckPile;
import cards.model.piles.Pile;

public class GameEngine {
    
    private Table table = new Table();

    void doMove(Integer currentPileOnBoard, Integer amountOfCardsToMove, Integer aimPileOnBoard) {
        BoardPile currentPile = getBoardPile(currentPileOnBoard);
        if (!currentPile.isEmpty()) {
            partOfMove(amountOfCardsToMove, aimPileOnBoard, currentPile);
        }
    }

    private void partOfMove(Integer amountOfCardsToMove, Integer aimPileOnBoard, BoardPile currentPile) {
        BoardPile aimPile = getBoardPile(aimPileOnBoard);
        Pile pile = currentPile.copyPilePart(amountOfCardsToMove);
        if (aimPile.put(pile)) currentPile.remove(amountOfCardsToMove);
    }

    void gatherPile(Integer indexOfCurrentPile, Integer indexOfAimPile) {
        CollectingPile aimCollectingPile = getCollectingPile(indexOfAimPile);
        BoardPile currentBoardingPile = getBoardPile(indexOfCurrentPile);
        if (!currentBoardingPile.isEmpty()) putInCollectingPileFromBoardingPile(aimCollectingPile, currentBoardingPile);
    }

    private void putInCollectingPileFromBoardingPile(CollectingPile aimPile, BoardPile currentPile) {
        Card lastCard = currentPile.copyLastCard();
        if (aimPile.put(lastCard)) currentPile.remove(1);
    }
    
    void gatherPileFromDeck(Integer indexOfAimPile) {
        CollectingPile aimPile = getCollectingPile(indexOfAimPile);
        DeckPile deck = table.getDeckPile();
        if (!deck.isEmpty() && aimPile.put(deck.getCurrentCard())) deck.remove();
    }

    void giveOutCard(Integer indexAimPile) {
        DeckPile deck = table.getDeckPile();
        BoardPile aimPile = getBoardPile(indexAimPile);
        Card card = deck.remove();
        aimPile.put(new Pile(card));
    }

    void getNextCardInDeckPile() {
        DeckPile deck = table.getDeckPile();
        deck.scrollToNextCard();
    }

    private BoardPile getBoardPile(Integer indexOfCurrentPile) {
        return table.getBoardPiles().get(indexOfCurrentPile);
    }

    private CollectingPile getCollectingPile(Integer indexOfAimPile) {
        return table.getCollectingPiles().get(indexOfAimPile);
    }
}
