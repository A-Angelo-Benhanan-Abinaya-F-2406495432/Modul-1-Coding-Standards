# Module 1: Coding Standards

## Exercise 1
Tutorial 1 ini mengajarkan saya banyak hal, terutama dalam clean code principles dan secure coding practices. Salah satu masalah yang saya alami adalah commit setelah semua selesai lalu push. Kebiasaan buruk tersebut masih sering muncul saat pengerjaan Tutorial 1 ini oleh saya. Dengan tutorial ini, saya diingatkan untuk menerapkan kebiasaan coding yang lebih baik dan aman; commit per sub-fitur/bagian fitur, bukan setelah fiturnya selesai.

## Exercise 2
1. Setelah membuat unit test, saya mempelajari banyak hal mengenai testing code yang telah kita tulis. Kita tidak perlu test manual setiap code yang kita buat karena hal tersebut rawan error, rawan kurang menyeluruh, dan memakan banyak waktu dan tenaga. Unit test adalah solusi yang tepat untuk itu. Kita bisa menguji code yang kita tulis dalam berbagai kondisi dan melihat seberapa banyak (dalam persentase) code kita yang teruji. Akan tetapi, coverage 100% tidak menjamin code kita tanpa bug dan error. Hal tersebut karena code coverage hanya menunjukkan seberapa banyak dari code kita yang diuji, bukan seberapa bagus unit test kita. Bisa saja kita mendapat code coverage 100% tanpa sepenuhnya menguji setiap kondisi pada code kita.

3. Jika dibuat suatu functional test baru untuk memverifikasi product list dengan setup procedure dan instance variable yang sama, akan terdapat beberapa masalah. Tentu akan terdapat duplikasi code yang melanggar DRY (Do not Repeat Yourself) principle dan mempersulit proses pembacaan bagian code yang lebih penting. Selain itu, sangat rawan terjadi inkonsistensi dan human error jika bagian dari setup procedure dan/atau instance variable ada yang perlu dimodifikasi.

# Module 2: Implementing CI/CD using Gradle & GitHub Actions

### 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Issue-issue code quality yang saya temui dan atasi serta cara saya mengatasi issue-issue tersebut adalah sebagai berikut:
- Beberapa class tidak memiliki constructor -> Menggunakan @NoArgsConstructor untuk menunjukkan bahwa class tersebut tidak membutuhkan sebuah constructor.
- Beberapa variable tidak dimodifikasi -> Menempatkan 'final' pada variable-variable tersebut.
- Beberapa variable memiliki nama yang terlalu pendek -> Mengganti nama variable-variable tersebut dengan nama yang lebih panjang dan deskriptif.
- Access modifier public yang redundant di class ProductService -> Menghapus access modifier tersebut.

### 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Berdasarkan workflow CI/CD pada GitHub, implementasi saya sudah memenuhi definisi Continuous Integration tetapi belum memenuhi untuk Continuous Deployment. Continuous Integration dikelola oleh ketiga file yang ada dalam .github/workflows; ci.yml, pmd.yml, dan scorecard.yml. Ketiga workflow tersebut bertugas untuk menganalisis dan mengevaluasi code yang ada pada setiap push dan pull request. Akan tetapi, ketiga workflow tersebut tidak melakukan Continuous Deployment. Tidak ada langkah yang membuat suatu deployable artifact dan mem-push-nya ke sebuah hosting platform seperti Render sehingga setiap deployment harus dilakukan secara manual. Oleh karena itu, implementasi saya pada saat ini belum memenuhi definisi dari Continuous Deployment.

# Module 3: OO Principles & Software Maintainability

## 1) Explain what principles you apply to your project!
### ● SRP
SRP adalah principle di mana suatu class hanya memiliki satu fungsi. Principle ini diterapkan pada class ProductController di mana ProductController (untuk Product dan Car) dipecah menjadi ProductController (untuk Product) dan CarController (untuk Car).

### ● OCP
OCP adalah principle di mana class/function/module/dll dapat dikembangkan via extension tetapi tidak dimodifikasi langsung. Principle ini diterapkan pada repository dan service di mana terdapat interface yang diimplement concrete class.

### ● LSP
LSP adalah principle di mana superclass bisa diganti dengan subclassnya tanpa perlu ada perubahan program. Principle ini diterapkan pada penghapusan 'extends ProductController' pada CarController karena ProductController tidak bisa diganti dengan CarController.

### ● ISP
ISP adalah principle di mana suatu interface besar dipecah menjadi interface-interface kecil yang lebih spesifik. Principle ini diterapkan pada interface repository dan service di mana interface CarService dan ProductService serta CarRepositoryInterface dan ProductRepositoryInterface terpisah karena keduanya tidak memiliki tugas yang overlapping.

### ● DIP
DIP adalah principle di mana high-level module tidak dependent pada low-level module. Principle ini diterapkan pada kedua concrete class service yang dependant dengan interface repository dan bukan implementasi concretenya.

## 2) Explain the advantages of applying SOLID principles to your project with examples.

Dengan menerapkan SOLID principles, pengembangan dan maintenance program dapat dilakukan dengan lebih mudah. Karena module-module dependant dengan interface spesifik dan perkembangan dilakukan dengan extension, perubahan pada concrete class tidak akan menyebabkan error. Perubahan yang dilakukan seorang developer pada suatu file tidak akan mengganggu kerja developer lain karena mereka bekerja pada file yang berbeda. Selain itu, testing juga dapat dilakukan dengan lebih mudah karena spesifiknya module-module.

## 3) Explain the disadvantages of not applying SOLID principles to your project with examples.

Jika tidak menerapkan SOLID principles, program dapat dengan mudah menjadi terlalu kompleks dan sulit dibaca dan di-debug. Error kecil pada suatu module dapat menyebabkan chain reaction yang membuat error besar di module lain. Selain itu, akan sering terjadi merge conflict saat 2+ developer berbeda melakukan perubahan pada file yang sama, meskipun function yang diubah berbeda.

# Module 4: Test-Driven Development & Refactoring

## 1. Reflection Based On Percival (2017) Proposed Self-Reflective Questions

### Correctness
Mengenai objektif correctness, objektif ini belum sepenuhnya terpenuhi. Belum ada functional test yang diimplementasi dan tidak semua edge case di-testing. Akan tetapi, sudah ada unit test yang diimplementasi.

### Maintainability
Mengenai objektif maintainability, objektif ini sudah cukup terpenuhi. Banyak penggunaan interface dan enum pada code yang memudahkan refactoring. Akan tetapi, menurut saya, terdapat beberapa bagian yang masih bisa dipecah untuk mengurangi kemungkinan terjadinya error, terutama di bagian Payment.

### Productive Workflow
Mengenai objektif productive workflow, objektif ini sudah cukup terpenuhi. Unit test yang ada sudah cukup cepat dan meng-cover bug-bug yang dapat terjadi. Akan tetapi, terdapat beberapa bagian yang mungkin dapat di-refactor untuk meningkatkan readability dan kecepatan unit test.

## 2. Reflection On Whether My Tests Have Successfully Followed F.I.R.S.T. Principle

### Fast
Mengenai prinsip fast, prinsip ini sudah cukup terpenuhi. Unit test berjalan dengan cepat (hanya dalam sekitar 2 detik). Selain itu, penggunaan mockito menghindari pemanggilan database yang dapat memakan waktu (meskipun program tidak dihubungkan ke database).

### Isolated/Independent
Mengenai prinsip isolated/independent, prinsip ini sudah cukup terpenuhi. Unit test saling independent tanpa penggunaan code yang sama. Akan tetapi, terdapat penggunaan method addPayment oleh dua unit test pada PaymentServiceImplTest memungkinkan terjadinya error pada kedua unit test tersebut jika addPayment gagal/rusak.

### Repeatable
Mengenai prinsip repeatable, prinsip ini sudah cukup terpenuhi. Penggunaan mocking dengan doReturn() memungkinkan pendapatan hasil yang sama meskipun dijalankan berulang kali.

### Thorough/Timely
Mengenai prinsip thorough/timely, prinsip ini sudah cukup terpenuhi. Unit test meng-cover happy dan unhappy path serta seluruh kemungkinan error.
