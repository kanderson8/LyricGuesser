package gateway.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import data.ImmutableSearchResults;
import data.SearchContext;
import data.SearchResults;
import data.musixmatch.MusixmatchResponse;
import gateway.SearchGateway;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.ServiceLoader;

public class MusixmatchSearchGateway implements SearchGateway {
    private final HttpClient httpClient;
    private final URIBuilder uriBuilder;

    public MusixmatchSearchGateway(HttpClient httpClient, URIBuilder uriBuilder) {
        this.httpClient = httpClient;
        this.uriBuilder = uriBuilder;
    }

    @Override
    public SearchResults search(SearchContext searchContext) {
        String trackName = "Blue Banisters";
        String artistName = "Lana Del Rey";

        try {
            uriBuilder.addParameter("q_track", trackName);
            uriBuilder.addParameter("q_artist", artistName);

            URI uri = uriBuilder.build();
            HttpGet httpGet = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpGet);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            GsonBuilder gsonBuilder = new GsonBuilder();
            for (TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class)) {
                gsonBuilder.registerTypeAdapterFactory(factory);
            }

            Gson gson = gsonBuilder.create();
            MusixmatchResponse musixmatchResponse = gson.fromJson(result.toString(), MusixmatchResponse.class);
            String[] lyricsBody = musixmatchResponse.getMessage().getBody().getLyrics().getLyricsBody().split(" ");
            return ImmutableSearchResults.builder().searchContext(searchContext).lyrics(Arrays.asList(lyricsBody)).build();

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
