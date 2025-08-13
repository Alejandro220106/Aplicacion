Gestor de Tareas en Java

Aplicación de escritorio desarrollada en Java que permite gestionar tareas personales. Utiliza Swing para la interfaz gráfica, SQL Server para la persistencia de datos y sigue una arquitectura modular basada en Programación Orientada a Objetos (POO).

---

Características

- Crear tareas con título, prioridad, fecha opcional y marca especial (★)
- Listar tareas en una tabla ordenada
- Alternar estado entre Hecho/Pendiente
- Eliminar tareas (sin borrarlas de la base de datos)
- Deshacer la última eliminación usando una pila
- Validación de datos y manejo de excepciones
- Persistencia con JDBC y SQL Server
- Arquitectura modular con capas: `app`, `uiux`, `servicio`, `dao`, `dominio`
- Control de versiones con Git

---

Estructura del Proyecto
GestorTareas/ ├── app/ ├── uiux/ ├── servicio/ ├── dao/ ├── dominio/ └── README.md

---
Autor: Luis Alejandro Arce Araya

