package tech.insight;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimestampRequestBody {

    boolean required() default true;
}
