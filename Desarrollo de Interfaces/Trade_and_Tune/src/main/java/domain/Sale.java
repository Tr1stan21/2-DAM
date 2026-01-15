package domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_offer", nullable = false)
    private Offer idOffer;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private Vehicle idVehicle;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "sale_ts", nullable = false)
    private Instant saleTs;

    @Column(name = "final_price_snapshot", nullable = false, precision = 12, scale = 2)
    private BigDecimal finalPriceSnapshot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Offer getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(Offer idOffer) {
        this.idOffer = idOffer;
    }

    public Vehicle getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Vehicle idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Instant getSaleTs() {
        return saleTs;
    }

    public void setSaleTs(Instant saleTs) {
        this.saleTs = saleTs;
    }

    public BigDecimal getFinalPriceSnapshot() {
        return finalPriceSnapshot;
    }

    public void setFinalPriceSnapshot(BigDecimal finalPriceSnapshot) {
        this.finalPriceSnapshot = finalPriceSnapshot;
    }

}