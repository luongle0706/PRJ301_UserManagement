/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.sp24.weapon.Cart;
import sample.sp24.weapon.CheckoutError;
import sample.sp24.weapon.OrderDAO;
import sample.sp24.weapon.Weapon;

/**
 *
 * @author duclu
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "checkout.jsp";
    private static final OrderDAO orderDao = new OrderDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CheckoutError checkoutError = new CheckoutError();
        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("LOGIN_USER") != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    List<Weapon> unavailableItems = checkOrderQuantity(cart);
                    if (unavailableItems.size() > 0) {
                        StringBuilder str = new StringBuilder();
                        for (Weapon item : unavailableItems) {
                            str.append(item.getName());
                        }
                        checkoutError.setQuantityError("There is not enough " + str.toString() + " in the warehouse" );
                        request.setAttribute("CHECKOUT_ERROR", checkoutError);
                    } else {
                        url = SUCCESS;
                    }
                }
            } else {
                request.setAttribute("SESSION_ERROR", "Het phien dang nhap, vui long dang nhap lai!");
            }
        } catch (Exception e) {
            log("Error at CheckoutController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    private List<Weapon> checkOrderQuantity(Cart cart) throws SQLException {
        List<Weapon> list = new ArrayList<>();
        for (Map.Entry<String, Weapon> weapon : cart.getCart().entrySet()) {
            String key = weapon.getKey();
            Weapon value = weapon.getValue();
            boolean isAvailable = orderDao.checkQuantity(value.getId(), value.getQuantity());
            if (!isAvailable) {
                list.add(value);
            }
        }
        return list;
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
