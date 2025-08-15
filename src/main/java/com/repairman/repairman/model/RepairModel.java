package com.repairman.repairman.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "repairs")
public class RepairModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repair_id")
    private Long repairID;

    //@Column(nullable = false )
    //private String custumerid;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false )
    private String  model;

    @Column(nullable = false)
    private String issue;

    @Column(nullable = false)
    private String description;

    @Column(length = 255, name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Double price;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "createdat", nullable = false, updatable = false)
    private LocalDateTime createdat;

    public RepairModel() {
    }

    public RepairModel(Long repairID, CustomerModel customer, String brand, String model ,String issue, String description, Double price, String imageUrl, LocalDateTime createdat) {
        this.repairID = repairID;
        this.customer = customer;
        this.brand = brand;
        this.model = model;
        this.issue = issue;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.createdat = createdat;
    }

    public Long getRepairid() {
        return repairID;
    }

    public void setRepairid(Long repairid) {
        this.repairID = repairid;
    }

  

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // formatea la fecha
    public LocalDateTime getCreatedat() {
        return createdat;
    }

    public void setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RepairModel that)) return false;
        return Objects.equals(repairID, that.repairID) && Objects.equals(brand, that.brand) && Objects.equals(model, that.model) && Objects.equals(issue, that.issue) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(createdat, that.createdat) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repairID, customer, brand, model, issue, description, price, imageUrl, createdat, customer);
    }

    @Override
    public String toString() {
        return "RepairModel{" +
                "repairid=" + repairID +
                '\'' +
                ", brand='" + brand + '\'' +
                ", issue='" + issue + '\'' +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdat=" + createdat +
                ", customer=" + customer +
                '}';
    }

    //Cardinalidad: Muchas reparaciones a un solo usuario
    @JsonBackReference // evita serializar el json de nuevo
    @ManyToOne
    @JoinColumn(name = "repair_id_customer")
    private CustomerModel customer;


}
