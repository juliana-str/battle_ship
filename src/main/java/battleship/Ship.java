package battleship;

public enum Ship {
    FOUR_DECK_SHIP("\uD83D\uDEA2"), THREE_DECK_SHIP("\uD83D\uDEA2"),
    TWO_DECK_SHIP("\uD83D\uDEA2"), SINGLE_DECK_SHIP("\uD83D\uDEA2"),
    EMPTY("⬜"), AREA("\uD83D\uDFE6"), FIRE("\uD83D\uDFE5"),
    POINT("\uD83C\uDFF4\uDB40\uDC62\uDB40\uDC73\uDB40\uDC62\uDB40\uDC70\uDB40\uDC7F");

    private String shipType;

    Ship(String shipType) {
        this.shipType = shipType;
    }

    public String getShipType() {
        return shipType;
    }

    @Override
    public String toString() {
        return shipType;
    }
}
