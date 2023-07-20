package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import org.immutables.gson.Gson.TypeAdapters;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableLyrics.class)
@TypeAdapters
public interface Lyrics {
    @SerializedName("lyrics_id")
    int getLyricsId();

    @SerializedName("explicit")
    int getExplicit();

    @SerializedName("lyrics_body")
    String getLyricsBody();

    @SerializedName("script_tracking_url")
    String getScriptTrackingUrl();

    @SerializedName("pixel_tracking_url")
    String getPixelTrackingUrl();

    @SerializedName("lyrics_copyright")
    String getLyricsCopyright();

    @SerializedName("updated_time")
    String getUpdatedTime();
}

