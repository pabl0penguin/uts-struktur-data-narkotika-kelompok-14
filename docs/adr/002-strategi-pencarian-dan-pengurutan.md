
# ADR 002: Strategi Pencarian dan Pengurutan

- **Tanggal:** 15 November 2026

---

## 1. Strategi Pencarian

### Konteks

Dalam Aplikasi Putusan Narkotika terdapat data dalam jumlah yang sangat banyak. Memilih data satu per satu secara manual kurang efisien, sehingga aplikasi menyediakan fitur pencarian berdasarkan `CaseID` dan `NomorPerkara`.

### Keputusan

Algoritma yang digunakan adalah **Linear Search**, yang bekerja dengan memeriksa data secara berurutan dari data pertama sampai data yang dicari ditemukan.

### Alasan Pemilihan

- Cara kerja yang simpel, data diperiksa satu per satu.
- Tidak membutuhkan data dalam posisi yang sudah terurut di awal.
- Cocok diimplementasikan untuk mencari data berdasarkan `CaseID` dan `NomorPerkara`.
- Implementasi mudah dan sesuai dengan struktur data `DynamicArray`.

### Konsekuensi

- Jika data berada pada bagian akhir atau data tidak ditemukan, sistem harus memeriksa seluruh data.
- Semakin banyak data, semakin banyak langkah pencariannya.
- Kompleksitas waktu terbaik adalah O(1), sedangkan terburuknya adalah O(n).

### Implementasi

Linear Search diterapkan pada method `cari()` pada class `PutusanService` di dalam package `service`.

---

## 2. Strategi Pengurutan

### Konteks

Setiap perkara narkotika memiliki lama vonis yang berbeda-beda. Aplikasi menyediakan fitur untuk mengurutkan data putusan berdasarkan vonis paling rendah hingga paling tinggi.

### Keputusan

Algoritma pengurutan menggunakan **Insertion Sort** dengan urutan *Ascending*.

### Alasan Pemilihan

- Cocok untuk mengurutkan data berdasarkan jumlah vonis (bulan).
- Algoritma cukup sederhana dan mudah diimplementasikan.
- Tidak membutuhkan algoritma yang terlalu kompleks.

### Konsekuensi

- Apabila data dalam jumlah besar dan acak, proses pengurutannya membutuhkan lebih banyak langkah.
- Kompleksitas waktu terbaik adalah O(n), sedangkan terburuknya adalah O(n^2).
- Hasil pengurutan disimpan dalam `DynamicArray` baru, sehingga data awal tetap berada dalam urutan sebelumnya.

### Implementasi

Insertion Sort diterapkan pada method `urutkanBerdasarkanVonis()` pada class `PutusanService` di dalam package `service`.
