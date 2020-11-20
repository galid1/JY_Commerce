package com.galid.commerce.common.config.aop;

import com.galid.commerce.domains.catalog.domain.item.ItemRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("deploy | devel")
@RequiredArgsConstructor
public class ItemRegisteredEventPublishAop {
    private final ApplicationContext context;

    @AfterReturning(pointcut = "execution(* com.galid.commerce.domains.catalog.service.ItemService.saveItem(..))", returning ="returnValue")
    public void publishItemRegisteredEvent(Object returnValue) {
        ItemRegisteredEvent event = new ItemRegisteredEvent((Long)returnValue);
        context.publishEvent(event);
    }
}
