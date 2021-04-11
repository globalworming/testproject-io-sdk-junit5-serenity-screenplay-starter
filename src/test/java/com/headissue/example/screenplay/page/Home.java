package com.headissue.example.screenplay.page;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://example.testproject.io/web/")
public class Home extends PageObject {
  public static final Target LOGOUT = Target.the("logout button").locatedBy("#logout");
  public static final Target LOGIN = Target.the("login button").locatedBy("#login");
}
