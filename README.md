# 🚪 API Gateway - Arka Platform

Este microservicio actúa como **puerta de entrada unificada** para los servicios de la plataforma Arka. Se encarga de enrutar, filtrar y gestionar todas las solicitudes externas hacia los microservicios internos, implementando patrones de arquitectura de gateway y seguridad.

Está construido con **Spring Cloud Gateway** y forma parte del ecosistema de microservicios distribuidos de Arka.

---

## ⚙️ Configuración

El API Gateway obtiene su configuración de forma remota desde el **Config Server** de la plataforma, permitiendo una gestión centralizada y versionada de las propiedades.

## 🧭 Descubrimiento de servicios

El gateway se registra automáticamente en el Eureka Server, lo que le permite descubrir dinámicamente otros microservicios del ecosistema y enrutar las solicitudes correctamente.

## 🚀 Características principales

🔗 Enrutamiento dinámico hacia microservicios registrados en Eureka.
⚙️ Carga de configuración externa vía Spring Cloud Config.
🔒 Preparado para implementar filtros de seguridad y autenticación.
🧱 Base para la futura integración con control de acceso y balanceo de carga.

🧪 Estado del proyecto
Este microservicio se encuentra en una fase inicial de desarrollo. Aún pueden realizarse cambios importantes en la arquitectura y configuración.

📌 Versión actual: v0.1.0