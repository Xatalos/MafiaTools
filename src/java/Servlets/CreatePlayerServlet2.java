/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Game;
import Models.Player;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A servlet for creating a Mafia player
 *
 * @author Teemu Salminen <teemujsalminen@gmail.com>
 */
public class CreatePlayerServlet2 extends BaseServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("playername");
        String meta = request.getParameter("meta");
        HttpSession session = request.getSession();
        User loggedIn = (User) session.getAttribute("loggedIn");

        if (!isLoggedIn(session)) {
            showJSP("index.jsp", request, response);
        } else {
            if (name == null || name.equals("")) {
                setError("You didn't give a name!", request);
                showJSP("createplayer.jsp", request, response);
            } else {
                try {
                    if (Player.isNameAvailable(name, loggedIn.getID()) == false) {
                        setError("A player with that name already exists!", request);
                        showJSP("createplayer.jsp", request, response);
                    } else {
                        try {
                            Player.createPlayer(name, meta, loggedIn.getID());
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePlayerServlet2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        request.setAttribute("user", loggedIn);
                        showJSP("Players", request, response);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CreatePlayerServlet2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
