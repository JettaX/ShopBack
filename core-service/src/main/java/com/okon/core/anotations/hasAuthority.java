package com.okon.core.anotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface hasAuthority {
    Authorities value() default Authorities.GUEST;
}
