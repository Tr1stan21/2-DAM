package domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "repair")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repair", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_dealership", nullable = false)
    private Dealership idDealership;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private Vehicle idVehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_client")
    private Client idClient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_head_mechanic", nullable = false)
    private HeadMechanic createdByHeadMechanic;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "assigned_mechanic")
    private Mechanic assignedMechanic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_repair_status", nullable = false)
    private RepairStatus idRepairStatus;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "creation_ts", nullable = false)
    private Instant creationTs;

    @Column(name = "start_ts")
    private Instant startTs;

    @Column(name = "end_ts")
    private Instant endTs;

    @Column(name = "estimated_time_hours", nullable = false, precision = 6, scale = 2)
    private BigDecimal estimatedTimeHours;

    @Column(name = "estimated_cost", nullable = false, precision = 12, scale = 2)
    private BigDecimal estimatedCost;

    @Column(name = "final_cost", precision = 12, scale = 2)
    private BigDecimal finalCost;

    @Column(name = "work_type", nullable = false, length = 120)
    private String workType;

    @Lob
    @Column(name = "notes")
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dealership getIdDealership() {
        return idDealership;
    }

    public void setIdDealership(Dealership idDealership) {
        this.idDealership = idDealership;
    }

    public Vehicle getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Vehicle idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public HeadMechanic getCreatedByHeadMechanic() {
        return createdByHeadMechanic;
    }

    public void setCreatedByHeadMechanic(HeadMechanic createdByHeadMechanic) {
        this.createdByHeadMechanic = createdByHeadMechanic;
    }

    public Mechanic getAssignedMechanic() {
        return assignedMechanic;
    }

    public void setAssignedMechanic(Mechanic assignedMechanic) {
        this.assignedMechanic = assignedMechanic;
    }

    public RepairStatus getIdRepairStatus() {
        return idRepairStatus;
    }

    public void setIdRepairStatus(RepairStatus idRepairStatus) {
        this.idRepairStatus = idRepairStatus;
    }

    public Instant getCreationTs() {
        return creationTs;
    }

    public void setCreationTs(Instant creationTs) {
        this.creationTs = creationTs;
    }

    public Instant getStartTs() {
        return startTs;
    }

    public void setStartTs(Instant startTs) {
        this.startTs = startTs;
    }

    public Instant getEndTs() {
        return endTs;
    }

    public void setEndTs(Instant endTs) {
        this.endTs = endTs;
    }

    public BigDecimal getEstimatedTimeHours() {
        return estimatedTimeHours;
    }

    public void setEstimatedTimeHours(BigDecimal estimatedTimeHours) {
        this.estimatedTimeHours = estimatedTimeHours;
    }

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public BigDecimal getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(BigDecimal finalCost) {
        this.finalCost = finalCost;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}