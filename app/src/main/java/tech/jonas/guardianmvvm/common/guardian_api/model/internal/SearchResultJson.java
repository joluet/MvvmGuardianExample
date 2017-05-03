package tech.jonas.guardianmvvm.common.guardian_api.model.internal;

import com.squareup.moshi.Json;

import java.util.Date;

public class SearchResultJson {

    @Json(name = "webTitle")
    public final String title;
    public final FieldsJson fields;
    @Json(name = "webPublicationDate")
    public final String publicationDate;

    public SearchResultJson(final String title, final FieldsJson fields, final String publicationDate) {
        this.title = title;
        this.fields = fields;
        this.publicationDate = publicationDate;
    }
}
