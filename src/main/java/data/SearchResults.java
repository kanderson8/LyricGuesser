package data;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface SearchResults {
    SearchContext getSearchContext();
    List<String> getLyrics();
}
