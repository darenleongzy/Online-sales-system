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

/**
 *
 * @author Daren
 */
@Entity(name="Payment")
public class PaymentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String cardType;
    private int cardNum;
    private String cardHolderName;
    private String time;
    
    public PaymentEntity() {
        setId(System.nanoTime());
    }
    
    public void create(String cardType, int cardNum, String cardHolderName, String time) {
        this.setCardType(cardType);
        this.setCardNum(cardNum);
        this.setCardHolderName(cardHolderName);
        this.setTime(time);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCardType() {
        return cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
    public int getCardNum() {
        return cardNum;
    }
    
    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
    
    public String getCardHolderName() {
        return cardHolderName;
    }
    
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
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
        if (!(object instanceof PaymentEntity)) {
            return false;
        }
        PaymentEntity other = (PaymentEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.PaymentEntity[ id=" + id + " ]";
    }
    
}
