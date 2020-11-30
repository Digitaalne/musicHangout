package ee.jannait.searchengine.search;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import ee.jannait.common.Provider;
import ee.jannait.common.SearchResponse;
import ee.jannait.common.YoutubeSearchResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchHandler implements RequestHandler<Input, List<SearchResponse>> {
    //TODO:: FIX THIS
    private YoutubeService youtubeService = new YoutubeService();
    @Override
    public List<SearchResponse> handleRequest(Input input, Context context) {
        if(input.getProvider().equals(Provider.YOUTUBE)){
            var ytResponse = youtubeService.search(input.getQuery());
            List<SearchResponse> responseList = new ArrayList<>();
            for (YoutubeSearchResource resource:
                 ytResponse.getItems()) {
                responseList.add(new SearchResponse(resource));
            }
            return responseList;
        }
        else {
            //TODO:: THROW ERROR
            return Collections.emptyList();
        }
    }
}
