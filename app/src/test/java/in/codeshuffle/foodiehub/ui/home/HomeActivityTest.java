package in.codeshuffle.foodiehub.ui.home;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;


public class HomeActivityTest {

    private HomeMvpView homeMvpView;
    private HomeMvpPresenter<HomeMvpView> homeMvpPresenter;
    private HomePresenter homePresenter;

    @Before
    public void start() {
        homeMvpView = mock(HomeMvpView.class);
       /* homePresenter = mock(HomeMvpPresenter.class);
        homePresenter = new HomePresenter();*/
    }

    @After
    public void finish() {
        homeMvpView = null;
        homeMvpPresenter = null;
        homePresenter = null;
    }

    @Test
    public void onLocationSearchClick() {

    }
}
