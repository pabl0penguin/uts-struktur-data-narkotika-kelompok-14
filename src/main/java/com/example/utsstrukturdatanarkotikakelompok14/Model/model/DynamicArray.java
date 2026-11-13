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

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
        validateInvariant();
    }

    @Override
    public T get(int position) {
        checkIndex(position);
        return (T) elements[position];
    }

    @Override
    public T remove(int position) {
        checkIndex(position);
        T removedElement = (T) elements[position];
        int numMoved = size - position - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, position + 1, elements, position, numMoved);
        }
        elements[--size] = null;
        validateInvariant();
        return removedElement;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        validateInvariant();
    }

    public void insert(int position, T value) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Posisi indeks tidak valid: " + position);
        }
        ensureCapacity();
        System.arraycopy(elements, position, elements, position + 1, size - position);
        elements[position] = value;
        size++;
        validateInvariant();
    }

    public void set(int position, T value) {
        checkIndex(position);
        elements[position] = value;
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

    private void ensureCapacity() {
        if (size >= elements.length) {
            int newCapacity = elements.length * 2;
            Object[] newArray = new Object[newCapacity];

            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }

    private void checkIndex(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Indeks: " + position + ", Ukuran: " + size);
        }
    }

}