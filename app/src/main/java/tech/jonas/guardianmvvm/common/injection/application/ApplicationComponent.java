package tech.jonas.guardianmvvm.common.injection.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;
import tech.jonas.guardianmvvm.common.guardian_api.GuardianApiService;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    @UiScheduler
    Scheduler uiScheduler();

    @IoScheduler
    Scheduler ioScheduler();

    GuardianApiService guardianApiService();
}
