package input;

import data.Guess;
import data.SearchContext;

public interface InputReader {
    SearchContext getSearchContext();
    Guess getGuess();
}
