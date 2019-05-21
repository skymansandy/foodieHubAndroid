package in.codeshuffle.foodiehub.ui.home;

import org.junit.After;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class HomePresenterTest {

    private HomeMvpView homeMvpView;
    private HomePresenter homePresenter;

    @Before
    public void start() {
        homeMvpView = mock(HomeMvpView.class);
//        homePresenter = new HomePresenter();
    }

    @After
    public void finish() {
        homeMvpView = null;
        homePresenter = null;
    }


}
