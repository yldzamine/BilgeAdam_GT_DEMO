# İş Analistleri İçin Senaryo Yazma Egzersizleri

> **Hedef Kitle:** Kodlama bilmeyen veya çok az bilen iş analistleri / test analistleri  
> **Yöntem:** Yapay zeka (ChatGPT, GitHub Copilot, vb.) destekli senaryo yazma  
> **Ön Koşul:** VS Code + Codespaces ortamı hazır, repo clone edilmiş

---

## BÖLÜM 0: Temel Kavramlar (Başlamadan Önce Okuyun)

### BDD Senaryo Yapısı
```gherkin
Feature: Özellik başlığı
  Kısa açıklama

  Scenario: Senaryonun adı (ne test ediliyor?)
    Given Ön koşul (başlangıç durumu)
    When Aksiyon (kullanıcının yaptığı şey)
    Then Beklenen sonuç (doğrulama)
```

### Dosya Kuralları
| Alan | Açıklama |
|------|----------|
| **Feature dosyası** | `.feature` uzantılı, `src/test/resources/features/` altında |
| **Step Definition** | Java dosyası, `src/test/java/.../steps/` altında. Feature'daki her cümleyi Java koduna bağlar |
| **Page/Screen Object** | Web sayfası veya mobil ekranın Java temsili |
| **Runner** | Testleri başlatan Java sınıfı |

### Yapay Zeka Kullanım Kuralları
1. **Senaryo yazmada AI kullanın** — Given/When/Then cümleleri oluşturmak için
2. **Mevcut step'leri AI'a gösterin** — AI'ın repo'daki mevcut step definition'lara uygun cümleler üretmesini sağlayın
3. **AI'dan debug isteyin** — Hata aldığınızda hatayı yapıştırıp "Neden?" diye sorun
4. **AI'a kopyala-yapıştır yapmayın** — Üretilen senaryoyu anlayıp onaylayın

---

## BÖLÜM 1: WEB SENARYOLARI (3 Egzersiz)

### Çalışma Ortamı
- **Dosya konumu:** `src/test/resources/features/web/`
- **Çalıştırma:** Terminal'de `mvn surefire:test` veya VS Code'da `WebCucumberTest.java` → Run
- **Mevcut step'ler:** Aşağıdaki step catalog'u AI'a vererek yeni senaryolar ürettirin

### Mevcut Web Step Catalog (AI'a Bu Listeyi Verin)

```
# ParaBank (Business)
Given I open ParaBank home page
When I login with invalid business credentials
Then I should see a business login error
Given I open ParaBank registration page
When I complete business registration with unique user
Then I should see registration form filled with my generated username

# The Internet (Technical)
Given I open The Internet technical lab home page
Then I should see technical lab heading "{text}"
Given I open The Internet dropdown page
When I choose dropdown option "{option}"
Then I should see selected dropdown option "{option}"
Given I open The Internet iframe page
When I type "{text}" into the iframe editor
Then iframe editor should contain "{text}"
Given I open The Internet dynamic loading page
When I trigger dynamic loading
Then I should see dynamic loading result "{text}"
Given I open The Internet shadow DOM page
Then I should read slotted shadow text containing "{text}"

# SelectorsHub (Advanced DOM)
Given I open SelectorsHub advanced DOM page
When I enter "{text}" inside open shadow root input
And I enter "{text}" inside nested shadow root input
Then open shadow root input should keep value "{text}"
And I switch into an iframe and back to default content
```

---

### 🔹 Egzersiz W1: ParaBank Başarılı Login Senaryosu

**Görev:** ParaBank'ta geçerli kullanıcıyla login yapıldığında anasayfaya ulaşıldığını doğrulayan bir senaryo yazın.

**Adım Adım:**

1. **Dosya oluşturun:**
   ```
   src/test/resources/features/web/business/parabank_login_success.feature
   ```

2. **AI'a şu prompt'u verin:**
   > "ParaBank web sitesinde başarılı login senaryosu yaz. Mevcut step'lerden `Given I open ParaBank home page` kullan. Login başarılı olursa anasayfa gösterilmeli. BDD Gherkin formatında, @web ve @smoke tag'leri ile yaz."

3. **Beklenen çıktı (AI'ın üreteceği):**
   ```gherkin
   @web @smoke
   Feature: ParaBank başarılı login

     Scenario: Geçerli kullanıcı ile login yapılabilmeli
       Given I open ParaBank home page
       When I login with valid business credentials
       Then I should be on the ParaBank accounts page
   ```

4. **Dikkat:** `When I login with valid business credentials` ve `Then I should be on the ParaBank accounts page` — bu step'ler henüz tanımlı DEĞİL. Bu normal! Eğitmen size step definition yazmanızı gösterecek veya mevcut step'lerle sınırlı kalmanızı isteyecek.

5. **Alternatif (Mevcut step'lerle çalışan versiyon):**
   ```gherkin
   @web @smoke
   Feature: ParaBank login doğrulama

     Scenario: Yanlış şifre ile login yapılırsa hata mesajı görülmeli
       Given I open ParaBank home page
       When I login with invalid business credentials
       Then I should see a business login error
   ```

6. **Çalıştırma:**
   ```bash
   mvn surefire:test
   ```

7. **Sonuçları kontrol edin:** `target/cucumber/web/cucumber-report.html` dosyasını açın.

---

### 🔹 Egzersiz W2: Dropdown Seçim Doğrulama (Farklı Değerle)

**Görev:** The Internet sitesindeki dropdown'dan "Option 1" seçildiğinde doğru değerin gösterildiğini test edin.

**Adım Adım:**

1. **Dosya:** `src/test/resources/features/web/technical/the_internet_smoke.feature` dosyasının ALTINA yeni senaryo ekleyin.

2. **AI'a şu prompt'u verin:**
   > "Bu mevcut step'leri kullanarak dropdown'dan 'Option 1' seçilmesini test eden bir senaryo yaz:
   > - Given I open The Internet dropdown page
   > - When I choose dropdown option '{option}'
   > - Then I should see selected dropdown option '{option}'
   > Tag olarak @web @smoke @technical @happy kullan."

3. **Beklenen çıktı:**
   ```gherkin
     Scenario: Dropdown Option 1 seçimi
       Given I open The Internet dropdown page
       When I choose dropdown option "Option 1"
       Then I should see selected dropdown option "Option 1"
   ```

4. **Dosyayı kaydedin ve çalıştırın:**
   ```bash
   mvn surefire:test
   ```

5. **Eğer hata alırsanız — AI ile debug:**
   > Hatayı kopyalayın → AI'a yapıştırın → "Bu hata ne anlama geliyor ve nasıl düzeltebilirim?" diye sorun.

---

### 🔹 Egzersiz W3: Scenario Outline ile Çoklu Dropdown Testi

**Görev:** Hem "Option 1" hem "Option 2" yi tek senaryo ile test eden bir Scenario Outline yazın.

**Adım Adım:**

1. **AI'a şu prompt'u verin:**
   > "Aşağıdaki senaryoyu Scenario Outline'a dönüştür. İki veri satırı olsun: Option 1 ve Option 2.
   > ```
   > Scenario: Dropdown selection
   >   Given I open The Internet dropdown page
   >   When I choose dropdown option "Option 2"
   >   Then I should see selected dropdown option "Option 2"
   > ```"

2. **Beklenen çıktı:**
   ```gherkin
   @web @smoke @technical
   Feature: Dropdown çoklu test

     Scenario Outline: Dropdown'dan <option> seçilmeli
       Given I open The Internet dropdown page
       When I choose dropdown option "<option>"
       Then I should see selected dropdown option "<option>"

       Examples:
         | option   |
         | Option 1 |
         | Option 2 |
   ```

3. **Neden bu daha iyi?** Aynı test mantığını tekrar yazmadan birden fazla veri ile test edebilirsiniz. Scenario Outline, "veri güdümlü test" demektir.

4. **Dosya:** Yeni bir dosya oluşturun:
   ```
   src/test/resources/features/web/technical/dropdown_outline_exercise.feature
   ```

5. **Çalıştırma ve doğrulama:**
   ```bash
   mvn surefire:test
   ```
   Raporda 2 senaryo (Option 1 + Option 2) ayrı ayrı listelenmelidir.

---

## BÖLÜM 2: API SENARYOLARI (3 Egzersiz)

### Çalışma Ortamı
- **Dosya konumu:** `src/test/resources/features/api/`
- **Çalıştırma:** `mvn failsafe:integration-test@api-tests failsafe:verify@api-tests`
- **Format:** Karate DSL (Cucumber'a benzer ama özel API sözdizimi)
- **Base URL:** `https://restful-booker.herokuapp.com`

### Karate Temel Sözdizimi (AI'a Bu Bilgiyi Verin)

```
Feature: API adı

  Background:
    * url apiBaseUrl                           ← Her senaryoda tekrarlanacak base URL
    * configure headers = { Accept: 'application/json' }

  Scenario: Test adı
    Given path 'endpoint'                     ← URL path (örn: 'booking')
    And request { ... }                        ← Gönderilecek body (POST/PUT için)
    When method get/post/put/delete            ← HTTP metod
    Then status 200                            ← Beklenen HTTP durum kodu
    And match response.field == 'value'        ← Response doğrulama
```

---

### 🔹 Egzersiz A1: Booking Listeleme (GET /booking)

**Görev:** Tüm booking'leri listeleyen bir GET isteği yazın.

**Adım Adım:**

1. **AI'a şu prompt'u verin:**
   > "Restful-Booker API'si için Karate formatında bir senaryo yaz. GET /booking endpoint'ine istek at. Status 200 dönmeli ve response bir array olmalı. Background'da `* url apiBaseUrl` kullan."

2. **Beklenen çıktı:**
   ```
   Feature: Booking listeleme

     Background:
       * url apiBaseUrl

     Scenario: Tüm booking'leri listeleme
       Given path 'booking'
       When method get
       Then status 200
       And match response == '#[]'
   ```

3. **Dosya:** Yeni dosya oluşturun:
   ```
   src/test/resources/features/api/booking_list_exercise.feature
   ```

4. **Açıklama:**
   - `#[]` → response'un bir JSON array olduğunu doğrular (Karate özel syntax)
   - `apiBaseUrl` → `karate-config.js` dosyasından otomatik gelir

5. **Çalıştırma:**
   ```bash
   mvn failsafe:integration-test@api-tests failsafe:verify@api-tests
   ```

6. **Rapor:** `target/karate-reports/karate-summary.html` dosyasını açın.

---

### 🔹 Egzersiz A2: Tek Booking Detayı (GET /booking/{id})

**Görev:** Önce bir booking oluşturup, sonra ID ile sorgulayan bir senaryo yazın.

**Adım Adım:**

1. **AI'a şu prompt'u verin:**
   > "Karate API test yaz. Önce POST /booking ile yeni bir booking oluştur (firstname: 'Ayse', lastname: 'Demir', totalprice: 200, depositpaid: true, checkin: '2026-03-01', checkout: '2026-03-05'). Sonra response'taki bookingid ile GET /booking/{id} yap ve firstname'in 'Ayse' olduğunu doğrula."

2. **Beklenen çıktı:**
   ```
   Feature: Booking oluştur ve sorgula

     Background:
       * url apiBaseUrl
       * configure headers = { Accept: 'application/json', 'Content-Type': 'application/json' }

     Scenario: Booking oluşturup detay sorgusu
       Given path 'booking'
       And request
       """
       {
         "firstname": "Ayse",
         "lastname": "Demir",
         "totalprice": 200,
         "depositpaid": true,
         "bookingdates": {
           "checkin": "2026-03-01",
           "checkout": "2026-03-05"
         }
       }
       """
       When method post
       Then status 200
       * def bookingId = response.bookingid

       Given path 'booking', bookingId
       When method get
       Then status 200
       And match response.firstname == 'Ayse'
       And match response.lastname == 'Demir'
   ```

3. **Dosya:** `src/test/resources/features/api/booking_create_read_exercise.feature`

4. **Önemli Karate kavramları:**
   - `* def bookingId = response.bookingid` → Response'tan değer alıp değişkene atama
   - `path 'booking', bookingId` → URL path birleştirme: `/booking/123`
   - `And match response.firstname == 'Ayse'` → Response body doğrulama

---

### 🔹 Egzersiz A3: Auth Token ile Booking Silme (Reusable Feature Call)

**Görev:** Mevcut `create_token.feature` dosyasını kullanarak token alıp, bir booking silme senaryosu yazın.

**Adım Adım:**

1. **Önce mevcut reusable feature'ı inceleyin:**
   ```
   src/test/resources/features/api/reusable/create_token.feature
   ```
   Bu dosya bir auth token döndürür.

2. **AI'a şu prompt'u verin:**
   > "Karate'de reusable feature call kullanarak token almak için şu kodu kullan:
   > `* def tokenResult = call read('classpath:features/api/reusable/create_token.feature')`
   > `* def token = tokenResult.token`
   > 
   > Sonra bir booking oluştur (POST /booking), ve ardından token'ı Cookie header'ında kullanarak sil (DELETE /booking/{id}). Status 201 beklenmeli."

3. **Beklenen çıktı:**
   ```
   Feature: Token ile booking silme

     Background:
       * url apiBaseUrl
       * configure headers = { Accept: 'application/json', 'Content-Type': 'application/json' }

     Scenario: Booking oluştur ve token ile sil
       * def tokenResult = call read('classpath:features/api/reusable/create_token.feature')
       * def token = tokenResult.token

       Given path 'booking'
       And request
       """
       {
         "firstname": "Test",
         "lastname": "Silme",
         "totalprice": 50,
         "depositpaid": false,
         "bookingdates": {
           "checkin": "2026-06-01",
           "checkout": "2026-06-02"
         }
       }
       """
       When method post
       Then status 200
       * def bookingId = response.bookingid

       Given path 'booking', bookingId
       And header Cookie = 'token=' + token
       When method delete
       Then status 201
   ```

4. **Dosya:** `src/test/resources/features/api/booking_delete_exercise.feature`

5. **Neden önemli?** Bu egzersiz size "reusable flow" (tekrar kullanılabilir akış) kavramını öğretir. Gerçek projelerde auth token alma gibi ortak adımlar böyle yazılır.

---

## BÖLÜM 3: MOBİL SENARYOLAR (3 Egzersiz)

### Çalışma Ortamı
- **Dosya konumu:** `src/test/resources/features/mobile/`
- **Çalıştırma:** `mvn -Pmobile surefire:test@mobile-tests` (Appium server + emülatör gerekli)
- **Not:** Mobil testler çalıştırmak için fiziksel cihaz veya emülatör gerekir. Codespaces'da çalışmaz. Bu egzersizleri **senaryo yazma pratiği** olarak yapın; çalıştırma eğitmen tarafından demo yapılacaktır.

### Mevcut Mobil Step Catalog (AI'a Bu Listeyi Verin)

```
# TheApp Native
Given the TheApp is launched
When I login successfully on TheApp
Then I should see successful native login state
Given I am on TheApp home screen
When I open list demo on TheApp
Then I should see list items in TheApp

# ApiDemos Hybrid
Given I open the ApiDemos hybrid screen
When I switch to WEBVIEW context
Then I can interact with a web element
```

---

### 🔹 Egzersiz M1: Geçersiz Login Senaryosu (TheApp)

**Görev:** TheApp uygulamasında yanlış şifre ile login yapıldığında hata mesajı gösterildiğini test eden bir senaryo yazın.

**Adım Adım:**

1. **AI'a şu prompt'u verin:**
   > "Mobil uygulama (TheApp) için BDD senaryosu yaz. Gherkin formatında olmalı. Uygulama açıldıktan sonra geçersiz kullanıcı bilgileri ile login yapılıyor ve bir hata mesajı görülmeli. Tag'ler: @mobile @android @theapp. Mevcut step'ten esinlen: 'Given the TheApp is launched'"

2. **Beklenen çıktı:**
   ```gherkin
   @mobile @android @theapp
   Feature: TheApp geçersiz login

     Scenario: Yanlış şifre ile login hata mesajı göstermeli
       Given the TheApp is launched
       When I login with invalid credentials on TheApp
       Then I should see a login error message on TheApp
   ```

3. **Dosya:** `src/test/resources/features/mobile/native/theapp_invalid_login_exercise.feature`

4. **Dikkat:** `When I login with invalid credentials on TheApp` ve `Then I should see a login error message on TheApp` step'leri henüz tanımlı değil. Bu senaryo bir **yazma egzersizi**dir. Step definition'lar eğitmen rehberliğinde eklenecektir.

5. **AI ile step definition taslağı isteme (ileri seviye):**
   > "Bu senaryo için Java step definition sınıfı taslağı oluştur. LoginScreen sınıfını kullan. Package: com.gt.demo.mobile.steps"

---

### 🔹 Egzersiz M2: Liste Filtreleme Senaryosu (TheApp)

**Görev:** TheApp'teki liste ekranında belirli bir elemanın görünür olduğunu doğrulayan senaryo yazın.

**Adım Adım:**

1. **AI'a şu prompt'u verin:**
   > "Mobil test senaryosu yaz. TheApp uygulamasının ana ekranında 'List Demo' açılıyor. Açılan listede 'Audi' marka bir eleman görünmeli. BDD Gherkin formatında. Tag: @mobile @android @theapp. Mevcut step'ler:
   > - Given I am on TheApp home screen
   > - When I open list demo on TheApp"

2. **Beklenen çıktı:**
   ```gherkin
   @mobile @android @theapp
   Feature: TheApp liste doğrulama

     Scenario: Liste içinde belirli bir eleman görülmeli
       Given I am on TheApp home screen
       When I open list demo on TheApp
       Then I should see "Audi" in the list items
   ```

3. **Dosya:** `src/test/resources/features/mobile/native/theapp_list_filter_exercise.feature`

4. **Tartışma sorusu (eğitmen yönlendirmeli):**
   - "Audi" hard-coded bir değer. Gerçek projede bunu nasıl parametrik yapardınız?
   - Cevap: Scenario Outline + Examples tablosu kullanarak.

---

### 🔹 Egzersiz M3: Hybrid Context Switch Senaryosu (ApiDemos)

**Görev:** ApiDemos uygulamasında NATIVE → WEBVIEW geçişi yapıp web elementinde bir değer doğrulayan senaryo yazın.

**Adım Adım:**

1. **AI'a şu prompt'u verin:**
   > "Android ApiDemos uygulaması için hybrid/context switching senaryosu yaz. Adımlar: (1) ApiDemos hybrid ekranı aç, (2) WEBVIEW context'e geç, (3) WebView içindeki bir input alanına 'test-text' yaz, (4) Değerin yazıldığını doğrula. BDD Gherkin formatı. Tag: @mobile @android @apidemos"

2. **Beklenen çıktı:**
   ```gherkin
   @mobile @android @apidemos
   Feature: ApiDemos hybrid text input

     Scenario: WebView context'te input alanına yazı yazılabilmeli
       Given I open the ApiDemos hybrid screen
       When I switch to WEBVIEW context
       And I type "test-text" into the webview input field
       Then the webview input should contain "test-text"
   ```

3. **Dosya:** `src/test/resources/features/mobile/hybrid/apidemos_input_exercise.feature`

4. **Kavramlar:**
   - **NATIVE_APP context:** Mobil uygulamanın native elementleri (butonlar, listeler)
   - **WEBVIEW context:** Uygulama içindeki web görünümü (HTML/CSS elementleri)
   - **Context switch:** `driver.context("WEBVIEW_...")` ile geçiş yapılır

---

## BÖLÜM 4: TEST ÇALIŞTIRMA VE SONUÇ YORUMLAMA

### Çalıştırma Komutları Özeti

| Modül | Komut | Rapor Konumu |
|-------|-------|--------------|
| Web | `mvn surefire:test` | `target/cucumber/web/cucumber-report.html` |
| API | `mvn failsafe:integration-test@api-tests failsafe:verify@api-tests` | `target/karate-reports/karate-summary.html` |
| Mobil | `mvn -Pmobile surefire:test@mobile-tests` | `target/cucumber/mobile/cucumber-report.html` |
| Hepsi (Web+API) | `mvn test` | Yukarıdaki tüm rapor konumları |

### Hata Aldığınızda Ne Yapmalı?

1. **Hata mesajını kopyalayın** (terminal'de kırmızı/ERROR satırları)
2. **AI'a yapıştırıp sorun:**
   > "Bu hata mesajını analiz et ve çözüm öner: [hata metni]"
3. **Yaygın hatalar ve çözümleri:**

| Hata Tipi | Olası Sebep | Çözüm |
|-----------|-------------|-------|
| `UndefinedStepException` | Step definition yazılmamış | Step catalog'da mevcut bir step kullanın veya yeni step definition isteyin |
| `NoSuchElementException` | Element bulunamadı | Locator yanlış veya sayfa yüklenmedi — wait ekleyin |
| `TimeoutException` | Sayfa/element geç yüklendi | Internet bağlantısını kontrol edin, retry edin |
| `CompilationError` | Java syntax hatası | AI'a Java dosyasını gösterip düzeltme isteyin |

### Git Push Akışı

Senaryolarınızı yazdıktan sonra:

```bash
# 1. Değişiklikleri görün
git status

# 2. Yeni/değişen feature dosyalarını stage'e alın
git add src/test/resources/features/web/technical/dropdown_outline_exercise.feature

# 3. Commit yapın (açıklayıcı mesaj ile)
git commit -m "feat: dropdown outline exercise eklendi"

# 4. Push edin
git push origin main
```

---

## BÖLÜM 5: AI PROMPT ŞABLONLARI (Kopyala-Yapıştır)

### Yeni Senaryo Yazdırma
```
Bu framework'te [Web/API/Mobil] için bir BDD test senaryosu yaz.
Aşağıdaki mevcut step'leri kullan: [step listesini yapıştır]
Test etmek istediğim akış: [ne test edileceğini açıkla]
Tag'ler: [tag listesi]
Format: Gherkin (Given/When/Then)
```

### Hata Debug Ettirme
```
Aşağıdaki test hatasını analiz et:
[hata mesajını yapıştır]

Sorum:
1. Bu hata ne anlama geliyor?
2. Muhtemel sebebi nedir?
3. Nasıl düzeltebilirim?
```

### Step Definition Taslağı İsteme
```
Aşağıdaki Gherkin senaryosu için Java step definition taslağı oluştur:
[senaryo'yu yapıştır]

Kullanılacak package: com.gt.demo.[web/mobile].steps
Mevcut page/screen sınıfları: [sınıf isimlerini yaz]
```

### Scenario Outline Dönüştürme
```
Aşağıdaki senaryoyu Scenario Outline'a dönüştür.
Parametre olarak değişen değerleri <placeholder> yap ve Examples tablosu ekle:
[senaryoyu yapıştır]
```

---

## Ekler

### A. Proje Dizin Haritası (Önemli Dosyalar)
```
src/test/resources/
├── features/
│   ├── web/
│   │   ├── business/     ← ParaBank senaryoları
│   │   ├── technical/    ← The Internet lab senaryoları
│   │   └── dom/          ← SelectorsHub senaryoları
│   ├── api/
│   │   ├── restful_booker_smoke.feature   ← Ana API senaryoları
│   │   └── reusable/
│   │       └── create_token.feature       ← Token alma (reusable)
│   ├── mobile/
│   │   ├── native/       ← TheApp senaryoları
│   │   └── hybrid/       ← ApiDemos senaryoları
│   └── training/
│       ├── bdd/          ← İyi/kötü karşılaştırma örnekleri
│       └── anti-patterns/ ← YAPMA örnekleri
├── config/
│   └── framework.properties  ← URL ve driver ayarları
└── karate-config.js          ← API base URL ve auth bilgileri
```

### B. Faydalı Linkler
- **ParaBank:** https://parabank.parasoft.com/parabank
- **The Internet:** https://the-internet.herokuapp.com
- **SelectorsHub:** https://selectorshub.com/xpath-practice-page/
- **Restful-Booker API:** https://restful-booker.herokuapp.com
- **Karate DSL Docs:** https://karatelabs.github.io/karate/
- **Cucumber Docs:** https://cucumber.io/docs/gherkin/reference/
