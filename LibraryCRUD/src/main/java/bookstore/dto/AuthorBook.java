/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.dto;

/**
 *
 * @author ricar
 */
public class AuthorBook {

    private String ISBN;
    private String nome_autor;
    private String title;
    private Integer number_copies;

    public AuthorBook(String ISBN, String nome_autor, String title,
            Integer number_copies) {
        this.ISBN = ISBN;
        this.nome_autor = nome_autor;
        this.title = title;
        this.number_copies = number_copies;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getNome_autor() {
        return nome_autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = titulo;
    }

    public int Number_Copies() {
        return number_copies;
    }

    public void setNumber_Copies(Integer numero_copias) {
        this.number_copies = numero_copias;
    }

}
