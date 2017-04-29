package tech.jonas.guardianmvvm.common.injection.view;

import dagger.Component;
import tech.jonas.guardianmvvm.common.injection.application.ApplicationComponent;
import tech.jonas.guardianmvvm.common.mvvm.RecyclerViewAdapter;
import tech.jonas.guardianmvvm.headlines.HeadlinesActivity;

@PerView
@Component(dependencies = ApplicationComponent.class, modules = ViewModule.class)
public interface ViewComponent {

    void inject(RecyclerViewAdapter recyclerViewAdapter);

    void inject(HeadlinesActivity headlinesActivity);
}
