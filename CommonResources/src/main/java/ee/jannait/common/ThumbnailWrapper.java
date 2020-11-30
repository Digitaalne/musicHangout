package ee.jannait.common;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
@Data
public class ThumbnailWrapper {
    @SerializedName("default")
    private Thumbnail def;
    private Thumbnail medium;
    private Thumbnail high;
    private Thumbnail standard;
    private Thumbnail maxres;

    @Data
    public static class Thumbnail {
        private String url;
        private Integer width;
        private Integer height;
    }
}