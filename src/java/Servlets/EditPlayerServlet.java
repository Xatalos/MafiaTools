/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Game;
import Models.Participant;
import Models.Player;
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
 * A servlet for editing information about a Mafia player
 *
 * @author Teemu Salminen <teemujsalminen@gmail.com>
 */
public class EditPlayerServlet extends BaseServlet {

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

        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        User loggedIn = (User) session.getAttribute("loggedIn");

        String gameidString = request.getParameter("gameid");
        int gameid = Integer.parseInt(gameidString);
        String name = request.getParameter("newname");
        boolean namesuccess = true;

        try {
            if (Player.isNameAvailable(name, gameid) == false) {
                name = request.getParameter("playername");
                setError("That name is already taken!", request);
                namesuccess = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditPlayerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (name == null || name.equals("")) {
            name = request.getParameter("playername");
        }
        String idString = request.getParameter("playerid");
        Player player = null;
        String pointsString = request.getParameter("points");
        String notes = request.getParameter("notes");
        String rolenotes = request.getParameter("rolenotes");
        int playerid = Integer.parseInt(idString);
        int points = 0;
        boolean pointsuccess = false;

        if (!isLoggedIn(session)) {
            showJSP("index.jsp", request, response);
        } else {
            try {
                if (Game.getGame(Player.getPlayer(playerid).getGameid()).getUserID() != loggedIn.getID()) {
                    setError("Stop trying to hack the database!", request);
                    showJSP("index.jsp", request, response);
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditPlayerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                player = Player.getPlayer(playerid);
                points = Integer.parseInt(pointsString);
                pointsuccess = true;
                Player.editPlayer(playerid, name, points, notes, rolenotes, true);
                request.removeAttribute("errorMessage");
            } catch (NumberFormatException ex) {
                setError("You didn't enter a valid number for points!", request);
                try {
                    player = Player.getPlayer(playerid);
                } catch (SQLException ex1) {
                    Logger.getLogger(EditPlayerServlet.class.getName()).log(Level.SEVERE, null, ex1);
                }
                request.setAttribute("player", player);
                request.setAttribute("gameid", gameid);
            } catch (SQLException ex) {
                Logger.getLogger(EditPlayerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (pointsuccess == false) {
                try {
                    Player.editPlayer(playerid, name, points, notes, rolenotes, false);
                } catch (SQLException ex) {
                    Logger.getLogger(EditPlayerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("player", player);
                request.setAttribute("gameid", gameid);
                response.sendRedirect("Player?gameid=" + gameid + "&id=" + playerid);
            } else {
                if (namesuccess == true) {
                    response.sendRedirect("Game?id=" + gameid);
                } else {
                    response.sendRedirect("Player?gameid=" + gameid + "&id=" + playerid);
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