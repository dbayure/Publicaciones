package uy.com.antel.Publicaciones.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import uy.com.antel.Publicaciones.controller.RegistroRevista;
import uy.com.antel.Publicaciones.model.Revista;


@ManagedBean
@RequestScoped
public class RevistaBean {

	@Inject
	private RegistroRevista registroRevista;
	
	public void registrar() {
		try {
			registroRevista.registro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
            Revista revista = ((Revista) event.getObject());
           
            try {
            	registroRevista.modificar(revista);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", revista.getTitulo());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", revista.getTitulo());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Revista) event.getObject()).getTitulo());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(int id) {
		try {
			registroRevista.eliminar(id);
			FacesMessage msg = new FacesMessage("Se eliminó ",Integer.toString(id));  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al eliminar", Integer.toString(id));  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
}
