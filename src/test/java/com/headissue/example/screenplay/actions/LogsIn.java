package com.headissue.example.screenplay.actions;

import com.headissue.example.screenplay.actor.Memories;
import com.headissue.example.screenplay.page.LoginForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;

public class LogsIn implements Performable {
  public static Performable withProvidedCredentials() {
    return new LogsIn();
  }

  @Override
  public <T extends Actor> void performAs(T t) {
    String name = t.recall(Memories.NAME);
    String password = t.recall(Memories.PASSWORD);

    t.attemptsTo(Enter.theValue(name).into(LoginForm.NAME));
    t.attemptsTo(Enter.theValue(password).into(LoginForm.PASSWORD).thenHit(Keys.ENTER));
  }
}
