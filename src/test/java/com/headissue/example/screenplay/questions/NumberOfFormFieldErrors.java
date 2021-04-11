package com.headissue.example.screenplay.questions;

import com.headissue.example.screenplay.page.Form;
import net.serenitybdd.screenplay.Actor;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class NumberOfFormFieldErrors extends QuestionWithDefaultSubject<Integer> {
  @Override
  public Integer answeredBy(Actor actor) {
    actor.should(
        eventually(seeThat(the(Form.FIELD_ERROR), isPresent()))
            .waitingForNoLongerThan(10)
            .seconds());
    return Form.FIELD_ERROR.resolveAllFor(actor).size();
  }
}
