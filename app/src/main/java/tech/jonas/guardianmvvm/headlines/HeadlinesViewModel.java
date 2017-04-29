package tech.jonas.guardianmvvm.headlines;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import tech.jonas.guardianmvvm.common.guardian_api.GuardianApiService;
import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResult;
import tech.jonas.guardianmvvm.common.injection.application.IoScheduler;
import tech.jonas.guardianmvvm.common.mvvm.ViewModel;

public class HeadlinesViewModel implements ViewModel {

    public final Single<List<ViewModel>> headlineViewModels;

    public HeadlinesViewModel(final GuardianApiService guardianApiService, @IoScheduler Scheduler ioScheduler) {
        this.headlineViewModels = guardianApiService.search("trump")
                .subscribeOn(ioScheduler)
                .map(searchResponse -> {
                    List<ViewModel> vms = new ArrayList<>();
                    for (SearchResult searchResult : searchResponse.results) {
                        vms.add(new HeadlineViewModel(searchResult));
                    }
                    return vms;
                });
    }
}
