package az.test.domain;

import java.util.HashMap;
import java.util.Map;

public class CurrencyUtility {
    public static final Currency AZN=new Currency(1,"AZN",1);
    public static final Currency TRY=new Currency(2,"TRY",2);
    public static final Currency USD=new Currency(3,"USD",3);
    public static final Currency EUR=new Currency(4,"EUR",4);

    public static Currency parse(String text){
        Map<String,Currency> currencyMap=new HashMap<>();
        currencyMap.put("TL",TRY);
        currencyMap.put("tl", TRY);
        currencyMap.put("azn", AZN);
        currencyMap.put("AZN", AZN);
        currencyMap.put("usd", USD);
        currencyMap.put("USD", USD);
        currencyMap.put("$", USD);
        currencyMap.put("EUR", EUR);
        currencyMap.put("eur", EUR);
        currencyMap.put("euro", EUR);

        Currency currency=USD;
        if(text!=null && !text.isEmpty()){
            currency=currencyMap.get(text);
        }
        return currency;
    }
}
