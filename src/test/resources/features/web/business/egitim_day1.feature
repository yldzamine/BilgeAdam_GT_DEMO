Feature: Eğitim Day 1 - Web Test Senaryoları    
  Background:
    Given Kullanıcı ana sayfayı açar
    And Başvuru verileri tanımlıdır
      | ad      | Ahmet         |
      | soyad   | Yılmaz        |
      | telefon | 05551234567   |
      | model   | Excavator X1  |

 
  Scenario: Başarılı başvuru formu doldurma
    When Kullanıcı "Başvuru" sayfasına gider
    And Kullanıcı adını "ad" girer
    And Kullanıcı soyadını "soyad" girer
    And Kullanıcı iletişim telefonu "telefon" girer
    And Kullanıcı model alanını "model" seçer
    And Kullanıcı dosya yükler
    And Kullanıcı başvuruyu gönderir
    Then Başvuru başarıyla gönderilmelidir

 
  Scenario: Zorunlu alanlar boş bırakıldığında hata mesajı görünmeli
    When Kullanıcı "Başvuru" sayfasına gider
    And Kullanıcı adını "" girer
    And Kullanıcı soyadını "" girer
    And Kullanıcı başvuruyu gönderir
    Then Zorunlu alanlar için uyarı mesajları görünmelidir

