import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.google.firebase.auth.FirebaseAuth;
import com.hata.login.Presentation.HomeActivity.HomeContract;
import com.hata.login.Presentation.HomeActivity.HomePresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FirebaseAuth.class, FirebaseAuth.class, HomePresenter.class})
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
public class ExampleUnitTest {

    @Mock
    HomeContract.View mockView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(Process.class);
        when(Process.myPid()).thenReturn(1234);
    }

    @Test
    public void checkStringAdd() {
        HomePresenter presenter = new HomePresenter(mockView);
        String newString = presenter.newText("Pitanga");
        assertEquals("Pitanga ", newString);
    }
}
