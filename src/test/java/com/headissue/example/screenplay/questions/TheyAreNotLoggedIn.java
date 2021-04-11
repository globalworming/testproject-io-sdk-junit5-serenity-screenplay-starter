package com.headissue.example.screenplay.questions;

import com.headissue.example.screenplay.page.Home;
import net.serenitybdd.screenplay.Actor;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class TheyAreNotLoggedIn extends QuestionWithDefaultSubject<Boolean> {
  @Override
  public Boolean answeredBy(Actor actor) {
    actor.should(seeThat(the(Home.LOGOUT), isNotVisible()));
    actor.should(seeThat(the(Home.LOGIN), isVisible()));
    return true;
  }
}
