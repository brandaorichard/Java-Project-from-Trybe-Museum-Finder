<!-- Olá, Tryber!
Esse é apenas um arquivo inicial para o README do seu projeto.
É essencial que você preencha esse documento por conta própria, ok?
Não deixe de usar nossas dicas de escrita de README de projetos, e deixe sua criatividade brilhar!
:warning: IMPORTANTE: você precisa deixar nítido:
- quais arquivos/pastas foram desenvolvidos por você; 
- quais arquivos/pastas foram desenvolvidos por outra pessoa estudante;
- quais arquivos/pastas foram desenvolvidos pela Trybe.
-->
# Museu Finder

Museu Finder é uma aplicação desenvolvida em Java utilizando Spring Boot que permite localizar museus. A aplicação fornece endpoints para criar museus, buscar museus mais próximos com base em coordenadas geográficas e obter informações detalhadas de museus específicos.

## Funcionalidades

<ul>
  <li><strong>Criação de Museus</strong>: Endpoint para criar novos museus.</li>
  <li><strong>Busca de Museu Mais Próximo</strong>: Endpoint para encontrar o museu mais próximo com base em coordenadas fornecidas.</li>
  <li><strong>Detalhes de Museu</strong>: Endpoint para obter detalhes de um museu específico pelo seu ID.</li>
  <li><strong>Contagem por Tipo de Coleção</strong>: Endpoint para contar museus que possuem tipos específicos de coleções.</li>
  <li><strong>Saúde da Aplicação</strong>: Monitoramento da saúde da aplicação utilizando Spring Boot Actuator.</li>
</ul>

## Tecnologias Utilizadas

<ul>
  <li><strong>Java</strong></li>
  <li><strong>Spring Boot</strong></li>
  <li><strong>Spring Boot Web</strong></li>
  <li><strong>Spring Boot Test</strong></li>
  <li><strong>Spring Boot Actuator</strong></li>
  <li><strong>Docker</strong></li>
  <li><strong>JUnit 5</strong></li>
  <li><strong>MockMvc</strong></li>
</ul>

## Estrutura do Projeto

### DTOs

<ul>
  <li><strong>MuseumCreationDto</strong>: DTO para criação de um novo museu.</li>
  <li><strong>MuseumDto</strong>: DTO para representar um museu.</li>
  <li><strong>CollectionTypeCount</strong>: DTO para transportar resultado da contagem por tipo de coleção. (Desenvolvido pela Trybe)</li>
</ul>

### Modelos

<ul>
  <li><strong>Museum</strong>: Representa um museu com atributos como nome, descrição, endereço, tipo de coleção, etc.</li>
  <li><strong>Coordinate</strong>: Representa as coordenadas geográficas de um museu.</li>
</ul>

### Serviços

<ul>
  <li><strong>MuseumService</strong>: Implementa a lógica para criação e busca de museus.</li>
  <li><strong>CollectionTypeService</strong>: Implementa a lógica para contagem de museus por tipos de coleção. (Desenvolvido pela Trybe)</li>
</ul>

### Controladores

<ul>
  <li><strong>MuseumController</strong>: Controlador para rotas relacionadas a museus.</li>
  <li><strong>CollectionTypeController</strong>: Controlador para rotas relacionadas a contagem de tipos de coleções. (Desenvolvido pela Trybe)</li>
</ul>

### Exceções Customizadas

<ul>
  <li><strong>InvalidCoordinateException</strong>: Exceção para coordenadas inválidas.</li>
  <li><strong>MuseumNotFoundException</strong>: Exceção para quando um museu não é encontrado.</li>
</ul>

### Tratamento de Erros

<ul>
  <li><strong>GeneralControllerAdvice</strong>: Classe para tratamento global de exceções e geração de respostas apropriadas.</li>
</ul>

## Endpoints

### POST /museums
Cria um novo museu.

**Request Body**:
```json
{
  "name": "Museu de Arte Moderna",
  "description": "Um museu dedicado à arte moderna e contemporânea.",
  "address": "Av. Paulista, 1234, São Paulo, SP",
  "collectionType": "Arte Moderna",
  "subject": "Artes Visuais",
  "url": "http://www.mam.org.br",
  "coordinate": {
    "latitude": -23.561657,
    "longitude": -46.655904
  }
}
```
**Response (Status 201 - Created)**:
```json
{
  "name": "Museu de Arte Moderna",
  "description": "Um museu dedicado à arte moderna e contemporânea.",
  "address": "Av. Paulista, 1234, São Paulo, SP",
  "collectionType": "Arte Moderna",
  "subject": "Artes Visuais",
  "url": "http://www.mam.org.br",
  "coordinate": {
    "latitude": -23.561657,
    "longitude": -46.655904
  }
}
```


### GET /closest/museums
Obtem informações sobre o museu mais próximo de uma determinada localização.

**Request Body**:
```json
GET /museums/closest?lat=-23.561657&lng=-46.655904&max_dist_km=5
```
**Response (Status 200 - OK)**:
```json
{
  "name": "Museu de Arte Moderna",
  "description": "Um museu dedicado à arte moderna e contemporânea.",
  "address": "Av. Paulista, 1234, São Paulo, SP",
  "collectionType": "Arte Moderna",
  "subject": "Artes Visuais",
  "url": "http://www.mam.org.br",
  "coordinate": {
    "latitude": -23.561657,
    "longitude": -46.655904
  }
}
```


### GET /museums/{id}
Obtem informações sobre um museu específico com base no seu ID.

**Request Body**:
```json
GET /museums/1
```
**Response (Status 200 - OK)**:
```json
{
  "id": 1,
  "name": "Museu de Arte Moderna",
  "description": "Um museu dedicado à arte moderna e contemporânea.",
  "address": "Av. Paulista, 1234, São Paulo, SP",
  "collectionType": "Arte Moderna",
  "subject": "Artes Visuais",
  "url": "http://www.mam.org.br",
  "coordinate": {
    "latitude": -23.561657,
    "longitude": -46.655904
  }
}
```

## Testes

Testes utilizando o framework Spring Boot Test e MockMvc para simular as requisições HTTP e verificar as respostas.

Verifica se o endpoint `/museums/{id}` retorna corretamente as informações de um museu existente com base em seu ID.

Verifica se o endpoint `/museums/{id}` retorna o status 404 (NOT FOUND) quando é fornecido um ID de museu inválido.

Verifica se o endpoint `/museums/closest` retorna corretamente as informações do museu mais próximo com base em coordenadas geográficas fornecidas.

Verifica se o endpoint `/collections/count/{type}` retorna o status 200 (OK) para um tipo de coleção válido.

Verifica se o endpoint `/collections/count/{type}` retorna o corpo da resposta com os valores corretos, incluindo o número correto de museus para o tipo de coleção especificado.

Verifica se o endpoint `/collections/count/{type}` retorna o status 404 (NOT FOUND) para um tipo de coleção inválido.

Verifica se o endpoint `/collections/count/{type1},{type2}` retorna corretamente o contador para dois tipos de coleção especificados.

Esses testes garantem que a camada esteja funcionando conforme o esperado, lidando adequadamente com diferentes situações, como IDs inválidos e busca de museus mais próximos.


## Instruções de Instalação

Siga estas etapas para instalar e configurar o projeto em seu ambiente local:

### Pré-requisitos

Certifique-se de ter os seguintes pré-requisitos instalados em seu sistema:

- Java Development Kit (JDK) 11 ou superior
- Maven

### Passos de Instalação

1. Clone o repositório para o seu ambiente local:

    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd seu-repositorio
    ```

3. Execute o comando Maven para limpar o projeto, compilar o código-fonte e construir o pacote JAR:

    ```bash
    mvn clean install
    ```

4. Após a conclusão bem-sucedida do comando Maven, você pode iniciar a aplicação. Por padrão, a aplicação é iniciada na porta 8080. Certifique-se de que a porta 8080 esteja disponível em seu sistema.

5. A aplicação estará disponível em `http://localhost:8080/`.

## Execução dos Testes

Para executar os testes do projeto, siga estas etapas:

### Pré-requisitos

Certifique-se de ter os seguintes pré-requisitos instalados em seu sistema:

- Java Development Kit (JDK) 11 ou superior
- Maven

### Passos para Execução dos Testes

1. Navegue até o diretório raiz do projeto no terminal.

2. Execute o seguinte comando Maven para limpar o projeto e executar os testes:

    ```bash
    mvn clean test
    ```

Este comando irá limpar o projeto, compilar o código-fonte e executar todos os testes automatizados. Certifique-se de que todos os testes passaram com sucesso antes de prosseguir com a implantação ou desenvolvimento adicional.
