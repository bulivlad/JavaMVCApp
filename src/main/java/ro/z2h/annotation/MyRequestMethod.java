package ro.z2h.annotation;

import javax.lang.model.element.Element;
import java.lang.annotation.*;

/**
 * Created by Buli on 11/11/2014.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyRequestMethod {

    String methodType() default "GET";
    String urlPath();
}
