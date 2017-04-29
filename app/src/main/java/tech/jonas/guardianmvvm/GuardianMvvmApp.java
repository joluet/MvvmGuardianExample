package tech.jonas.guardianmvvm;

import android.app.Application;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import tech.jonas.guardianmvvm.common.injection.application.ApplicationComponent;
import tech.jonas.guardianmvvm.common.injection.application.ApplicationModule;
import tech.jonas.guardianmvvm.common.injection.application.DaggerApplicationComponent;
import tech.jonas.guardianmvvm.common.mvvm.ViewModel;
import tech.jonas.guardianmvvm.common.mvvm.ViewModelBinder;

public class GuardianMvvmApp extends Application {

    private final ApplicationComponent applicationComponent = createComponent();

    @Override
    public void onCreate() {
        super.onCreate();
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