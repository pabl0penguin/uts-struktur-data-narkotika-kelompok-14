# Sistem Pengelolaan Data Putusan Narkotika

## Anggota Kelompok

| Nama | NIM |
|------|-----|
| Devis Suherma | 202510370110056 |
| Muhammad Rizqi Erlangga Putra | 202510370110081 |

## Deskripsi Project

Aplikasi Pengelolaan Data Putusan Narkotika merupakan aplikasi berbasis Java yang dibuat untuk membantu mengelola data putusan perkara narkotika. Aplikasi ini menyediakan fitur untuk menambahkan, menghapus, mencari, memfilter, mengurutkan, serta menampilkan laporan data putusan.

Project ini dibuat sebagai penerapan materi Struktur Data dengan menggunakan Dynamic Array yang diimplementasikan secara mandiri tanpa menggunakan koleksi bawaan Java sebagai struktur data utama.

## Tujuan

- Menerapkan konsep struktur data Dynamic Array.
- Memahami penggunaan algoritma pencarian dan pengurutan.
- Mengelola data putusan narkotika secara terstruktur.
- Mengimplementasikan materi Struktur Data ke dalam aplikasi berbasis GUI.

## Fitur Aplikasi

- Menambahkan data putusan narkotika.
- Menghapus data berdasarkan Case ID.
- Mengimpor data dari file CSV.
- Mengekspor data ke file CSV.
- Mencari data berdasarkan Case ID dan Nomor Perkara.
- Memfilter data berdasarkan jenis narkotika, berat, pasal, dan vonis.
- Mengurutkan data berdasarkan vonis menggunakan Insertion Sort.
- Menampilkan laporan berupa total perkara, rata-rata vonis, dan total denda.
- Mencatat aktivitas aplikasi melalui Audit Log.

## Teknologi yang Digunakan

| Komponen | Teknologi |
|----------|-----------|
| Bahasa Pemrograman | Java |
| GUI | Java Swing |
| Struktur Data | Dynamic Array |
| Algoritma Pencaria | Linear Search |
| Algoritma Sorting | Insertion Sort |
| Format Penyimpanan | CSV |
| Version Control | Git dan GitHub |

## Struktur Project

```
src/main/java/
└── utsstrukturdatanarkotikakelompok14/
    ├── App/
    │   └── Main.java
    ├── Model/
    │   ├── DynamicArray.java
    │   ├── PutusanNarkotika.java
    │   └── StrukturData.java
    ├── Service/
    │   └── PutusanService.java
    └── View/
        └── AplikasiPutusanGUI.java
```

## Cara Kompilasi dan Menjalankan di IntelliJ IDEA

### Persyaratan

- IntelliJ IDEA (Community atau Ultimate)
- JDK 17 atau lebih baru

### Langkah-langkah
### A. Clone Project dari GitHub

1. **Buat folder kosong** di komputer, misalnya `D:\Tugas-Struktur-Data`.

2. **Buka terminal di dalam folder tersebut.**
   - Windows: klik kanan di folder → `Open in Terminal` atau `Git Bash Here`.
   - Linux/macOS: buka terminal, lalu pindah ke folder itu dengan `cd`.

3. **Clone repository.**

   ```bash
   git clone <Link Repository>
   ```

   - Ganti `<username>` dan `<nama-repository>` dengan alamat repository kelompok.
   - Alamat lengkap bisa disalin dari tombol hijau **Code** di GitHub.

### B. Buka Project di IntelliJ IDEA

4. **Buka folder project.** Pilih `File` → `Open`, lalu pilih folder hasil clone.
   - Pilih folder yang berisi `pom.xml`, bukan folder induknya.
   - Jika muncul **Trust Project**, pilih `Trust Project`.

5. **Tunggu Maven selesai memuat project.** Progresnya terlihat di bagian bawah IntelliJ.
   - Jika tidak berjalan otomatis: klik kanan `pom.xml` → `Maven` → `Reload project`.

6. **Atur JDK.** Buka `File` → `Project Structure` → `Project`.
   - `SDK`: pilih versi 17 atau lebih baru.
   - `Language level`: sesuaikan dengan SDK.

### C. Kompilasi dan Jalankan

7. **Kompilasi project.** Pilih menu `Build` → `Rebuild Project`.
   - Berhasil jika jendela **Build** tidak menampilkan error.

8. **Jalankan aplikasi.**
   - Buka `src/main/java/utsstrukturdatanarkotikakelompok14/App/Main.java`.
   - Klik ikon hijau di samping `public static void main`.
   - Pilih `Run 'Main.main()'`.

9. **Muat data.**
    - Isi kolom **Path CSV** di aplikasi (misalnya `data/DataPutusan_50.csv`).
    - Klik **Impor CSV**.
### Jika terjadi error

- **`package ... does not exist`**: periksa deklarasi `package` dan `import` di tiap file, serta pastikan sama persis dengan nama folder (huruf besar/kecil berpengaruh).
- **`invalid source release`**: versi JDK yang dipilih lebih lama dari yang tertulis di `pom.xml`. Ganti SDK, atau turunkan nilai `maven.compiler.release` di `pom.xml`.
- **`Gagal membaca file` saat impor**: path CSV salah atau berkas belum ada di folder `data/`.
