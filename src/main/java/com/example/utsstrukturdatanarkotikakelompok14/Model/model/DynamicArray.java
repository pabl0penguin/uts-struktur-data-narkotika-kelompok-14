package model;

public class DynamicArray<T> implements StrukturData<T> {

    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public DynamicArray() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        validateInvariant();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int capacity() {
        return elements.length;
    }
}