package tech.jonas.guardianmvvm.headlines;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import tech.jonas.guardianmvvm.common.guardian_api.GuardianApiService;
import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResult;
import tech.jonas.guardianmvvm.common.injection.application.IoScheduler;
import tech.jonas.guardianmvvm.common.mvvm.ViewModel;

public class HeadlinesViewModel implements ViewModel {

    public static final String DATE_FORMAT_STRING = "dd/MM/YYYY";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STRING, Locale.ENGLISH);

    public final Single<List<ViewModel>> headlineViewModels;

    public HeadlinesViewModel(final GuardianApiService guardianApiService, @IoScheduler Scheduler ioScheduler) {
        this.headlineViewModels = guardianApiService.search("trump")
                .subscribeOn(ioScheduler)
                .map(searchResponse -> {
                    List<ViewModel> vms = new ArrayList<>();
                    for (SearchResult searchResult : searchResponse.results) {
                        final String formattedDate = DATE_FORMAT.format(searchResult.publicationDate);
                        vms.add(new HeadlineViewModel(searchResult.title, formattedDate, searchResult.imageUrl));
                    }
                    return vms;
                });
    }
}
