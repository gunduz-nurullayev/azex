package az.test.crawler;

import az.test.domain.CurrencyUtility;
import az.test.domain.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class TrendYolCrawler implements Crawler {
    @Override
    public Optional<Product> parse(String url) {
        Optional<Product> optionalProduct = Optional.empty();

        try {
            Document doc = Jsoup.connect(url).get();
            Product product = new Product();
            product.setUrl(url);


            Element productNameDiv = doc.selectFirst(".product-info-titlebox");
            if (productNameDiv != null) {
                Element spanName = productNameDiv.selectFirst(".product-name-text");
                product.setName(spanName.text().trim());
            }
            Element priceDev = doc.selectFirst(".product-info-priceBox");
            if (priceDev != null) {
                Element spanPrice = priceDev.selectFirst(".sale-price");
                String price = spanPrice.text().trim();
                String[] parsedArr = price.split(" ");
                if (parsedArr.length >= 2) {
                    String priceStr = parsedArr[0].replace(",", ".");
                    String priceCurrency = parsedArr[1].trim();
                    product.setPrice(new BigDecimal(priceStr));
                    product.setCurrency(CurrencyUtility.parse(priceCurrency));

                }

            }
            Element sizeDev = doc.selectFirst(".variant-picker-select");
            if (sizeDev != null) {
                Elements spanSize = sizeDev.select("option");
                for (Element size : spanSize) {
                    if (!size.attr("data-stock-status").isEmpty() && !size.attr("data-stock-status").equals(0)) {
                        product.addSize(size.text().trim());
                    }
                }
            }
            Element imageElement = doc.getElementById("thumbnailContainer");
            if (imageElement != null) {
                for (Element image : imageElement.select("img")) {
                    if (image.attr("data-zoom-image") != null &&
                            !image.attr("data-zoom-image").isEmpty()) {
                        product.addImage(image.attr("data-zoom-image").trim());
                    }
                }

            }
            optionalProduct = Optional.of(product);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return optionalProduct;
    }
}
