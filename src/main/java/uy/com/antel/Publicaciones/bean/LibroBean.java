package uy.com.antel.Publicaciones.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import uy.com.antel.Publicaciones.controller.RegistroLibro;
import uy.com.antel.Publicaciones.model.Libro;


@ManagedBean
@RequestScoped
public class LibroBean {

	@Inject
	private RegistroLibro registroLibro;
	
	public void registrar() {
		try {
			registroLibro.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
            Libro libro = ((Libro) event.getObject());
           
            try {
            	registroLibro.modificar(libro);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", libro.getTitulo());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", libro.getTitulo());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Libro) event.getObject()).getTitulo());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(int id) {
		try {
			registroLibro.eliminar(id);
			FacesMessage msg = new FacesMessage("Se eliminó ", Integer.toString(id));  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al eliminar", Integer.toString(id));  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
}
