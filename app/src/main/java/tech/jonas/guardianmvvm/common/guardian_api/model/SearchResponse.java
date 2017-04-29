package tech.jonas.guardianmvvm.common.guardian_api.model;

import java.util.List;

public class SearchResponse {

    public final List<SearchResult> results;

    public SearchResponse(final List<SearchResult> results) {
        this.results = results;
    }
}
