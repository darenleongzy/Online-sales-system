/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Daren
 */
@Entity(name="Request")
public class RequestEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long requestId;
    private String requestTime;
    private String requestMessage;
    private String status;
    private String comment;
    @ManyToOne
    private UsersEntity user = new UsersEntity();
    
    public RequestEntity() {
        setRequestId(System.nanoTime());
    }
    
    public void create(String requestTime, String requestMessage, String status, String comment) {
        this.setRequestTime(requestTime);
        this.setRequestMessage(requestMessage);
        this.setStatus(status);
        this.setComment(comment);
    }
    
    
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
    
     public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public String getRequestTime() {
        return requestTime;
    }
    
    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
        
    public String getRequestMessage() {
        return requestMessage;
    }
    
    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String requestStatus) {
        this.status = status;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
        
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequestEntity)) {
            return false;
        }
        RequestEntity other = (RequestEntity) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.RequestEntity[ Request id=" + requestId + " ]";
    }
}

