package client;

import com.google.gson.Gson;
import data.musixmatch.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MusixmatchClient {
    private static final String BASE_ENDPOINT = "https://api.musixmatch.com/ws/1.1/";
    private static final String ARTIST_SEARCH_ENDPOINT = BASE_ENDPOINT + "artist.search";
    private static final String ARTIST_ALBUMS_GET_ENDPOINT = BASE_ENDPOINT + "artist.albums.get";
    private static final String ALBUM_TRACKS_GET_ENDPOINT = BASE_ENDPOINT + "album.tracks.get";
    private static final String TRACK_LYRICS_GET_ENDPOINT = BASE_ENDPOINT + "track.lyrics.get";
    private static final String MATCHER_LYRICS_GET_ENDPOINT = BASE_ENDPOINT + "matcher.lyrics.get";

    private final String apiKey;
    private final Gson gson;

    public MusixmatchClient(String apiKey, Gson gson) {
        this.apiKey = apiKey;
        this.gson = gson;
    }

    public Optional<Artist> searchArtist(String artistName) {
        try {
            URIBuilder uriBuilder = new URIBuilder(ARTIST_SEARCH_ENDPOINT);
            uriBuilder.addParameter("apikey", apiKey);
            uriBuilder.addParameter("q_artist", artistName);
            MusixmatchResponse musixmatchResponse = get(uriBuilder.build());
            return musixmatchResponse.getMessage()
                    .getBody()
                    .getArtistList()
                    .stream()
                    .map(ArtistWrapper::getArtist)
                    .filter(artist -> artistName.equals(artist.getArtistName()))
                    .findFirst();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Album> getArtistAlbums(String artistId) {
        try {
            URIBuilder uriBuilder = new URIBuilder(ARTIST_ALBUMS_GET_ENDPOINT);
            uriBuilder.addParameter("apikey", apiKey);
            uriBuilder.addParameter("artist_id", artistId);
            uriBuilder.addParameter("g_album_name", "1");
            MusixmatchResponse musixmatchResponse = get(uriBuilder.build());
            return musixmatchResponse.getMessage()
                    .getBody()
                    .getAlbumList()
                    .stream()
                    .map(AlbumWrapper::getAlbum)
                    .collect(Collectors.toList());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Track> getAlbumTracks(String albumId) {
        try {
            URIBuilder uriBuilder = new URIBuilder(ALBUM_TRACKS_GET_ENDPOINT);
            uriBuilder.addParameter("apikey", apiKey);
            uriBuilder.addParameter("album_id", albumId);
            uriBuilder.addParameter("f_has_lyrics", "1");
            MusixmatchResponse musixmatchResponse = get(uriBuilder.build());
            return musixmatchResponse.getMessage()
                    .getBody()
                    .getTrackList()
                    .stream()
                    .map(TrackWrapper::getTrack)
                    .collect(Collectors.toList());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Lyrics getTrackLyrics(String trackId) {
        try {
            URIBuilder uriBuilder = new URIBuilder(TRACK_LYRICS_GET_ENDPOINT);
            uriBuilder.addParameter("apikey", apiKey);
            uriBuilder.addParameter("track_id", trackId);

            MusixmatchResponse musixmatchResponse = get(uriBuilder.build());
            return musixmatchResponse.getMessage().getBody().getLyrics();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public MusixmatchResponse matchLyrics(String artistName, String trackName) {
        try {
            URIBuilder uriBuilder = new URIBuilder(MATCHER_LYRICS_GET_ENDPOINT);
            uriBuilder.addParameter("apikey", apiKey);
            uriBuilder.addParameter("q_track", trackName);
            uriBuilder.addParameter("q_artist", artistName);

            return get(uriBuilder.build());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private MusixmatchResponse get(URI uri) {
        HttpGet httpGet = new HttpGet(uri);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpResponse response = httpClient.execute(httpGet);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return gson.fromJson(result.toString(), MusixmatchResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
