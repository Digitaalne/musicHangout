package ee.jannait.searchengine.search;

import com.google.gson.Gson;

public interface MusicProviderService {
    Gson gson = new Gson();

    Object search(String query);
}
