package fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.kafka;

import fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.stocks.Stock;
import fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.stocks.StockService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class KafkaConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private final StockService stockService;

    public KafkaConsumer(StockService stockService) {
        this.stockService = stockService;
    }

    @KafkaListener(topics = "my-first-topic", groupId = "my-first-group")
    public void consume(ConsumerRecord<String, String> record) {
        String message = record.value();
        String[] lignes = message.split("\\n");

        for (String ligne : lignes) {
            String[] parts = ligne.trim().split(" ");
            if (parts.length < 2) continue;

            String nomArticle = parts[0];
            int quantiteCommandee;
            try {
                quantiteCommandee = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                LOGGER.warn("Quantité invalide pour l'article {} : {}", nomArticle, parts[1]);
                continue;
            }

            Optional<Stock> stockOpt = stockService.findByNom(nomArticle);
            if (stockOpt.isPresent()) {
                Stock stock = stockOpt.get();
                int nouvelleQuantite = stock.getQuantite() - quantiteCommandee;

                if (nouvelleQuantite < 0) {
                    nouvelleQuantite = 0;
                    LOGGER.warn("Stock insuffisant pour l'article {}. Commandé : {}, Disponible : {}",
                            nomArticle, quantiteCommandee, stock.getQuantite());
                }
                stock.setQuantite(nouvelleQuantite);
                stockService.save(stock);
                LOGGER.info("Stock mis à jour : {} -> {}", nomArticle, nouvelleQuantite);
            } else {
                LOGGER.warn("Article '{}' non trouvé dans le stock", nomArticle);
            }

        }
    }
}
