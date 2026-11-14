# Kontribusi Anggota Kelompok 14

Dokumen ini mencatat pembagian tugas tiap anggota pada project **Aplikasi Pengelolaan Data Putusan Narkotika** (UTS Struktur Data).

---

## 1. Devis Suherma

| Nama | NIM | Akun github |
|------|-----|-----|
| Devis Suherma | 202510370110056 | [Gambitz96](https://github.com)  |

### Ekstraksi Data

- Ekstraksi data putusan narkotika beserta provenance, mulai dari P01 sampai P025.

### Implementasi

- Membuat class `PutusanNarkotika` beserta implementasi kodenya.
- Membuat interface `StrukturData` beserta implementasi kodenya.
- Membuat seluruh kelas `AplikasiPutusanGUI`.
- Mengimplementasikan fitur pada GUI:
  - Impor CSV.
  - Pencarian (Case ID dan Nomor Perkara) beserta validasi input.
  - Filter (Jenis, Berat, Pasal, Vonis) beserta validasi.
  - Tambah data (form lengkap dan validasi).
  - Hapus data (dengan konfirmasi).
  - Pengurutan berdasarkan Vonis.
  - Ekspor CSV.
  - Tampilkan Audit Log.
  - Agregasi data.
- Menghubungkan GUI dengan `PutusanService`.

### Pengujian

- Menguji seluruh fitur GUI (cari, filter, tambah, hapus, sort, ekspor).
- Menemukan dan memperbaiki bug pada validasi input dan konfirmasi hapus.
- Menguji integrasi antara GUI dan service.

---

## 2. Muhammad Rizqi Erlangga 

| Nama | NIM | Akun github |
|------|-----|-----|
| Muhammad Rizqi Er | 202510370110081 | [pabl0penguin](https://github.com) |

### Ekstraksi Data

- Ekstraksi data putusan narkotika beserta provenance, mulai dari P026 sampai P050.

### Implementasi

- Membuat kelas `DynamicArray` dari nol (tanpa `java.util`).
- Mengimplementasikan seluruh method `StrukturData`, ditambah `insert`, `set`, `search`, `ensureCapacity`, dan `validateInvariant`.
- Membuat kelas `PutusanService` sebagai engine utama:
  - Load dan ekspor CSV.
  - Linear Search.
  - Filter.
  - Tambah dan hapus data.
  - Insertion Sort berdasarkan Vonis.
  - Agregasi.
  - Audit Log.

### Pengujian

- Menguji method-method pada `DynamicArray`.
- Menguji logika di `PutusanService` (cari, filter, sort, load CSV).
- Menemukan bug pada proses parsing CSV dan sorting.
