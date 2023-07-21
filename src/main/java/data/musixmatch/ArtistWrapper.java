package data.musixmatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableArtistWrapper.class)
@Gson.TypeAdapters
public interface ArtistWrapper {
    Artist getArtist();
}
