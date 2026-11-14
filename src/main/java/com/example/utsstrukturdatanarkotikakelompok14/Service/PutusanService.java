package Service;

import Model.DynamicArray;
import Model.PutusanNarkotika;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PutusanService {
    private final DynamicArray<PutusanNarkotika> database = new DynamicArray<>();
    private final DynamicArray<String> auditLog = new DynamicArray<>();

    public DynamicArray<PutusanNarkotika> getAll() {
        return database;
    }
    public int size() {
        return database.size();
    }
    public boolean isEmpty() {
        return database.isEmpty();
    }
    public void clear() {
        database.clear();
    }

    public int loadFromCSV(String filePath) throws IOException {
        database.clear();
        int valid = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine();
            if (header == null) return 0;

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    if (data.length < 16) continue;

                    PutusanNarkotika p = getPutusanNarkotika(data);
                    database.add(p);
                    valid++;
                } catch (Exception ex) {
                    System.out.println("Data gagal diproses: " + ex.getMessage());
                }
            }
        }
        catatAudit("Impor CSV: " + valid + " data dari " + filePath);
        return valid;
    }

    private PutusanNarkotika getPutusanNarkotika(String[] data) {
        String caseId = data[0].trim();
        String nomorPerkara = data[1].trim();
        String pengadilan = data[2].trim();
        LocalDate tanggalPutusan = LocalDate.parse(data[3].trim());
        String terdakwaKode = data[4].trim();
        String jenisNarkotika = data[5].trim();
        double berat = data[6].trim().isEmpty() ? 0.0 : Double.parseDouble(data[6].trim());
        String pasalDakwaan = data[7].trim();
        String pasalTerbukti = data[8].trim();
        int tuntutan = data[9].trim().isEmpty() ? 0 : (int) Double.parseDouble(data[9].trim());
        int vonis = data[10].trim().isEmpty() ? 0 : Integer.parseInt(data[10].trim());
        long denda = data[11].trim().isEmpty() ? 0L : Long.parseLong(data[11].trim());
        String barangBukti = data[12].trim();
        String memberatkan = data[13].trim();
        String meringankan = data[14].trim();
        String sourcePages = data[15].trim();

        return new PutusanNarkotika(
                caseId, nomorPerkara, pengadilan, tanggalPutusan, terdakwaKode,
                jenisNarkotika, berat, pasalDakwaan, pasalTerbukti,
                tuntutan, vonis, denda, barangBukti, memberatkan, meringankan, sourcePages
        );
    }

    public static class SearchResult {
        public final PutusanNarkotika data;
        public final int langkah;
        public final boolean found;

        public SearchResult(PutusanNarkotika data, int langkah, boolean found) {
            this.data = data;
            this.langkah = langkah;
            this.found = found;
        }
    }

    public SearchResult cari(String kategori, String keyword) {
        keyword = keyword.toLowerCase().trim();
        int langkah = 0;
        PutusanNarkotika hasil = null;

        for (int i = 0; i < database.size(); i++) {
            langkah++;
            PutusanNarkotika p = database.get(i);
            boolean cocok;

            if (kategori.equals("Case ID")) {
                cocok = p.getCaseId().equalsIgnoreCase(keyword);
            } else {
                cocok = p.getNomorPerkara().equalsIgnoreCase(keyword);
            }

            if (cocok) {
                hasil = p;
                break;
            }
        }

        boolean found = hasil != null;
        catatAudit("Cari " + kategori + " [" + keyword + "] (" + (found ? 1 : 0) + " hasil)");
        return new SearchResult(hasil, langkah, found);
    }

    public DynamicArray<PutusanNarkotika> filter(String kategori, String keyword) {
        DynamicArray<PutusanNarkotika> hasil = new DynamicArray<>();
        keyword = keyword.trim();

        for (int i = 0; i < database.size(); i++) {
            PutusanNarkotika p = database.get(i);
            boolean cocok = false;

            switch (kategori) {
                case "Jenis Narkotika":
                    if (p.getJenisNarkotika() != null &&
                            p.getJenisNarkotika().toLowerCase().contains(keyword.toLowerCase())) {
                        cocok = true;
                    }
                    break;
                case "Berat Narkotika":
                    double targetBerat = Double.parseDouble(keyword.replace(',', '.'));
                    cocok = Double.compare(p.getBeratBarangBuktiGram(), targetBerat) == 0;
                    break;
                case "Pasal Terbukti":
                    if (p.getPasalTerbukti() != null &&
                            p.getPasalTerbukti().toLowerCase().contains(keyword.toLowerCase())) {
                        cocok = true;
                    }
                    break;
                case "Vonis":
                    int targetVonis = Integer.parseInt(keyword);
                    cocok = p.getVonisBulan() == targetVonis;
                    break;
            }

            if (cocok) {
                hasil.add(p);
            }
        }

        catatAudit("Filter " + kategori + " [" + keyword + "] → " + hasil.size() + " data");
        return hasil;
    }

    public void tambah(PutusanNarkotika p) {
        if (cariIndexCaseId(p.getCaseId()) >= 0) {
            throw new IllegalArgumentException("Case ID sudah digunakan.");
        }
        database.add(p);
        catatAudit("Tambah putusan Case ID " + p.getCaseId());
    }

    public boolean hapusByCaseId(String caseId) {
        int index = cariIndexCaseId(caseId);
        if (index < 0) return false;

        database.remove(index);
        catatAudit("Hapus putusan Case ID " + caseId);
        return true;
    }

    public int cariIndexCaseId(String id) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getCaseId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void eksporCSV(File file, DynamicArray<PutusanNarkotika> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            // Header
            out.println("\"Case ID\",\"Nomor Perkara\",\"Pengadilan\",\"Tanggal Putusan\"," +
                    "\"Terdakwa\",\"Jenis Narkotika\",\"Berat (g)\",\"Pasal Dakwaan\"," +
                    "\"Pasal Terbukti\",\"Tuntutan (Bln)\",\"Vonis (Bln)\",\"Denda (Rp)\"," +
                    "\"Barang Bukti\",\"Memberatkan\",\"Meringankan\",\"Source Pages\"");

            for (int i = 0; i < data.size(); i++) {
                PutusanNarkotika p = data.get(i);
                out.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%.2f,\"%s\",\"%s\",%d,%d,%d,\"%s\",\"%s\",\"%s\",\"%s\"%n",
                        csvEscape(p.getCaseId()),
                        csvEscape(p.getNomorPerkara()),
                        csvEscape(p.getPengadilan()),
                        p.getTanggalPutusan(),
                        csvEscape(p.getTerdakwaKode()),
                        csvEscape(p.getJenisNarkotika()),
                        p.getBeratBarangBuktiGram(),
                        csvEscape(p.getPasalDakwaan()),
                        csvEscape(p.getPasalTerbukti()),
                        p.getTuntutanBulan(),
                        p.getVonisBulan(),
                        p.getDendaRupiah(),
                        csvEscape(p.getBarangBukti()),
                        csvEscape(p.getKeadaanMemberatkan()),
                        csvEscape(p.getKeadaanMeringankan()),
                        csvEscape(p.getSourcePages())
                );
            }
        }
        catatAudit("Ekspor " + data.size() + " baris ke " + file.getName());
    }

    private String csvEscape(String value) {
        if (value == null) return "";
        return value.replace("\"", "\"\"");
    }

}
