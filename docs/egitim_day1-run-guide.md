# Eğitim Day 1 Feature Çalıştırma Rehberi

Bu doküman, `egitim_day1.feature` dosyasını VS Code terminalinden çalıştırma ve yapılan değişiklikleri repoya kaydetme adımlarını içerir.

## 1. Proje kök dizinine git

VS Code içinde yeni bir terminal açın (`Terminal > New Terminal`), sonra proje kök dizinine geçin:

```bash
cd /workspaces/BilgeAdam_GT_DEMO
```

## 2. `egitim_day1.feature` dosyasını çalıştırma

Aşağıdaki komutla sadece web feature dosyasını çalıştırın:

```bash
mvn -Dtest=WebCucumberTest -Dcucumber.features="classpath:features/web/business/egitim_day1.feature" -Dcucumber.filter.tags="not @wip" test
```

Bu komut:
- `WebCucumberTest` runner sınıfını çalıştırır
- sadece `egitim_day1.feature` dosyasını hedefler
- `@wip` etiketi varsa bunları atlar

## 3. Çalışma sonuçlarını kontrol etme

Test tamamlandıktan sonra aşağıdaki dosyalara bakabilirsiniz:

- Web raporu: `target/cucumber/web/cucumber-report.html`
- Web ekran görüntüsü: `target/cucumber-report.png`

Eğer test başarılı ise terminalde `Tests run: 2, Failures: 0, Errors: 0, Skipped: 0` gibi bir çıktı görmelisiniz.

## 4. Döküman ve değişiklikleri repoya kaydetme

Değişiklikleri Git ile kaydetmek için:

```bash
git status
git add .
git commit -m "Add egitim_day1 run guide and save latest feature / hook updates"
```

Eğer bu commit’i GitHub’a göndermek isterseniz:

```bash
git push origin main
```

## 5. Codespace yeniden açıldığında güncel kalma

Yeni bir Codespace açıldığında repo güncellenmiş olmak için:

```bash
git pull origin main
```

Bu komutlar, yapılan tüm dosya değişikliklerinin GitHub üzerindeki `main` branch ile senkron kalmasını sağlar.
