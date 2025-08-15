package com.repairman.repairman.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "customers")
public class CustomerModel {
    //Atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerID;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phonenumber;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "createdat", nullable = false, updatable = false)
    private LocalDateTime createdat;


    //Constructor lleno y vacio (Lo usa JPA)
    public CustomerModel(Long customerID, String username, String firstName, String lastName, String email, String password, String phoneNumber, LocalDateTime createdat) {
        this.customerID = customerID;
        this.username = username;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
        this.phonenumber = phoneNumber;
        this.createdat = createdat;
    }

    public CustomerModel() {
    }

    //Getter & Setter
    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }

    public void setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    //HashCode & Equals
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CustomerModel that)) return false;
        return Objects.equals(customerID, that.customerID) && Objects.equals(username, that.username)
                && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname)
                && Objects.equals(email, that.email) && Objects.equals(phonenumber, that.phonenumber)
                && Objects.equals(password, that.password) && Objects.equals(createdat, that.createdat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, username, firstname, lastname, email, phonenumber, password, createdat);
    }

    //ToString
    @Override
    public String toString() {
        return "CustomerModel{" +
                "ID=" + customerID +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", password='" + password + '\'' +
                ", date=" + createdat +
                '}';
    }


    //Cardinalidad: Un cliente a muchas reparaciones
    public List<RepairModel> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<RepairModel> repairs) {
        this.repairs = repairs;
    }
    
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List <RepairModel> repairs;


    //Cardinalidad: Un cliente a muchas compras
    public List<PurchaseModel> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseModel> purchases) {
        this.purchases = purchases;
    }

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List <PurchaseModel> purchases;


    //Cardinalidad: Un cliente a muchas ventas
    public List<SalesModel> getSales() {
        return sales;
    }

    public void setSales(List<SalesModel> sales) {
        this.sales = sales;
    }

    @JsonManagedReference // maneja lo que serializa en json
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List <SalesModel> sales;
    /**
     * @JsonManagedReference
     * @JsonBackReference
     * Evitan serializar los objetos json sin entrar en un bucle infinito.
     */
}
