# 💳 Payment Service (Microsserviço de Pagamento)

Este microsserviço é responsável pelo processamento de fluxos de pagamento da **Loja Vintage** e pela publicação dos eventos correspondentes para que outros serviços da arquitetura possam reagir de forma assíncrona.

## 🛠️ Tecnologias Utilizadas
* **Java 17** & **Spring Boot 3**
* **Spring Kafka (KafkaTemplate):** Para produção e envio de mensagens para o broker.
* **JUnit 5 & Spring Boot Test:** Estrutura de testes integrada para simulação de cenários reais.

## ⚙️ Funcionamento e Integração
O serviço simula o processamento de uma venda. Assim que o pagamento é processado, o objeto contendo os dados do `Pedido` (ID, Nome, E-mail, Produto, Valor e Status) é convertido em formato JSON e enviado de forma assíncrona para o tópico `lojavintage-pedidos` do Apache Kafka.

## 🧪 Teste de Integração Real (`PaymentServiceApplicationTests`)
O projeto conta com uma classe de testes automatizados configurada para interagir diretamente com o ambiente do **Docker**:
* Força a conexão com o broker real do Kafka rodando localmente na porta `9092`.
* Dispara um cenário real de pagamento **APROVADO** contendo uma carga útil (Payload) JSON válida.
* Utiliza pausas programadas controladas para garantir que o broker receba a mensagem e o serviço de notificação processe o disparo do e-mail de ponta a ponta sem concorrência de logs.