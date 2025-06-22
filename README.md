#  Sistema de Votación Electrónica

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![Swing](https://img.shields.io/badge/GUI-Swing-blue.svg)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Version](https://img.shields.io/badge/Version-1.0-blue.svg)]()

##  Descripción

Sistema de Votación Electrónica es una aplicación Java que permite realizar elecciones internas de manera eficiente, transparente y accesible. Diseñado para organizaciones pequeñas y medianas, universidades, empresas y comunidades que necesitan un sistema electoral confiable y fácil de usar.

##  Características Principales

- **Votación en tiempo real** con resultados instantáneos
- **Registro de candidatos** con validación automática
- **Cálculo automático** de porcentajes y estadísticas
- **Detección inteligente** de ganadores y empates
- **Interfaz gráfica moderna** e intuitiva
- **Validación robusta** de datos de entrada
- **Actualización dinámica** de resultados
- **Diseño responsivo** que se adapta a diferentes pantallas

##  Problema que Resuelve

- ❌ **Conteo manual** de votos propenso a errores
- ❌ **Resultados tardíos** que generan desconfianza
- ❌ **Procesos costosos** inaccesibles para organizaciones pequeñas
- ❌ **Falta de transparencia** en procesos electorales tradicionales

**Nuestra solución:**
- ✅ **Conteo automático** y preciso
- ✅ **Resultados instantáneos** y transparentes
- ✅ **Sistema accesible** y económico
- ✅ **Proceso confiable** y verificable

## Arquitectura

El sistema utiliza el patrón **MVC (Modelo-Vista-Controlador)** para una separación clara de responsabilidades:

```
📁 votacion_modelo/     - Lógica de negocio
├── SistemaVotacion.java
└── Candidato.java

📁 votacion_vista/      - Interfaz de usuario
├── VentanaPrincipal.java
├── PanelRegistroCandidatos.java
└── PanelVotacion.java

📁 votacion_controlador/ - Control de flujo
└── ControladorVotacion.java

📁 votacion_util/       - Utilidades
└── Utilidades.java

📁 votacion_excepciones/ - Manejo de errores
└── VotacionException.java
```

## Instalación

### Requisitos Previos

- **Java 11 o superior**
- **Sistema operativo**: Windows, macOS, Linux

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/wilito48/PROYECTO_POO_FINAL.git
   cd PROYECTO_POO_FINAL
   ```

2. **Compilar el proyecto**
   -abrir el eclipse
   -open folder
   -seleccionar la carpeta PROYECTO_POO_FINAL



## 📖 Manual de Usuario

### 1. Registro de Candidatos

1. Abrir la pestaña **"Registro de Candidatos"**
2. Completar los campos:
   - **Número**: Identificador único del candidato
   - **Nombre**: Nombre completo del candidato
   - **Partido**: Partido político o agrupación
3. Hacer clic en **"Registrar Candidato"**
4. El candidato aparecerá en la lista inferior

### 2. Sistema de Votación

1. Ir a la pestaña **"Sistema de Votación"**
2. Hacer clic en **"Iniciar Votación"**
3. Los votantes ingresan el número del candidato
4. Hacer clic en **"Votar"** o presionar **Enter**
5. Los resultados se actualizan en tiempo real
6. Hacer clic en **"Finalizar Votación"** para terminar

### 3. Resultados

- **En tiempo real**: Se muestran durante la votación
- **Finales**: Se determinan al finalizar la votación
- **Estadísticas**: Porcentajes calculados automáticamente
- **Empates**: Detectados automáticamente

## Tecnologías Utilizadas

- **Java 11+** - Lenguaje de programación principal
- **Swing** - Framework de interfaz gráfica
- **MVC** - Patrón de arquitectura
- **Java Modules** - Organización modular del código
- **Git** - Control de versiones

## Funcionalidades Técnicas

### Validaciones Implementadas
- Campos obligatorios completos
- Números de candidato únicos
- Solo números en campos numéricos
- Candidatos existentes para votar
- Estado activo de votación

### Cálculos Automáticos
- Porcentajes de votos por candidato
- Determinación del ganador
- Detección de empates
- Estadísticas en tiempo real
- Reportes finales

## Casos de Uso

### Organizaciones Educativas
- Elecciones estudiantiles
- Votaciones de juntas directivas
- Encuestas y consultas

### Empresas
- Elecciones sindicales
- Votaciones de comités
- Decisiones organizacionales

### Comunidades
- Asociaciones vecinales
- Clubes deportivos
- Organizaciones comunitarias

## Métricas del Proyecto

- **Líneas de código**: ~1,500
- **Clases**: 8 principales
- **Métodos**: ~50 implementados
- **Casos de uso**: 16 cubiertos
- **Documentación**: 100% completa

## Desarrollo

### Estructura del Proyecto
```
votacion_sistema/
├── src/                    # Código fuente
│   ├── votacion/
│   ├── votacion_modelo/
│   ├── votacion_vista/
│   ├── votacion_controlador/
│   ├── votacion_util/
│   ├── votacion_excepciones/
│   └── img/               # Recursos gráficos
├── bin/                   # Archivos compilados
├── .classpath            # Configuración Eclipse
├── .project              # Configuración proyecto
└── README.md             # Este archivo
```


##  Próximas Mejoras

### Funcionalidades Planificadas
- **Autenticación de usuarios**
- **Base de datos para persistencia**
- **Exportación de reportes a PDF/Excel**
- **Gráficos interactivos de resultados**
- **Tema oscuro opcional**



## Contribución

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request


## 👥 Autores

- **Kervyn, Percy, Jefferson** - *Sistema de votación* - [wilito48](https://github.com/wilito48)

## Agradecimientos

- Comunidad Java por las herramientas y documentación
- Profesores y mentores que guiaron el desarrollo
- Usuarios beta que probaron el sistema

## Contacto

- **Email**: willykervyn@gmail.com, percy@gmail.com, jefferson@gmail.com
- **GitHub**: [@wilito48, aronplay123, jeffersonquizado](https://github.com/wilito48)(https://github.com/aronplay123)(https://github.com/jeffersonguizado)

## Estado del Proyecto

![Estado](https://img.shields.io/badge/Estado-Completado-brightgreen)
![Versión](https://img.shields.io/badge/Versión-1.0-blue)

---

**Si este proyecto te ha sido útil, ¡dale una estrella en GitHub!**

**🗳️ ¡Democratizando la tecnología electoral, una votación a la vez!** 