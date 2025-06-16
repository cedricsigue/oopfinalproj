package com.example;

import java.time.LocalDate;

public class Rental {
    private String carModel;
    private int price;
    private String customerName;
    private String contactNumber;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private boolean isActive;

    public Rental(String carModel, int price, String customerName, String contactNumber, LocalDate rentalDate) {
        this.carModel = carModel;
        this.price = price;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.rentalDate = rentalDate;
        this.isActive = true;
    }

    // Getters
    public String getCarModel() {
        return carModel;
    }

    public int getPrice() {
        return price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isActive() {
        return isActive;
    }

    // Setters
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "carModel='" + carModel + '\'' +
                ", price=" + price +
                ", customerName='" + customerName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", isActive=" + isActive +
                '}';
    }
}