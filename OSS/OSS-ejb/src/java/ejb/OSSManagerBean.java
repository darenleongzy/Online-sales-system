/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Daren
 */
@Stateless
public class OSSManagerBean implements OSSManagerBeanRemote {

    @PersistenceContext
    private EntityManager em;
    
    private UsersEntity usersEntity;
    private RequestEntity requestEntity;
    private PaymentEntity paymentEntity;
    private DeliveryEntity deliveryEntity;
    private DeliveryLineEntity deliveryLineEntity;
    private OrdersEntity orderEntity;
    private OrderLineEntity orderLineEntity;
    private ProductEntity productEntity;
    //private Set<ModuleEntity> modules;
 
    public OSSManagerBean() {
    }
    
    @Override
    public void createUser(String userName, String password, String contactNumber, String email, String address) {
        usersEntity = new UsersEntity();
        usersEntity.create(userName, password, contactNumber, email, address);
        em.persist(usersEntity);
    }

    @Override
    public void createRequest(String requestTime, String requestMessage, String status, String comment) {
        requestEntity = new RequestEntity();
        requestEntity.create(requestTime, requestMessage, status, comment);
        em.persist(requestEntity);
    }

    @Override
    public void createPayment(String cardType, int cardNum, String cardHolderName, String time) {
        paymentEntity = new PaymentEntity();
        paymentEntity.create(cardType, cardNum, cardHolderName, time);
        em.persist(paymentEntity);
    }

    @Override
    public void createDelivery(Long trackingNum, String shippingTime, String deliveryDate) {
        deliveryEntity = new DeliveryEntity();
        deliveryEntity.create(trackingNum, shippingTime, deliveryDate);
        em.persist(deliveryEntity);
    }

    @Override
    public void createDeliveryLine(String quantityDelivered) {
        deliveryLineEntity = new DeliveryLineEntity();
        deliveryLineEntity.create(quantityDelivered);
        em.persist(deliveryLineEntity);
    }

    @Override
    public void createOrderLine(String unitPrice, int quantityOrdered, double totalValue) {
        orderLineEntity = new OrderLineEntity();
        orderLineEntity.create(unitPrice, quantityOrdered, totalValue);
        em.persist(orderLineEntity);
    }

    @Override
    public void createProduct(String productType, String brand, String model, String description, String unitPrice, int stockQuantity, int reorderLevel) {
        productEntity = new ProductEntity();
        productEntity.create(productType, brand, model, description, unitPrice, stockQuantity, reorderLevel);
        em.persist(productEntity);
    }

    @Override
    public void createOrder(String orderTime, String totalValue) {
        orderEntity = new OrdersEntity();
        orderEntity.create(orderTime, totalValue);
        em.persist(orderEntity);
    }

    @Override
    public boolean isNewUser(String userName) {
        UsersEntity ue = new UsersEntity();
        ue = null;
        System.out.println(userName);
        ue = findUser(userName);
        if (ue == null) {
            return true;
        }
        return false;
    }
    
    private UsersEntity findUser(String username) {
        //UsersEntity u = new UsersEntity();
        //try {
        try {
            usersEntity = em.find(UsersEntity.class, username);
        } catch (EntityNotFoundException e) {
            System.out.println("ERROR: User not found. " + e.getMessage());
            em.remove(usersEntity);
            return null;
        } catch (NoResultException e) {
            System.out.println("ERROR: User does not exist. " + e.getMessage());
            em.remove(usersEntity);
            return null;
        }
        return usersEntity;
    }
            //u = (UserEntity) q.getSingleResult();
            
        /*}
        catch (EntityNotFoundException e) {
            System.out.println("ERROR: User not found. " + e.getMessage());
            em.remove(u);
            return null;
        } 
        catch (NoResultException e) {
            System.out.println("ERROR: User does not exist. " + e.getMessage());
            em.remove(u);
            return null;
        }
        return u;
        */
    
    
    private OrdersEntity findOrder(Long orderId) {
        OrdersEntity oe = new OrdersEntity();
        try {
            orderEntity = em.find(OrdersEntity.class, orderId);
            
            
        } 
        catch (EntityNotFoundException e) {
            System.out.println("ERROR: Order not found. " + e.getMessage());
            
            return null;
        } 
        catch (NoResultException e) {
            System.out.println("ERROR: Order does not exist. " + e.getMessage());
            
            return null;
        }
        return orderEntity;
        
    }
    
    public RequestEntity findRequest(Long requestId) {
        try {
            requestEntity = em.find(RequestEntity.class, requestId);
            
            
        } 
        catch (EntityNotFoundException e) {
            System.out.println("ERROR: Request not found. " + e.getMessage());
            
            return null;
        } 
        catch (NoResultException e) {
            System.out.println("ERROR: Request does not exist. " + e.getMessage());
            
            return null;
        }
        return requestEntity;
        
        
    }

    @Override
    public String removeUser(String UserName) {
        if (findUser(UserName) == null) {
            return "User not found \n";
        }
        usersEntity = em.find(UsersEntity.class, UserName);
        List<OrdersEntity> currOrders;
        List<RequestEntity> currRequests;
        
        /*OrderEntity o = new OrderEntity();
        RequestEntity r = new RequestEntity();
        */
        
        Query q1 = em.createQuery("SELECT o FROM Orders o");
        currOrders = q1.getResultList();
        
        Query q2 = em.createQuery("SELECT r FROM Request r");
        currRequests = q2.getResultList();
        
        if (currRequests.isEmpty() && currOrders.isEmpty()) {
            
            em.remove(usersEntity);
            em.flush();
            em.clear();
            return "User is successfully deleted!\n";
        }
        else {
            for (RequestEntity currReq: currRequests) {
                if (currReq.getUser().getUserName().equals(UserName)) {
                    em.clear();
                    return "User has existing requests or orders, cannot be deleted!\n";
                }
            }
            for (OrdersEntity currOrd: currOrders) {
                if (currOrd.getUser().getUserName().equals(UserName)){
                    em.clear();
                    return "User has existing orders or requests, cannot be deleted!\n";
                }
            }
            
        }
        return "User cannot be deleted!\n";
    }

    @Override
    public boolean isNewProduct(String brand, String model) {
        ProductEntity pe = new ProductEntity();
        pe = null;
        pe = findProduct(brand, model);
        if (pe == null) {
            return true;
        }
        return false;
    }
    
    private ProductEntity findProduct(String brand, String model){
        ProductEntity p = new ProductEntity();
        
        Query q = em.createQuery("SELECT p FROM Product p");
        List<ProductEntity> currProducts = q.getResultList();
        
        for (ProductEntity currPro: currProducts) {
            if (currPro.getBrand().equals(brand) && currPro.getModel().equals(model)) {
                return currPro;
            }
        }
        return null;
    }
        
        
        
        
        
    /*   
        
        try {
            Query q = em.createQuery("SELECT p FROM Product AS p WHERE p.id:=id");
            q.setParameter("id", productId);
            p = (ProductEntity) q.getSingleResult();
            
        }
        catch (EntityNotFoundException e) {
            System.out.println("ERROR: Product not found. " + e.getMessage());
            em.remove(p);
            return null;
        } 
        catch (NoResultException e) {
            System.out.println("ERROR: Product does not exist. " + e.getMessage());
            em.remove(p);
            return null;
        }
        return p;
        
    }
    */
    
    @Override
    public void updateProduct(String productType, String brand, String model, String description, String unitPrice, int stockQuantity, int reorderLevel) {
       
        ProductEntity productToUpdate = findProduct(brand, model);
       
            productToUpdate.setProductType(productType);
            productToUpdate.setBrand(brand);
            productToUpdate.setModel(model);
            productToUpdate.setDescription(description);
            productToUpdate.setUnitPrice(unitPrice);
            productToUpdate.setStockQuantity(stockQuantity);
            productToUpdate.setReorderLevel(reorderLevel);
            
        
        
    }
    @Override
    public void updateProductWithOrders(String brand, String model,String unitPrice, int stockQuantity, int reorderLevel) {
       
        ProductEntity productToUpdate = findProduct(brand, model);
        
        
        productToUpdate.setUnitPrice(unitPrice);
        productToUpdate.setStockQuantity(stockQuantity);
        productToUpdate.setReorderLevel(reorderLevel);
        
        
    }
    @Override
    public boolean productHasOrders(String brand, String model) {
        ProductEntity productToUpdate = findProduct(brand, model);
        Collection<OrdersEntity> currOrders = productToUpdate.getOrder();
        if (currOrders.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
    
    @Override
    public String removeProduct(String brand, String model) {
        if (isNewProduct(brand, model)) {
            return "Product not found!\n";
        }
        else {
                   
            ProductEntity productToRemove = findProduct(brand, model);
            productEntity = em.find(ProductEntity.class, productToRemove.getId());
            List<OrdersEntity> currOrder;
            Query q = em.createQuery("SELECT o FROM Product o");
            currOrder = q.getResultList();
            if (currOrder.isEmpty()) {
                em.remove(productEntity);
                em.flush();
                em.clear();
                return "Product is successfully removed!\n";
            } 
            else {
                return "Product associated with order and cannot be removed!\n";
            }
        }
    }

    @Override
    public String getNewOrders() {
        Query q = em.createQuery("SELECT o from Orders o");
        if (q.getResultList().isEmpty()) {
            return "No new orders";
        }
        else {
            String newOrders = "";
            for (Object obj: q.getResultList()) {
                
                OrdersEntity o = (OrdersEntity) obj;
                if (o.getDelivery() == null){
                    newOrders = "UserName: " + o.getUser().getUserName() + "\n";
                    newOrders += "Email: " + o.getUser().getEmail() + "\n";
                    newOrders += "Address: " + o.getUser().getAddress() + "\n";
                    newOrders += "Order Id: " + o.getId() + "\n";
                    newOrders += "Total Amount: " + o.getTotalValue() + "\n";
                    newOrders += "Order time: " + o.getOrderTime() + "\n";
                    newOrders += "Products: " + "\n";
                    Collection<OrderLineEntity> ordLine = (Set<OrderLineEntity>) o.getOrderLine();
                    for (Object ordL : ordLine) {
                        OrderLineEntity orderL = (OrderLineEntity) ordL;
                        newOrders += "Product Type: " + orderL.getProduct().getProductType() + "\n";
                        newOrders += "Product Brand: " + orderL.getProduct().getBrand() + "\n";
                        newOrders += "Product Model: " + orderL.getProduct().getModel() + "\n";
                        newOrders += "Purchase Quantity: " + orderL.getQuantityOrdered() + "\n";
                        newOrders += "Unit Price: " + orderL.getUnitPrice() + "\n";
                        newOrders += "Total value: " + orderL.getTotalValue() + "\n";
                        newOrders +=  "\n";

                    }
                }
               
            }
             return newOrders;
        }
    }

    @Override
    public String viewPayment(Long orderId) {
        
        String paymentResult = "";
        if(findOrder(orderId) == null) {
            paymentResult = "Order not found! \n"; 
        }
        else {
            OrdersEntity oe = findOrder(orderId);
            paymentResult = "Payment Id: " + oe.getPayment().getId() + "\n";
            paymentResult += "Card Type: " + oe.getPayment().getCardType() + "\n";
            paymentResult += "Card Number " + oe.getPayment().getCardNum() + "\n";
            paymentResult += "Card Holder name: " + oe.getPayment().getCardHolderName() + "\n";
        }
        return paymentResult;
    }
    @Override
    public boolean readyToShip(Long orderId){
        if (findOrder(orderId) == null) {
            System.out.println("Order not found! \n");
            return false;
        }
        OrdersEntity orderToShip = findOrder(orderId);
        if (orderToShip.getDelivery()!= null) {
            System.out.println("Order already Shipped! \n");
            return false;
        }
        else if (orderToShip.getPayment() == null) {
            System.out.println("Order not paid! \n");
            return false;
        }
        else 
            System.out.println("Ready to ship! \n");
        return true;
    }
 
    @Override
    public String shipOrder(Long orderId, Long trackingNum, String expDeliveryDate) {
       
        OrdersEntity orderToShip = findOrder(orderId);

        DeliveryEntity newDelivery = new DeliveryEntity();
        Date currDate = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("ddMMyyyy hh:mm:ss a zzz");
        String shippingDate = ft.format(currDate);
        
        newDelivery.create(trackingNum, expDeliveryDate, shippingDate);
        orderToShip.setDelivery(newDelivery);
        em.persist(orderToShip);
        String shipOrderResult = "Order to be shipped! \n";
        
        return shipOrderResult;
    }

    @Override
    public String orderDeliveryReport(Long orderId) {
        String delRep = "";
        if (findOrder(orderId) == null) {
            delRep = "Order not found! \n";
        }
        OrdersEntity orderReport = findOrder(orderId);
        if (orderReport.getDelivery()== null) {
            delRep = "Order not approved for delivery yet! \n";
        }
        
        else {
            delRep = "Delivery Id: " + orderReport.getDelivery().getId() + "\n";
            delRep += "Tracking Number: " + orderReport.getDelivery().getTrackingNum() + "\n";
            delRep += "Shipping Time: " + orderReport.getDelivery().getShippingTime() +"\n";
            delRep += "Delivery Date: " + orderReport.getDelivery().getDeliveryDate() + "\n";
            delRep += "Products to be Delivered";
            Collection<OrderLineEntity> orderL = orderReport.getOrderLine();
            for (Object o: orderL) {
                OrderLineEntity ol = (OrderLineEntity)o;
                delRep += "Product Type: " + ol.getProduct().getProductType() + "\n";
                delRep += "Product Brand: " + ol.getProduct().getBrand() + "\n";
                delRep += "Product Model: " + ol.getProduct().getModel() + "\n";
                delRep += "Quantity: " + ol.getQuantityOrdered() + "\n";
                delRep += "\n";
            }
        }
        return delRep;
    }

    @Override
    public String monthlyOrderReport(String brand, String model) {
        String ordRep = "";
        
        ProductEntity prod = findProduct(brand, model);
        
        Set<OrdersEntity> orders = prod.getOrder();
        if (orders.isEmpty()) {
            return "No orders for the product";
        }
        for (OrdersEntity o : orders) {
            if (sameMonth(o.getOrderTime())) {
                int productQty = 0;
                Collection<OrderLineEntity> ordL = o.getOrderLine();
                for (OrderLineEntity ole : ordL) {
                    if (ole.getProduct().getId() == prod.getId()) {
                        productQty = ole.getQuantityOrdered();
                        break;
                    }
                }
                ordRep = o.getId() + "," + productQty + "," + o.getOrderTime() + "," + o.getUser().getUserName();
            }
        }
        return ordRep;
    }

    @Override
    public String reorderReport() {
        Query q = em.createQuery("SELECT p FROM Product p");
        String reorderRep = "";
        for (Object o: q.getResultList()) {
            ProductEntity p = (ProductEntity) o;
            if (p.getStockQuantity() <= p.getReorderLevel()) {
                reorderRep += "Product Type: " + p.getProductType() + "\n";
                reorderRep += "Product Brand: " + p.getBrand() + "\n";
                reorderRep += "Product Model: " + p.getModel() + "\n";
                reorderRep += "Current Stock Level: " + p.getStockQuantity() + "\n";
                reorderRep += "Reorder Level: " + p.getReorderLevel() + "\n";
                
            }
            reorderRep += "\n";
        }
        return reorderRep;
    }

    @Override
    public String processRequest() {
        String result = "";
        Query q = em.createQuery("SELECT r FROM Request r");
        List<RequestEntity> requestList = q.getResultList();
        for (RequestEntity request : requestList) {
            if (request.getStatus().equals("Unread") ) {
                result = result + request.getRequestId() + ": " + request.getRequestTime() + ", " + request.getUser().getUserName()
                        + ", " + request.getRequestMessage() + ", " + request.getStatus() + ".\n";
            }
        }
        if (result.equals("")) {
            return "No appeals to show!\n";
        }
        return result;
    }
    @Override
    public String handleRequest(Long requestId, String comments, String status) {
        if (findRequest(requestId) == null) {
            return "Request not found!";
        }
        RequestEntity req = findRequest(requestId);
        req.setComment(comments);
        req.setStatus(status);
        return "Request Updated!";
        
        
    }

    private boolean sameMonth(String date) {
        
        String[] parts = date.split("");
        String part1 = parts[0]; // 004
        if (Integer.parseInt(part1.substring(0,3)) != Calendar.getInstance().get(Calendar.YEAR) +1 ) {
            return false;
        }
        else if (Integer.parseInt(part1.substring(4, 5)) != Calendar.getInstance().get(Calendar.MONTH) +1){
            return false;
        }
        else
            return true;
    }
    @Override
    public void persist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String viewOrder(Long orderId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createUser(String userName, String password, String parameter1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
