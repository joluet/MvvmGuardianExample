package tech.jonas.guardianmvvm;

import org.junit.Test;

import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResult;
import tech.jonas.guardianmvvm.headlines.HeadlineViewModel;

import static junit.framework.Assert.assertEquals;

public class HeadlineViewModelTest {

    @Test
    public void readTitle_titleIsCorrect() throws Exception {
        final String title = "some title";

        final HeadlineViewModel viewModelUnderTest = new HeadlineViewModel(new SearchResult(title));

        assertEquals(title, viewModelUnderTest.title);
    }
}