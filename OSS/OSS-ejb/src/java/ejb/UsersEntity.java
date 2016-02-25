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
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Daren
 */
@Entity(name="Users")
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String userName;
    private String password;
    private String contactNumber;
    private String email;
    private String address;
    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "User")
    private Collection<RequestEntity> requests = new ArrayList<RequestEntity>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "User")
    private Collection<OrdersEntity> orders = new ArrayList<OrdersEntity>();
    
    public UsersEntity() {
    }
    
    public void create(String userName, String password, String contactNumber, String email, String address) {
        this.setUserName(userName);
        this.setPassword(password);
        this.setContactNumber(contactNumber);
        this.setEmail(email);
        this.setAddress(address);
    }
    
    public Collection<RequestEntity> getRequests() {
        return requests;
    }

    public void setRequests(Collection<RequestEntity> requests) {
        this.requests = requests;
    }
    
    public Collection<OrdersEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrdersEntity> orders) {
     
        this.orders = orders;
    }
    
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    /*
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }

    
    

    /*

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.UserEntity[ User Name =" + userName + " ]";
    }
*/
}