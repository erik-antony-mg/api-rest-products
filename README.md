# API-REST-PRODUCTS
# #Spring Boot 3, MySQL, JPA, Rest API, Swagger, Maven

Create Restful CRUD API as a practice using Spring Boot, Mysql, JPA and Hibernate.

## Steps to Setup

**1. Clone the application**

```bash
 git clone https://github.com/erik-antony-mg/api-rest-products.git
```

**2. Create Mysql database**
```bash
create database: db_products
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**

```bash
create a folder on drive c called images
```
- The app will start running at <http://localhost:8085>
- swagger will start running at <http://localhost:8085/doc/swagger-ui/index.html>

### EndPoints

| Method | Url | Description
| ------ | --- | ------|
| GET    | /api/products | Get all products
| GET    |  /api/products{idProduct} | find by product id
| POST    | /api/products/create | Create product
| PUT    | /api/products/edit/{idProduct} | Edit product
| DELETE   | /api/products/delete/{idProduct} |  Delete Product
| GET    | /api/products/images/{idProduct}  | get product image by id
| POST   |  /api/products/images/create/{idProduct} | Save product image
