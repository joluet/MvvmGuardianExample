package tech.jonas.guardianmvvm.headlines;

import android.support.annotation.NonNull;

import tech.jonas.guardianmvvm.common.guardian_api.model.SearchResult;
import tech.jonas.guardianmvvm.common.mvvm.ViewModel;

public class HeadlineViewModel implements ViewModel {

    @NonNull
    public final String title;

    public HeadlineViewModel(@NonNull final SearchResult searchResult) {
        this.title = searchResult.title;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final HeadlineViewModel that = (HeadlineViewModel) o;

        if (!title.equals(that.title)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}