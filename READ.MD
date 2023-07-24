# Wishlist

Descrição do Projeto

## _Projeto java com springboot para manipular a lista de desejos do cliente_

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

###### Endpoints da API

# Adicionar Item à Lista de Desejos

Método: POST
URL: POST /wishlist
Content-Type: application/json
request

curl:
```
curl --location 'http://localhost:8080/wishlist' \
--header 'Content-Type: application/json' \
--data '{
    "product": "b",
    "customer": "a"
``` 

data:
```json  
{
    "customer": "12345",
    "product": "Produto A"
}
```
Parâmetros:
customer **(obrigatório)**: O identificador do cliente.
product **(obrigatório)**: O nome ou identificador do produto a ser adicionado.

resposta:
```json  
{
"id": "33ee31b4-95af-44e7-a546-27ab6fbc80e1"
}
```

# Remover Item da Lista de Desejos

Método: DELETE
URL: DELETE /wishlist
Content-Type: application/json

curl
```json  
curl --location --request DELETE 'http://localhost:8080/wishlist' \
--header 'Content-Type: application/json' \
--data '{
    "product": "a",
    "customer": "a"
}'
```

Parâmetros:
customer **(obrigatório)**: O identificador do cliente.
product **(obrigatório)**: O nome ou identificador do produto a ser removido.

data:
```json  
{
"customer": "12345",
"product": "Produto A"
}
```

resposta:
```json  
{
"isRemoved": true
}
```
retorna true, quando existia um objeto que foi apagado, e false quando o dado a ser apagado não foi encontrado,
em ambos os casos retorna SUCCESS 200

# Verificar se um Produto Está na Lista de Desejos

Método: GET
URL: GET /wishlist/searchProductInCustomerWishlist
Parâmetros:
customer **(obrigatório)**: O identificador do cliente.
product **(obrigatório)**: O nome ou identificador do produto a ser verificado.


curl
```json  
curl --location --request GET 'http://localhost:8080/wishlist/searchProductInCustomerWishlist' \
--header 'Content-Type: application/json' \
--data '{
    "customer": "a",
    "product": "z"
}'
```

Requisição
``` 
http://localhost:8080/wishlist/searchProductInCustomerWishlist?customer=12345&product=Produto A
```

Response:
``` 
{"isProductPresent": true }
``` 
retorna:
true HTTP 200 quando encontrado e
false HTTP 404 quando não foi encontrado

# Listar Produtos da Lista de Desejos de um Cliente

Método: GET
URL: http://localhost:8080/wishlist/listProductsByCustomer/{customer}
Parâmetros:
customer (obrigatório): O identificador do cliente.

Requisção:
``` 
curl --location 'http://localhost:8080/wishlist/listProductsByCustomer/a' --data ''
``` 

Resposta:
``` 
{
"products": ["a","b","c"]
}
``` 

Leitura sugerida:

https://martinfowler.com/bliki/ValueObject.html

https://blog.cleancoder.com/uncle-bob/2011/09/30/Screaming-Architecture.html

https://www.baeldung.com/spring-boot-clean-architecture