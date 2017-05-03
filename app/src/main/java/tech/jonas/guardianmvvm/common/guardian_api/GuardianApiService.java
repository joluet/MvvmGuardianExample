package tech.jonas.guardianmvvm.common.guardian_api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResponse;

public interface GuardianApiService {

    @GET("search?show-fields=thumbnail")
    Single<SearchResponse> search(@Query("q") String query);
}
