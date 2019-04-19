package az.test.crawler;

import az.test.domain.Product;

import java.util.Optional;

public class CrawlerTest {
    public static void main(String[] args) {
        Crawler crawler=new TrendYolCrawler();
        Optional<Product> optionalProduct=crawler.parse("https://www.trendyol.com/slazenger/erkek-sneaker-mini---sa28le064-p-3436728?boutiqueId=310879&merchantId=4662");
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            System.out.println(product);
        }
        else {
            System.out.println("Crawler tapilmadi!");
        }
    }
}
