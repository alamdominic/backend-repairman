package com.repairman.repairman.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "sales")
public class SalesModel {
    /* base de datos MySQL
    CREATE TABLE VENTA(
    VENTA_ID INT NOT NULL AUTO_INCREMENT,
    CLIENTE_ID INT NULL,
    MARCA VARCHAR(50) NOT NULL,
    MODELO VARCHAR(50) NOT NULL,
    ESTADO_EQUIPO VARCHAR(100) NOT NULL,
    DESCRIPCION TEXT,
    PRECIO DECIMAL(10,2) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (VENTA_ID),
    FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE(CLIENTE_ID)
        ON DELETE SET NULL ON UPDATE CASCADE
    */


    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_ID")
    private Long salesID;

    @Column(nullable = false )
    private String costumerID;

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

    @Column(name = "createdAt", nullable = false, columnDefinition = "DATETIME")
    private LocalDate createdAt;

    //Constructor leno 1 vacio (Lo usa JPA)
    public SalesModel(Long salesID, String costumerID, String brand, String model, String cellphoneStatus,
                      String description, Double price, LocalDate createdAt) {
        this.salesID = salesID;
        this.costumerID = costumerID;
        this.brand = brand;
        this.model = model;
        this.cellphoneStatus = cellphoneStatus;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    //Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SalesModel that)) return false;
        return Objects.equals(salesID, that.salesID) && Objects.equals(costumerID, that.costumerID)
                && Objects.equals(brand, that.brand) && Objects.equals(model, that.model)
                && Objects.equals(cellphoneStatus, that.cellphoneStatus)
                && Objects.equals(description, that.description) && Objects.equals(price, that.price)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesID, costumerID, brand, model, cellphoneStatus, description, price, createdAt);
    }


    //Método ToString
    @Override
    public String toString() {
        return "SalesModel{" +
                "salesID=" + salesID +
                ", costumerID='" + costumerID + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", cellphoneStatus='" + cellphoneStatus + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "sales_id_customer")
    private CustomerModel customer;
}
