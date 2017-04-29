package tech.jonas.guardianmvvm.headlines;

import android.support.annotation.NonNull;

public class Headline {

    @NonNull
    public final String name;

    public Headline(@NonNull String name) {
        this.name = name;
    }
}
