package dagger;

import factory.LyricGameFactory;
import game.GameOrchestrator;
import game.LyricGameOrchestrator;
import gateway.SearchGateway;
import gateway.impl.LocalSearchGateway;
import input.InputReader;
import input.LocalInputReader;

@Module
public class GameModule {

    @Provides
    public GameOrchestrator provideGame(SearchGateway searchGateway,
                                        InputReader inputReader,
                                        LyricGameFactory lyricGameFactory) {
        return new LyricGameOrchestrator(searchGateway, inputReader, lyricGameFactory);
    }

    @Provides
    public SearchGateway provideLyricGateway() {
        return new LocalSearchGateway();
    }

    @Provides
    public InputReader provideInputReader() {
        return new LocalInputReader();
    }

    @Provides
    public LyricGameFactory provideLyricGameFactory() {
        return new LyricGameFactory();
    }
}
