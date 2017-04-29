package tech.jonas.guardianmvvm.headlines;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import tech.jonas.guardianmvvm.R;
import tech.jonas.guardianmvvm.common.BaseActivity;
import tech.jonas.guardianmvvm.common.guardian_api.GuardianApiService;
import tech.jonas.guardianmvvm.common.injection.application.IoScheduler;
import tech.jonas.guardianmvvm.common.injection.view.DefaultBinder;
import tech.jonas.guardianmvvm.common.mvvm.ViewModel;
import tech.jonas.guardianmvvm.common.mvvm.ViewModelBinder;

public class HeadlinesActivity extends BaseActivity {

    @Inject
    GuardianApiService guardianApiService;
    @Inject
    @IoScheduler
    Scheduler ioScheduler;
    @Inject
    @DefaultBinder
    ViewModelBinder defaultBinder;

    private ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_headlines);
        defaultBinder.bind(binding, createViewModel());
    }

    @NonNull
    private ViewModel createViewModel() {
        return new HeadlinesViewModel(guardianApiService, ioScheduler);
    }

    @Override
    protected void onDestroy() {
        defaultBinder.bind(binding, null);
        binding.executePendingBindings();
        super.onDestroy();
    }
}
