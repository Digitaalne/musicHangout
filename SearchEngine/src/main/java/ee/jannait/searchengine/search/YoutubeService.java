package ee.jannait.searchengine.search;

import io.mikael.urlbuilder.UrlBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class YoutubeService implements MusicProviderService {
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    HttpClient client = HttpClient.newBuilder().connectTimeout(TIMEOUT).build();
    private static final String SCHEMA = "https";
    private static final String HOST = "youtube.googleapis.com";
    private static final String BASE_PATH = "/youtube/v3";
    private static final String SEARCH_PATH = BASE_PATH+"/search";
    private static final Integer MAX_RESULTS = 15;
    private final String apiKey = System.getenv("YOUTUBE_API_KEY");

    @Override
    public YoutubeSearchResponse search(String query) {
        URI uri = UrlBuilder.empty()
                .withScheme(SCHEMA)
                .withHost(HOST)
                .withPath(SEARCH_PATH)
                .addParameter("part", "snippet")
                .addParameter("maxResults", MAX_RESULTS.toString())
                .addParameter("order", "viewCount")
                .addParameter("q", query)
                .addParameter("regionCode", "ee")
                .addParameter("type", "video")
                .addParameter("videoEmbeddable", "true")
                .addParameter("key", apiKey).toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type",  "application/json")
                .GET().build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            // TODO:: FIX IT
            throw new RuntimeException();
        }
        return gson.fromJson(response.body(), YoutubeSearchResponse.class);
    }
}
