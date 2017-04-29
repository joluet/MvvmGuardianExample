package tech.jonas.guardianmvvm.common.mvvm;

import android.databinding.ViewDataBinding;

public interface ViewModelBinder {
    void bind(ViewDataBinding viewDataBinding, ViewModel viewModel);
}