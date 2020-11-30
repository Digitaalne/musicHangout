package ee.jannait.common;

import lombok.Data;

@Data
public class SearchResponse {
    private String id;
    private String artist;
    private String title;
    private Provider provider;
    private ThumbnailWrapper thumbnailList;

    public SearchResponse (YoutubeSearchResource youtubeSearchResource) {
        setId(youtubeSearchResource.getId().getVideoId());
        setArtist(youtubeSearchResource.getSnippet().getChannelTitle());
        setTitle(youtubeSearchResource.getSnippet().getTitle());
        setProvider(Provider.YOUTUBE);
        setThumbnailList(youtubeSearchResource.getSnippet().getThumbnails());
    }
}
