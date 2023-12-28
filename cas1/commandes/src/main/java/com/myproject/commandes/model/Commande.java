package com.myproject.commandes.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;

    private String description;
    private int quantity;
    private String date_;

    private Double amount;
    public Commande() {
    }
    public Commande(String description, String date_ , int quantity, Double amount) {

        this.description = description;
        this.quantity = quantity;
        this.date_ = date_;
        this.amount = amount;

    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }



    public String getDate() { return date_; }
    public void setDate(String date) { this.date_ = date_; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", quantity='" + quantity + '\'' + ", description='" + description + '\'' + ", date='" + date_ + '\'' + ", amount=" + amount + '}'; }
}
