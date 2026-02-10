# Modul-1-Coding-Standards

## Exercise 1
Tutorial 1 ini mengajarkan saya banyak hal, terutama dalam clean code principles dan secure coding practices. Salah satu masalah yang saya alami adalah commit setelah semua selesai lalu push. Kebiasaan buruk tersebut masih sering muncul saat pengerjaan Tutorial 1 ini oleh saya. Dengan tutorial ini, saya diingatkan untuk menerapkan kebiasaan coding yang lebih baik dan aman; commit per sub-fitur/bagian fitur, bukan setelah fiturnya selesai.

## Exercise 2
1. Setelah membuat unit test, saya mempelajari banyak hal mengenai testing code yang telah kita tulis. Kita tidak perlu test manual setiap code yang kita buat karena hal tersebut rawan error, rawan kurang menyeluruh, dan memakan banyak waktu dan tenaga. Unit test adalah solusi yang tepat untuk itu. Kita bisa menguji code yang kita tulis dalam berbagai kondisi dan melihat seberapa banyak (dalam persentase) code kita yang teruji. Akan tetapi, coverage 100% tidak menjamin code kita tanpa bug dan error. Hal tersebut karena code coverage hanya menunjukkan seberapa banyak dari code kita yang diuji, bukan seberapa bagus unit test kita. Bisa saja kita mendapat code coverage 100% tanpa sepenuhnya menguji setiap kondisi pada code kita.

3. Jika dibuat suatu functional test baru untuk memverifikasi product list dengan setup procedure dan instance variable yang sama, akan terdapat beberapa masalah. Tentu akan terdapat duplikasi code yang melanggar DRY (Do not Repeat Yourself) principle dan mempersulit proses pembacaan bagian code yang lebih penting. Selain itu, sangat rawan terjadi inkonsistensi dan human error jika bagian dari setup procedure dan/atau instance variable ada yang perlu dimodifikasi.
