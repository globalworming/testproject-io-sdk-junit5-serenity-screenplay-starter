package com.headissue.example.screenplay.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

import static org.testng.Assert.fail;

public class ChecksCheckerResult implements Performable {
  @Override
  public <T extends Actor> void performAs(T t) {
    fail("It should be 4 though?!");
  }
}
