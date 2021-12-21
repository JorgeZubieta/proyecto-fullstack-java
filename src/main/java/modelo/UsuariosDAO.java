/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//agregamos el importa para la conexion con=new!
import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author jorgezubieta
 */
public class UsuariosDAO {
    
    //Agregamos una conexion la cual la importa
    Connection conexion;
    
    //Creamos un metodo constructor -> conexion
    public UsuariosDAO() {
        //este constructor va a llamar a conexion y debemos imporatr la misma
        Conexion con=new Conexion();
        
        //llamamos al metodo getConnection del archvo Conexion.java
        conexion = con.getConnection();
    }
    
    //MOSTRAMOS UNA LISTA DE LA BD
    //se conecta con la bb y me devuelve los usuarios en una List
    //lista compuesta por 4 porpiedades -> id, nombre, apellido, mail
    //con este metodo le estoy diciendo que voy a devolver algo <usuarios> su contenido
    //no recibe nada por argumento listaUsuarios(.....)
    //para terminar este metodo tengo que devolver una lista de alumnos
    public List<Usuarios>listarUsuarios(){
        PreparedStatement ps;
        //ResultSet almacena la info que devuevle al bd
        ResultSet rs;
        
        //creamos una lista vacia con arrays!
        List<Usuarios> lista=new ArrayList<>();
        
        try{
            ps = conexion.prepareStatement("SELECT * FROM usuarios");
            rs = ps.executeQuery();
            
            while( rs.next() ){
                int id=rs.getInt("id");
                String nombre=rs.getString("nombre");
                String apellido=rs.getString("apellido");
                String mail=rs.getString("mail");
                
                //de la clase usuarios creo un nuevo objeto
                Usuarios usuarios=new Usuarios(id,nombre,apellido,mail); 
                //se egenera la lista con los rgistros de while
                lista.add(usuarios);
            }
            return lista;
        }
        catch(SQLException e) {
            System.out.println( e.toString() );
            return null;
        }
    }
    
    //mtodo para mostrar un usuario o ninguno
    public Usuarios mostrarUsuario(int _id){
        PreparedStatement ps;
        //ResultSet almacena la info que devuevle al bd
        ResultSet rs;
        
        //creamos unobjeto del tipo Alumno que se llama alumno nulo! 
        Usuarios usuario=null;
                
        try{
            ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE id=?");
            // en el 1er id que ponega el dato enviado que lo setee que ponga no obtener! ojo!
            ps.setInt(1, _id);
            
            rs = ps.executeQuery();
            
            while( rs.next() ){
                int id=rs.getInt("id");
                String nombre=rs.getString("nombre");
                String apellido=rs.getString("apellido");
                String mail=rs.getString("mail");
                
                //de la clase usuarios creo un nuevo objeto
                usuario = new Usuarios(id,nombre,apellido,mail); 
            }
            return usuario;
        }
        catch(SQLException e) {
            System.out.println( e.toString() );
            return null;
        }
    
    
    }

    //metodo para insertar usuarios
    //pasamos un objetodel tipo usurios
    public boolean insertarUsuario(Usuarios usuario){
        PreparedStatement ps;
        try{
            ps=conexion.prepareStatement("INSERT INTO usuarios(nombre,apellido,mail) VALUES (?,?,?)");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getMail());
            ps.execute();
            return true;

        }
        catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean actualizarUsuario(Usuarios usuario){
        PreparedStatement ps;
        try{
            ps=conexion.prepareStatement("UPDATE usuarios SET nombre=?,apellido=?,mail=? WHERE id=?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getMail());
            
            ps.setInt(4, usuario.getId());

            ps.execute();
            return true;

        }
        catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    //METODO ELIMINAR
    public boolean eliminarUsuario(int _id){
        PreparedStatement ps;
        try{
            ps=conexion.prepareStatement("DELETE from usuarios WHERE id=?");
            ps.setInt(1, _id);
            ps.execute();
            return true;

        }
        catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
}