package order.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "TT_ORDER")
@NoArgsConstructor
@AllArgsConstructor

public class TtOrder {

    private Long orderId;
    private String orderNumber;
    private List<TtOrderParts> orderList;

    @Id
    @Column(name = "ORDER_ID", nullable = false)
    @EqualsAndHashCode.Include
    public Long getOrderId() {
        return orderId;
    }

    @Basic
    @Column(name = "ORDER_NUMBER", nullable = false)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void getOrderNumber(String orderNumber) {
        this.code = orderNumber;
    }

    @Basic
    @Column(name = "ORDER_LIST", nullable = false)
    public List<TtOrderParts> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<TtOrderParts>  orderList) {
        this.orderList = orderList;
    }

}
