package com.gt.demo.web.pages.business;

import com.gt.demo.web.base.BaseWebPage;
import com.gt.demo.web.locators.JsonLocatorRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParaBankHomePage extends BaseWebPage {
  private static final JsonLocatorRepository LOCATORS =
      JsonLocatorRepository.fromResource("locators/web/parabank-locators.json");

  private static final By USERNAME = LOCATORS.by("home", "username");
  private static final By PASSWORD = LOCATORS.by("home", "password");
  private static final By LOGIN_BUTTON = LOCATORS.by("home", "loginButton");
  private static final By REGISTER_LINK = LOCATORS.by("home", "registerLink");
  private static final By LOGIN_ERROR = LOCATORS.by("home", "loginError");
  private static final By LOGOUT_LINK = LOCATORS.by("home", "logoutLink");

  public ParaBankHomePage(WebDriver driver) {
    super(driver);
  }

  public void open(String baseUrl) {
    driver.get(baseUrl + "/index.htm");
  }

  public void login(String username, String password) {
    type(USERNAME, username);
    type(PASSWORD, password);
    click(LOGIN_BUTTON);
  }

  public void goToRegister() {
    click(REGISTER_LINK);
  }

  public String loginError() {
    return text(LOGIN_ERROR);
  }

  public String dashboardTitle() {
    return driver.getTitle();
  }

  public boolean isLoggedIn() {
    return !driver.findElements(LOGOUT_LINK).isEmpty();
  }
}
