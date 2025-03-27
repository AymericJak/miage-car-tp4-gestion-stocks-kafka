package fr.univlille.mastermiage.car.miagecartp4gestionstockskafka.stocks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stocks")
public class StockController {

    @GetMapping("")
    public String home() {
        return "stocks";
    }
}
