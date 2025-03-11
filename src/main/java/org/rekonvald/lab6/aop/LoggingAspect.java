package org.rekonvald.lab6.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.rekonvald.lab6.entity.Order;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before(value = "Pointcuts.orderControllerMethodsWithOrder(order)", argNames = "order")
    public void logArgumentsBeforeExecutionWithOrder(Order order) {
        if (order != null) {
            logger.info("Method called with Order: {}", order.toString());
        } else {
            logger.info("Method called with null order.");
        }
    }

    @Before(value = "Pointcuts.orderControllerMethodsWithId(id)", argNames = "id")
    public void logArgumentsBeforeExecutionWithId(Long id) {
        if (id != null) {
            logger.info("Method called with Order ID: {}", id);
        } else {
            logger.info("Method called with null ID.");
        }
    }

    @Before(value = "Pointcuts.editOrderMethod(id, order)", argNames = "id, order")
    public void logEditOrderMethod(Long id, Order order) {
        if (id != null) {
            logger.info("editOrder method called with ID: {}", id);
        } else {
            logger.info("editOrder method called with null ID.");
        }

        if (order != null) {
            logger.info("editOrder method called with Order: {}", order.toString());
        } else {
            logger.info("editOrder method called with null Order.");
        }
    }
}
