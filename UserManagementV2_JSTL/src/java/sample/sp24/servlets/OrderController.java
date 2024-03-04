/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.sp24.user.UserDAO;
import sample.sp24.user.UserDTO;
import sample.sp24.user.UserOrderDTO;
import sample.sp24.weapon.Cart;
import sample.sp24.weapon.OrderDAO;
import sample.sp24.weapon.OrderDetailDTO;
import sample.sp24.weapon.Weapon;

/**
 *
 * @author duclu
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

    private static final String ERROR = "checkout.jsp";
    private static final String SUCCESS = "shopping.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserDAO dao = new UserDAO();
        OrderDAO orderDao = new OrderDAO();
        try {
            String fullName = request.getParameter("fullName");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String paymentMethod = request.getParameter("paymentMethod");
            Double total = Double.parseDouble(request.getParameter("total"));
            HttpSession session = request.getSession(false);
            if (session.getAttribute("LOGIN_USER") != null) {
                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    UserOrderDTO userOrder = new UserOrderDTO(user.getUserID(), total, fullName, phoneNumber, address, email, paymentMethod);
                    boolean checkOrder = dao.submitOrder(userOrder);
                    if (checkOrder) {
                        boolean flag = true;
                        for (Map.Entry<String, Weapon> item : cart.getCart().entrySet()) {
                            String key = item.getKey();
                            Weapon value = item.getValue();
                            OrderDetailDTO orderDetail = new OrderDetailDTO(key, userOrder.getOrderID(), value.getPrice(), value.getQuantity());
                            boolean insertOrderDetail = orderDao.insertOrderDetail(orderDetail);
                            if (!insertOrderDetail) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            request.getSession().removeAttribute("CART");
                            url = SUCCESS;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at OderController" + e.toString());
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
