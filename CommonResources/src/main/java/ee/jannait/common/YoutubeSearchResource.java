package ee.jannait.common;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class YoutubeSearchResource {

    private String etag;
    private Id id;
    private Snippet snippet;

    @Data
    public static class Id {
        private String kind;
        private String videoId;
        private String channelId;
        private String playlistId;
    }

    @Data
    public static class Snippet {
        private OffsetDateTime publishedAt;
        private String channelId;
        private String title;
        private String description;
        private ThumbnailWrapper thumbnails;
        private String channelTitle;
        private String liveBroadcastContent;
    }
}
