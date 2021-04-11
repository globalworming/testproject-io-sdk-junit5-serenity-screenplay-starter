package com.headissue.example.features.basic;

import com.headissue.example.TestBase;
import com.headissue.example.screenplay.actions.*;
import com.headissue.example.screenplay.page.Home;
import com.headissue.example.screenplay.questions.NumberOfFormFieldErrors;
import com.headissue.example.screenplay.questions.TheyAreNotLoggedIn;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.CoreMatchers.is;

@EnabledIfEnvironmentVariable(named = "TP_DEV_TOKEN", matches = ".*")
public class BasicWebTest extends TestBase {

  @Test
  @DisplayName("when logging in and logging out")
  void whenLoggingInAndOut() {
    checker.attemptsTo(Open.browserOn(new Home()));
    checker.attemptsTo(LogsIn.withProvidedCredentials());
    checker.should(seeThat(the(Home.LOGOUT), isVisible()));
    checker.attemptsTo(new LogsOut());
    checker.should(seeThat(new TheyAreNotLoggedIn()));
  }

  @Test
  @DisplayName("when filling the form")
  void whenFillingTheForm() {
    givenThat(checker).wasAbleTo(new LogInSuccessfully());
    checker.attemptsTo(new FillTheFormIncorrectly());
    checker.should(seeThat(new NumberOfFormFieldErrors(), is(6)));
    checker.attemptsTo(Comment.that("LGTM"));
    new Actor("Super Tester").attemptsTo(new ChecksCheckerResult());
  }
}
