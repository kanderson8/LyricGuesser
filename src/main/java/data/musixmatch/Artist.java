package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableArtist.class)
@Gson.TypeAdapters
public interface Artist {
    @SerializedName("artist_id")
    int getArtistId();

    @SerializedName("artist_name")
    String getArtistName();
}
