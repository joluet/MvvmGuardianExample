package tech.jonas.guardianmvvm.common.mvvm;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class BindingMethods {

    @BindingAdapter("adapter")
    public static void bindAdapter(@NonNull RecyclerView recyclerView, @Nullable RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"items", "view_provider"})
    public static void bindAdapterWithDefaultBinder(@NonNull RecyclerView recyclerView, @Nullable Single<List<ViewModel>> items,
                                                    @Nullable ViewProvider viewProvider) {
        final RecyclerViewAdapter adapter;
        if (items != null && viewProvider != null) {
            adapter = new RecyclerViewAdapter(items, viewProvider, recyclerView.getContext());
        } else {
            adapter = null;
        }
        bindAdapter(recyclerView, adapter);
    }

    @BindingConversion
    @NonNull
    public static ViewProvider getViewProviderForStaticLayout(@LayoutRes final int layoutId) {
        return vm -> layoutId;
    }

    @BindingAdapter("layout_vertical")
    public static void bindLayoutManager(@NonNull RecyclerView recyclerView, boolean vertical) {
        int orientation = vertical ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), orientation, false));
    }
}
