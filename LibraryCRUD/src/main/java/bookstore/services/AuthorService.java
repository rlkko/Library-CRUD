/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.services;

import bookstore.dto.Author;
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
public class AuthorService {

    public static ArrayList<Author> retrieveAuthorList() {
        ArrayList<Author> authorList = new ArrayList<>();

        String query = """
                     select * from bookstore.author;
                     """;

        try (Connection c = DBService.openConnection();
                Statement statement = c.createStatement();
                ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                String author_name = rs.getString("name");
                Integer author_number = rs.getInt("number");

                authorList.add(new Author(author_number,
                        author_name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authorList;
    }

    public static String getAuthorName(Integer id) {

        String query = """
                        select name from author where number = ?;
                       """;
        try (Connection c = DBService.openConnection()) {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;

    }

    public static String getAuthor(String ISBN)
            throws
            SQLException {

        ArrayList<String> tmp = new ArrayList<>();

        try (Connection c = DBService.openConnection()) {
            String query = """
                        select name
                        from book_author
                        inner join author on
                        book_author.author_number = author.number
                        where book_author.book_isbn = ?;
                       """;
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, ISBN);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tmp.add(rs.getString(1));
            }

            return tmp.toString().replaceAll("[\\[\\]]", "");
        }
    }
}
