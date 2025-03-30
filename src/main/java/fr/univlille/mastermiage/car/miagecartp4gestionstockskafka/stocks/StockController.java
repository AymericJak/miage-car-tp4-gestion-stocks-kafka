package fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.stocks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/stocks")
public class StockController {

    private final IStock stockService;

    public StockController(IStock stockService) {
        this.stockService = stockService;
    }

    @GetMapping("")
    public ModelAndView home() {
        Iterable<Stock> stocks = stockService.findAll();
        ModelAndView modelAndView = new ModelAndView("stocks");
        modelAndView.addObject("stocks", stocks);
        return modelAndView;
    }

    @PostMapping("reapprovisionner")
    public String reapprovisionner() {
        List<String> nomsArticles = List.of("Livre", "Ordinateur", "Clavier", "Souris", "Téléphone", "Écran", "Casque");

        for (String nom : nomsArticles) {
            Optional<Stock> stockOpt = stockService.findByNom(nom);

            Stock stock;
            if (stockOpt.isPresent()) {
                stock = stockOpt.get();
                stock.setQuantite(stock.getQuantite() + 10);
            } else {
                stock = new Stock(nom, 10);
            }
            stockService.save(stock);
        }

        return "redirect:/stocks";
    }

}
