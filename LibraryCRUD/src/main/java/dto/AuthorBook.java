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
    private String nome_autor;
    private String titulo;
    private int numero_copias;

    public AuthorBook(String ISBN, String nome_autor, String titulo,
            int numero_copias) {
        this.ISBN = ISBN;
        this.nome_autor = nome_autor;
        this.titulo = titulo;
        this.numero_copias = numero_copias;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getNome_autor() {
        return nome_autor;
    }

    public void setNome_autor(String nome_autor) {
        this.nome_autor = nome_autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumero_copias() {
        return numero_copias;
    }

    public void setNumero_copias(int numero_copias) {
        this.numero_copias = numero_copias;
    }

}
