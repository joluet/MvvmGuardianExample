package tech.jonas.guardianmvvm.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tech.jonas.guardianmvvm.GuardianMvvmApp;
import tech.jonas.guardianmvvm.common.injection.view.DaggerViewComponent;
import tech.jonas.guardianmvvm.common.injection.view.ViewComponent;
import tech.jonas.guardianmvvm.common.injection.view.ViewModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ViewComponent viewComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.viewComponent = DaggerViewComponent.builder()
                .applicationComponent(GuardianMvvmApp.get(this).getComponent())
                .viewModule(new ViewModule(this))
                .build();
    }

    protected ViewComponent getComponent() {
        return this.viewComponent;
    }
}