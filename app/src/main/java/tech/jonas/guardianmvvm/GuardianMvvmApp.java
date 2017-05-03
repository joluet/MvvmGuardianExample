package tech.jonas.guardianmvvm;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.leakcanary.LeakCanary;

import tech.jonas.guardianmvvm.common.injection.application.ApplicationComponent;
import tech.jonas.guardianmvvm.common.injection.application.ApplicationModule;
import tech.jonas.guardianmvvm.common.injection.application.DaggerApplicationComponent;

public class GuardianMvvmApp extends Application {

    private final ApplicationComponent applicationComponent = createComponent();

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    protected ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public static GuardianMvvmApp get(@NonNull Context context) {
        return ((GuardianMvvmApp) context.getApplicationContext());
    }
}