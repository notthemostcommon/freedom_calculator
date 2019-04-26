package com.notthemostcommon.creditcardpayoff;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    String username() default "test@test.com";

    String name() default "Testy";

}
