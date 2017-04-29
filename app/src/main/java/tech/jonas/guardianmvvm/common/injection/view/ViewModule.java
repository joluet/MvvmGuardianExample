package tech.jonas.guardianmvvm.common.injection.view;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.android.databinding.library.baseAdapters.BR;

import dagger.Module;
import dagger.Provides;
import tech.jonas.guardianmvvm.common.mvvm.ViewModel;
import tech.jonas.guardianmvvm.common.mvvm.ViewModelBinder;

@Module
public class ViewModule {

    private Context context;

    public ViewModule(Context context) {
        this.context = context;
    }

    @Provides
    @ViewContext
    Context provideContext() {
        return context;
    }

    @Provides
    @DefaultBinder
    ViewModelBinder provideDefaultBinder() {
        return (viewDataBinding, viewModel) -> viewDataBinding.setVariable(BR.viewModel, viewModel);
    }
}
