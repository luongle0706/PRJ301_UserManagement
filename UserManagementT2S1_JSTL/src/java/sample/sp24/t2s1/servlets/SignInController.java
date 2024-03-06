/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.t2s1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.sp24.t2s1.user.UserDAO;
import sample.sp24.t2s1.user.UserDTO;
import sample.sp24.t2s1.user.UserError;

/**
 *
 * @author duclu
 */
@WebServlet(name = "SignInController", urlPatterns = {"/SignInController"})
public class SignInController extends HttpServlet {

    private static final String ERROR = "signIn.jsp";
    private static final String SUCCESS = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        boolean check = true;
        UserDAO dao = new UserDAO();
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = "US";
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            if (userID.length() < 2 || userID.length() > 10) {
                userError.setUserIDError("UserID must be in (2, 10)");
                check = false;
            }
            if (fullName.length() < 5 || fullName.length() > 20) {
                userError.setFullNameError("FullName must be in (5, 20)");
                check = false;
            }
            if (!password.equals(confirm)) {
                userError.setConfirmError("Confirm password does not match!");
                check = false;

            }
            if (check) {
                UserDTO user = new UserDTO(userID, fullName, roleID, password);
                boolean checkInsert = dao.insertV2(user);
                if (checkInsert) {
                    url = SUCCESS;
                } else {
                    userError.setError("Unknown error");
                    request.setAttribute("USER_ERROR", userError);
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at SignInController:" + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setUserIDError("duplicate UserID!");
                request.setAttribute("USER_ERROR", userError);
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
