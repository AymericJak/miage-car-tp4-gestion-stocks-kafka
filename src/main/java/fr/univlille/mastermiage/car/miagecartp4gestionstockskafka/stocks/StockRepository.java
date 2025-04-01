package fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.stocks;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<Stock, Long> {
    Optional<Stock> findByNom(String nom);
}
