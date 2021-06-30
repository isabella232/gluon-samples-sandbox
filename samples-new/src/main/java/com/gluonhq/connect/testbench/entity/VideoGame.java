package com.gluonhq.connect.testbench.entity;

//import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

//FixME: JDK 9
//@XmlRootElement
public class VideoGame {
    private String name;
    private String developer;
    private String publisher;
    private List<String> genres;
    private int year;
    private int metaScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(int metaScore) {
        this.metaScore = metaScore;
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "name='" + name + '\'' +
                ", developer='" + developer + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genres=" + genres +
                ", year=" + year +
                ", metaScore=" + metaScore +
                '}';
    }
}
