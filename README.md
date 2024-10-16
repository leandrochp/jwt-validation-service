# jwt-validation-service

**jwt-validation-service** é um microsserviço construído em Kotlin e Spring essencialmente para validar token (JWT) por meio de uma API REST.

### Como executar

Clone o repositório, vá até o diretório raiz do projeto e execute o seguinte comando:
```bash
./gradlew bootRun
```
Outra alternativa é executar o docker-compose

Verifique se você tem instalado o [`docker-compose`](https://docs.docker.com/compose/gettingstarted/) em seu computador.
- No diretório raiz do projeto, execute o seguinte comando:
```bash
docker-compose up --build
```

### Como usar

Você pode usar uma ferramenta como [`postman`](https://www.postman.com/) ou curl para fazer as solicitações. Aqui estão os exemplos curls para os endpoints existentes, 
que você pode importar no software de sua preferência.

```bash
curl --location 'http://localhost:8080/validate' \
--header 'Content-Type: application/json' \
--data '{
    "token" :"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJMZWFuZHJvIFBlcmVpcmEifQ.aaX-OGChXQTLjgbKhn25YDm9sSdJjxiQOgOHjOjl6h4"
}
'
```
```bash
curl --location 'http://localhost:8080/actuator/health'
```
Você ainda pode acessar a documentação do swagger em um navegador com o endpoint:
```
http://localhost:8080/docs
```

### Testes
- Testes unitários para regras de negócio e
- Testes de componente para o contrato e respostas http esperadas.

### Decisões do projeto

O DDD (Domain Driven Design) foi utilizado para estruturar o microsserviço. Utilizando apenas duas camadas, aplicação para receber requisições e domínio para armazenar a lógica de negócio. 
A camada de infraestrutura não foi usada porque a persistência não era um requisito.

A requisição é passada para a interface do serviço do domínio, que converte a solicitação no objeto de domínio. A implementação do serviço possui todos os requisitos de negócios.
Se uma exceção for lançada, este próprio serviço a trata e retorna o resultado. A camada de aplicação é responsável por convertê-la em uma resposta mais apresentável.

A camada de domínio imprime o que está acontecendo nos logs, que podem fornecer uma solução de problemas no ambiente produtivo.
