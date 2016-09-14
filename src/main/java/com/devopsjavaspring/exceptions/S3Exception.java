package com.devopsjavaspring.exceptions;

/**
 * Created by stephenasamoah on 9/14/16.
 */
public class S3Exception extends RuntimeException {

    public S3Exception(Throwable e) {
        super(e);
    }

    public S3Exception(String s) {
        super(s);
    }
}
