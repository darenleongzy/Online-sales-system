/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Daren
 */
@Entity(name ="Orders")
public class OrdersEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private long id;
    private String orderTime;
    private String totalValue;
    
    @ManyToOne
    private UsersEntity user = new UsersEntity();
    @OneToMany(cascade={CascadeType.PERSIST})
    private Collection<OrderLineEntity> orderLine = new ArrayList<OrderLineEntity>();
    @OneToOne(cascade={CascadeType.PERSIST})
    private PaymentEntity payment = new PaymentEntity();
    @OneToOne(cascade={CascadeType.PERSIST})
    private DeliveryEntity delivery;
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(name="Orders_Product")
    private Set<ProductEntity> product = new HashSet<ProductEntity>();
    
    
    public OrdersEntity() {
        setId(System.nanoTime());
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void create(String orderTime, String totalValue) {
        this.setOrderTime(orderTime);
        this.setTotalValue(totalValue);
    }

    public String getOrderTime() {
        return orderTime;
    }
    
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    
    public String getTotalValue(){
        return totalValue;
    }
    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }
    
    
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
    
    public Collection<OrderLineEntity> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(Collection<OrderLineEntity> orderLine) {
        this.orderLine = orderLine;
    }
     public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }
    
    public DeliveryEntity getDelivery() {
        return delivery;
    }
    
    public void setDelivery(DeliveryEntity delivery) {
        this.delivery = delivery;
    }
    
    public Set<ProductEntity> getProduct() {
        return product;
    }
    
    public void setProduct(Set<ProductEntity> product) {
        this.product = product;
    }
    
    /*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderEntity)) {
            return false;
        }
        OrderEntity other = (OrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ejb.OrdersEntity[ id=" + id + " ]";
    }
    */
}
