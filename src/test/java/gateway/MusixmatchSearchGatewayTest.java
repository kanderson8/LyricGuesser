package gateway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import data.musixmatch.MusixmatchResponse;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

public class MusixmatchSearchGatewayTest {

    @Test
    public void testSearch() {
        //TODO: Add serialization/deserialization classes, mold this into a test for those, write actual tests for this class
        String result = "{\"message\":{\"header\":{\"status_code\":200,\"execute_time\":0.083481073379517},\"body\":{\"lyrics\":{\"lyrics_id\":30525030,\"explicit\":1,\"lyrics_body\":\"There's a picture on the wall\\nOf me on a John Deere\\nJenny handed me a beer\\nSaid, \\\"How the hell did you get there?\\\"\\nOh, Oklahoma\\nMm-mm, mm\\n\\nThere were flowers that were dry\\nSittin' on the dresser\\nShe asked me where they're from\\nI said, \\\"A place I don't remember\\\"\\nOh, Oklahoma (oh-oh)\\n\\nJenny jumped into the pool\\nShe was swimmin' with Nikki Lane\\nShe said, \\\"Most men don't want a woman\\nWith a legacy, it's of age\\\"\\nShe said, \\\"You can't be a muse and be happy, too\\nYou can't blacken the pages with Russian poetry\\nAnd be happy\\\"\\nAnd that scared me\\n'Cause I met a man who\\n\\n...\\n\\n******* This Lyrics is NOT for Commercial use *******\",\"script_tracking_url\":\"https:\\/\\/tracking.musixmatch.com\\/t1.0\\/m_js\\/e_1\\/sn_0\\/l_30525030\\/su_0\\/rs_0\\/tr_3vUCAOp57uWL1gt4pXQZGh_JkiNAnJFV5EeR4YyueEML5c0dv6oS0M52n2fq9oE3Z9f0mPiZhrRr81MNwjS0xwMXX40V7IXBcQb8ykCufD4uGLqiZxE1_TCQFqi0iuzZnmGaai4S93J-ZLJ3hlrirKemqAuNkGdbPmcK4eb-JQdSJ6tpY8XGSxE38Ms9ObY7pXPxIzj7weVJ_BkCoi2vVrX_WetR9p3YxSNCyAU6aDS9Q6VQVpEAjrFk0G8qdNPW6EB_vXXpkYd53WL91VZMuQ4rvNQSkAPVq0jZnmcktdoEk9icq17NPFA18QA8XxXQe5LhFBAkSn2i_pg-5IviMYOtEz5e2XJrC5gbbhLVFG26A3DM_Rm5xcsh4Gqwv45ZeRb9d3F2HENnPFmPYTw8TFoX3UZs06acvMCWODhp_h8\\/\",\"pixel_tracking_url\":\"https:\\/\\/tracking.musixmatch.com\\/t1.0\\/m_img\\/e_1\\/sn_0\\/l_30525030\\/su_0\\/rs_0\\/tr_3vUCAAb2xZP6xbGvvjYE0TNA00T4ny0WOkB_BkLPTXaOK_ZYw1fjt2hETKJJsR7mYdZyP61Fdz-RJEqcgCeA4kkldT6SWvImuT3NiR8LPUs0HeeVajJQLSfXHR0awBj_OoBvatq7_6oOAyDyKcwu5QRZpyuSwVA22T8xy1m9WHWD7AJA3fID2ry6qbQoqiNVKClP6VXUXMjd2St8s4rWPIkoFc68BBeXWvaQA6rOseaAzN9Ntq7x5GYYELN1-ztJceBp6QYZnIMT7-FQ9_SqrDzz196_f20AzXx7P3QNIUAQ4T-lUN-KAO4gcUpTs7w5WzpZ_oHgvxB32P14hxI8EbVFZkj7S0ZnmjDXk-uC1HI8JjsopmVLHgmuNx6KDVOTSCQGDhnqB3AiiNw_QENVyzSGWDZkFxP1utZklzm7D3g\\/\",\"lyrics_copyright\":\"Lyrics powered by www.musixmatch.com. This Lyrics is NOT for Commercial use and only 30% of the lyrics are returned.\",\"updated_time\":\"2023-05-23T00:51:21Z\"}}}}";

        GsonBuilder gsonBuilder = new GsonBuilder();
        for (TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class)) {
            gsonBuilder.registerTypeAdapterFactory(factory);
        }

        Gson gson = gsonBuilder.create();
        MusixmatchResponse musixmatchResponse = gson.fromJson(result, MusixmatchResponse.class);
        System.out.println(result.toString());
    }
}
