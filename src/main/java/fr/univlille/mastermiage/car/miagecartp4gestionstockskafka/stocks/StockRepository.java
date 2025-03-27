package fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.stocks;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
