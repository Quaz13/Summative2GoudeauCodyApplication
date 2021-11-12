package com.company.Summative2GoudeauCody.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Book {

    private int id;
    private String isbn;
    private LocalDate publish_date;
    private int fk_book_author;
    private String title;
    private int fk_book_publisher;
    private BigDecimal price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(LocalDate publish_date) {
        this.publish_date = publish_date;
    }

    public int getFk_book_author() {
        return fk_book_author;
    }

    public void setFk_book_author(int fk_book_author) {
        this.fk_book_author = fk_book_author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFk_book_publisher() {
        return fk_book_publisher;
    }

    public void setFk_book_publisher(int fk_book_publisher) {
        this.fk_book_publisher = fk_book_publisher;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getId() == book.getId() && getFk_book_author() == book.getFk_book_author() && getFk_book_publisher() == book.getFk_book_publisher() && Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getPublish_date(), book.getPublish_date()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getPrice(), book.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsbn(), getPublish_date(), getFk_book_author(), getTitle(), getFk_book_publisher(), getPrice());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", publish_date=" + publish_date +
                ", fk_book_author=" + fk_book_author +
                ", title='" + title + '\'' +
                ", fk_book_publisher=" + fk_book_publisher +
                ", price=" + price +
                '}';
    }
}
