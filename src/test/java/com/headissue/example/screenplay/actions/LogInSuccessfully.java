package com.headissue.example.screenplay.actions;

import com.headissue.example.screenplay.page.Home;
import com.headissue.example.screenplay.questions.TheyAreLoggedIn;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class LogInSuccessfully implements Performable {
  @Override
  public <T extends Actor> void performAs(T t) {
    t.attemptsTo(Open.browserOn(new Home()), LogsIn.withProvidedCredentials());
    t.should(seeThat(new TheyAreLoggedIn()));
  }
}
