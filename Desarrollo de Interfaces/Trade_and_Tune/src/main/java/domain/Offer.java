package domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_offer", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private Client idClient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private Vehicle idVehicle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sales_employee", nullable = false)
    private SalesEmployee idSalesEmployee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_payment_method", nullable = false)
    private PaymentMethod idPaymentMethod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_offer_status", nullable = false)
    private OfferStatus idOfferStatus;

    @ColumnDefault("(curdate())")
    @Column(name = "offer_date", nullable = false)
    private LocalDate offerDate;

    @Column(name = "validity_date", nullable = false)
    private LocalDate validityDate;

    @Column(name = "base_price_snapshot", nullable = false, precision = 12, scale = 2)
    private BigDecimal basePriceSnapshot;

    @ColumnDefault("0.00")
    @Column(name = "discount_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "final_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal finalPrice;

    @Lob
    @Column(name = "details")
    private String details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Vehicle getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Vehicle idVehicle) {
        this.idVehicle = idVehicle;
    }

    public SalesEmployee getIdSalesEmployee() {
        return idSalesEmployee;
    }

    public void setIdSalesEmployee(SalesEmployee idSalesEmployee) {
        this.idSalesEmployee = idSalesEmployee;
    }

    public PaymentMethod getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(PaymentMethod idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public OfferStatus getIdOfferStatus() {
        return idOfferStatus;
    }

    public void setIdOfferStatus(OfferStatus idOfferStatus) {
        this.idOfferStatus = idOfferStatus;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public BigDecimal getBasePriceSnapshot() {
        return basePriceSnapshot;
    }

    public void setBasePriceSnapshot(BigDecimal basePriceSnapshot) {
        this.basePriceSnapshot = basePriceSnapshot;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}