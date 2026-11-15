# AI Usage

**Nama Proyek:** Sistem Pengolahan Data Putusan Narkotika

Dokumen ini mencatat penggunaan alat AI selama pengerjaan proyek. Setiap kali anggota memakai AI.

## Log Penggunaan AI

| No. | Tanggal | Anggota | Alat | Tujuan Penggunaan | Prompt | Keluaran AI yang Digunakan | Perubahan yang Dilakukan | Cara Verifikasi |
|---|---|---|---|---|---|---|---|---|
| 1 | 14-11-2026 | Devis | ChatGPT | Membantu menyusun fitur pencarian data putusan. | Meminta bantuan untuk membuat validasi pada fitur pencarian berdasarkan CaseID dan NomorPerkara. | Validasi dan proses pencarian pada method `lakukanPencarian()`. | Menyesuaikan kode dengan yang ada pada `PutusanService` dan komponen GUI. | Mencari menggunakan CaseID dan NomorPerkara. |
| 2 | 14-11-2026 | Devis | ChatGPT | Membantu membuat validasi input pada fitur filter data. | Meminta bantuan untuk membuat validasi pada fitur filter berdasarkan jenis narkotika, vonis, dan berat barang bukti. | Validasi input dan pemanggilan method filter pada `filterPencarian()`. | Menyesuaikan kategori filter dan tipe data dengan kebutuhan aplikasi. | Mencoba filter menggunakan input yang benar dan salah. |
| 3 | 14-11-2026 | Devis | ChatGPT | Membantu memahami proses ekspor data ke file CSV. | Meminta bantuan untuk memahami proses ekspor data aplikasi ke format CSV. | Saran pemanggilan method `eksporCSV()` dan penanganan file. | Menyesuaikan lokasi penyimpanan dan data yang akan diekspor. | Mengekspor data, kemudian membuka file CSV untuk memastikan isinya sesuai. |
| 4 | 14-11-2026 | Devis | ChatGPT | Membantu menyusun tampilan audit log pada aplikasi. | Meminta bantuan untuk menampilkan riwayat aktivitas pengguna melalui fitur audit log. | Saran pengambilan dan penampilan data audit log. | Menyesuaikan tampilan audit dengan komponen GUI aplikasi. | Melakukan beberapa aktivitas, kemudian memeriksa apakah riwayatnya berhasil ditampilkan. |
| 5 | 13-11-2026 | Erlangga | Claude | Membantu menyesuaikan susunan folder proyek. | Koreksi dari project kosong, susunan folder berikut apakah sesuai dengan struktur folder yang diminta. | Saran susunan folder. | Mengubah kembali struktur folder sesuai ketentuan yang diminta. | Mencocokkan kembali pembentukan folder dengan struktur yang diminta. |
| 6 | 14-11-2026 | Erlangga | ChatGPT | Membantu memahami proses import data dari CSV. | Bagaimana membaca file CSV dan menyimpan datanya? | Penjelasan `BufferedReader`, pemisahan kolom, dan pembacaan data CSV. | Menyesuaikan proses import melalui `loadFromCSV()` dan `getPutusanNarkotika()`. | Menguji import dan memeriksa jumlah serta isi data. |
| 7 | 14-11-2026 | Erlangga | ChatGPT | Membantu memahami proses ekspor data ke CSV. | Bagaimana mengekspor data ke CSV? | Penjelasan `PrintWriter`, penulisan kolom dan header, dan method `csvEscape()`. | Menyesuaikan penulisan kolom dan header. | Memeriksa file hasil ekspor dan isi datanya. |
| 8 | 14-11-2026 | Erlangga | Gemini | Meminta bantuan tentang bug pada bagian filter. | Saat mencari angka 12 pada kolom vonis, hasilnya juga menampilkan data yang tidak memiliki angka 12 di vonisnya. | Saran menambahkan validasi di beberapa bagian kode. | Menyesuaikan kembali logika filter. | Mencoba kembali filter untuk memastikan bug tidak muncul lagi. |
| 9 | 14-11-2026 | Erlangga | ChatGPT | Memahami pencarian dan validasi struktur data. | Bagaimana mencari elemen dan memastikan kondisi Dynamic Array tetap valid? | Penjelasan `search()`, `contains()`, `clear()`, dan `validateInvariant()`. | Menyesuaikan pencarian elemen dan pemeriksaan kondisi array. | Menguji pencarian, pengosongan, dan validasi setelah operasi. |
| 10 | 14-11-2026 | Erlangga | ChatGPT | Memahami algoritma Insertion Sort dan menerapkannya pada vonis. | Bagaimana mengurutkan data berdasarkan lama vonis? | Penjelasan penggunaan key, perulangan, dan pergeseran elemen. | Menyesuaikan pengurutan menggunakan `getVonisBulan()` dan array sementara. | Memastikan data terurut dari vonis terkecil ke terbesar dan data utama tidak berubah. |
| 11 | 14-11-2026 | Erlangga | ChatGPT | Memahami pencarian data dengan Linear Search. | Bagaimana mencari data berdasarkan Case ID dan Nomor Perkara? | Penjelasan perulangan dan pencocokan data secara berurutan. | Menyesuaikan pencarian pada method `cari()` serta penggunaan `SearchResult`. | Menguji pencarian data yang ditemukan dan tidak ditemukan. |

