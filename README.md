# jQuery Datepicker Test Automation

**Automatización de pruebas para el componente jQuery Datepicker utilizando Serenity BDD, Cucumber y Screenplay Pattern.**

[![Java](https://img.shields.io/badge/Java-11%2B-orange.svg)](https://openjdk.java.net/projects/jdk/11/)
[![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue.svg)](https://maven.apache.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.3.0-green.svg)](https://selenium.dev/)
[![Serenity](https://img.shields.io/badge/Serenity-3.3.0-purple.svg)](https://serenity-bdd.info/)

---

##  Objetivo

Automatizar tres escenarios de prueba del jQuery UI Datepicker:
1. Selección de fecha en el mes actual
2. Selección de fecha en el próximo mes  
3. Validación de entrada manual

## Arquitectura

El proyecto sigue el patrón **Screenplay** de Serenity BDD, que proporciona:
- **Mejor legibilidad**: Los tests se leen como historias de usuario
- **Alta mantenibilidad**: Separación clara de responsabilidades
- **Reutilización**: Componentes altamente reutilizables

### Estructura del Proyecto

jquery-datepicker-automation/
|-- src/
|   |-- test/
|   |   |-- java/
|   |   |   |-- com/
|   |   |   |   |-- sqa/
|   |   |   |   |   |-- datepicker/
|   |   |   |   |   |   |-- runners/           # Test Runners
|   |   |   |   |   |   |-- stepdefinitions/   # Cucumber 
|   |   |   |   |   |   |-- tasks/             # alto nivel
|   |   |   |   |   |   |-- interactions/      # reutilizables
|   |   |   |   |   |   |-- questions/         # Preguntas / Validaciones
|   |   |   |   |   |   |-- ui/                # Page Objects / Targets
|   |   |-- resources/
|   |   |   |-- features/                      # Archivos .feature de Cucumber
|   |   |   |-- serenity.properties            # Configuración Serenity
|-- pom.xml                                    # Configuración Maven
|-- README.md



##  Tecnologías

- **Java 11**
- **Serenity BDD 3.3.0** - Framework de automatización y reporting
- **Cucumber 7.14.0** - BDD framework  
- **Screenplay Pattern** - Patrón de diseño para tests mantenibles
- **Maven** - Gestión de dependencias y build
- **Selenium WebDriver** - Automatización web
- **WebDriverManager** - Gestión automática de drivers

## Prerrequisitos

- Java JDK 11 o superior
- Maven 3.6 o superior
- Google Chrome (última versión)
- ChromeDriver (se descarga automáticamente)

## Instalación

1. Clonar o descargar el proyecto
2. Navegar al directorio del proyecto:
cd jquery-datepicker-automation_20251005_104754

text

3. Compilar el proyecto:
mvn clean compile

text

## Ejecución

### Ejecutar todos los tests:
mvn clean verify

text

### Ejecutar con interfaz gráfica:
mvn clean verify -Dheadless.mode=false

text

### Ejecutar tests por tags:
Solo escenario 1
mvn clean verify -Dcucumber.filter.tags="@scenario1"

Solo escenario 2
mvn clean verify -Dcucumber.filter.tags="@scenario2"

Solo escenario 3
mvn clean verify -Dcucumber.filter.tags="@scenario3"

text

### Modo headless:
mvn clean verify -Dheadless.mode=true

text

## Reportes

Después de la ejecución, Serenity genera reportes detallados:

### Ver los reportes:
El reporte se genera automáticamente en:
target/site/serenity/index.html

Abrir en el navegador (Windows):
start target/site/serenity/index.html

text

Los reportes incluyen:
- Resultados de cada escenario
- Screenshots en caso de fallos
- Pasos detallados de cada test
- Estadísticas y métricas
- Timeline de ejecución

## 🧪 Escenarios de Prueba

### Escenario 1: Reserva de una cita seleccionando una fecha en el calendario
- Abre la página del jQuery Datepicker
- Cambia al iframe del calendario
- Hace clic en el campo de fecha
- Selecciona el día 15 del mes actual
- Valida que la fecha aparece correctamente

### Escenario 2: Selección de una fecha específica en un mes diferente
- Abre la página del jQuery Datepicker
- Cambia al iframe del calendario
- Hace clic en el campo de fecha  
- Navega al próximo mes
- Selecciona el día 10
- Valida que la fecha aparece correctamente

### Escenario 3: Validación de campo bloqueado
- Abre la página del jQuery Datepicker
- Intenta ingresar una fecha manualmente
- Valida que el calendario es la forma principal de selección

## Patrón Screenplay

El proyecto implementa el patrón Screenplay con los siguientes componentes:

### Actors (Actores)
Representan a los usuarios que interactúan con el sistema.

### Tasks (Tareas)
Acciones de alto nivel que un actor puede realizar:
- `NavigateToDatepicker.page()`
- `SelectDateFromCalendar.day()`
- `AttemptManualDateInput.withText()`

### Interactions (Interacciones) 
Acciones de bajo nivel reutilizables:
- `SwitchToDatepickerFrame.inside()`
- `WaitForCalendar.toBeVisible()`

### Questions (Preguntas)
Consultas sobre el estado del sistema:
- `SelectedDate.value()`
- `FieldEditability.ofDateField()`

### Targets (Objetivos)
Elementos de la UI (Page Objects):
- `DatepickerPage.DATE_INPUT_FIELD`
- `DatepickerPage.CALENDAR_WIDGET`

## Buenas Prácticas Implementadas

**Patrón Screenplay**: Código legible y mantenible  
**Separación de responsabilidades**: Cada clase tiene un propósito único  
**DRY (Don't Repeat Yourself)**: Componentes reutilizables  
**Nomenclatura clara**: Nombres descriptivos en español e inglés  
**Manejo de iframes**: Cambio de contexto adecuado  
**Esperas explícitas**: No uso de Thread.sleep excepto donde es necesario  
**Page Objects**: Localizadores centralizados  
**Reporting detallado**: Información completa en reportes Serenity  
**Configuración externalizada**: serenity.properties para configuraciones  
**Tags de Cucumber**: Organización y ejecución selectiva

## Configuración

### serenity.properties
Contiene la configuración de Serenity y WebDriver:
- Configuración del proyecto
- Screenshots automáticos
- Configuración del navegador
- Configuración de reportes

## Resultados Esperados

### Ejecución Exitosa:
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
[INFO] Total time: 45.230 s

text

### Artefactos Generados:
-  **Reporte HTML** con navegación interactiva
-  **Screenshots** automáticos de cada paso
-  **Logs detallados** de ejecución
-  **Métricas** de tiempo y rendimiento

### Problemas Comunes:

** ChromeDriver version mismatch:**
Solución: Limpiar cache de WebDriverManager
rm -rf ~/.cache/selenium
mvn clean verify

text

** Elemento no encontrado:**
Solución: Ejecutar en modo no-headless para debug
mvn verify -Dheadless.mode=false

text

** Timeout en carga de página:**
Solución: Aumentar timeouts en serenity.properties
serenity.timeout=30000

text

## Contribución

Requerimiento:
 Serenity BDD = configurado     
 Cucumber =  escenarios implementados    
 Screenplay Pattern = Tasks, Interactions, Questions
 Buenas prácticas = Implementadas y documentadas  
---------------------------------------------=-
Funcionalidad = Escenarios funcionando 
Manejo iframe = Implementado para jQuery 
Screenshots = Configurado y funcionando
Reportes = Serenity completo        



**Proyecto:** jQuery Datepicker Test Automation  
**Desarrollado por:** Diana Alexandra Dávila
**Desarrollado para:** Reto Técnico SQA  
**Patrón:** Screenplay + Serenity BDD  
**Año:** 2025

---

** Proyecto desarrollado siguiendo las mejores prácticas de automatización de pruebas y BDD.**

