package ee.jannait.searchengine.search;

import ee.jannait.common.Provider;
import lombok.Data;

@Data
public class Input {
    private String query;
    private Provider provider;

}
