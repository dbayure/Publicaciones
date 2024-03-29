package uy.com.antel.Publicaciones.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import uy.com.antel.Publicaciones.controller.RegistroEditoriales;
import uy.com.antel.Publicaciones.data.EditorialListProducer;
import uy.com.antel.Publicaciones.model.Editorial;

@ManagedBean
@RequestScoped
public class EditorialBean {

	private RegistroEditoriales registroEditorial = new RegistroEditoriales();
	public Editorial editorial = new Editorial();
	public EditorialListProducer elp = new EditorialListProducer();
	
	public EditorialListProducer getElp() {
		return elp;
	}

	public void setElp(EditorialListProducer elp) {
		this.elp = elp;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public void registrar() {
		try {
			registroEditorial.registro(editorial);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró ", "con éxito!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e) {
		e.printStackTrace();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al registrar ", "");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
	}
	
	public void onEdit(RowEditEvent event) {  
            Editorial editorial = ((Editorial) event.getObject());
           
            try {
            	registroEditorial.modificar(editorial);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó ", editorial.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar ", editorial.getNombre());  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
			}
    }
	
	public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Se canceló modificar ", ((Editorial) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	public void eliminar(int id) {
		try {
			registroEditorial.eliminar(id);
			FacesMessage msg = new FacesMessage("Se eliminó ", Integer.toString(id));  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error al eliminar", Integer.toString(id));  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
}
