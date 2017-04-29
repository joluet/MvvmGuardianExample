package tech.jonas.guardianmvvm;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

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

    @Mock
    GuardianApiService guardianApiServiceMock;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void subscribeToHeadlines_emitsCorrectHeadlines() throws Exception {
        final SearchResult result1 = new SearchResult("result number one");
        final SearchResult result2 = new SearchResult("result number two");
        final List<SearchResult> searchResults = Arrays.asList(result1, result2);
        when(guardianApiServiceMock.search(anyString()))
                .thenReturn(Single.just(new SearchResponse(searchResults)));

        final HeadlinesViewModel viewModelUnderTest = new HeadlinesViewModel(guardianApiServiceMock, Schedulers.trampoline());
        final TestObserver<List<ViewModel>> testObserver = viewModelUnderTest.headlineViewModels.test();

        final List<ViewModel> expectedViewModels = Arrays.asList(new HeadlineViewModel(result1), new HeadlineViewModel(result2));
        testObserver.assertNoErrors()
                .assertValue(expectedViewModels);
    }
}