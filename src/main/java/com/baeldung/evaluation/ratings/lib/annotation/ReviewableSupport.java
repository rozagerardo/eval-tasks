package com.baeldung.evaluation.ratings.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.baeldung.evaluation.ratings.lib.config.RatingsConfig;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RatingsConfig.class)
public @interface ReviewableSupport {
}
