package com.notthemostcommon.creditcardpayoff.util;


import com.notthemostcommon.creditcardpayoff.Security.JWTAuthorizationFilter;
import com.notthemostcommon.creditcardpayoff.User.AppUser;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.slf4j.Logger;


public final class Utils {
    private Utils(){};

    private static final Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);


    public static Long getAuthUserId() {
        final SecurityContext ctx = SecurityContextHolder.getContext();

        if (ctx == null) {
            log.debug("No security context available");
            return 0L;
        }

        final Authentication auth = ctx.getAuthentication();
        if (auth == null) {
            log.debug("No authentication available in security context {}", ctx);
            return 0L;
        }

        final Object principal = auth.getPrincipal();
        if (!(principal instanceof AppUser)) {
            log.warn("Principal {} is not an instance of AUser!", principal);
            return 0L;
        }

        final AppUser au = (AppUser) principal;
        return au.getId();
    }
}
