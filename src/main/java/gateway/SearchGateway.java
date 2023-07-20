package gateway;

import data.SearchContext;
import data.SearchResults;

public interface SearchGateway {
    SearchResults search(SearchContext searchContext);
}
