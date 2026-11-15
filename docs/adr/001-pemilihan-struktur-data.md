
# ADR 001: Pemilihan Struktur Data

- **Tanggal:** 15 November 2026

---

## Konteks

Aplikasi menyimpan Data Putusan Narkotika. Setiap data memiliki atribut `CaseID`, `Nomor perkara`, `Nama pengadilan`, `Tanggal putusan`, `Jenis narkotika`, `Berat barang bukti`, `Pasal terbukti`, `Tuntutan bulan`, `Vonis bulan`, `Denda rupiah`, `Barang bukti`, `Keadaan memberatkan`, `Keadaan meringankan`, dan `Source page`.

Pengguna juga dapat menambahkan atau menghapus data. Oleh karena itu, dibutuhkan struktur data yang dapat menyesuaikan dengan jumlah penyimpanan tanpa batasan kapasitas tetap.

## Keputusan

Menggunakan `DynamicArray` sebagai struktur data utama untuk menyimpan data putusan narkotika.

## Alasan Pemilihan

- `DynamicArray` dapat menyesuaikan kapasitas penyimpanan secara dinamis.
- Mendukung operasi menambah data dan menghapus data.
- Data dapat diakses berdasarkan indeks.

## Konsekuensi

- Penambahan data dapat dilakukan dengan mudah tanpa menentukan ukuran array sejak awal.
- Akses data berdasarkan indeks menjadi lebih sederhana.
- Jika kapasitas array sudah penuh, sistem perlu membuat array baru dengan kapasitas yang lebih besar dan memindahkan data.
- Penghapusan data di tengah array membutuhkan proses penggeseran elemen.
- Perpindahan data dan pengelolaan kapasitas dilakukan secara manual dalam class `DynamicArray`.

## Implementasi

`DynamicArray` diterapkan pada class `PutusanService` di dalam package `service`.
