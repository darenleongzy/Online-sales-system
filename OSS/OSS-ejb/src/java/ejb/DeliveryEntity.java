/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Daren
 */
@Entity(name="Delivery")
public class DeliveryEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private Long trackingNum;
    private String shippingTime;
    private String deliveryDate;
    
    @OneToMany(cascade={CascadeType.PERSIST})
    private Collection<DeliveryLineEntity> deliveryLine = new ArrayList<DeliveryLineEntity>();
    
    public DeliveryEntity() {
        setId(System.nanoTime());
    }
    
    public void create(Long trackingNum, String shippingTime, String deliveryDate) {
        this.setTrackingNum(trackingNum);
        this.setShippingTime(shippingTime);
        this.setDeliveryDate(deliveryDate);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getTrackingNum() {
        return trackingNum;
    }
    
    public void setTrackingNum(Long trackingNum) {
        this.trackingNum = trackingNum;
    }
    
    public String getShippingTime() {
        return shippingTime;
    }
    
    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }
    
    public String getDeliveryDate(){
        return deliveryDate;
    }
    
    public void setDeliveryDate(String deliveryDate){
        this.deliveryDate = deliveryDate;
    }
    
    public Collection<DeliveryLineEntity> getDeliveryLine() {
        return deliveryLine;
    }
    
    public void setDeliveryLine(Collection<DeliveryLineEntity> deliveryLine){
        this.deliveryLine = deliveryLine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryEntity)) {
            return false;
        }
        DeliveryEntity other = (DeliveryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.DeliveryEntity[ id=" + id + " ]";
    }
    
}
