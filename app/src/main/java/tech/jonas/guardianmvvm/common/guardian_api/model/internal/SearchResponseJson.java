package tech.jonas.guardianmvvm.common.guardian_api.model.internal;

import java.util.List;

import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResult;

public class SearchResponseJson {

    public final List<SearchResultJson> results;

    public SearchResponseJson(final List<SearchResultJson> results) {
        this.results = results;
    }
}
