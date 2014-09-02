package uy.com.antel.Publicaciones.model;

import java.util.Date;

public abstract class Publicacion {
private String titulo;
private Date fecha;
private int idEditorial;

public int getIdEditorial() {
	return idEditorial;
}

public void setIdEditorial(int idEditorial) {
	this.idEditorial = idEditorial;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

}
