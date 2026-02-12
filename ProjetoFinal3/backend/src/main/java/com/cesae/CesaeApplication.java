package com.cesae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe Principal - Ponto de Entrada da Aplicação Spring Boot
 * 
 * Sistema de Gestão Académica do CESAE Digital.
 * 
 * @author Mário Amorim
 * @version 2.0
 */
@SpringBootApplication
public class CesaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CesaeApplication.class, args);
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     CESAE DIGITAL - GESTÃO ACADÉMICA         ║");
        System.out.println("║          API REST - Spring Boot              ║");
        System.out.println("║     http://localhost:8080/api                ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
