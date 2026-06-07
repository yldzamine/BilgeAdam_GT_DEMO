package com.gt.demo.web.hooks;

import com.gt.demo.web.base.WebDriverContext;
import com.gt.demo.web.base.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Cucumber hook sınıfı.
 *
 * <p>@web tag'ine sahip scenario'lar için browser yaşam döngüsünü yönetir. Böylece step
 * definition veya page class içinde browser başlatma/kapatma kodu yazılmaz.
 */
public class WebHooks {

  // Her web runner senaryosu başlamadan önce yeni bir WebDriver oluşturulur ve context'e kaydedilir.
  @Before
  public void startWeb() {
    WebDriverContext.set(WebDriverFactory.create());
  }

  // Her web runner senaryosu bittikten sonra browser kapatılır ve thread-local driver temizlenir.
  @After
  public void stopWeb() {
    WebDriverContext.clear();
  }
}
