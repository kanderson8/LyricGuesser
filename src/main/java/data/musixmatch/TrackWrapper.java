package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableTrackWrapper.class)
@Gson.TypeAdapters
public interface TrackWrapper {
    @SerializedName("track")
    Track getTrack();
}
