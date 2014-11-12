package ro.z2h.annotation;

import java.lang.annotation.*;

/**
 * Created by Buli on 11/11/2014.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {
    String urlPath();
}
