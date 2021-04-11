package com.headissue.example.screenplay.page;

import net.serenitybdd.screenplay.targets.Target;

public class Form {

  public static final Target FIELD_ERROR =
      Target.the("form field errors").locatedBy("body .invalid-feedback");
}
