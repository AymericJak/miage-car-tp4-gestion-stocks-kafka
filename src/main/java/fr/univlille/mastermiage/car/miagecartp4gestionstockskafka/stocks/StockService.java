package fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService implements IStock {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Iterable<Stock> findAll() {
        return stockRepository.findAll();
    }

    public Stock addArticle(String nom, int quantite) {
        Stock stock = new Stock();
        return stockRepository.save(stock);
    }

    public void deleteArticle(Long id) {
        stockRepository.deleteById(id);
    }

    public Stock saveArticle(Stock stock) {
        return stockRepository.save(stock);
    }

    public Optional<Stock> getArticleById(Long id) {
        return stockRepository.findById(id);
    }
}
