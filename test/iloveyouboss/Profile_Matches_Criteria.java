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
        assertThat(profile.matches(new Criteria()), is(false));
    }

    @Test
    public void score_is_0_when_empty() {
        profile.matches(new Criteria());
        assertThat(profile.score(), is(0));
    }

    @Test
    public void true_when_has_single_criterion_and_dont_care() {

        Criteria criteria = new Criteria();

        Question question = new BooleanQuestion(1, "Test Question");
        Answer criterionAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criterionAnswer, Weight.DontCare);
        criteria.add(criterion);

        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

        assertThat(profile.matches(criteria), is(true));
    }

    @Test
    public void score_is_dont_care_value_when_has_single_criterion_and_dont_care() {

        Criteria criteria = new Criteria();

        Question question = new BooleanQuestion(1, "Test Question");
        Answer criterionAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criterionAnswer, Weight.DontCare);
        criteria.add(criterion);

        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

        profile.matches(criteria);

        assertThat(profile.score(), is(Weight.DontCare.getValue()));
    }
}