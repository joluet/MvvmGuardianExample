package tech.jonas.guardianmvvm.common.mvvm;

import android.support.annotation.LayoutRes;

public interface ViewProvider {
    @LayoutRes
    int getView(ViewModel vm);
}