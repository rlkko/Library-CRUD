/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ricar
 */
public class AuthorBook {

    private String ISBN;
    private String author_name;
    private String title;
    private Integer number_copies;

    public AuthorBook(String ISBN, String author_name, String title,
            Integer number_copies) {
        this.ISBN = ISBN;
        this.author_name = author_name;
        this.title = title;
        this.number_copies = number_copies;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getNome_autor() {
        return author_name;
    }

    public void setNome_autor(String nome_autor) {
        this.author_name = nome_autor;
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
