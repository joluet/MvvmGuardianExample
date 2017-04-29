package tech.jonas.guardianmvvm.common.guardian_api.model;

import com.squareup.moshi.Json;

public class SearchResult {

    @Json(name = "webTitle")
    public final String title;

    public SearchResult(final String title) {
        this.title = title;
    }
}
