/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author Daren
 */
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface OSSManagerBeanRemote {
    public void createUser(String userName, String password, String contactNumber, String email, String address);
    public void createRequest(String requestTime, String requestMessage, String status, String comment);
    public void createPayment(String cardType, int cardNum, String cardHolderName, String time);
    public void createDelivery(Long trackingNum, String shippingTime, String deliveryDate);
    public void createDeliveryLine(String quantityDelivered);
    public void createOrderLine(String unitPrice, int quantityOrdered, double totalValue);
    public void createProduct(String productType, String brand, String model, String description, String unitPrice, int stockQuantity, int reorderLevel);
    public void createOrder(String orderTime, String totalValue);
    
    
    public boolean isNewUser(String UserName);
    public String removeUser(String UserName);
    public boolean isNewProduct(String brand, String model);
    public boolean productHasOrders(String brand, String model);
    public void updateProductWithOrders(String brand, String model,String unitPrice, int stockQuantity, int reorderLevel);
    public void updateProduct(String productType, String brand, String model, String description, String unitPrice, int stockQuantity, int reorderLevel);
    public String removeProduct(String brand, String model);
    public String viewOrder(Long orderId);
    public String viewPayment(Long paymentId);
    public boolean readyToShip(Long orderId);
    public String shipOrder(Long orderId, Long trackingNum, String expDeliveryDate);
    public String orderDeliveryReport(Long orderId);
    public String monthlyOrderReport(String brand, String model);
    public String reorderReport();
    public String processRequest();
    public String handleRequest(Long requestId, String comments, String status);
    public String getNewOrders();
    
    public void persist();
    public void remove();

    void createUser(String userName, String password, String parameter1);

    
    

}