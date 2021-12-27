<%-- 
    Document   : usuarios
    Created on : 16 dic. 2021, 18:50:25
    Author     : jorgezubieta
--%>

<%@page import="modelo.UsuariosDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <!--CDN css y js agregados-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

        
    </head>
    <body>
        <h1>Listado de Usuarios</h1>
        
        <div class = "container">
            <div class = "row">
                <a class="btn btn-primary col-4 m-4" href="UsersController?accion=nuevo">Agregar usuario</a>
                <table class="table table-primary">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NOMBRE</th>
                            <th>APELLIDO</th>
                            <th>E-MAIL</th>
                            <th>MODIFICAR</th>
                            <th>ELIMINAR</th>
                        </tr>
                    </thead>
                    <!-- Antes de cirre de la tabla inseertamos codigo Java el que me va adevolver infomacionnde la bd  -->
                    <%
                        //codigo java
                        List<Usuarios> resultado=null;
                        UsuariosDAO usuario=new UsuariosDAO();
                        resultado=usuario.listarUsuarios();

                        for(int i=0;i<resultado.size();i++){
                            String ruta="UsuariosController?accion=modificar="+resultado.get(i).getId();
                            String rutaE="UsuariosController?accion=eliminar="+resultado.get(i).getId();
                            %>
                                <tr>
                                    <td><%=resultado.get(i).getId()%></td>
                                    <td><%=resultado.get(i).getNombre()%></td>
                                    <td><%=resultado.get(i).getApellido()%></td>
                                    <td><%=resultado.get(i).getMail()%></td>
                                    <td><a class="text-success" href=<%=ruta%> >X</a> </td>                        
                                    <td><a class="text-success" href=<%=rutaE%> >X</a> </td>                        
                                </tr>
                            <%
                       }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
