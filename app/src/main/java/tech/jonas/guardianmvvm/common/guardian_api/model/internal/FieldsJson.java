package tech.jonas.guardianmvvm.common.guardian_api.model.internal;

import com.squareup.moshi.Json;

public class FieldsJson {

    @Json(name="thumbnail")
    public final String thumbnailUrl;

    public FieldsJson(final String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
