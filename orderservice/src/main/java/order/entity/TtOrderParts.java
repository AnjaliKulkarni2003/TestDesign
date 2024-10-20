package order.entity;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TT_ORDERPARTS")
@NoArgsConstructor
@AllArgsConstructor

public class TtOrderParts {

    private Long id;
    private Integer qty;
    private Integer price;


    @Id
    @Column(name = "ORDERPARTS_ID", nullable = false)
    @EqualsAndHashCode.Include
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "ORDERPARTS_QTY", nullable = false)
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Basic
    @Column(name = "ORDERPARTS_PRICE", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
