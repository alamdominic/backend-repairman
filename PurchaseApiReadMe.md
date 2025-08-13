# 📱 Purchases API - Backend Repairman

API REST para la gestión de compras de teléfonos móviles en el sistema Repairman.

## 📋 Descripción

Este módulo maneja las operaciones CRUD (Create, Read, Update, Delete) para las compras de dispositivos móviles realizadas por los clientes del sistema de reparación.

## 🚀 Endpoints Disponibles

### **Base URL**
```
/api/v1/repairman/purchases
```

### **📋 CRUD Completo**

| Operación | HTTP Method | Endpoint | Descripción |
|-----------|-------------|----------|-------------|
| **CREATE** | `POST` | `/api/v1/repairman/purchases` | Crear nueva compra |
| **READ** | `GET` | `/api/v1/repairman/purchases` | Obtener todas las compras |
| **READ** | `GET` | `/api/v1/repairman/purchases/{id}` | Obtener compra específica |
| **READ** | `GET` | `/api/v1/repairman/purchases/customer/{customerId}` | Obtener compras de un cliente |
| **UPDATE** | `PUT` | `/api/v1/repairman/purchases/{id}` | Actualizar compra existente |
| **DELETE** | `DELETE` | `/api/v1/repairman/purchases/{id}` | Eliminar compra |

## 📝 Modelo de Datos

### **PurchaseModel**
```json
{
    "purchaseID": "Long (auto-generado)",
    "brand": "String (requerido)",
    "phoneStatus": "String (requerido)", 
    "description": "String (requerido)",
    "price": "Double (requerido)",
    "createdAt": "LocalDate (requerido)",
    "customer": "CustomerModel (relación ManyToOne)"
}
```

## 🧪 Ejemplos de Uso

### **1. CREATE - Crear Nueva Compra**
```bash
POST /api/v1/repairman/purchases
Content-Type: application/json

{
    "brand": "iPhone",
    "phoneStatus": "Usado",
    "description": "Pantalla rota",
    "price": 200.0,
    "createdAt": "2024-01-15"
}
```

**Respuesta exitosa (201 Created):**
```json
{
    "purchaseID": 1,
    "brand": "iPhone",
    "phoneStatus": "Usado",
    "description": "Pantalla rota",
    "price": 200.0,
    "createdAt": "2024-01-15",
    "customer": null
}
```

### **2. READ - Obtener Todas las Compras**
```bash
GET /api/v1/repairman/purchases
```

**Respuesta exitosa (200 OK):**
```json
[
    {
        "purchaseID": 1,
        "brand": "iPhone",
        "phoneStatus": "Usado",
        "description": "Pantalla rota",
        "price": 200.0,
        "createdAt": "2024-01-15",
        "customer": {...}
    },
    {
        "purchaseID": 2,
        "brand": "Samsung",
        "phoneStatus": "Nuevo",
        "description": "Galaxy S21",
        "price": 800.0,
        "createdAt": "2024-01-16",
        "customer": {...}
    }
]
```

### **3. READ - Obtener Compra Específica**
```bash
GET /api/v1/repairman/purchases/1
```

**Respuesta exitosa (200 OK):**
```json
{
    "purchaseID": 1,
    "brand": "iPhone",
    "phoneStatus": "Usado",
    "description": "Pantalla rota",
    "price": 200.0,
    "createdAt": "2024-01-15",
    "customer": {...}
}
```

### **4. READ - Obtener Compras de un Cliente**
```bash
GET /api/v1/repairman/purchases/customer/5
```

**Respuesta exitosa (200 OK):**
```json
[
    {
        "purchaseID": 3,
        "brand": "Huawei",
        "phoneStatus": "Nuevo",
        "description": "P40 Pro",
        "price": 600.0,
        "createdAt": "2024-01-17",
        "customer": {...}
    }
]
```

### **5. UPDATE - Actualizar Compra Existente**
```bash
PUT /api/v1/repairman/purchases/1
Content-Type: application/json

{
    "brand": "Samsung",
    "phoneStatus": "Nuevo",
    "description": "En perfecto estado",
    "price": 300.0,
    "createdAt": "2024-01-15"
}
```

**Respuesta exitosa (200 OK):**
```json
{
    "purchaseID": 1,
    "brand": "Samsung",
    "phoneStatus": "Nuevo",
    "description": "En perfecto estado",
    "price": 300.0,
    "createdAt": "2024-01-15",
    "customer": {...}
}
```

### **6. DELETE - Eliminar Compra**
```bash
DELETE /api/v1/repairman/purchases/1
```

**Respuesta exitosa (200 OK):**
```json
{
    "message": "Compra eliminada exitosamente"
}
```

## ⚠️ Respuestas de Error

### **404 Not Found**
```json
{
    "error": "No se encontraron compras para el cliente con ID: 5"
}
```

### **404 Not Found - Compra específica**
```json
{
    "error": "No se encontró la compra con ID: 999"
}
```

### **200 OK - Lista vacía**
```json
"No hay compras registradas"
```

## 🗂️ Estructura del Proyecto

```
src/main/java/com/repairman/repairman/
├── controller/
│   └── PurchaseController.java
├── service/
│   └── PurchaseService.java
├── repository/
│   └── PurchaseRepository.java
├── model/
│   ├── PurchaseModel.java
│   └── CustomerModel.java
└── exceptions/
    └── PurchaseCustomerNotFoundException.java
```

## 🛠️ Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Jakarta Persistence API (JPA)**
- **MySQL** (Base de datos)
- **Maven** (Gestión de dependencias)

## 📊 Relaciones de Base de Datos

- **PurchaseModel** → **CustomerModel** (ManyToOne)
- Un cliente puede tener múltiples compras
- Una compra pertenece a un solo cliente

## 🚀 Cómo Usar

1. **Iniciar la aplicación Spring Boot**
2. **Usar Postman, cURL, o cualquier cliente HTTP**
3. **Realizar peticiones a la base URL:** `http://localhost:8080/api/v1/repairman/purchases`
4. **Seguir los ejemplos de uso proporcionados**

## 📋 Notas Importantes

- Todos los campos excepto `purchaseID` son requeridos al crear una compra
- El `purchaseID` se genera automáticamente
- Las fechas deben estar en formato `YYYY-MM-DD`
- Los precios deben ser valores decimales positivos
- La relación con `customer` es opcional al crear, pero recomendada

---

**Desarrollado con ❤️ para el sistema Backend Repairman**