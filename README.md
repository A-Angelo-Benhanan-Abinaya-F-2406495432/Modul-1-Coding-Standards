# Module 1: Coding Standards

## Exercise 1
Tutorial 1 ini mengajarkan saya banyak hal, terutama dalam clean code principles dan secure coding practices. Salah satu masalah yang saya alami adalah commit setelah semua selesai lalu push. Kebiasaan buruk tersebut masih sering muncul saat pengerjaan Tutorial 1 ini oleh saya. Dengan tutorial ini, saya diingatkan untuk menerapkan kebiasaan coding yang lebih baik dan aman; commit per sub-fitur/bagian fitur, bukan setelah fiturnya selesai.

## Exercise 2
1. Setelah membuat unit test, saya mempelajari banyak hal mengenai testing code yang telah kita tulis. Kita tidak perlu test manual setiap code yang kita buat karena hal tersebut rawan error, rawan kurang menyeluruh, dan memakan banyak waktu dan tenaga. Unit test adalah solusi yang tepat untuk itu. Kita bisa menguji code yang kita tulis dalam berbagai kondisi dan melihat seberapa banyak (dalam persentase) code kita yang teruji. Akan tetapi, coverage 100% tidak menjamin code kita tanpa bug dan error. Hal tersebut karena code coverage hanya menunjukkan seberapa banyak dari code kita yang diuji, bukan seberapa bagus unit test kita. Bisa saja kita mendapat code coverage 100% tanpa sepenuhnya menguji setiap kondisi pada code kita.

3. Jika dibuat suatu functional test baru untuk memverifikasi product list dengan setup procedure dan instance variable yang sama, akan terdapat beberapa masalah. Tentu akan terdapat duplikasi code yang melanggar DRY (Do not Repeat Yourself) principle dan mempersulit proses pembacaan bagian code yang lebih penting. Selain itu, sangat rawan terjadi inkonsistensi dan human error jika bagian dari setup procedure dan/atau instance variable ada yang perlu dimodifikasi.

# Module 2: Implementing CI/CD using Gradle & GitHub Actions

### 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy
on fixing them.

Issue-issue code quality yang saya temui dan atasi serta cara saya mengatasi issue-issue tersebut adalah sebagai berikut:
- Beberapa class tidak memiliki constructor -> Menggunakan @NoArgsConstructor untuk menunjukkan bahwa class tersebut tidak membutuhkan sebuah constructor.
- Beberapa variable tidak dimodifikasi -> Menempatkan 'final' pada variable-variable tersebut.
- Beberapa variable memiliki nama yang terlalu pendek -> Mengganti nama variable-variable tersebut dengan nama yang lebih panjang dan deskriptif.
- Access modifier public yang redundant di class ProductService -> Menghapus access modifier tersebut.

### 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current
implementation has met the definition of Continuous Integration and Continuous
Deployment? Explain the reasons (minimum 3 sentences)!

Berdasarkan workflow CI/CD pada GitHub, implementasi saya sudah memenuhi definisi Continuous Integration tetapi belum memenuhi untuk Continuous Deployment. Continuous Integration dikelola oleh ketiga file yang ada dalam .github/workflows; ci.yml, pmd.yml, dan scorecard.yml. Ketiga workflow tersebut bertugas untuk menganalisis dan mengevaluasi code yang ada pada setiap push dan pull request. Akan tetapi, ketiga workflow tersebut tidak melakukan Continuous Deployment. Tidak ada langkah yang membuat suatu deployable artifact dan mem-push-nya ke sebuah hosting platform seperti Render sehingga setiap deployment harus dilakukan secara manual. Oleh karena itu, implementasi saya pada saat ini belum memenuhi definisi dari Continuous Deployment.
