/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.UsuariosDAO;
/**
 *
 * @author jorgezubieta
 */
@WebServlet(name = "UsuariosController", urlPatterns = {"/UsuariosController"})
public class UsuariosController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //creamos una accion  qu muestre una lista de usuarios
                UsuariosDAO usuariosDAO=new UsuariosDAO();
                String accion;
                RequestDispatcher dispatcher=null;
                accion=request.getParameter("accion");
                if(accion==null|accion.isEmpty()){
                    //nos devuelve la siguiente vista
                    dispatcher=request.getRequestDispatcher("Vistas/usuarios.jsp");
                }
                dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //requeriminto do post que llama al do get
                doGet(request,response);
            }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
