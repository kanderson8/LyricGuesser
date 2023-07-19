package gateway;

import data.SearchContext;
import data.SearchResults;

import java.util.List;

public interface SearchGateway {
    SearchResults search(SearchContext searchContext);
}
