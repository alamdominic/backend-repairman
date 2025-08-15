package com.repairman.repairman.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "purchases")
public class PurchaseModel {
    //Atributos de clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long purchaseID;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String phoneModel;

    @Column(nullable = false)
    private String phoneStatus;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(name = "image_url", length = 500) // length = 500 para URLs largas
    private String imageUrl; // Para imagen del teléfono

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // le da un formato de salida más legible
    @JsonIgnore
    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //Constructor leno 1 vacio (Lo usa JPA)
    public PurchaseModel(Long purchaseID, String brand, String phoneModel,
                         String phoneStatus, String description, Double price,
                         String imageUrl, LocalDateTime createdAt, CustomerModel customer) {
        this.purchaseID = purchaseID;
        this.brand = brand;
        this.phoneModel = phoneModel;
        this.phoneStatus = phoneStatus;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.customer = customer;
    }

    public PurchaseModel() {
        //Constructor vacio
    }

    //getter & setters


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

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //Equals & hashcode

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PurchaseModel that)) return false;
        return Objects.equals(purchaseID, that.purchaseID) && Objects.equals(brand, that.brand) && Objects.equals(phoneModel, that.phoneModel) && Objects.equals(phoneStatus, that.phoneStatus) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(createdAt, that.createdAt) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseID, brand, phoneModel, phoneStatus, description, price, imageUrl, createdAt, customer);
    }

    //To string

    @Override
    public String toString() {
        return "PurchaseModel{" +
                "purchaseID=" + purchaseID +
                ", brand='" + brand + '\'' +
                ", phoneModel='" + phoneModel + '\'' +
                ", phoneStatus='" + phoneStatus + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                '}';
    }

    //FK - Relación muchas compras pueden ser hechas por un usuario
    @JsonBackReference // evita serializar el json de nuevo
    @ManyToOne
    @JoinColumn(name = "purchases_id_customer")
    private CustomerModel customer;

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}