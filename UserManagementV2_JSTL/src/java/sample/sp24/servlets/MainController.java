/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duclu
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String WELCOME = "login.html";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String CREATE_USER_PAGE = "Create_User_Page";
    private static final String CREATE_USER_PAGE_VIEW = "createUser.html";
    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";
    private static final String SIGN_IN_PAGE = "Sign_In_Page";
    private static final String SIGN_IN_PAGE_VIEW = "signIn.html";
    private static final String SIGN_IN = "SignIn";
    private static final String SIGN_IN_CONTROLLER = "SignInController";
    private static final String LOGIN_GOOGLE = "LoginGoogle";
    private static final String LOGIN_GOOGLE_CONTROLLER = "LoginGoogleController";
    private static final String SHOPPING_PAGE = "Shopping_Page";
    private static final String SHOPPING_PAGE_CONTROLLER = "shopping.html";
    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String VIEW = "View";
    private static final String VIEW_CART_PAGE = "viewCart.jsp";
    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";
    private static final String CHECKOUT = "Checkout";
    private static final String CHECKOUT_CONTROLLER = "checkout.jsp";
    private static final String ORDER = "Order";
    private static final String ORDER_CONTROLLER = "OrderController";
    private static final String EDIT = "Edit";
    private static final String EDIT_CONTROLLER = "EditController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String action = request.getParameter("action");
            if (null != action) {
                switch (action) {
                    case LOGIN:
                        url = LOGIN_CONTROLLER;
                        break;
                    case SEARCH:
                        url = SEARCH_CONTROLLER;
                        break;
                    case DELETE:
                        url = DELETE_CONTROLLER;
                        break;
                    case UPDATE:
                        url = UPDATE_CONTROLLER;
                        break;
                    case LOGOUT:
                        url = LOGOUT_CONTROLLER;
                        break;
                    case CREATE_USER_PAGE:
                        url = CREATE_USER_PAGE_VIEW;
                        break;
                    case CREATE:
                        url = CREATE_CONTROLLER;
                        break;
                    case SIGN_IN_PAGE:
                        url = SIGN_IN_PAGE_VIEW;
                        break;
                    case SIGN_IN:
                        url = SIGN_IN_CONTROLLER;
                        break;
                    case LOGIN_GOOGLE:
                        url = LOGIN_GOOGLE_CONTROLLER;
                        break;
                    case SHOPPING_PAGE:
                        url = SHOPPING_PAGE_CONTROLLER;
                        break;
                    case ADD:
                        url = ADD_CONTROLLER;
                        break;
                    case VIEW:
                        url = VIEW_CART_PAGE;
                        break;
                    case REMOVE:
                        url = REMOVE_CONTROLLER;
                        break;
                    case CHECKOUT:
                        url = CHECKOUT_CONTROLLER;
                        break;
                    case ORDER:
                        url = ORDER_CONTROLLER;
                        break;
                    case EDIT:
                        url = EDIT_CONTROLLER;
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            log("Error at MainController" + e.toString());
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
