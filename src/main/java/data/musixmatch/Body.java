package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonDeserialize(as = ImmutableBody.class)
@Gson.TypeAdapters
public interface Body {
    @SerializedName("lyrics")
    @Nullable
    Lyrics getLyrics();

    @SerializedName("artist_list")
    @Nullable
    List<ArtistWrapper> getArtistList();

    @SerializedName("album_list")
    @Nullable
    List<AlbumWrapper> getAlbumList();

    @SerializedName("track_list")
    @Nullable
    List<TrackWrapper> getTrackList();
}
