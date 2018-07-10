package cards.model.piles;

import cards.model.card.Card;
import cards.model.card.Rank;
import lombok.Data;

import java.util.*;

@Data
public class BoardPile extends Pile {

    public Stack<Card> getVisible() {
        return visible;
    }

    private Stack<Card> visible = new Stack<>();
    private Stack<Card> invisible = new Stack<>();

    public BoardPile(List<Card> invisible, Card visible) {
        this.visible.push(visible);
        this.invisible.addAll(invisible);
    }

    public BoardPile(Stack<Card> invisible, Card visible) {
        this.visible.push(visible);
        this.invisible.addAll(invisible);
    }

    public BoardPile(List<Card> invisible, List<Card> visible) {
        this.invisible.addAll(invisible);
        this.visible.addAll(visible);
    }

    public BoardPile(List<Card> cards) {
        visible.push(cards.get(cards.size() - 1));
        invisible.addAll(cards.subList(0, cards.size() - 1));
    }

    public BoardPile(Card... cardPile) {
        visible.addAll(new ArrayList<>(Arrays.asList(cardPile)));
    }

    public boolean put(Pile pile) {
        return canPut(pile) && visible.addAll(pile.cardPile);
    }

    private boolean canPut(Pile pile) {
        Card startingCard = pile.getFirstCardInPile();
        if (isEmpty()) return isKing(startingCard);
        else if (checkIfCardFit(startingCard)) return true;
        return false;
    }

    @Override
    public boolean isEmpty() {
        return visible.isEmpty();
    }
    
    @Override
    public Card getLastCardInPile() {
        return visible.lastElement();
    }

    private boolean checkIfCardFit(Card startingCard) {
        Card aimCard = visible.peek();
        return (startingCard.oppositeSuits(aimCard) && startingCard.isNextRank(aimCard));
    }

    public BoardPile copyPilePart(int amountOfCards) {
        checkBounds(amountOfCards);
        LinkedList<Card> cards = new LinkedList<>();
        for (int i = 0; i < amountOfCards; i++) {
            cards.addLast(visible.peek());
        }
        return new BoardPile(cards);
    }

    public Card copyLastCard() {
        checkBounds(1);
        return visible.peek();
    }

    public void remove(int amountOfCards) {
        LinkedList<Card> cards = new LinkedList<>();
        for (int i = 0; i < amountOfCards; i++) {
            cards.addLast(visible.remove(i));
        }
        ensureAtLeastOneCardIsOpen();
    }

    private void ensureAtLeastOneCardIsOpen() {
        if (visible.isEmpty() && !invisible.isEmpty()) {
            visible.push(invisible.pop());
        }
    }

    private void checkBounds(int amountOfCards) {
        if (amountOfCards < 1 || amountOfCards > visible.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isKing(Card startingCard) {
        return startingCard.getRank() == Rank.KING;
    }
    
}
