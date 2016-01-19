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
}