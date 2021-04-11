package com.headissue.example.screenplay.actions;

import com.headissue.example.screenplay.page.Home;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;

public class LogsOut implements Performable {
  @Override
  public <T extends Actor> void performAs(T t) {
    t.attemptsTo(Click.on(Home.LOGOUT));
  }
}
