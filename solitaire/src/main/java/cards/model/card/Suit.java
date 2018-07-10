package cards.model.card;

import lombok.Getter;

import static cards.model.card.Color.BLACK;
import static cards.model.card.Color.RED;

@Getter
public enum Suit {

    HEART(RED), DIAMOND(RED), CLUB(BLACK), SPADE(BLACK);

    private Color color;

    Suit(Color color) {
        this.color = color;
    }

}
