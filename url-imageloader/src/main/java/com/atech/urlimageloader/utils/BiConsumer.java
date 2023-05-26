package com.atech.urlimageloader.utils;

@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}