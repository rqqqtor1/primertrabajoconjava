/*

 */
package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Producto;
import vista.frmProductos;

/**
 *
 * @author Estudiante
 */
public class ctrlProducto implements MouseListener {
    private frmProductos vista;
    private Producto modelo;
    
    public ctrlProducto(frmProductos Vista, Producto Modelo){
        this.vista = Vista;
        this.modelo = Modelo;
        
      vista.btnGuardar.addMouseListener(this);
      
      
      
     vista.tablaMostrar.addMouseListener(this);
      modelo.Mostrar(vista.tablaMostrar);
      vista.btnEliminar.addMouseListener(this);
      vista.btnEditar.addMouseListener(this);
      
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnGuardar){
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText())  );
            modelo.setCategoria(vista.txtCategoria.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.tablaMostrar);
            
        }
        
        if (e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.tablaMostrar);
            modelo.Mostrar(vista.tablaMostrar);
            vista.btnEliminar.addMouseListener(this);
            vista.tablaMostrar.addMouseListener(this);
        }
        
        if (e.getSource()== vista.btnEditar){
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            modelo.setCategoria(vista.txtCategoria.getText());
            modelo.Actualizar(vista.tablaMostrar);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
