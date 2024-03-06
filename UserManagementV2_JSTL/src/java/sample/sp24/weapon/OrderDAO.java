/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.sp24.untils.DBUtils;
import sample.sp24.user.UserOrderDTO;

/**
 *
 * @author duclu
 */
public class OrderDAO {

    private static final String INSERT = "INSERT INTO tblOrderDetail (productID, orderID, price, quantity) VALUES (?,?,?,?)";
    private static final String UPDATE_PRODUCT_QUANTITY = "UPDATE tblProducts SET quantity = quantity - ? WHERE productID=?";
    private static final String ORDER = "INSERT INTO tblOrders (orderID, userID, orderDate, total, fullName, phoneNumber, address, email, paymentMethod)"
            + "VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String CHECK_QUANTITY = "SELECT quantity FROM tblProducts WHERE productID=?";

    public boolean submitOrder(UserOrderDTO userOrder) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ORDER);
                ptm.setString(1, userOrder.getOrderID());
                ptm.setString(2, userOrder.getUserID());
                ptm.setString(3, userOrder.getOrderDate());
                ptm.setString(4, "" + userOrder.getTotal());
                ptm.setString(5, userOrder.getFullName());
                ptm.setString(6, userOrder.getPhoneNumber());
                ptm.setString(7, userOrder.getAddress());
                ptm.setString(8, userOrder.getEmail());
                ptm.setString(9, userOrder.getPaymentMethod());

                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insertOrderDetail(OrderDetailDTO orderDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmInsert = null;
        PreparedStatement ptmUpdate = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptmInsert = conn.prepareStatement(INSERT);
                ptmInsert.setString(1, orderDetail.getProductID());
                ptmInsert.setString(2, orderDetail.getOrderID());
                ptmInsert.setString(3, "" + orderDetail.getPrice());
                ptmInsert.setString(4, "" + orderDetail.getQuantity());
                check = ptmInsert.executeUpdate() > 0 ? true : false;
                if (check) {
                    ptmUpdate = conn.prepareStatement(UPDATE_PRODUCT_QUANTITY);
                    ptmUpdate.setString(1, "" + orderDetail.getQuantity());
                    ptmUpdate.setString(2, orderDetail.getProductID());
                    check = ptmUpdate.executeUpdate() > 0 ? true : false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptmUpdate != null) {
                ptmUpdate.close();
            }
            if (ptmInsert != null) {
                ptmInsert.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkQuantity(String id, int quantity) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_QUANTITY);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int storeQuantity = Integer.parseInt(rs.getString("quantity"));
                    if (quantity > storeQuantity) {
                        check = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}
