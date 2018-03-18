package io.reactivex;

/**
 * Created by Wesley Gomes on 18/03/2018.
 */

public enum BackpressureStrategy {
    MISSING,
    ERROR,
    BUFFER,
    DROP,
    LATEST
}
