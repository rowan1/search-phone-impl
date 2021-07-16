package com.exercise.impl.dto;

import java.util.function.Function;

public class NullCheck<T> {
    private T root;

    public NullCheck(T root) {
        this.root = root;
    }

    public <C> NullCheck<C> with(Function<T, C> getter) {
        return root != null ? new NullCheck<>(getter.apply(root)) : new NullCheck<>(null);
    }

    public boolean isNull() {
        return root == null;
    }

    public boolean isNotNull() {
        return root != null;
    }
}