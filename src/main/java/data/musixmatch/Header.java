package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableHeader.class)
@Gson.TypeAdapters
public interface Header {
    @SerializedName("status_code")
    int getStatusCode();

    @SerializedName("execute_time")
    double getExecuteTime();
}
