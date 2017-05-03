package tech.jonas.guardianmvvm;

import org.junit.Test;

import tech.jonas.guardianmvvm.headlines.HeadlineViewModel;

import static junit.framework.Assert.assertEquals;

public class HeadlineViewModelTest {

    @Test
    public void readTitle_titleIsCorrect() throws Exception {
        final String title = "some title";
        final String imageUrl = "some title";
        final String publicationDate = "18/04/2017";

        final HeadlineViewModel viewModelUnderTest = new HeadlineViewModel(title, publicationDate, imageUrl);

        assertEquals(title, viewModelUnderTest.title);
        assertEquals(imageUrl, viewModelUnderTest.imageUrl);
        assertEquals(publicationDate, viewModelUnderTest.date);
    }
}