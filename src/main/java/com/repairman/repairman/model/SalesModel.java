package com.repairman.repairman.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // le da un formato de salida más legible
    @JsonIgnore
    @CreationTimestamp
    @Column(name = "createdat", nullable = false, updatable = false)
    private LocalDateTime createdat;

    //Constructor leno 1 vacio (Lo usa JPA)
    public SalesModel(Long salesID, String brand, String model, String cellphoneStatus, String description, Double price, LocalDateTime createdat, CustomerModel customer) {
        this.salesID = salesID;    
        this.brand = brand;
        this.model = model;
        this.cellphoneStatus = cellphoneStatus;
        this.description = description;
        this.price = price;
        this.createdat = createdat;
        this.customer = customer; // Se añade un objeto de tipo CustomerModel
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // formatea la salida del json
    public LocalDateTime getCreatedAt() {
        return createdat;
    }

    public void setCreatedAt(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    // Getter & Setter para el objeto tipo CustomerModel
    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
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
    
    @JsonBackReference // evita serializar el json de nuevo
    @ManyToOne
    @JoinColumn(name = "sales_id_customer")
    private CustomerModel customer;
}
