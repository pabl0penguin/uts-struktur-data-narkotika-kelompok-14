package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;

public class AplikasiPutusanGUI extends JFrame {
    private final PutusanService service = new PutusanService();
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JTextField txtSearch;
    private final JTextField txtFilterJenis;
    private final JTextField txtPath;
    private final JComboBox<String> kategoriCari;
    private final JComboBox<String> pasalTerbukti;
    private final JLabel lblStatus;
    private final JLabel lblAgregasi;

    private DynamicArray<PutusanNarkotika> dataTampilan = new DynamicArray<>();
    public AplikasiPutusanGUI() {
        setTitle("Aplikasi Pengolahan Putusan Narkotika - UTS Struktur Data");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(10, 10));

        JPanel panelAtas = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelAtas.setBorder(BorderFactory.createTitledBorder("1. Impor & Kontrol Data"));

        txtPath = new JTextField("tugasUtsSudahdiPecah/data/DataPutusan_50.csv", 25);
        JButton btnLoad = new JButton("Impor CSV");
        lblStatus = new JLabel("Status: Belum dimuat");

        panelAtas.add(new JLabel("Path CSV:"));
        panelAtas.add(txtPath);
        panelAtas.add(btnLoad);
        panelAtas.add(lblStatus);
        add(panelAtas, BorderLayout.NORTH);

        String[] kolom = {
                "Case ID", "Nomor Perkara", "Pengadilan", "Tanggal Putusan",
                "Terdakwa", "Jenis Narkotika", "Berat (g)", "Pasal Dakwaan",
                "Pasal Terbukti", "Tuntutan (Bln)", "Vonis (Bln)", "Denda (Rp)",
                "Barang Bukti", "Memberatkan", "Meringankan", "Source Pages"
        };
        tableModel = new DefaultTableModel(kolom, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("2. Tampilan Struktur Data Utama (DynamicArray)"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel();
        panelBawah.setLayout(new BoxLayout(panelBawah, BoxLayout.Y_AXIS));
        panelBawah.setBorder(BorderFactory.createTitledBorder("3. Fitur Algoritma (Cari, Filter, Agregasi)"));

        JPanel subPanelAksi = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JPanel subPanelTombol = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        String[] pilihanKategori = {"Case ID", "Nomor Perkara"};
        kategoriCari = new JComboBox<>(pilihanKategori);
        String[] pilihJenis = {"Jenis Narkotika", "Berat Narkotika", "Pasal Terbukti", "Vonis"};
        pasalTerbukti = new JComboBox<>(pilihJenis);

        txtSearch = new JTextField(10);
        JButton btnSearch = new JButton("Cari");
        JButton btnTambahData = new JButton("Tambah Data");
        JButton btnHapusData = new JButton("Hapus Data");
        JButton btnUrutVonis = new JButton("Urutan Vonis");
        txtFilterJenis = new JTextField(10);
        JButton btnFilter = new JButton("Filter");
        JButton btnReset = new JButton("Reset Tampilan");
        JButton btnEkspor = new JButton("Ekspor CSV");
        JButton btnAudit = new JButton("Riwayat Audit");

        subPanelAksi.add(kategoriCari);
        subPanelAksi.add(txtSearch);
        subPanelAksi.add(btnSearch);
        subPanelAksi.add(new JLabel(" | Filter Berdasarkan:"));
        subPanelAksi.add(pasalTerbukti);
        subPanelAksi.add(txtFilterJenis);
        subPanelAksi.add(btnFilter);
        subPanelAksi.add(btnReset);

        subPanelTombol.add(btnTambahData);
        subPanelTombol.add(btnHapusData);
        subPanelTombol.add(btnUrutVonis);
        subPanelTombol.add(btnEkspor);
        subPanelTombol.add(btnAudit);

        JPanel subPanelAgregasi = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        lblAgregasi = new JLabel("Agregasi: Belum dihitung.");
        subPanelAgregasi.add(lblAgregasi);

        panelBawah.add(subPanelAksi);
        panelBawah.add(subPanelTombol);
        panelBawah.add(subPanelAgregasi);
        add(panelBawah, BorderLayout.SOUTH);

        btnLoad.addActionListener(e -> muatData(txtPath.getText().trim()));
        btnReset.addActionListener(e -> tampilkanSemua());
        btnSearch.addActionListener(e -> lakukanPencarian());
        btnFilter.addActionListener(e -> filterPencarian());
        btnTambahData.addActionListener(e -> tambahData());
        btnHapusData.addActionListener(e -> hapusData());
        btnUrutVonis.addActionListener(e -> tampilkanUrutVonis());
        btnEkspor.addActionListener(e -> eksporFile());
        btnAudit.addActionListener(e -> tampilkanAudit());
    }
    private void tampilkanData(DynamicArray<PutusanNarkotika> data) {
        tableModel.setRowCount(0);
        dataTampilan = data;
        for (int i = 0; i < data.size(); i++) {
            tambahKeTabel(data.get(i));
        }
    }
    private void tampilkanSemua() {
        tampilkanData(service.getAll());
        updateAgregasi();
        lblStatus.setText("Status: Menampilkan semua data (" + service.size() + ")");
    }
    private void tambahKeTabel(PutusanNarkotika p) {
        tableModel.addRow(new Object[]{
                p.getCaseId(),
                p.getNomorPerkara(),
                p.getPengadilan(),
                p.getTanggalPutusan(),
                p.getTerdakwaKode(),
                p.getJenisNarkotika(),
                p.getBeratBarangBuktiGram(),
                p.getPasalDakwaan(),
                p.getPasalTerbukti(),
                p.getTuntutanBulan(),
                p.getVonisBulan(),
                p.getDendaRupiah(),
                p.getBarangBukti(),
                p.getKeadaanMemberatkan(),
                p.getKeadaanMeringankan(),
                p.getSourcePages()
        });
    }
    private void updateAgregasi() {
        PutusanService.Agregasi agg = service.hitungAgregasi();
        lblAgregasi.setText(String.format(
                "Agregasi -> Total Perkara: %d | Rata-rata Vonis: %.2f bulan | Total Denda: Rp %,d",
                agg.totalPerkara, agg.rataVonis, agg.totalDenda));
    }

    private void muatData(String path) {
        try {
            int jumlah = service.loadFromCSV(path);
            tampilkanSemua();
            lblStatus.setText("Status: Berhasil memuat " + jumlah + " data");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal membaca file: " + e.getMessage(),
                    "Error I/O", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void lakukanPencarian() {
        String kategori = kategoriCari.getSelectedItem().toString();
        String keyword = txtSearch.getText().trim();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan kata kunci pencarian!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (kategori.equals("Case ID") && keyword.contains("/")) {
            JOptionPane.showMessageDialog(this, "Anda memilih pencarian Case ID, bukan Nomor Perkara!", "Input Tidak Sesuai", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (kategori.equals("Nomor Perkara") && !keyword.contains("/")) {
            JOptionPane.showMessageDialog(this, "Anda memilih pencarian Nomor Perkara, bukan Case ID!", "Input Tidak Sesuai", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PutusanService.SearchResult hasil = service.cari(kategori, keyword);

        if (hasil.found) {
            DynamicArray<PutusanNarkotika> temp = new DynamicArray<>();
            temp.add(hasil.data);
            tampilkanData(temp);

            JOptionPane.showMessageDialog(this,
                    "Data ditemukan!\n\nKategori : " + kategori +
                            "\nKata Kunci : " + keyword +
                            "\nJumlah Langkah : " + hasil.langkah,
                    "Hasil Pencarian", JOptionPane.INFORMATION_MESSAGE);

            lblStatus.setText("Status: Data ditemukan dalam " + hasil.langkah + " langkah");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Data tidak ditemukan setelah " + hasil.langkah + " langkah.",
                    "Pencarian Gagal", JOptionPane.WARNING_MESSAGE);
            tampilkanSemua();
        }
    }

    private void filterPencarian() {
        String kategori = pasalTerbukti.getSelectedItem().toString();
        String keyword = txtFilterJenis.getText().trim();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan kata kunci filter!");
            return;
        }

        // Validasi input
        if (kategori.equals("Jenis Narkotika") && !keyword.matches("[a-zA-Z\\s().-]+")) {
            JOptionPane.showMessageDialog(this, "Filter Jenis Narkotika harus berupa teks!", "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (kategori.equals("Vonis")) {
            try { Integer.parseInt(keyword); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Filter Vonis harus berupa angka bulat!", "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        if (kategori.equals("Berat Narkotika")) {
            try { Double.parseDouble(keyword.replace(',', '.')); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Filter Berat Narkotika harus berupa angka!", "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        DynamicArray<PutusanNarkotika> hasil = service.filter(kategori, keyword);
        tampilkanData(hasil);
        lblStatus.setText("Status: Filter aktif (" + hasil.size() + " data cocok)");
    }

    private void tampilkanUrutVonis() {
        DynamicArray<PutusanNarkotika> hasil = service.urutkanBerdasarkanVonis();
        tampilkanData(hasil);
        lblStatus.setText("Status: Data ditampilkan berdasarkan Vonis (Ascending)");
    }

    private void tambahData() {
        JTextField id = new JTextField();
        JTextField perkara = new JTextField();
        JTextField pengadilan = new JTextField();
        JTextField tanggal = new JTextField(LocalDate.now().toString());
        JTextField terdakwa = new JTextField();
        JTextField jenis = new JTextField();
        JTextField berat = new JTextField();
        JTextField dakwaan = new JTextField();
        JTextField terbukti = new JTextField();
        JTextField tuntutan = new JTextField();
        JTextField vonis = new JTextField();
        JTextField denda = new JTextField();
        JTextField memberatkan = new JTextField();
        JTextField meringankan = new JTextField();
        JTextField barangBukti = new JTextField();

        Object[] fields = {
                "Case ID:", id, "Nomor perkara:", perkara, "Pengadilan:", pengadilan,
                "Tanggal (YYYY-MM-DD):", tanggal, "Kode terdakwa:", terdakwa,
                "Jenis narkotika:", jenis, "Berat barang bukti (gram):", berat,
                "Pasal dakwaan:", dakwaan, "Pasal terbukti:", terbukti,
                "Tuntutan (bulan):", tuntutan, "Vonis (bulan):", vonis, "Denda (rupiah):", denda,
                "Keadaan Memberatkan:", memberatkan, "Keadaan Meringankan:", meringankan,
                "Barang Bukti:", barangBukti
        };

        int pilihan = JOptionPane.showConfirmDialog(this, fields, "Tambah Putusan",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (pilihan != JOptionPane.OK_OPTION) return;

        try {
            if (id.getText().trim().isEmpty() || perkara.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Case ID dan nomor perkara wajib diisi.");
            }

            PutusanNarkotika p = new PutusanNarkotika(
                    id.getText().trim(),
                    perkara.getText().trim(),
                    pengadilan.getText().trim(),
                    LocalDate.parse(tanggal.getText().trim()),
                    terdakwa.getText().trim(),
                    jenis.getText().trim(),
                    Double.parseDouble(berat.getText().trim()),
                    dakwaan.getText().trim(),
                    terbukti.getText().trim(),
                    Integer.parseInt(tuntutan.getText().trim()),
                    Integer.parseInt(vonis.getText().trim()),
                    Long.parseLong(denda.getText().trim()),
                    barangBukti.getText().trim(),
                    memberatkan.getText().trim(),
                    meringankan.getText().trim(),
                    "Input manual"
            );

            service.tambah(p);
            tampilkanSemua();
            lblStatus.setText("Status: Data berhasil ditambahkan.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Data tidak valid: " + ex.getMessage(),
                    "Gagal menambah data", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void hapusData() {
        int baris = table.getSelectedRow();
        if (baris < 0) {
            JOptionPane.showMessageDialog(this, "Pilih satu baris pada tabel terlebih dahulu.");
            return;
        }

        String id = String.valueOf(table.getValueAt(baris, 0));
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Hapus putusan dengan Case ID " + id + "?", "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            if (service.hapusByCaseId(id)) {
                tampilkanSemua();
                lblStatus.setText("Status: Data " + id + " berhasil dihapus.");
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan pada struktur utama.");
            }
        }
    }
    private void eksporFile() {
        if (dataTampilan.size() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data pada tabel untuk diekspor.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("hasil_putusan.csv"));
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();
        try {
            service.eksporCSV(file, dataTampilan);
            lblStatus.setText("Status: Ekspor berhasil (" + dataTampilan.size() + " data).");
            JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke:\n" + file.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengekspor: " + ex.getMessage(),
                    "Error Ekspor", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tampilkanAudit() {
        DynamicArray<String> log = service.getAuditLog();
        if (log.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada aktivitas yang tercatat.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < log.size(); i++) {
            sb.append(i + 1).append(". ").append(log.get(i)).append("\n");
        }

        JTextArea area = new JTextArea(sb.toString(), 18, 75);
        area.setEditable(false);
        area.setCaretPosition(0);
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Riwayat Audit", JOptionPane.INFORMATION_MESSAGE);
    }
}
