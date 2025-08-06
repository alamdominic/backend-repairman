package com.repairman.repairman.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "purchases")
public class PurchaseModel {

    //Atributos de clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_ID")
    private Long purchaseID;

    @Column(nullable = false )
    private String costumerID;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String phoneStatus;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(name = "createdAt", nullable = false, columnDefinition = "DATETIME")
    private LocalDate createdAt;

    //Constructor
    public PurchaseModel(Long purchaseID, String costumerID, String brand, String phoneStatus, String description, Double price, LocalDate createdAt) {
        this.purchaseID = purchaseID;
        this.costumerID = costumerID;
        this.brand = brand;
        this.phoneStatus = phoneStatus;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
    }

    //Lo usa JPA
    public PurchaseModel() {
    }

    //Getters & Setters
    public Long getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(Long purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getCostumerID() {
        return costumerID;
    }

    public void setCostumerID(String costumerID) {
        this.costumerID = costumerID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(String phoneStatus) {
        this.phoneStatus = phoneStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    //Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PurchaseModel that)) return false;
        return Objects.equals(purchaseID, that.purchaseID) && Objects.equals(costumerID, that.costumerID)
                && Objects.equals(brand, that.brand) && Objects.equals(phoneStatus, that.phoneStatus)
                && Objects.equals(description, that.description) && Objects.equals(price, that.price)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseID, costumerID, brand, phoneStatus, description, price, createdAt);
    }

    //Método ToString
    @Override
    public String toString() {
        return "PurchaseModel{" +
                "purchaseID=" + purchaseID +
                ", costumerID='" + costumerID + '\'' +
                ", brand='" + brand + '\'' +
                ", phoneStatus='" + phoneStatus + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "purchase_id_customer")
    private CustomerModel customer;
}
