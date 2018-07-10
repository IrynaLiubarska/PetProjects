package cards.model;

import cards.model.piles.Deck;

/**
 * Created by Iryna on 22.08.2017.
 */
public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer(new Table(), Deck.generate());
        dealer.generateAllPiles();
    }
}
