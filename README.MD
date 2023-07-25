# Wishlist

Descrição do Projeto

## _Projeto com arquitetura limpa escrito com java e springboot para manipular a lista de desejos do cliente_

Funcionalidades Principais:
- Adicionar um produto na Wishlist do cliente;
- Remover um produto da Wishlist do cliente;
- Consultar todos os produtos da Wishlist do cliente;
- Consultar se um determinado produto está na Wishlist do cliente;

...
Tecnologias Utilizadas

Java 17
Spring Boot 3+
MongoDB
Testcontainers

Requisitos do Sistema

Java 17
Docker
...
###### Como executar o projeto?
é necessario possuir java17+ e docker

build:
./gradlew clean build

Avisos:
é necessario iniciar o banco de dados antes de iniciar o projeto
doker-compose up
...

----------------------------
###### Endpoints da API

# Listar
**Método**: GET
**URL**: GET /wishlist/listProductsByCustomer?customer=customer
**Content-Type**: application/json

CURL
```
curl --location 'http://localhost:8080/wishlist/listProductsByCustomer?customer=customer'
```

RESPONSE:
```
{"products":["awesome product"]}
```
quando a lista é vazia o status de retorno é **NOT_FOUND 404**
quando a lista possui itens o retorno é **SUCESSO 200**

----------------------------
# Adicionar
**Método**: GET
**URL**: GET /wishlist/listProductsByCustomer?customer=customer
**Content-Type**: application/json

CURL
```
curl --location 'http://localhost:8080/wishlist' \
--header 'asdadas: qwewqeq' \
--header 'wswss: sassa' \
--header 'Content-Type: application/json' \
--data '{
    "product": "awesome product",
    "customer": "awesome customer"
}'
```

RESPONSE:
```
{"id": "85f86c74-e2a5-4e34-9ea8-eac6a6c70b53"}
```
**SUCESSO 200**
----------------------------
# REMOVER
**Método**: DELETE
**URL**: DELETE /wishlist
**Content-Type**: application/json

CURL
```
curl --location --request DELETE 'http://localhost:8080/wishlist' \
--header 'Content-Type: application/json' \
--data '{
    "product": "a",
    "customer": "a"
}'
```

RESPONSE:
```
{ "isRemoved": true}
```
isRemoved retorna true quando um produto foi encontrado. e false quando não houve correspôndencia
**SUCESSO 200**
----------------------------
# BUSCAR
**Método**: GET
**URL**: GET /wishlist/isProductInCustomerWishlist
**Content-Type**: application/json

CURL
```
curl --location --request GET 'http://localhost:8080/wishlist/isProductInCustomerWishlist?customer=awesome%20customer&product=awesome%20product' \
--header 'Content-Type: application/json' \
--data '{
    "customer": "a",
    "product": "z"
}'
```

RESPONSE:
quando o produto está cadastrado:
**SUCESSO 200**
```
{"isProductPresent": true}
```

quando o produto não foi encontrado:
**SUCESSO 404**
```
{"isProductPresent": false}
```

-------------------------------
Leituras sugeridas:
https://martinfowler.com/bliki/ValueObject.html
https://blog.cleancoder.com/uncle-bob/2011/09/30/Screaming-Architecture.html
https://www.baeldung.com/spring-boot-clean-architecture