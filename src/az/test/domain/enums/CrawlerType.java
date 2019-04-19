package az.test.domain.enums;

import az.test.crawler.Crawler;
import az.test.crawler.TrendYolCrawler;

public enum CrawlerType {
    TRENDYOL(2, new TrendYolCrawler());
    
    private int num;
    private Crawler crawler;


    CrawlerType(int num, Crawler crawler) {
        this.num = num;
        this.crawler = crawler;
    }

    public int getNum() {
        return num;
    }

    public Crawler getCrawler() {
        return crawler;
    }

    public static CrawlerType getTypeByNum(int num){
        for(CrawlerType crawlerType : values()){
            if(crawlerType.getNum() == num)
                return crawlerType;
        }
        return null;
    }
}
