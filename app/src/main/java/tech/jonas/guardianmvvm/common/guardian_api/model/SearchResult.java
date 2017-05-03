package tech.jonas.guardianmvvm.common.guardian_api.model;

import com.squareup.moshi.Json;

import java.util.Date;

public class SearchResult {

    public final String title;
    public final String imageUrl;
    public final Date publicationDate;

    public SearchResult(final String title, final String imageUrl, final Date publicationDate) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.publicationDate = publicationDate;
    }
}
