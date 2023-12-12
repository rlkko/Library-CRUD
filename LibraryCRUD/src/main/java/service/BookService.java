/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dto.AuthorBook;
import dto.Book;
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
                from book_author
                inner join book on book.isbn = book_author.book_isbn
                inner join author on book_author.author_number = author.number
                group by ISBN
                """;

        try (Connection con = ConnectionManager.openConnection();
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {

                String ISBN = rs.getString("ISBN");
                String author_name = getAuthorList(con, ISBN);
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
                book.isbn as ISBN,
                book.title as title,
                book.no_copies as no_copies
                from book_author
                inner join book on book.isbn = book_author.book_isbn
                inner join author on book_author.author_number = author.number
                where title = ?
                group by ISBN
                """;

        try (Connection con = ConnectionManager.openConnection()) {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, bookTitle);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String ISBN = rs.getString("ISBN");
                String author_name = getAuthorList(con, ISBN);
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

        try (Connection con = ConnectionManager.openConnection();) {
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

    public static boolean deleteBook(String ISBN) {
        ArrayList<AuthorBook> bookList = new ArrayList<>();
        String query
                = """
               delete from book where isbn = ?
                """;

        try (Connection con = ConnectionManager.openConnection();) {
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

        try (Connection con = ConnectionManager.openConnection();) {
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

    public static ArrayList<AuthorBook> retrieveBookByAuthor(String item) {
        ArrayList<AuthorBook> bookList = new ArrayList<>();
        String query
                = """
                select
                book.isbn as ISBN,
                book.title as title,
                book.no_copies as no_copies
                from book_author
                inner join book on book.isbn = book_author.book_isbn
                inner join author on book_author.author_number = author.number
                where author.name = ?
                group by ISBN
                """;

        try (Connection con = ConnectionManager.openConnection()) {

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, item);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String ISBN = rs.getString("ISBN");
                String author_name = getAuthorList(con, ISBN);
                int no_copies = rs.getInt("no_copies");
                String title = rs.getString("title");

                bookList.add(new AuthorBook(ISBN, author_name, title, no_copies));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    private static String getAuthorList(Connection con, String ISBN) throws
            SQLException {
        if (con == null) {
            return null;
        }

        ArrayList<String> tmp = new ArrayList<>();

        String query = """
                        select name
                        from book_author
                        inner join author on book_author.author_number = author.number
                        where book_author.book_isbn = ?;
                       """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, ISBN);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            tmp.add(rs.getString(1));
        }

        return tmp.toString().replaceAll("[\\[\\]]", "");
    }

}
