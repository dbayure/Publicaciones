package uy.com.antel.Publicaciones.model;

public class Revista extends Publicacion{
private int id;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

private String numero;

public String getNumero() {
	return numero;
}

public void setNumero(String numero) {
	this.numero = numero;
}

}
