package com.headissue.example.screenplay.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Comment implements Performable {

  private final String s;

  public Comment(String s) {
    this.s = s;
  }

  public static Comment that(String s) {
    return instrumented(Comment.class, s);
  }

  @Override
  @Step("{0} comments '#s'")
  public <T extends Actor> void performAs(T t) {}
}
