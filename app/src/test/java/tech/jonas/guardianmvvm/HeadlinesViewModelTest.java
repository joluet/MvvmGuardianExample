package tech.jonas.guardianmvvm;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import tech.jonas.guardianmvvm.common.guardian_api.GuardianApiService;
import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResponse;
import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResult;
import tech.jonas.guardianmvvm.common.mvvm.ViewModel;
import tech.jonas.guardianmvvm.headlines.HeadlineViewModel;
import tech.jonas.guardianmvvm.headlines.HeadlinesViewModel;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HeadlinesViewModelTest {

    private static final String DATE_FORMAT_ISO_8601_STRING = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final SimpleDateFormat DATE_FORMAT_ISO_8601 = new SimpleDateFormat(DATE_FORMAT_ISO_8601_STRING, Locale.ENGLISH);
    @Mock
    GuardianApiService guardianApiServiceMock;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void subscribeToHeadlines_emitsCorrectHeadlines() throws Exception {
        final SearchResult result1 = new SearchResult("result number one", "image url 1",
                                                      DATE_FORMAT_ISO_8601.parse("2017-04-18T17:20:59Z"));
        final SearchResult result2 = new SearchResult("result number two", "image url 2",
                                                      DATE_FORMAT_ISO_8601.parse("2017-03-21T17:29:43Z"));
        final List<SearchResult> searchResults = Arrays.asList(result1, result2);
        when(guardianApiServiceMock.search(anyString()))
                .thenReturn(Single.just(new SearchResponse(searchResults)));

        final HeadlinesViewModel viewModelUnderTest = new HeadlinesViewModel(guardianApiServiceMock, Schedulers.trampoline());
        final TestObserver<List<ViewModel>> testObserver = viewModelUnderTest.headlineViewModels.test();

        final List<ViewModel> expectedViewModels = Arrays.asList(new HeadlineViewModel("result number one", "18/04/2017", "image url 1"),
                                                                 new HeadlineViewModel("result number two", "21/03/2017", "image url 2"));
        testObserver.assertNoErrors()
                .assertValue(expectedViewModels);
    }
}