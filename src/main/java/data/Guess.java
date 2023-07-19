package data;

import org.immutables.value.Value;

@Value.Immutable
public interface Guess {
    String getText();
}
