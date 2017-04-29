package tech.jonas.guardianmvvm.common.mvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import tech.jonas.guardianmvvm.GuardianMvvmApp;
import tech.jonas.guardianmvvm.common.injection.application.UiScheduler;
import tech.jonas.guardianmvvm.common.injection.view.DaggerViewComponent;
import tech.jonas.guardianmvvm.common.injection.view.DefaultBinder;
import tech.jonas.guardianmvvm.common.injection.view.ViewModule;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataBindingViewHolder> {

    private final ViewProvider viewProvider;
    private final ViewModelBinder binder;
    private final Observable<List<ViewModel>> source;
    private List<ViewModel> latestViewModels = Collections.emptyList();
    @Inject
    @DefaultBinder
    ViewModelBinder viewModelBinder;
    @Inject
    @UiScheduler
    Scheduler uiScheduler;
    private final
    @NonNull
    HashMap<RecyclerView.AdapterDataObserver, Disposable> subscriptions = new HashMap<>();

    public RecyclerViewAdapter(final Single<List<ViewModel>> viewModelsObservable, final ViewProvider viewProvider, final Context
            context) {
        DaggerViewComponent.builder()
                .applicationComponent(GuardianMvvmApp.get(context).getComponent())
                .viewModule(new ViewModule(context))
                .build().inject(this);

        this.viewProvider = viewProvider;
        this.binder = viewModelBinder;
        source = viewModelsObservable
                .observeOn(uiScheduler)
                .toObservable()
                .doOnNext(viewModels -> {
                    latestViewModels = viewModels != null ? viewModels : new ArrayList<>();
                    notifyDataSetChanged();
                })
                .share();
    }

    @Override
    public int getItemViewType(int position) {
        return viewProvider.getView(latestViewModels.get(position));
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new DataBindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {
        binder.bind(holder.viewBinding, latestViewModels.get(position));
        holder.viewBinding.executePendingBindings();
    }

    @Override
    public void onViewRecycled(DataBindingViewHolder holder) {
        binder.bind(holder.viewBinding, null);
        holder.viewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return latestViewModels.size();
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        subscriptions.put(observer, source.subscribe());
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        Disposable subscription = subscriptions.remove(observer);
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    public static class DataBindingViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        final ViewDataBinding viewBinding;

        public DataBindingViewHolder(@NonNull ViewDataBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }
}
