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

}
