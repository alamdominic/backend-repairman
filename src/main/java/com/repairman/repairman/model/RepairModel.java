package com.repairman.repairman.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "repairs")
public class RepairModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repair_id")
    private Long repairid;

    @Column(nullable = false)
    private String custumerid;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String issue;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(name = "createdat", nullable = false, columnDefinition = "DATETIME")
    private LocalDate createdat;

    // Relación ManyToOne con CustomerModel
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // FK en tabla repairs
    private CustomerModel customer;

    public RepairModel() {
    }

    public RepairModel(Long repairid, String custumerid, String brand,
                       String issue, String description, Double price,
                       LocalDate createdat, CustomerModel customer) {
        this.repairid = repairid;
        this.custumerid = custumerid;
        this.brand = brand;
        this.issue = issue;
        this.description = description;
        this.price = price;
        this.createdat = createdat;
        this.customer = customer;
    }

    public Long getRepairid() {
        return repairid;
    }

    public void setRepairid(Long repairid) {
        this.repairid = repairid;
    }

    public String getCustumerid() {
        return custumerid;
    }

    public void setCustumerid(String custumerid) {
        this.custumerid = custumerid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public LocalDate getCreatedat() {
        return createdat;
    }

    public void setCreatedat(LocalDate createdat) {
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
        if (!(o instanceof RepairModel)) return false;
        RepairModel that = (RepairModel) o;
        return Objects.equals(repairid, that.repairid)
                && Objects.equals(custumerid, that.custumerid)
                && Objects.equals(brand, that.brand)
                && Objects.equals(issue, that.issue)
                && Objects.equals(description, that.description)
                && Objects.equals(price, that.price)
                && Objects.equals(createdat, that.createdat)
                && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repairid, custumerid, brand, issue, description, price, createdat, customer);
    }

    @Override
    public String toString() {
        return "RepairModel{" +
                "repairid=" + repairid +
                ", custumerid='" + custumerid + '\'' +
                ", brand='" + brand + '\'' +
                ", issue='" + issue + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdat=" + createdat +
                ", customer=" + customer +
                '}';
    }
}
