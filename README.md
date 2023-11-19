# Loja online backend

Um sistema backend para gerenciar uma concessionária de carros

## Licença

Este projeto é licenciado sob a Licença MIT. Consulte o texto completo da [licença](https://github.com/Joaogneves/lojaonlinebackend/blob/main/LICENSE.MD).


## Sobre

O software oferece funcionalidades para gerenciamento de veículos, vendedores e clientes, possibilitando atribuir a venda do veículo a um vendedor e também a um cliente. </br>
Pode-se ter dois tipos de usuários, um usuário vendedor e um usuário gerente.</br>
O Vendedor pode cadastrar veículos para venda, adicionar e remover fotos e opcionais e efetuar uma venda a um cliente já cadastrado.</br>
O Gerente tem a capacidade de realizar todas as tarefas de um vendedor, além de ter acesso às vendas totais da loja e às vendas individuais de cada vendedor. Adicionalmente, somente o gerente pode adicionar um novo vendedor e bloquear o acesso de um vendedor ao sistema.</br>
O login é feito por meio do Spring Security e é gerado um token e uma role.</br>
O Front End de gerenciamento foi desenvolvido usando Angular e pode ser acessado Por esse link [FrontEnd Gerenciamento](https://github.com/Joaogneves/lojaonline-vendedor/blob/main/README.md)</br>
Clientes podem se cadastrar para serem atendidos pelos vendedores, preenchendo um formulário com seus dados. Essas informações são armazenadas em um banco de dados e ficam disponíveis para que algum vendedor entre em contato.</br>
O Front End da loja também foi desenvolvido em Angular e pode ser acessado por esse link [FrontEnd Loja](https://github.com/Joaogneves/lojaonlinefrontend/blob/main/README.md)</br>

1. Destaque de Funcionalidades:

- "Através do sistema, os vendedores têm a capacidade de cadastrar veículos para venda, gerenciar fotos e opcionais, e concluir vendas para clientes registrados."
- "O Gerente, por outro lado, pode não apenas realizar as tarefas do vendedor, mas também monitorar as vendas totais da loja e as performances individuais dos vendedores."
2. Segurança e Autenticação:

- "Para garantir a segurança, o sistema utiliza o Spring Security, gerando tokens de autenticação durante o processo de login."
3. Comunicação com Clientes:

- "Clientes têm a facilidade de se cadastrar no sistema, preenchendo um formulário que armazena suas informações no banco de dados. Esses dados ficam disponíveis para que os vendedores possam entrar em contato."
4. Informações sobre Usuários:

- "Além disso, detalhes sobre o primeiro usuário ADMIN, como CPF e senha criptografada, são fornecidos no README para facilitar a inicialização do sistema."
5. Links para Front-End:

- "Para uma experiência visual, os usuários podem explorar o Front-End de gerenciamento e a Loja, ambos desenvolvidos em Angular. Links para os READMEs desses projetos estão disponíveis para referência."

## Tecnologias Utilizadas

- Java
- Spring boot
- Hibernate
- Spring Security
- JWT
- PostgreSQL

## Requisitos

- Java
- PostgreSQL

## Configuração do Projeto

1. Clone o repositório:

```bash
git clone git@github.com:Joaogneves/lojaonlinebackend.git
 ```

2. Edite o arquivo 'application.properties'


```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.username= 'Seu Usuário do PostgreSQL'
spring.datasource.password= 'Sua senha do PostgreSQL'
spring.datasource.url=jdbc:postgresql://localhost:5432/lojacar
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

api.security.token.secret=${JWT_SECRET:conkeysecret}

spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/,file:src/downloads/

```
3. Pode ser startado diretamente da IDE.

4. Execute o comando SQL no PostgreSQL para criar o primeiro Usuário ADMIN

```SQL
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO public.tb_user(
	id, cpf, first_name, is_deleted, is_inative, last_name, password, role)
	VALUES (uuid_generate_v4(), '12345678989', 'User',  false, false,'Admin', '$2a$10$Jfhp2wBrYXrQZnpjmzOG6u4mZplsu3XI2xv0d/1lWdAUd2By9XYZq' , 'ADMIN');
```

5. Clone as duas páginas front End para usar o programa com interface gráfica ou teste pelo Postman

[FrontEnd Gerenciamento](https://github.com/Joaogneves/lojaonline-vendedor/blob/main/README.md)</br>
[FrontEnd Loja](https://github.com/Joaogneves/lojaonlinefrontend/blob/main/README.md)</br>
</br>

### Endpoints

O servidor estará disponível em http://localhost:8080
- /auth/login: Para login e conseguir o retorno do token.
- /cars: Listagem de carros disponíveis.
- /clientes: Informações sobre clientes.
- /users: Registro de usuários.

## Contato

Caso de dúvidas ou sugestões fique a vontade para entrar em contato.
- Email: joaogabriel443@gmail.com
- Linkedin: https://www.linkedin.com/in/joaognevess

## Contribuindo
Sinta-se à vontade para contribuir com novos recursos, correções de bugs ou melhorias. Abra uma issue para discussões ou envie um pull request.
