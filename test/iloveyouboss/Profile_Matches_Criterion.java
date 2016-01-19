package iloveyouboss;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Profile_Matches_Criterion {

    private Profile profile;
    private Criteria criteria;
    private Question question;

    @Before
    public void setUp() {

        profile = new Profile("Test Profile");
        criteria = new Criteria();
        question = new BooleanQuestion(1, "Test Question");

        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);
    }

    @Test
    public void true_when_dont_care() {

        Answer criterionAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criterionAnswer, Weight.DontCare);
        criteria.add(criterion);

        assertThat(profile.matches(criteria), is(true));
    }

    @Test
    public void score_is_weight_value_when_dont_care() {

        Answer criterionAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criterionAnswer, Weight.DontCare);
        criteria.add(criterion);

        profile.matches(criteria);

        assertThat(profile.score(), is(Weight.DontCare.getValue()));
    }


    @Test
    public void true_when_match() {

        Answer criterionAnswer = new Answer(question, Bool.FALSE);
        Criterion criterion = new Criterion(criterionAnswer, Weight.Important);
        criteria.add(criterion);

        assertThat(profile.matches(criteria), is(true));
    }

    @Test
    public void score_is_weight_value_when_match() {

        Answer criterionAnswer = new Answer(question, Bool.FALSE);
        Criterion criterion = new Criterion(criterionAnswer, Weight.Important);
        criteria.add(criterion);

        profile.matches(criteria);

        assertThat(profile.score(), is(Weight.Important.getValue()));
    }

    @Test
    public void false_when_not_match() {

        Answer criterionAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criterionAnswer, Weight.Important);
        criteria.add(criterion);

        assertThat(profile.matches(criteria), is(false));
    }

    @Test
    public void score_is_0_when_not_match() {

        Answer criterionAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criterionAnswer, Weight.Important);
        criteria.add(criterion);

        profile.matches(criteria);

        assertThat(profile.score(), is(0));
    }
}