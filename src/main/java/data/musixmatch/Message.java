package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableMessage.class)
@Gson.TypeAdapters
public interface Message {
    @SerializedName("header")
    Header getHeader();

    @SerializedName("body")
    Body getBody();
}
