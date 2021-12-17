/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jorgezubieta
 */
public class Conexion {
    //PROPIEDAD
    //defino el dirver que voy a utilizar con mysql
    public String driver = "com.mysql.jdbc.Driver";
    
    //METODO en esta caso lo nombre getConection por defaul usado en internet!
    //este metodo devulete un tipo de dato conexion!
    public Connection getConnection() {
        //conexion es una tipo de dato connection 
        Connection conexion=null;
        try{
            //me trae la dependecnia del archivo correspondiente
            Class.forName(driver);
            //nombre de mi BD a utilizar, usuario y password (*ver)
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/CryptoBlog","root","administrador");
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println( e.toString() );
        }
        return conexion;
    }
    
    
    //agregamos un main para activar la opcion Run File
    public static void main(String[] args) throws SQLExcepction, SQLException{
        
        //VERIFICAMOS LA CONEXION
        //creamos una propiedad = nulo
        Connection conexionX = null; 
        //estamos instanciando u nuevo obejeto
        Conexion con = new Conexion();
        //llamo al elemento creado y al metodo getConection y lo guarda en conexion!
        conexionX = con.getConnection();
        //dentro de conexion me va aguardar la conexion (*ver)
        
        PreparedStatement ps;
        ResultSet rs;
        
        ps = conexionX.prepareStatement("SELECT * FROM usuarios");
        rs = ps.executeQuery();
        
        while( rs.next() ){
            int id = rs.getInt("id");
            String nombre=rs.getString("nombre");
            String apellido=rs.getString("apellido");
            String mail=rs.getString("mail");        
            
            System.out.println("ID: "+id+" - NOMBRE: "+nombre+" - APELLIDO: "+apellido+" - E-MAIL: "+mail);
        }

    }    
    
}
