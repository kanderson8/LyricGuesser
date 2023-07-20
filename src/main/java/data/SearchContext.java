package data;

import org.immutables.value.Value;

@Value.Immutable
public interface SearchContext {
    String getArtist(); //TODO: Revisit and make some of these nullable
}
