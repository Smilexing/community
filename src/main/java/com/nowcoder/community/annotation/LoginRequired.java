package com.nowcoder.community.annotation;

import java.lang.annotation.*;

/**
 * @author Tom Smile
 * @version 1.0
 * @description: TODO
 * @date 2024/5/22 16:14
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

}
