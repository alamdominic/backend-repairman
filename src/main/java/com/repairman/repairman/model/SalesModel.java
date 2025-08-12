package com.repairman.repairman.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sales")
public class SalesModel {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id") // Correccion from purchase_id a sale_id
    private Long salesID;

    @Column(nullable = false )
    private String brand;

    @Column(nullable = false )
    private String  model;

    @Column(nullable = false )
    private String cellphoneStatus;

    @Column(nullable = false )
    private String description;

    @Column(nullable = false)
    private Double price;

    @CreationTimestamp
    @Column(name = "createdat", nullable = false, updatable = false)
    private LocalDateTime createdat;

    //Constructor leno 1 vacio (Lo usa JPA)
    public SalesModel(Long salesID, String brand, String model, String cellphoneStatus, String description, Double price, LocalDateTime createdat) {
        this.salesID = salesID;    
        this.brand = brand;
        this.model = model;
        this.cellphoneStatus = cellphoneStatus;
        this.description = description;
        this.price = price;
        this.createdat = createdat;
    }

    public SalesModel() {
    }

    //Getter & Setters
    public Long getSalesID() {
        return salesID;
    }

    public void setSalesID(Long salesID) {
        this.salesID = salesID;
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

    public String getCellphoneStatus() {
        return cellphoneStatus;
    }

    public void setCellphoneStatus(String cellphoneStatus) {
        this.cellphoneStatus = cellphoneStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdat;
    }

    public void setCreatedAt(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    //Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SalesModel that)) return false;
        return Objects.equals(salesID, that.salesID) && Objects.equals(brand, that.brand) && Objects.equals(model, that.model) && Objects.equals(cellphoneStatus, that.cellphoneStatus) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(createdat, that.createdat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesID, brand, model, cellphoneStatus, description, price, createdat);
    }


    //Método ToString
    @Override
    public String toString() {
        return "SalesModel{" +
                "salesID=" + salesID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", cellphoneStatus='" + cellphoneStatus + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt=" + createdat +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "sales_id_customer")
    private CustomerModel customer;
}
