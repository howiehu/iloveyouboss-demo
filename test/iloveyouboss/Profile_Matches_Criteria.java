package iloveyouboss;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Profile_Matches_Criteria {

    @Test
    public void false_when_empty() {

        Profile profile = new Profile("Test Profile");

        Criteria criteria = new Criteria();

        assertThat(profile.matches(criteria), is(false));
    }

    @Test
    public void score_is_0_when_empty() {
        Profile profile = new Profile("Test Profile");

        Criteria criteria = new Criteria();

        profile.matches(criteria);

        assertThat(profile.score(), is(0));
    }
}