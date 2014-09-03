package uy.com.antel.Publicaciones.model;

public class Libro extends Publicacion{
private int id;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

private String isbn;

public String getIsbn() {
	return isbn;
}

public void setIsbn(String isbn) {
	this.isbn = isbn;
}

}
