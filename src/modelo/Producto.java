
package modelo;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estudiante
 */
public class Producto {
   String UUID_producto;

    public String getUUID_producto() {
        return UUID_producto;
    }

    public void setUUID_producto(String UUID_producto) {
        this.UUID_producto = UUID_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
   String nombre;
   double precio;
   String categoria;
   
   
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO tbProducto(UUID_producto, nombre, precio, categoria) VALUES (?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre());
            addProducto.setDouble(3, getPrecio());
            addProducto.setString(4, getCategoria());
 
            //Ejecutar la consulta
            addProducto.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
     
        Connection conexion = ClaseConexion.getConexion();
        
        DefaultTableModel modeloPinulito = new DefaultTableModel();
        modeloPinulito.setColumnIdentifiers(new Object[]{"UUID_producto", "Nombre", "Precio", "Categoria"});
        try {
         
            Statement statement = conexion.createStatement();
           
            ResultSet rs = statement.executeQuery("SELECT * FROM tbProducto");
          
            while (rs.next()) {
                
                modeloPinulito.addRow(new Object[]{rs.getString("UUID_producto"), 
                    rs.getString("nombre"), 
                    rs.getInt("precio"), 
                    rs.getString("categoria")});
            }
           
            tabla.setModel(modeloPinulito);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbProducto where UUID_producto = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void Actualizar (JTable tabla){
        Connection conexion = ClaseConexion.getConexion();
        
        int filaSeleccionada = tabla.getSelectedRow();
        
        if (filaSeleccionada != 1){
            String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                PreparedStatement updateUser = conexion.prepareStatement("update tbProducto set nombre = ?, precio = ?, categoria = ?, where UUID_producto = ?");
                updateUser.setString(1, getNombre());
                updateUser.setDouble(2, getPrecio());
                updateUser.setString(3, getCategoria());
                updateUser.setString(4, miUUID);
                updateUser.executeUpdate();
            }catch(Exception e){
                System.out.println("este es el erro del metodo de actualizar" + e);
                
            }
        }else{
            System.out.println("no funciona el metodo de actualizar");
            
        }
    }
    
}


