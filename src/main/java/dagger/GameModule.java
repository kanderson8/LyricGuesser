package dagger;

import factory.LyricGameFactory;
import game.GameOrchestrator;
import game.LyricGameOrchestrator;
import gateway.SearchGateway;
import gateway.impl.MusixmatchSearchGateway;
import input.InputReader;
import input.LocalInputReader;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import java.net.URISyntaxException;

@Module
public class GameModule {

    @Provides
    public GameOrchestrator provideGame(SearchGateway searchGateway,
                                        InputReader inputReader,
                                        LyricGameFactory lyricGameFactory) {
        return new LyricGameOrchestrator(searchGateway, inputReader, lyricGameFactory);
    }

    @Provides
    public SearchGateway provideLyricGateway(HttpClient httpClient, URIBuilder uriBuilder) {
        //return new LocalSearchGateway();
        return new MusixmatchSearchGateway(httpClient, uriBuilder);
    }

    @Provides
    public HttpClient provideHttpClient() {
        return HttpClients.createDefault();
    }

    @Provides
    public URIBuilder provideUriBuilder(String apiKey) {
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder("https://api.musixmatch.com/ws/1.1/matcher.lyrics.get");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        uriBuilder.addParameter("apikey", apiKey);
        return uriBuilder;
    }

    @Provides
    public String provideMusixmatchApiKey() {
        return System.getenv("MUSIXMATCH_API_KEY");
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
