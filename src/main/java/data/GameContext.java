package data;

import org.immutables.value.Value;

@Value.Immutable
public interface GameContext {
    SearchResults getSearchResults();

    static GameContext of(SearchResults searchResults) {
        return builder().searchResults(searchResults).build();
    }

    static ImmutableGameContext.Builder builder() {
        return ImmutableGameContext.builder();
    }
}
