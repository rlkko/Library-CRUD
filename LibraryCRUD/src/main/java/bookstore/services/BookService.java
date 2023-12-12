/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.services;

import bookstore.dto.AuthorBook;
import bookstore.dto.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ricar
 */
public class BookService {

    public static ArrayList<AuthorBook> retrieveAllBooks() {
        ArrayList<AuthorBook> bookList = new ArrayList<>();
        String query
                = """
                select
                book.isbn as ISBN,
                book.title as title,
                book.no_copies as no_copies
                from bookstore.book_author
                inner join book on book.isbn = book_author.book_isbn
                inner join author on book_author.author_number = author.number
                group by ISBN;
                """;

        try (Connection con = DBService.openConnection();
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {

                String ISBN = rs.getString("ISBN");
                String author_name = AuthorService.getAuthor(ISBN);
                int no_copies = rs.getInt("no_copies");
                String title = rs.getString("title");

                bookList.add(new AuthorBook(ISBN, author_name, title,
                        no_copies));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static ArrayList<AuthorBook> retrieveBookByTitle(String bookTitle) {
        ArrayList<AuthorBook> bookList = new ArrayList<>();
        String query
                = """
                select
                author.name as author_name,
                book.isbn as ISBN,
                book.title as title,
                book.no_copies as no_copies
                from book_author
                inner join book on book.isbn = book_author.book_isbn
                inner join author on book_author.author_number = author.number
                where title = ?
                """;

        try (Connection con = DBService.openConnection()) {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, bookTitle);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String ISBN = rs.getString("ISBN");
                String author_name = rs.getString("author_name");
                int no_copies = rs.getInt("no_copies");
                String title = rs.getString("title");

                bookList.add(new AuthorBook(ISBN, author_name, title,
                        no_copies));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static ArrayList<AuthorBook> updateBookQuantity(int newQuanity,
            String ISBN) {
        ArrayList<AuthorBook> bookList = new ArrayList<>();
        String query
                = """
               update book set no_copies=? where isbn = ?
                """;

        try (Connection con = DBService.openConnection();) {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, newQuanity);
            statement.setString(2, ISBN);

            statement.executeUpdate();
            System.out.println(statement.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static boolean addNewBook(String ISBN, String title,
            Integer quantity_available, Integer author_id) {
        String book_query = "insert into book(isbn,no_copies,title) values(?,?,?)";
        String association_query = "insert into book_author(author_number,book_isbn) values(?,?);";

        try (Connection con = DBService.openConnection();) {
            PreparedStatement statement = con.prepareStatement(book_query);

            statement.setString(1, ISBN);
            statement.setInt(2, quantity_available);
            statement.setString(3, title);

            statement.executeUpdate();
            System.out.println(statement.toString());

            statement = con.prepareStatement(association_query);

            statement.setInt(1, author_id);
            statement.setString(2, ISBN);

            statement.executeUpdate();
            System.out.println(statement.toString());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteBook(String ISBN) {
        String query
                = """
               delete from book where isbn = ?
                """;

        try (Connection con = DBService.openConnection();) {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ISBN);

            statement.executeUpdate();
            System.out.println(statement.toString());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<AuthorBook> updateBookTitle(String item,
            String ISBN) {
        ArrayList<AuthorBook> bookList = new ArrayList<>();
        String query
                = """
               update book set title=? where isbn = ?
                """;

        try (Connection con = DBService.openConnection();) {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, item);
            statement.setString(2, ISBN);

            statement.executeUpdate();
            System.out.println(statement.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static ArrayList<AuthorBook> retrieveBookByAuthor(String author_name) {
        ArrayList<AuthorBook> bookList = new ArrayList<>();
        String query
                = """
                select
                author.name as author_name,
                book.isbn as ISBN,
                book.title as title,
                book.no_copies as no_copies
                from book_author
                inner join book on book.isbn = book_author.book_isbn
                inner join author on book_author.author_number = author.number
                where author.number = ?
                group by ISBN
                """;

        try (Connection con = DBService.openConnection()) {

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, author_name);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String ISBN = rs.getString("ISBN");
                String author_id = rs.getString("author_name");
                int no_copies = rs.getInt("no_copies");
                String title = rs.getString("title");

                bookList.add(new AuthorBook(ISBN, author_id, title,
                        no_copies));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

}
