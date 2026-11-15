# Algorithm Implementation Trace

Dokumen ini menjelaskan algoritma struktur data yang digunakan beserta lokasi implementasinya di kode.

---

## 1. Dynamic Array (Struktur Data Utama)

- **Lokasi**: `model.DynamicArray`
- **Karakteristik**:
    - Dibangun dari nol tanpa `java.util.ArrayList` / Collection
    - Kapasitas awal = 10 (`DEFAULT_CAPACITY`)
    - Kapasitas digandakan otomatis saat penuh (`ensureCapacity()`)
    - Menyimpan data dalam `Object[]`
- **Method penting**:
  | Method              | Fungsi |
  |---------------------|--------|
  | `add(T value)`      | Menambah elemen di akhir |
  | `insert(int, T)`    | Menyisipkan elemen di posisi tertentu |
  | `remove(int)`       | Menghapus elemen dan menggeser ke kiri |
  | `get(int)`          | Mengambil elemen berdasarkan indeks |
  | `set(int, T)`       | Mengganti elemen pada posisi tertentu |
  | `search(T)`         | Mencari posisi elemen (mengembalikan -1 jika tidak ditemukan) |
  | `ensureCapacity()`  | Menggandakan kapasitas array |
  | `validateInvariant()` | Memeriksa konsistensi internal array |

---

## 2. Linear Search

- **Digunakan untuk**: Pencarian data berdasarkan **Case ID** dan **Nomor Perkara**
- **Lokasi**: `service.PutusanService.cari(String kategori, String keyword)`

- **Cara kerja**:
1. Melakukan iterasi dari indeks 0 sampai `size - 1`
2. Membandingkan Case ID / Nomor Perkara (case-insensitive)
3. Berhenti saat data ditemukan
4. Mengembalikan objek `SearchResult` yang berisi:
    - Data yang ditemukan
    - Jumlah langkah (steps)
    - Status `found`
- **Kompleksitas**: O(n)

---

## 3. Insertion Sort

- **Digunakan untuk**: Mengurutkan data berdasarkan **Vonis** (Ascending)
- **Lokasi**: `service.PutusanService.urutkanBerdasarkanVonis()`
- **Cara kerja**:
1. Data disalin ke array sementara
2. Insertion Sort dijalankan berdasarkan `getVonisBulan()`
3. Hasil sorting dimasukkan ke `DynamicArray` baru
4. Data asli di `database` **tidak diubah**
- **Kompleksitas**: O(n²)
- **Sifat**: Stable sort

---

## 4. Filter (Linear Scan)

- **Lokasi**: `service.PutusanService.filter(String kategori, String keyword)`
- **Kategori yang didukung**:
    - Jenis Narkotika (partial match, case-insensitive)
    - Berat Narkotika (exact match)
    - Pasal Terbukti (partial match)
    - Vonis (exact match)
- **Cara kerja**: Linear scan seluruh data, elemen yang cocok dimasukkan ke `DynamicArray` hasil

---

## 5. Agregasi

- **Lokasi**: `service.PutusanService.hitungAgregasi()`
- **Menghitung**:
    - Total Perkara → `database.size()`
    - Rata-rata Vonis → total vonis / jumlah data
    - Total Denda → penjumlahan seluruh denda
- **Keamanan**: Mengembalikan nilai 0 jika data kosong

---

## Ringkasan Algoritma yang Dipakai

| Algoritma / Teknik     | Digunakan di Fitur              | Class & Method                          |
|------------------------|----------------------------------|-----------------------------------------|
| Dynamic Array          | Penyimpanan utama data           | `DynamicArray`                          |
| Linear Search          | Pencarian Case ID & Nomor Perkara| `PutusanService.cari()`                 |
| Linear Scan + Filter   | Filter multi-kategori            | `PutusanService.filter()`               |
| Insertion Sort         | Urutan berdasarkan Vonis         | `PutusanService.urutkanBerdasarkanVonis()` |
| Aggregation (Sum/Avg)  | Statistik statistik               | `PutusanService.hitungAgregasi()`       |

