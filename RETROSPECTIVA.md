# Retrospectiva del Proyecto - HelpDesk Flow

### 1. ¿Qué aportó Kanban al trabajo de la pareja?
El uso del tablero Kanban permitió visualizar de forma transparente el estado real de cada tarea, evitando la acumulación de trabajo y facilitando la distribución equitativa de responsabilidades entre los integrantes del equipo. Gracias al flujo visual, pudimos identificar rápidamente cuellos de botella y priorizar la finalización de tareas pendientes antes de iniciar nuevas historias de usuario.

### 2. ¿Qué dificultad generó el límite WIP?
Los límites de trabajo en progreso (WIP), especialmente en las columnas de desarrollo y validación, nos obligaron a frenar la tentación de abrir múltiples tareas en paralelo. Al principio generó cierta resistencia operativa, pero nos enseñó la valiosa disciplina de terminar lo que se empieza y colaborar estrechamente para desbloquear al compañero cuando una tarjeta se detenía en validación.

### 3. ¿Qué errores fueron detectados mediante TDD?
La aplicación de Desarrollo Guiado por Pruebas (TDD) nos permitió detectar errores lógicos relacionados con el cálculo incorrecto de prioridades ante combinaciones atípicas de impacto y urgencia. Escribir las pruebas primero (*RED*) dejó al descubierto fallos en los valores por defecto y en los validadores de caracteres antes de escribir el código de producción.

### 4. ¿Qué parte del código fue refactorizada?
Refactorizamos el bloque de validación de transiciones de estados dentro del sistema. Inicialmente, las condiciones se encontraban dispersas y acopladas dentro de los métodos principales. La refactorización consistió en extraer esta lógica hacia un validador independiente, mejorando la legibilidad del código y reduciendo la complejidad ciclomática sin alterar el comportamiento previamente probado.

### 5. ¿Cómo afectó el cambio de requerimiento?
La incorporación de la clase de servicio `EXPEDITE` puso a prueba la flexibilidad de nuestra arquitectura. Al haber construido un diseño modular y protegido por pruebas automatizadas anteriores, pudimos integrar la nueva regla de negocio (permitir una única incidencia urgente en curso simultáneamente) sin romper ninguna funcionalidad preexistente ni alterar las pruebas anteriores.

### 6. ¿En qué ayudó la IA?
La inteligencia artificial funcionó como un valioso asistente técnico para resolver dudas puntuales de sintaxis en Java, estructurar borradores iniciales de criterios de aceptación y comprender rápidamente la causa raíz de errores de compilación en el entorno de desarrollo.

### 7. ¿En qué se equivocó o fue insuficiente la IA?
En ocasiones, la IA tendía a proponer soluciones sobrediseñadas o patrones arquitectónicos complejos que no se ajustaban a la simplicidad requerida por la rúbrica de una aplicación de consola. Fue necesario aplicar un filtro crítico para rechazar propuestas excesivas y mantener el código simple y mantenible.

### 8. ¿Qué cambiarían en una siguiente versión?
En una versión futura, nos gustaría incorporar una interfaz gráfica de usuario más robusta (como JavaFX o una aplicación web ligera) y conectar el sistema a una base de datos relacional persistente (como PostgreSQL o MySQL) en lugar del almacenamiento en memoria actual, mejorando aún más la escalabilidad del producto.