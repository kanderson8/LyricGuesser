package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonDeserialize(as = ImmutableHeader.class)
@Gson.TypeAdapters
public interface Header {
    @SerializedName("status_code")
    int getStatusCode();

    @SerializedName("execute_time")
    double getExecuteTime();

    @SerializedName("available")
    @Nullable
    Integer getAvailable();


}
