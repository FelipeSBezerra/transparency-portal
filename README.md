# Readme da API do Portal da Transparência Simplificado em Spring (Java)

Em desenvolvimento

## Descrição

A API do Portal da Transparência Simplificado permite acessar e gerenciar dados como remunerações, cargos e outros atributos dos servidores públicos de uma cidade. Ideal para garantir transparência e eficiência na gestão dessas informações.

## Finalidade

A API foi desenvolvida para centralizar o gerenciamento das informações relacionadas aos gastos públicos de um município com os pagamentos dos servidores. Permite o cadastro de setores, cargos, funcionários e remunerações, bem como a consulta desses dados através de diversos filtros disponíveis. Isso facilita a democratização das informações, permitindo que outros sistemas também possam utilizá-las.

## Tecnologias Utilizadas

A API foi desenvolvida utilizando as seguintes tecnologias:

- **Java 17**: Linguagem de programação utilizada no projeto.
- **Spring**: Framework para o desenvolvimento rápido de aplicativos Java.

	 Projetos Spring utilizados:
	- **Spring Boot**: Simplifica o processo de configuração, desenvolvimento e implantação de aplicativos bas
	- eados em Spring-Java;
	- **Spring Data JPA**: Facilita o acesso a dados relacionais usando a plataforma Java 	Persistence API (JPA);
	- **Spring HATEOAS**: Para a implementação do princípio HATEOAS (Hypermedia as the Engine of Application State), tornando o serviço RESTful.
	- **Spring Web (MVC)**: Facilita o desenvolvimento de aplicativos web baseados em Java;
	- **Spring Security**: Para gerenciamento de autenticação e autorização;
- **Hibernate**: Framework ORM para mapeamento objeto-relacional.
- **PostgreSQL**: Banco de dados utilizado para a persistência dos dados.
- **Lombok**: Biblioteca Java utilizada para reduzir a verbosidade do código, automatizando a geração de getters, setters, construtores e outros métodos comuns.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **Modelo de Domínio Driven Design (DDD)**: Padrão de projeto que organiza o código em torno do domínio da aplicação.

## Estrutura do Projeto

O projeto segue uma estrutura baseada no padrão de projeto DDD, dividido em módulos principais:

- **Controller:** Camada responsável por receber as requisições HTTP e chamar os serviços adequados.
- **Service:** Lógica de negócios e regras específicas do domínio.
- **Repository:** Interação com o banco de dados.
- **Model:** Classes que representam o modelo de domínio.
- **DTO (Data Transfer Object):** Objetos para transferência de dados entre as camadas.
- **Config:** Configurações específicas do Spring.
- **Security:** Configurações relacionadas à segurança.
