package tech.jonas.guardianmvvm.common.guardian_api.model.internal;

import java.util.List;

import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResult;

public class SearchResponseJson {

    public final List<SearchResult> results;

    public SearchResponseJson(final List<SearchResult> results) {
        this.results = results;
    }
}
