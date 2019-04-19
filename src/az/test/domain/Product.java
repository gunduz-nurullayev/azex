package az.test.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String url;
    private String name;
    private BigDecimal price;
    private Currency currency;
    private List<String > sizeList;
    private List<String> imageList;

    public Product() {
        this.url = "";
        this.name = "";
        this.price = BigDecimal.ZERO;
        this.currency = CurrencyUtility.USD;
        this.sizeList = new ArrayList<>();
        this.imageList = new ArrayList<>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<String> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<String> sizeList) {
        this.sizeList = sizeList;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
    public void addSize(String size){
        getSizeList().add(size);
    }
    public void addImage(String image){
        getImageList().add(image);
    }

    @Override
    public String toString() {
        return "Product{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", sizeList=" + sizeList +
                ", imageList=" + imageList +
                '}';
    }
}
