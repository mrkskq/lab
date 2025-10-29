package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.add(new Book("To Kill a Mockingbird", "Fiction", 4.8));
        books.add(new Book("1984", "Dystopian", 4.6));
        books.add(new Book("Pride and Prejudice", "Romance", 4.7));
        books.add(new Book("The Hobbit", "Fantasy", 4.8));
        books.add(new Book("The Catcher in the Rye", "Classic", 4.0));
        books.add(new Book("The Da Vinci Code", "Mystery", 4.2));
        books.add(new Book("The Alchemist", "Philosophical", 4.5));
        books.add(new Book("The Girl on the Train", "Thriller", 4.1));
        books.add(new Book("Harry Potter and the Sorcerer’s Stone", "Fantasy", 4.9));
        books.add(new Book("The Great Gatsby", "Classic", 4.4));

    }
}
