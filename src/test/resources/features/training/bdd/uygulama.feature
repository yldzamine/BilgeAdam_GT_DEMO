Feature: Uygulama testleri
    Uygulama testleri için örnek senaryolar.
    @smoke @happy
  Scenario: Geçersiz giriş yapıldığında net bir hata mesajı gösterilmeli
    Given Uygulama ana sayfasını açtım
    When Geçersiz kullanıcı bilgileri ile giriş yapıyorum
    Then Net bir giriş hatası mesajı görmeliyim