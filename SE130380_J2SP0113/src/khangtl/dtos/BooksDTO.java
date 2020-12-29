/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.dtos;

/**
 *
 * @author Peter
 */
public class BooksDTO {

    String code, name, author, publisher;
    int publisherYear;
    boolean forRent;

    public BooksDTO() {
    }

    public BooksDTO(String code, String name, String author, String publisher, int publisherYear, boolean forRent) {
        this.code = code;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publisherYear = publisherYear;
        this.forRent = forRent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublisherYear() {
        return publisherYear;
    }

    public void setPublisherYear(int publisherYear) {
        this.publisherYear = publisherYear;
    }

    public boolean isForRent() {
        return forRent;
    }

    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }

}
