package tech.jonas.guardianmvvm.headlines;

import android.support.annotation.NonNull;

import tech.jonas.guardianmvvm.common.mvvm.ViewModel;

public class HeadlineViewModel implements ViewModel {

    @NonNull
    public final String title;
    @NonNull
    public final String date;
    @NonNull
    public final String imageUrl;

    public HeadlineViewModel(@NonNull final String title, @NonNull final String date, @NonNull final String imageUrl) {
        this.title = title;
        this.date = date;
        this.imageUrl = imageUrl;
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
        if (!date.equals(that.date)) {
            return false;
        }
        return imageUrl.equals(that.imageUrl);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + imageUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HeadlineViewModel{" +
               "title='" + title + '\'' +
               ", date='" + date + '\'' +
               ", imageUrl='" + imageUrl + '\'' +
               '}';
    }
}