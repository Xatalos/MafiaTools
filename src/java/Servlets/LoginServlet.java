/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Teemu
 */
public class LoginServlet extends BaseServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        System.out.println("Your user name is " + name);
        System.out.println("Your password is " + password);

        /* Jos kummatkin parametrit ovat null, käyttäjä ei ole edes yrittänyt vielä kirjautua. 
         * Näytetään pelkkä lomake */
        if (name == null || !name.equals("")) {
            showJSP("index.jsp", request, response);
            return;
        }

        //Tarkistetaan että vaaditut kentät on täytetty:
        if (name == null || !name.equals("")) {
            setError("You didn't give a username!", request);
            showJSP("index.jsp", request, response);
            return;
        }

        /* Välitetään näkymille tieto siitä, mikä tunnus yritti kirjautumista */
        request.setAttribute("username", name);

        if (password == null && !password.equals("")) {
            setError("You didn't give a password!", request);
            showJSP("index.jsp", request, response);
            return;
        }

        /* Tarkistetaan onko parametrina saatu oikeat tunnukset */
        if (name.equals("testi") && password.equals("testi")) {
            /* Jos tunnus on oikea, ohjataan käyttäjä HTTP-ohjauksella kissalistaan. */
            HttpSession session = request.getSession(true);
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            try {
                user = User.getUser(name, password);
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (user != null) {
                session.setAttribute("loggedIn", user);
            }
            showJSP("games.jsp", request, response);
        } else {
            /* Väärän tunnuksen syöttänyt saa eteensä lomakkeen ja virheen.
             * Tässä käytetään omalta yläluokalta perittyjä yleiskäyttöisiä metodeja.
             */
            setError("Try again! Your username or password was incorrect.", request);
            showJSP("index.jsp", request, response);
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
