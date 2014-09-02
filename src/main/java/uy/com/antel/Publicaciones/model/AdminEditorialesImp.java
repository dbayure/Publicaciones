package uy.com.antel.Publicaciones.model;

import java.util.List;

public class AdminEditorialesImp  implements AdminEditoriales {

private static AdminEditorialesImp AdmEdit = new AdminEditorialesImp();

public static AdminEditorialesImp getAdmEdit(){
	return AdmEdit;
}
	
	@Override
	public void agregarEditorial(Editorial editorial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarPublicacion(Publicacion publicacion, Editorial editorial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Editorial> obtenerEditoriales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacion> obtenerPublicacion() {
		// TODO Auto-generated method stub
		return null;
	}

}
