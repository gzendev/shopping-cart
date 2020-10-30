# shopping-cart

Software de carritos de compras desarrollado como challenge para Garbarino. Las tecnologías usadas son: Spring Boot, PostgreSQL, JPA, y Hibernate.

## Modulos

1. Rest API´s <shopping-cart-ws>

2. Cart Task <cart-process-task>

3. Task Scheduler <cart-process-ws>
  
## Requerimientos

1. Java - 1.8.x +

2. Maven - 3.x.x +

3. PostgreSQL 9.x +

## Pasos para Configurar

**1. Clonar la Aplicación

```bash
git clone https://github.com/gzendev/shopping-cart.git
```

**2. Crear PostgreSQL database

```bash
run create.sql
```

**3. Cambiar PostgreSQL username y password de acuerdo a tu instalación y entorno

+ open `src/main/resources/application-*.yml`

+ change `spring.datasource.username` , `spring.datasource.password` 

**4. Construir y correr la app usando Maven 

```bash
mvn package
java -jar shopping-cart-ws/target/shopping-cart-ws.jar

Also Deploy cart-process-ws/target/cart-process-ws.war
```
Alternativamente, también podés correr las apps usando,

```bash
mvn spring-boot:run
```

La apps comenzaran a correr en <http://localhost:8080>


## Explorar Rest APIs

La app define los siguientes EndPoints

	- POST /carts
		  Ej. BODY: {"firstname": "Pedro", "lastname": "Gomez", "email": "pedro.gomez@gmail.com"}
		 	   
	- POST /carts/{id}/products
			  Ej. BODY: {"id": 5, "quantity": 10}
			  
	- DELETE /carts/{cartId}/products/{productId}
           (Elimina el producto con productId del carrito con cartId)
			  
	- GET /carts/{id}/products
           (Consigue todos los productos del carrito con id)
  
	- GET /carts/{id}
	   (Consigue la información del carrito con id y sus productos)
  
        - POST /carts/{id}/checkout
           (Cambia el estado del carrito a READY)
	   
			  
## Comentarios
Se ha empleado Spring Security para segurizar los diferentes request al sistema, por default permiteAll. Y a través de Spring Scheduler se programó una tarea que correrá cada 10' para procesar los carritos segun un determinada lógica.

En entornos productivos de alta demanda es posible implementar un balanceo de la carga de los request distribuidos en diferentes nodos de un cluster. De esta manera se obtiene un alto rendimiento, escalabilidad, y mayor disponibilidad en los servicios bajo un entorno empresarial.

