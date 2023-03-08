package com.okon.core.anotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AuthorityAspect {
    //get annotation value

    @Around("@annotation(hasAuthority)")
    public Object hasAuthority(ProceedingJoinPoint joinPoint, hasAuthority hasAuthority) throws Throwable {
        Authorities authority = hasAuthority.value();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof String) {
                String roles = (String) arg;
                if (roles.contains(authority.getValue())) {
                    return joinPoint.proceed();
                }
            }
        }
        log.warn("User has no authority to perform this action");
        return null;
    }
}
