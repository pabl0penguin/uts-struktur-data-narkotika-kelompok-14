# Panduan Skenario Demo Aplikasi

**Aplikasi Pengolahan Putusan Narkotika - UTS Struktur Data (Kelompok 14)**

Dokumen ini berisi urutan pengujian aplikasi saat presentasi UTS. Setiap skenario dibuat untuk membuktikan bahwa struktur data (DynamicArray) dan fitur yang diminta pada soal berjalan dengan benar.

**Persiapan:** jalankan aplikasi dari awal (agar Riwayat Audit bersih) dan pastikan file `data/dataPutusan_50.csv` tersedia.

---

## Skenario 1: Memuat 50 Data Utama

**Cara tes:** Buka aplikasi. Pada bagian "1. Impor & Kontrol Data", pastikan Path CSV berisi `data/dataPutusan_50.csv`, lalu klik tombol **Impor CSV**.

**Yang diharapkan:**
- Status berubah menjadi "Berhasil memuat 50 data".
- Tabel "Tampilan Struktur Data Utama (DynamicArray)" menampilkan Case ID P001 sampai P050.
- Baris agregasi di bawah menampilkan: Total Perkara: 50 | Rata-rata Vonis: 76,06 bulan | Total Denda: Rp 53.975.000.000.

**Screenshot bukti:** `img/01-impor-csv.png`

![Skenario 1](img/01-impor-csv.png)

---

## Skenario 2: Pencarian Berdasarkan Case ID (Linear Search)

**Cara tes:** Pada bagian "3. Fitur Algoritma", pilih kategori **Case ID**, ketik `P007` di kolom pencarian, lalu klik **Cari**.

**Yang diharapkan:**
- Muncul dialog "Hasil Pencarian": "Data ditemukan!", Kategori: Case ID, Kata Kunci: P007, **Jumlah Langkah: 7**.
- Tabel hanya menampilkan satu baris, yaitu P007 (Vonis 144 bulan, Denda 1.500.000.000).
- Jumlah langkah sama dengan posisi data (P007 adalah data ke-7), sesuai cara kerja Linear Search yang memeriksa data satu per satu dari awal.

**Screenshot bukti:** `img/02-cari-case-id.png`

![Skenario 2](img/02-cari-case-id.png)

---

## Skenario 3: Pencarian Berdasarkan Nomor Perkara

**Cara tes:** Ubah kategori pencarian menjadi **Nomor Perkara**, ketik `788/Pid.Sus/2026/PN Tng`, lalu klik **Cari**.

**Yang diharapkan:**
- Muncul dialog "Hasil Pencarian": "Data ditemukan!", Kategori: Nomor Perkara, Kata Kunci: 788/Pid.Sus/2026/PN Tng, **Jumlah Langkah: 15**.
- Tabel hanya menampilkan P015 (tanggal putusan 2026-08-06, terdakwa NU015, Vonis 108 bulan).
- Jumlah langkah 15 sama dengan posisi P015 pada array.

**Screenshot bukti:** `img/03-cari-nomor-perkara.png`

![Skenario 3](img/03-cari-nomor-perkara.png)

---

## Skenario 4: Filter Berdasarkan Jenis Narkotika

**Cara tes:** Pada bagian "Filter Berdasarkan", pilih **Jenis Narkotika**, ketik `Ganja`, lalu klik **Filter**.

**Yang diharapkan:**
- Status berubah menjadi "Filter aktif (4 data cocok)".
- Tabel hanya menampilkan P010, P030, P049, dan P050.
- P010 ikut muncul karena jenisnya tertulis "Ganja (Cannabis)", yang membuktikan filter mencocokkan sebagian teks.
  **Screenshot bukti:** `img/04-filter-jenis-narkotika.png`

![Skenario 4](img/04-filter-jenis-narkotika.png)

---

## Skenario 5: Filter Berdasarkan Berat Narkotika

**Cara tes:** Ubah kategori filter menjadi **Berat Narkotika**, ketik `1.12`, lalu klik **Filter**.

**Yang diharapkan:**
- Status berubah menjadi "Filter aktif (1 data cocok)".
- Tabel hanya menampilkan P008 dengan berat 1.12 g.

**Screenshot bukti:** `img/05-filter-berat.png`

![Skenario 5](img/05-filter-berat.png)

---

## Skenario 6: Filter Berdasarkan Pasal Terbukti

**Cara tes:** Ubah kategori filter menjadi **Pasal Terbukti**, ketik `114`, lalu klik **Filter**.

**Yang diharapkan:**
- Status berubah menjadi "Filter aktif (41 data cocok)".
- Sembilan data lain tidak ikut tampil karena pasal terbuktinya berbeda: P008 dan P012 (Pasal 112), P010 (Pasal 111), P020 (Pasal 119), P018, P029, dan P046 (Pasal 609), serta P013 dan P014 (Pasal 610).

**Screenshot bukti:** `img/06-filter-pasal-terbukti.png`

![Skenario 6](img/06-filter-pasal-terbukti.png)

---

## Skenario 7: Filter Berdasarkan Vonis

**Cara tes:** Ubah kategori filter menjadi **Vonis**, ketik `48`, lalu klik **Filter**.

**Yang diharapkan:**
- Status berubah menjadi "Filter aktif (8 data cocok)".
- Tabel menampilkan P008, P024, P029, P030, P031, P038, P043, dan P050. Semuanya berkolom Vonis 48 bulan.

**Screenshot bukti:** `img/07-filter-vonis.png`

![Skenario 7](img/07-filter-vonis.png)

---

## Skenario 8: Pengurutan Berdasarkan Vonis

**Cara tes:** Klik tombol **Urutan Vonis**.

**Yang diharapkan:**
- Status berubah menjadi "Data ditampilkan berdasarkan Vonis (Ascending)".
- Tabel menampilkan 50 data dari vonis terkecil (36 bulan: P010, P039, P046, P049) naik bertahap ke vonis terbesar.
- Data dengan vonis sama tetap berurutan menurut urutan aslinya (misalnya vonis 48: P008, P024, P029, P030, P031, P038, P043, P050).
- Agregasi tetap: Total Perkara 50, Rata-rata Vonis 76,06 bulan.

**Screenshot bukti:** `img/08-urutan-vonis.png`

![Skenario 8](img/08-urutan-vonis.png)

---

## Skenario 9: Ekspor Data ke CSV

**Cara tes:** Klik tombol **Ekspor CSV**. Pada dialog Save, biarkan nama file `hasil_putusan.csv`, pilih lokasi penyimpanan, lalu klik **Save**. Buka file hasilnya.

**Yang diharapkan:**
- Dialog Save muncul dengan nama file bawaan `hasil_putusan.csv`.
- File berhasil dibuat, memuat baris header dan seluruh kolom data putusan, dan isinya sesuai dengan data di tabel.

**Screenshot bukti:** `img/09-ekspor-csv.png`

![Skenario 9](img/09-ekspor-csv.png)

---

## Skenario 10: Riwayat Audit

**Cara tes:** Klik tombol **Riwayat Audit**.

**Yang diharapkan:** Muncul dialog "Riwayat Audit" berisi semua aktivitas sebelumnya, urut dari yang terlama, masing-masing dengan timestamp:

**Screenshot bukti:** `img/10-riwayat-audit.png`

![Skenario 10](img/10-riwayat-audit.png)

---

## Skenario 11: Menambah Data Baru

**Cara tes:** Klik tombol **Tambah Data**, isi form dengan data, lalu simpan.

**Yang diharapkan:**
- Status berubah menjadi "Data berhasil ditambahkan."
- Baris P051 muncul di paling bawah tabel dan langsung terpilih.

**Screenshot bukti:** `img/11-tambah-data.png`

![Skenario 11](img/11-tambah-data.png)

---

## Skenario 12: Menghapus Data


**Cara tes:** Pilih baris P050 di tabel, lalu klik **Hapus Data**.

**Yang diharapkan:** Baris P050 hilang, dan baris P050 hilang pada tabel.

**Screenshot bukti:** `img/12-hapus-data.png, 12-bukti-data-hapus`

![Skenario 12](img/12-tambah-data.png)
![Skenario 12](img/12-bukti-data-hapus)
