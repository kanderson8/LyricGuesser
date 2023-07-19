package game;

import data.*;
import factory.LyricGameFactory;
import gateway.SearchGateway;
import input.InputReader;

public class LyricGameOrchestrator implements GameOrchestrator {
    private final SearchGateway searchGateway;
    private final InputReader inputReader;
    private final LyricGameFactory lyricGameFactory;

    public LyricGameOrchestrator(SearchGateway searchGateway,
                                 InputReader inputReader,
                                 LyricGameFactory lyricGameFactory) {
        this.searchGateway = searchGateway;
        this.inputReader = inputReader;
        this.lyricGameFactory = lyricGameFactory;
    }

    @Override
    public void playGame() {
        SearchContext searchContext = inputReader.getSearchContext();
        SearchResults searchResults = searchGateway.search(searchContext);
        LyricGame lyricGame = lyricGameFactory.create(GameContext.of(searchResults));
        while (!lyricGame.isComplete()) {
            lyricGame.display();
            lyricGame.process(inputReader.getGuess());
        }
        lyricGame.displayStats();
    }
}
