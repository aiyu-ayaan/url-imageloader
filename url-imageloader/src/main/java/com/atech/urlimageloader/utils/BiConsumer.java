package com.atech.urlimageloader.utils;

import java.util.function.Consumer;

/**
 * Represents an operation that accepts two input arguments and returns no
 * result.  This is the two-arity specialization of {@link Consumer}.
 *
 * @author aiyu
 */
@FunctionalInterface
public interface BiConsumer<T, U> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t The first input argument
     * @param u The second input argument
     */
    void accept(T t, U u);
}