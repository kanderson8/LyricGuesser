package dagger;

import client.MusixmatchClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import factory.LyricGameFactory;
import game.GameOrchestrator;
import game.LyricGameOrchestrator;
import gateway.SearchGateway;
import gateway.impl.MusixmatchSearchGateway;
import input.InputReader;
import input.LocalInputReader;

import java.util.ServiceLoader;

@Module
public class GameModule {

    @Provides
    public GameOrchestrator provideGame(SearchGateway searchGateway,
                                        InputReader inputReader,
                                        LyricGameFactory lyricGameFactory) {
        return new LyricGameOrchestrator(searchGateway, inputReader, lyricGameFactory);
    }

    @Provides
    public SearchGateway provideLyricGateway(MusixmatchClient musixmatchClient) {
        //return new LocalSearchGateway();
        return new MusixmatchSearchGateway(musixmatchClient);
    }

    @Provides
    public MusixmatchClient provideMusixmatchClient(String apiKey, Gson gson) {
        return new MusixmatchClient(apiKey, gson);
    }

    @Provides
    public String provideMusixmatchApiKey() {
        return System.getenv("MUSIXMATCH_API_KEY");
    }

    @Provides
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        for (TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class)) {
            gsonBuilder.registerTypeAdapterFactory(factory);
        }

        return gsonBuilder.create();
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
