package az.test.crawler;

import az.test.domain.Product;

import java.util.Optional;

public interface Crawler {

    Optional<Product> parse(String url);
}
