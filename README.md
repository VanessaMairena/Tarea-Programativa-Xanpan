# HelpDesk Flow - Sistema de Gestión de Incidencias

Aplicación desarrollada para la gestión, priorización, seguimiento y control de incidencias técnicas aplicando metodologías ágiles (Kanban, XP) y asistencia de Inteligencia Artificial.

## Integrantes
* Olga Vanessa Mairena Solano
* Nazareth Villegas 

## Descripción del Sistema
HelpDesk Flow es un sistema orientado al registro de incidencias técnicas que permite automatizar el cálculo de prioridades en función del impacto y la urgencia, gestionar estados mediante un flujo estricto con restricciones, realizar consultas/filtros avanzados y administrar una clase de servicio urgente (`EXPEDITE`).

## Requisitos de Ejecución
* Java Development Kit (JDK) 17 o superior
* Entorno de desarrollo compatible (Visual Studio Code o IntelliJ IDEA)
* JUnit 5 para pruebas unitarias

## Instrucciones para Compilar y Ejecutar
1. Clona el repositorio en tu máquina local.
2. Abre la terminal en la raíz del proyecto.
3. Compila los archivos ubicados en la carpeta `src`:
   ```bash
   javac -d bin src/*.java