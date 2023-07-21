package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonDeserialize(as = ImmutableAlbum.class)
@Gson.TypeAdapters
public interface Album {
    @SerializedName("album_id")
    int getAlbumId();

    @SerializedName("album_name")
    @Nullable
    String getAlbumName();

    @SerializedName("album_release_type")
    @Nullable
    String getAlbumReleaseType();
}
