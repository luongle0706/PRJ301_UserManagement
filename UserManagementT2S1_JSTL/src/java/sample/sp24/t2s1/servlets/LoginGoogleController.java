/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.t2s1.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import sample.sp24.t2s1.user.Constants;
import sample.sp24.t2s1.user.UserDAO;
import sample.sp24.t2s1.user.UserDTO;
import sample.sp24.t2s1.user.UserGoogleDTO;

/**
 *
 * @author duclu
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String US = "US";
    private static final String USER_PAGE = "user.jsp";
    private static final String AD = "AD";
    private static final String ADMIN_PAGE = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            UserGoogleDTO user = getUserInfo(accessToken);
            UserDAO dao = new UserDAO();
            if (!dao.checkDuplicate(user.getEmail())) {
                UserDTO newUser = new UserDTO(user.getEmail(), user.getFamily_name() + " " + user.getGiven_name(),
                         "US", "1");
                boolean googleInsert = dao.insert(newUser);
                if (googleInsert) {
                    UserDTO loginUser = dao.checkGoogleLogin(user.getEmail());
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", loginUser);
                    String roleID = loginUser.getRoleID();
                    if (US.equals(roleID)) {
                        url = USER_PAGE;
                    } else if (AD.equals(roleID)) {
                        url = ADMIN_PAGE;
                    } else {
                        request.setAttribute("ERROR", "Your role is not supported yet!");
                    }
                } else {
                    request.setAttribute("ERROR", "Error occured! Something is wrong!");
                }
            } else {
                UserDTO loginUser = dao.checkGoogleLogin(user.getEmail());
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                String roleID = loginUser.getRoleID();
                if (US.equals(roleID)) {
                    url = USER_PAGE;
                } else if (AD.equals(roleID)) {
                    url = ADMIN_PAGE;
                } else {
                    request.setAttribute("ERROR", "Your role is not supported yet!");
                }
            }
        } catch (Exception e) {
            log("Error at LoginGoogleController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    private String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
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
