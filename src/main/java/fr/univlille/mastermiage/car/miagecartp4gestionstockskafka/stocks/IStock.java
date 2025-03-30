package fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.stocks;

import java.util.Optional;

public interface IStock {
    Iterable<Stock> findAll();

    Stock addArticle(String nom, int quantite);

    void deleteArticle(Long id);

    Stock save(Stock stock);

    Optional<Stock> getArticleById(Long id);

    Optional<Stock> findByNom(String nom);
}
