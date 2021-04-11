package com.headissue.example.screenplay.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.SendKeys;
import org.openqa.selenium.Keys;

public class FillTheFormIncorrectly implements Performable {
  @Override
  public <T extends Actor> void performAs(T t) {
    t.attemptsTo(SendKeys.of(Keys.ENTER).into("#address"));
  }
}
