package ee.jannait.searchengine.search;

import ee.jannait.common.YoutubeSearchResource;
import lombok.Data;

import java.util.List;

@Data
public class YoutubeSearchResponse {
    private String kind;
    private String etag;
    private String nextPageToken;
    private String prevPageToken;
    private String regionCode;
    private PageInfo pageInfo;
    private List<YoutubeSearchResource> items;

    @Data
    public static class PageInfo {
        private Integer totalResults;
        private Integer resultsPerPage;
    }


}
