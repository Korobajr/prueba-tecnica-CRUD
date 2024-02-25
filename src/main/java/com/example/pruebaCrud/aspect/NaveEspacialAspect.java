package com.example.pruebaCrud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
public class NaveEspacialAspect {
    private final Logger logger = LoggerFactory.getLogger(NaveEspacialAspect.class);
    @AfterThrowing(pointcut = "execution(* com.example.pruebaCrud.NaveEspacialService.obtenerNavePorId(..)) && args(id)",
            throwing = "ex")
    public void logIdNegativo(JoinPoint joinPoint, IllegalArgumentException ex, Long id) {
        if (id < 0) {
            logger.error("Intento de obtener una nave con ID negativo: {}", id);
        }
    }
}
