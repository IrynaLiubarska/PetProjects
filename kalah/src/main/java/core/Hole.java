package core;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Hole {

    private int index;
    private boolean user;

    public Hole(int index, boolean user) {
        this.index = index;
        this.user = user;
    }

    boolean getUser() {
        return user;
    }

    boolean isKalah() {
        return getIndex() == 6;
    }
}
