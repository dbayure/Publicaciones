package uy.com.antel.Publicaciones.model;

import java.util.List;

public interface AdminEditoriales {

public void agregarEditorial(Editorial editorial);
public void agregarPublicacion(Publicacion publicacion, Editorial editorial);
public List<Editorial> obtenerEditoriales();
public List<Publicacion> obtenerPublicacion();

}
