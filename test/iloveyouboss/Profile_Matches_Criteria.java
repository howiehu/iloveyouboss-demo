package iloveyouboss;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Profile_Matches_Criteria {

    private Profile profile;

    @Before
    public void setUp() throws Exception {
        profile = new Profile("Test Profile");
    }

    @Test
    public void false_when_empty() {
        Criteria criteria = new Criteria();
        assertThat(profile.matches(criteria), is(false));
    }

    @Test
    public void score_is_0_when_empty() {
        profile.matches(new Criteria());
        assertThat(profile.score(), is(0));
    }
}