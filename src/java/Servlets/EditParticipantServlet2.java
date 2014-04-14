/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Participant;
import Models.Player;
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
public class EditParticipantServlet2 extends BaseServlet {

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
        String gameidString = request.getParameter("gameid");
        String playeridString = request.getParameter("playerid");
        String pointsString = request.getParameter("points");
        String notes = request.getParameter("notes");
        String meta = request.getParameter("meta");
        int gameid = Integer.parseInt(gameidString);
        int playerid = Integer.parseInt(playeridString);
        int points = 0;
        boolean success = false;
        Participant participant = null;

        if (!isLoggedIn(session)) {
            showJSP("index.jsp", request, response);
        } else {
            try {
                points = Integer.parseInt(pointsString);
                success = true;
                Participant.editParticipant(gameid, playerid, points, notes);
            } catch (Exception ex) {
                setError("You didn't enter a valid number for points!", request);
                try {
                    participant = Participant.getParticipant(gameid, playerid);
                } catch (SQLException ex1) {
                    Logger.getLogger(EditParticipantServlet2.class.getName()).log(Level.SEVERE, null, ex1);
                }
                request.setAttribute("participant", participant);
                request.setAttribute("gameid", gameid);
            }
            if (success == false) {
                showJSP("EditParticipant?gameid=" + gameid + "&playerid=" + playerid, request, response);
            } else {
                try {
                    Player.editPlayer(playerid, "", meta);
                } catch (SQLException ex) {
                    Logger.getLogger(EditParticipantServlet2.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("Game?id=" + gameid);
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
