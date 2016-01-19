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

        Criterion criterion = generateDontCareCriterion();
        criteria.add(criterion);

        assertThat(profile.matches(criteria), is(true));
    }

    @Test
    public void score_is_weight_value_when_dont_care() {

        Criterion criterion = generateDontCareCriterion();
        criteria.add(criterion);

        profile.matches(criteria);

        assertThat(profile.score(), is(Weight.DontCare.getValue()));
    }

    @Test
    public void true_when_match() {

        Criterion criterion = generateMatchCriterion();
        criteria.add(criterion);

        assertThat(profile.matches(criteria), is(true));
    }

    @Test
    public void score_is_weight_value_when_match() {

        Criterion criterion = generateMatchCriterion();
        criteria.add(criterion);

        profile.matches(criteria);

        assertThat(profile.score(), is(Weight.Important.getValue()));
    }

    @Test
    public void false_when_not_match() {

        Criterion criterion = generateNotMatchCriterion();
        criteria.add(criterion);

        assertThat(profile.matches(criteria), is(false));
    }

    @Test
    public void score_is_0_when_not_match() {

        Criterion criterion = generateNotMatchCriterion();
        criteria.add(criterion);

        profile.matches(criteria);

        assertThat(profile.score(), is(0));
    }

    private Criterion generateDontCareCriterion() {
        Answer criterionAnswer = new Answer(question, Bool.TRUE);
        return new Criterion(criterionAnswer, Weight.DontCare);
    }

    private Criterion generateMatchCriterion() {
        Answer criterionAnswer = new Answer(question, Bool.FALSE);
        return new Criterion(criterionAnswer, Weight.Important);
    }

    private Criterion generateNotMatchCriterion() {
        Answer criterionAnswer = new Answer(question, Bool.TRUE);
        return new Criterion(criterionAnswer, Weight.Important);
    }
}