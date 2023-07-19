package gateway.impl;

import data.ImmutableSearchResults;
import data.SearchContext;
import data.SearchResults;
import gateway.SearchGateway;

import java.util.Arrays;

public class LocalSearchGateway implements SearchGateway {

    @Override
    public SearchResults search(SearchContext searchContext) {
        return ImmutableSearchResults.builder()
                .searchContext(searchContext)
                .lyrics(Arrays.asList(
                        "God knows I live, God knows I died, God knows I begged, Begged, borrowed, and cried".split(" ")))
                .build();
    }
}
