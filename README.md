# EnUcuzFirebaseData
Proje KOTLIN ile geliştirilmiştir.

Geliştirmekte olduğum EnUcuz isimli uygulamada kullandığım verilerimi Firebase'e kayıt etmek ve yine projede kullandığım verilere ait fiyat güncellemeleri yapmak üzere kullanmak için geliştirdiğim uygulamadır.

+ Proje Yeni Ürün Kaydı ve Ürün Fiyatı güncellemek için kullandığım 2 Fragmentten oluşmaktadır.

+ Yeni Ürün Kaydı ekranında kamera yardımıyla ürün barkodu okutulup veya ilgili edittexte barkod elle girilerek ve yine ilgili edittext e ürün ismi tanımlaması yapılarak ürün bilgilerinin eğer daha önce kayıt edilmemişse FirebaseDatabase'e kaydı yapılır, daha önce kayıt edilmişse Toast mesaj ile 'Ürün Kayıtlı' uyarısı yapılmaktadır.
+ Ürün Fiyatı Güncelleme ekranında ise, yine yeni kayıtta olduğu gibi barkod bilgisi verilerek, ilgili market seçimi yapıldıktan sonra fiyat bilgisi girilerek FirebaseDatabaseden bilgi güncellemesi yapılmaktadır.

Dependencies
  + Firebase - com.google.firebase:firebase-core:16.0.8
  + FirebaseDatabase - com.google.firebase:firebase-database:16.1.0
  + Zxing Barcode Reader - com.journeyapps:zxing-android-embedded:3.6.0
  + ViewPager - androidx.viewpager:viewpager:1.0.0
  + TabLayout - com.google.android.material:material:1.0.0
