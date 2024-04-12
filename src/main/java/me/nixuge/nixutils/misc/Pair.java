package me.nixuge.nixutils.misc;

import java.util.AbstractMap;

// Why this class:
// - Because typing "AbstractMap.SimpleImmutableEntry" every time is too long & confusing.
// that is all.
public class Pair<T1, T2> extends AbstractMap.SimpleImmutableEntry<T1, T2> {
    public Pair(T1 key, T2 value) {
        super(key, value);
    }
}
