/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Game;
import Models.Participant;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A servlet for viewing a single Mafia game
 * 
 * @author Teemu Salminen <teemujsalminen@gmail.com>
 */
public class GameServlet extends BaseServlet {

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

        HttpSession session = request.getSession();
        if (!isLoggedIn(session)) {
            showJSP("index.jsp", request, response);
        }

        String idParam = request.getParameter("id");
        Game game = null;
        int id = 1;
        try {
            id = Integer.parseInt(idParam);
        } catch (Exception e) {
        }
        try {
            game = Game.getGame(id);
        } catch (SQLException ex) {
            Logger.getLogger(GameServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (game != null) {
            List<Participant> participants = null;
            try {
                participants = Participant.getParticipants(id);
            } catch (SQLException ex) {
                Logger.getLogger(DeleteGameServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("participants", participants);
            request.setAttribute("game", game);
            showJSP("game.jsp", request, response);
        } else {
            request.setAttribute("game", null);
            setError("The game was not found!", request);
            List<Game> games = null;
            try {
                games = Game.getGames(User.getUser("testi", "testi"));
            } catch (SQLException ex) {
                Logger.getLogger(GameServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("games", games);
            showJSP("games.jsp", request, response);
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
