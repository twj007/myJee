package com.common.annotation;

import java.lang.annotation.*;

/**
 * @author info
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatScan {
    boolean scan() default true;
}