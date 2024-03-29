package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.JavaExpression;

@Target({ ElementType.FIELD })
public @interface HasSubsequence {

    @JavaExpression
    String subsequence();

    @JavaExpression
    String from();

    @JavaExpression
    String to();
}
