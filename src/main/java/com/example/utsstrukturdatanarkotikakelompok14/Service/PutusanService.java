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
}
