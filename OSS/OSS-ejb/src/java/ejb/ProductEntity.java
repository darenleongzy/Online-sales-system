/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Daren
 */
@Entity(name="Product")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String productType;
    private String brand;
    private String model;
    private String description;
    private String unitPrice;
    private int stockQuantity;
    private int reorderLevel;
    @ManyToMany(cascade={CascadeType.ALL}, mappedBy="product")
    private Set<OrdersEntity> orders = new HashSet<OrdersEntity>();
    
    public ProductEntity() {
        setId(System.nanoTime());
    }
    
    public void create(String productType, String brand, String model, String description, String unitPrice, int stockQuantity, int reorderLevel) {
        this.setProductType(productType);
        this.setBrand(brand);
        this.setModel(model);
        this.setDescription(description);
        this.setUnitPrice(unitPrice);
        this.setStockQuantity(stockQuantity);
        this.setReorderLevel(reorderLevel);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProductType(){
        return productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand =brand;
    }

    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public int getReorderLevel() {
        return reorderLevel;
    }
    
    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    
    public Set<OrdersEntity> getOrder() {
        return orders;
    }

    public void setOrder(Set<OrdersEntity> order) {
        this.orders = order;
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
        if (!(object instanceof ProductEntity)) {
            return false;
        }
        ProductEntity other = (ProductEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.ProductEntity[ id=" + id + " ]";
    }
}

    