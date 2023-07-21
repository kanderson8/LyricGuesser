package gateway.impl;

import client.MusixmatchClient;
import data.ImmutableSearchResults;
import data.SearchContext;
import data.SearchResults;
import data.musixmatch.Album;
import data.musixmatch.Artist;
import data.musixmatch.Lyrics;
import data.musixmatch.Track;
import gateway.SearchGateway;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MusixmatchSearchGateway implements SearchGateway {
    private final MusixmatchClient musixmatchClient;

    public MusixmatchSearchGateway(MusixmatchClient musixmatchClient) {
        this.musixmatchClient = musixmatchClient;
    }

    @Override
    public SearchResults search(SearchContext searchContext) {
        Optional<Artist> artist = musixmatchClient.searchArtist(searchContext.getArtist());
        if (artist.isPresent()) {
            List<Album> albums = musixmatchClient.getArtistAlbums(Integer.toString(artist.get().getArtistId()));
            Album randomAlbum = albums.get(Instant.now().getNano() % albums.size());
            List<Track> tracks = musixmatchClient.getAlbumTracks(Integer.toString(randomAlbum.getAlbumId()));
            Track track = tracks.get(Instant.now().getNano() % tracks.size());
            Lyrics lyrics = musixmatchClient.getTrackLyrics(Integer.toString(track.getTrackId()));
            String[] lyricsBody = lyrics.getLyricsBody().split("\\s+");
            return ImmutableSearchResults.builder().searchContext(searchContext).lyrics(Arrays.asList(lyricsBody)).build();
        } else {
            throw new RuntimeException("Could not find any matching artist :(");
        }
    }
}
