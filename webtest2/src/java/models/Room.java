/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

public class Room {
    private double price;
    private Date   checkin;
    private Date   checkout;
    private User   customer;
    private String roomType;

    public Room() {
    }

    public Room(double price, Date checkin, Date checkout, User customer, String roomType) {
        this.price = price;
        this.checkin = checkin;
        this.checkout = checkout;
        this.customer = customer;
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room{" + "price=" + price + ", checkin=" + checkin + ", checkout=" + checkout + ", customer=" + customer + ", roomType=" + roomType + '}';
    }
    
}
