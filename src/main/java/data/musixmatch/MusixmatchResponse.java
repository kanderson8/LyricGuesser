package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableMusixmatchResponse.class)
@Gson.TypeAdapters
public interface MusixmatchResponse {
    @SerializedName("message")
    Message getMessage();
}
