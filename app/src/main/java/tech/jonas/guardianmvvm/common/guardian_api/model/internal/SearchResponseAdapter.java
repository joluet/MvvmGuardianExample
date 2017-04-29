package tech.jonas.guardianmvvm.common.guardian_api.model.internal;

import com.squareup.moshi.FromJson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResponse;
import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResult;

public class SearchResponseAdapter {

    @FromJson
    SearchResponse fromJson(SearchResponseWrapperJson searchResponseWrapperJson) throws JSONException {
        final List<SearchResult> searchResultList = new ArrayList<>();
        final SearchResponseJson searchResponseJson = searchResponseWrapperJson.response;
        for (final SearchResult searchResult : searchResponseJson.results) {
            final String webTitle = searchResult.title;
            searchResultList.add(new SearchResult(webTitle));
        }
        return new SearchResponse(searchResultList);
    }
}