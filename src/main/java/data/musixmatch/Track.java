package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableTrack.class)
@Gson.TypeAdapters
public interface Track {
    @SerializedName("track_id")
    int getTrackId();
}
