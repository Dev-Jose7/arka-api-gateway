# 🚪 API Gateway - Arka Platform

Este microservicio actúa como **puerta de entrada unificada** para los servicios de la plataforma Arka. Se encarga de enrutar, filtrar y gestionar todas las solicitudes externas hacia los microservicios internos, implementando patrones de arquitectura de gateway y seguridad.

Está construido con **Spring Cloud Gateway WebFlux** y forma parte del ecosistema de microservicios distribuidos de Arka.

---

## 🆕 Cambios en la versión v1.0.0

- 🔐 Se implementó un sistema de autenticación reactivo basado en **JWT**.
- 🌐 La configuración del gateway se externalizó completamente usando **Spring Cloud Config Server**.
- 🎯 Se habilitó el **rate limiting por usuario e IP** utilizando **Redis** como almacén de control.
- 🧼 Limpieza de propiedades locales en `application.yml` en favor de la configuración centralizada.
- ✅ Proyecto considerado **estable para entornos de staging o producción supervisada**.

---

## ⚙️ Configuración centralizada

El API Gateway obtiene su configuración desde el **Spring Cloud Config Server**, permitiendo una gestión remota, centralizada y versionada de las propiedades de entorno, incluyendo:

- Rutas
- Seguridad
- Eureka
- Redis
- Logging

---

## 🧭 Descubrimiento de servicios

El Gateway se registra automáticamente en el **Eureka Server**, lo que le permite descubrir dinámicamente otros microservicios del ecosistema y enrutar las solicitudes adecuadamente.

---

## 🔐 Seguridad

El API Gateway implementa un sistema de autenticación reactiva con **JWT** usando Spring Security WebFlux:

- Validación del token JWT en cada solicitud entrante.
- Extracción del usuario autenticado para usar en filtros personalizados.
- Base para futuras extensiones como autorización por rol o scopes.

---

## 📈 Rate Limiting

Se implementó **limitación de solicitudes** (rate limiting) utilizando:

- 📦 `Redis` como backend reactivo para almacenamiento de tokens de consumo.
- 🔑 `JwtKeyResolver` para limitar peticiones por usuario autenticado.
- 🌍 `IpKeyResolver` para limitar por dirección IP.

Esto permite proteger los microservicios de abusos o sobrecarga intencional.

---

## 📦 Dependencias principales del proyecto

Este proyecto usa las siguientes tecnologías clave:

| Propósito                     | Dependencia técnica                                                  |
|------------------------------|----------------------------------------------------------------------|
| Gateway WebFlux              | `spring-cloud-starter-gateway-server-webflux`                        |
| Descubrimiento de servicios  | `spring-cloud-starter-netflix-eureka-client`                         |
| Configuración centralizada   | `spring-cloud-starter-config`                                       |
| Seguridad reactiva           | `spring-boot-starter-security`                                      |
| JWT                          | `com.auth0:java-jwt`                                                 |
| Rate limiting con Redis      | `spring-boot-starter-data-redis-reactive`                            |
| Compatibilidad Mac (M1/M2)   | `io.netty:netty-resolver-dns-native-macos`                           |
| Anotaciones y boilerplate    | `org.projectlombok:lombok`                                           |
| Testing                      | `spring-boot-starter-test`, `junit-platform-launcher`                |

---

## 🧪 Estado del proyecto

Este microservicio ha alcanzado un estado **estable** con configuración centralizada, autenticación JWT y control de tráfico mediante Redis. Es adecuado para su uso en entornos de **staging** y puede considerarse para producción con monitoreo.

📌 **Versión actual:** `v1.0.0-STABLE`
