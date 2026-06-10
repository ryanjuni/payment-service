package com.example.payment_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest(properties = "spring.kafka.bootstrap-servers=localhost:9092")
class PaymentServiceApplicationTests {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Test
	void testarFluxoDePagamentoIntegradoReal() throws InterruptedException {
		System.out.println("🚀 [INTEGRAÇÃO REAL] Iniciando simulação de eventos para o Kafka...");

		// Apenas o cenário real que deve enviar o e-mail
		String pedidoJson = """
        {
            "pedidoId": 2002,
            "nomeCliente": "Ryan Junio",
            "emailCliente": "ryanjuniofc@gmail.com",
            "descricaoProduto": "Terno masculino azul marinho",
            "valorTotal": 89.90,
            "statusPagamento": "APROVADO"
        }
        """;

		System.out.println("⏳ Disparando pedido para o tópico 'lojavintage-pedidos'...");
		kafkaTemplate.send("lojavintage-pedidos", pedidoJson);

		// Aguarda 5 segundos para processar e finaliza
		Thread.sleep(5000);
		System.out.println("====== TESTE DE DISPARO CONCLUÍDO ======");
	}
}