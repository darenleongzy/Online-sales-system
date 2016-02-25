/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Daren
 */
@Entity(name="Delivery Line")
public class DeliveryLineEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String quantityDelivered;
    @OneToOne(cascade={CascadeType.PERSIST})
    private ProductEntity product;
    
    public DeliveryLineEntity() {
        setId(System.nanoTime());
    }
    
    public void create(String quantityDelivered) {
        this.setQuantityDelivered(quantityDelivered);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getQuantityDelivered() {
        return quantityDelivered;
    }
    
    public void setQuantityDelivered(String quantityDelivered) {
        this.quantityDelivered = quantityDelivered; 
    }
    
    public ProductEntity getProduct() {
        return product;
    }
    
    public void setProduct(ProductEntity product) {
        this.product = product;
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
        if (!(object instanceof DeliveryLineEntity)) {
            return false;
        }
        DeliveryLineEntity other = (DeliveryLineEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.DeliveryLineEntity[ id=" + id + " ]";
    }
    
}
