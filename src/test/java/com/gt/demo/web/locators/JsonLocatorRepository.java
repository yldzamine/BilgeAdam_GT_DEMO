package com.gt.demo.web.locators;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.Locale;

/**
 * JSON tabanlı locator repository.
 * Beklenen kaynak: `locators/<scope>/<file>.json` formatında, örnek:
 * { "home": { "username": {"by":"id","value":"customer.username"}, ... } }
 */
public final class JsonLocatorRepository {
  private final JSONObject root;

  private JsonLocatorRepository(JSONObject root) {
    this.root = root;
  }

  public static JsonLocatorRepository fromResource(String resource) {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    if (is == null) {
      throw new IllegalArgumentException("Locator resource not found: " + resource);
    }
    JSONTokener tok = new JSONTokener(is);
    JSONObject obj = new JSONObject(tok);
    return new JsonLocatorRepository(obj);
  }

  public By by(String... path) {
    JSONObject node = root;
    for (int i = 0; i < path.length - 1; i++) {
      String p = path[i];
      if (!node.has(p)) {
        return By.id(String.join("-", path));
      }
      node = node.getJSONObject(p);
    }
    String last = path[path.length - 1];
    if (!node.has(last)) {
      return By.id(String.join("-", path));
    }
    JSONObject entry = node.getJSONObject(last);
    String by = entry.optString("by", "id").toLowerCase(Locale.ROOT);
    String val = entry.optString("value", "");
    return toBy(by, val);
  }

  private By toBy(String by, String value) {
    return switch (by) {
      case "id" -> By.id(value);
      case "css" , "cssselector" -> By.cssSelector(value);
      case "xpath" -> By.xpath(value);
      case "linktext" -> By.linkText(value);
      case "partiallinktext" -> By.partialLinkText(value);
      case "name" -> By.name(value);
      case "classname", "class" -> By.className(value);
      case "tagname", "tag" -> By.tagName(value);
      default -> By.id(value);
    };
  }
}
