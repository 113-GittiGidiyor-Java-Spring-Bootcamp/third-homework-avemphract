package dev.patika.currencyexchange;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.Locale;

@RestController
@RequestMapping("/currency")
public class CurrencyExchangeController {
    private static final double tl_usd = 8.35d;
    private static final Locale tr_local=new Locale("tr","TR");
    private static final Locale usd_local=new Locale("en","US");

    @GetMapping("/totl")
    public String convertTL(@RequestParam double amount){
        return NumberFormat.getCurrencyInstance(tr_local).format(amount*tl_usd);
    }
    @GetMapping("/tous")
    public String convertUS(@RequestParam double amount){
        return NumberFormat.getCurrencyInstance(usd_local).format(amount/tl_usd);
    }

    @GetMapping()
    public ResponseEntity<String> convertUSD(@RequestParam double amount){
        return ResponseEntity.ok("");
    }

}
