package tech.jonas.guardianmvvm.common.guardian_api.model.internal;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResponse;
import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResult;

public class SearchResponseAdapter {

    private static final String DATE_FORMAT_ISO_8601_STRING = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final SimpleDateFormat DATE_FORMAT_ISO_8601 = new SimpleDateFormat(DATE_FORMAT_ISO_8601_STRING, Locale.ENGLISH);

    @FromJson
    SearchResponse fromJson(final SearchResponseWrapperJson searchResponseWrapperJson) throws JSONException, ParseException {
        final List<SearchResult> searchResultList = new ArrayList<>();
        final SearchResponseJson searchResponseJson = searchResponseWrapperJson.response;
        for (final SearchResultJson searchResult : searchResponseJson.results) {
            final String webTitle = searchResult.title;
            final String imageUrl = searchResult.fields.thumbnailUrl;
            final Date publicationDate = DATE_FORMAT_ISO_8601.parse(searchResult.publicationDate);
            searchResultList.add(new SearchResult(webTitle, imageUrl, publicationDate));
        }
        return new SearchResponse(searchResultList);
    }

    @ToJson
    SearchResponseWrapperJson toJson(final SearchResponse searchResponse) {
        final List<SearchResultJson> searchResultList = new ArrayList<>();
        for (final SearchResult searchResult : searchResponse.results) {
            final String webTitle = searchResult.title;
            final String imageUrl = searchResult.imageUrl;
            final String publicationDate = DATE_FORMAT_ISO_8601.format(searchResult.publicationDate);
            searchResultList.add(new SearchResultJson(webTitle, new FieldsJson(imageUrl), publicationDate));
        }
        return new SearchResponseWrapperJson(new SearchResponseJson(searchResultList));
    }
}