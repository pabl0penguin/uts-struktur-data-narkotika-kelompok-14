package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
}