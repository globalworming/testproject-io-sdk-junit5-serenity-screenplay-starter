package com.headissue.example.screenplay.page;

import net.serenitybdd.screenplay.targets.Target;

public class LoginForm {
  public static final Target NAME = Target.the("user name input").locatedBy("#name");
  public static final Target PASSWORD = Target.the("password input").locatedBy("#password");
}
