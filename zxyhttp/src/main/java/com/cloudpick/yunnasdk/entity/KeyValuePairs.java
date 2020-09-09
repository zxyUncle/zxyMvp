package com.cloudpick.yunnasdk.entity;


import java.io.Serializable;

public class KeyValuePairs<K, V> implements Serializable {
    K[] keys;
    V[] values;

    public KeyValuePairs(K[] k, V[] v) {
        keys = k;
        values = v;
    }

    public V getValueNoNull(K key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return values[0];
    }

    public V getValue(K key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }


    public K getKey(V value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(value)) {
                return keys[i];
            }
        }
        return null;
    }

    public int getIndexByKey(K key) {
        K[] keys = getKeys();
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexByValue(V v) {
        V[] values = getValues();
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(v)) {
                return i;
            }
        }
        return -1;
    }

    public K[] getKeys() {
        return keys;
    }

    public V[] getValues() {
        return values;
    }
}
