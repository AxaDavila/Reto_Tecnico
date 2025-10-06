#language: es
Característica: Automatización del componente jQuery Datepicker
  Como usuario del sistema
  Quiero interactuar con el componente jQuery Datepicker
  Para seleccionar fechas de manera efectiva

  Antecedentes:
    Dado que el usuario navega a la página principal de jQuery Datepicker

  @scenario1
  Escenario: Reserva de una cita seleccionando una fecha en el calendario
    Cuando el usuario hace clic en el campo de selección de fecha
    Y selecciona el día 15 del mes actual
    Entonces la fecha seleccionada debe aparecer en el campo de texto

  @scenario2  
  Escenario: Selección de una fecha específica en un mes diferente
    Cuando el usuario hace clic en el campo de selección de fecha
    Y navega hasta el próximo mes
    Y selecciona el día 10 del próximo mes
    Entonces la fecha seleccionada debe aparecer en el campo de texto

  @scenario3
  Escenario: Validación de campo bloqueado
    Cuando el usuario intenta ingresar una fecha manualmente en el campo de texto
    Entonces el campo no debe permitir la edición manual
    Y solo debe ser posible seleccionar una fecha desde el calendario