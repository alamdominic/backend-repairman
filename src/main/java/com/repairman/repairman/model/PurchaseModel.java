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

    // IMPORTANTE: La relación debe estar ANTES de los constructores
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id_customer")
    private CustomerModel customer;

    //Constructor completo (sin ID porque es auto-generado)
    public PurchaseModel(String brand, String phoneStatus, String description, Double price, LocalDate createdAt, CustomerModel customer) {
        this.brand = brand;
        this.phoneStatus = phoneStatus;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
        this.customer = customer;
    }

    //Constructor sin customer (para casos donde se asigne después)
    public PurchaseModel(String brand, String phoneStatus, String description, Double price, LocalDate createdAt) {
        this.brand = brand;
        this.phoneStatus = phoneStatus;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
    }

    //Constructor vacío - Lo usa JPA
    public PurchaseModel() {
    }

    //Getters & Setters
    public Long getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(Long purchaseID) {
        this.purchaseID = purchaseID;
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

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    //Equals & HashCode - ACTUALIZADO para incluir customer
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PurchaseModel that)) return false;
        return Objects.equals(purchaseID, that.purchaseID)
                && Objects.equals(brand, that.brand)
                && Objects.equals(phoneStatus, that.phoneStatus)
                && Objects.equals(description, that.description)
                && Objects.equals(price, that.price)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseID, brand, phoneStatus, description, price, createdAt, customer);
    }

    //Método ToString - ACTUALIZADO para incluir customer
    @Override
    public String toString() {
        return "PurchaseModel{" +
                "purchaseID=" + purchaseID +
                ", brand='" + brand + '\'' +
                ", phoneStatus='" + phoneStatus + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", customer=" + (customer != null ? customer.getCustomerID() : "null") +
                '}';
    }
}