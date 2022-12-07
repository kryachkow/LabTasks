package com.task4.model;

import java.util.Objects;

public class Book {
    private String author;
    private String bookTitle;
    private String publisher;
    private int pageNumber;
    private int price;

    public Book() {}

    public Book(String author, String bookTitle, String publisher, int pageNumber, int price) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.publisher = publisher;
        this.pageNumber = pageNumber;
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getPageNumber() == book.getPageNumber() && getPrice() == book.getPrice() && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getBookTitle(), book.getBookTitle()) && Objects.equals(getPublisher(), book.getPublisher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getBookTitle(), getPublisher(), getPageNumber(), getPrice());
    }


    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pageNumber=" + pageNumber +
                ", price=" + price +
                '}';
    }
}
