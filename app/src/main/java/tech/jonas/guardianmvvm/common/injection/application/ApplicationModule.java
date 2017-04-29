package tech.jonas.guardianmvvm.common.injection.application;

import android.app.Application;
import android.content.Context;

import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import tech.jonas.guardianmvvm.common.Constants;
import tech.jonas.guardianmvvm.common.guardian_api.GuardianApiService;
import tech.jonas.guardianmvvm.common.guardian_api.model.internal.SearchResponseAdapter;

@Module
public class ApplicationModule {

    protected final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @UiScheduler
    Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @IoScheduler
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        final Interceptor apiKeyInterceptor = chain -> {
            final Request request = chain.request();
            final HttpUrl url = request.url().newBuilder().addQueryParameter("api-key", Constants.GUARDIAN_API_KEY).build();
            return chain.proceed(request.newBuilder().url(url).build());
        };
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(apiKeyInterceptor)
                .build();
    }

    @Provides
    Moshi provideMoshi() {
        return new Moshi.Builder().add(new SearchResponseAdapter()).build();
    }

    @Provides
    GuardianApiService provideGuardianApiService(final OkHttpClient okHttpClient, final Moshi moshi) {
        final Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://content.guardianapis.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
        return retrofit.create(GuardianApiService.class);
    }
}
