package data;

public class Slot {
    private final String value;
    private boolean isGuessed;

    public Slot(final String value) {
        this.value = value;
        this.isGuessed = false;
    }

    public String getValue() {
        return value;
    }

    public boolean isGuessed() {
        return isGuessed;
    }

    public void setGuessed(boolean guessed) {
        isGuessed = guessed;
    }
}
