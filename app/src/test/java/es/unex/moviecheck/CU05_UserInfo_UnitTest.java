package es.unex.moviecheck;

import static org.junit.Assert.assertNotEquals;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class CU05_UserInfo_UnitTest {

    @Mock
    Context context;

    @Test
    public void test () throws NoSuchFieldException, IllegalAccessException {

        int test = R.layout.class.getField("fragment_profile").getInt(null);
        assertNotEquals(test,0);
    }

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
        context = ApplicationProvider.getApplicationContext();
    }

}
