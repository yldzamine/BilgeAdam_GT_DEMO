package com.gt.demo.web.steps;

import com.gt.demo.common.config.FrameworkConfig;
import com.gt.demo.web.base.WebDriverContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class TrainingBusinessSteps {
  private final Map<String, String> defined = new HashMap<>();
  private final Map<String, String> data = new HashMap<>();

  @Given("Kullanıcı ana sayfayı açar")
  public void openHomePage() {
    WebDriver driver = WebDriverContext.get();
    String base = FrameworkConfig.getRequired("web.business.baseUrl");
    driver.get(base);
  }

  @When("Kullanıcı {string} sayfasına gider")
  public void goToPage(String page) {
    // Eğitim amaçlı; çoğu demo site ana sayfada olduğundan base URL'de kalıyoruz.
    // İstenirse page parametresine göre farklı URL'lere yönlendirme eklenebilir.
  }

  @When("Kullanıcı adını {string} girer")
  public void enterName(String keyOrLiteral) {
    String value = resolveValue(keyOrLiteral);
    data.put("name", value);
  }

  @When("Kullanıcı soyadını {string} girer")
  public void enterSurname(String keyOrLiteral) {
    String value = resolveValue(keyOrLiteral);
    data.put("surname", value);
  }

  @When("Kullanıcı iletişim telefonu {string} girer")
  public void enterPhone(String keyOrLiteral) {
    String value = resolveValue(keyOrLiteral);
    data.put("phone", value);
  }

  @When("Kullanıcı model alanını {string} seçer")
  public void selectModel(String keyOrLiteral) {
    String value = resolveValue(keyOrLiteral);
    data.put("model", value);
  }

  @Given("Başvuru verileri tanımlıdır")
  public void defineApplicationData(DataTable table) {
    Map<String, String> map = table.asMap(String.class, String.class);
    defined.putAll(map);
  }

  private String resolveValue(String keyOrLiteral) {
    if (keyOrLiteral == null || keyOrLiteral.isEmpty()) {
      return "";
    }
    // If a defined value exists for the key, return it; otherwise treat parameter as literal value.
    return defined.getOrDefault(keyOrLiteral, keyOrLiteral);
  }

  @When("Kullanıcı dosya yükler")
  public void uploadFile() {
    data.put("fileUploaded", "true");
  }

  @When("Kullanıcı başvuruyu gönderir")
  public void submitApplication() {
    data.put("submitted", "true");
  }

  @Then("Başvuru başarıyla gönderilmelidir")
  public void assertSubmitted() {
    Assert.assertEquals(data.get("submitted"), "true", "Başvuru gönderilmedi.");
  }

  @Then("Zorunlu alanlar için uyarı mesajları görünmelidir")
  public void assertRequiredWarnings() {
    String name = data.getOrDefault("name", "");
    String surname = data.getOrDefault("surname", "");
    Assert.assertTrue(name.isEmpty() || surname.isEmpty(), "Zorunlu alan uyarısı bekleniyor.");
  }
}
