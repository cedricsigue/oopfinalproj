package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.format.DateTimeFormatter;

public class RentalManager {
    private static RentalManager instance;
    private List<Rental> rentals;
    private static final String RENTAL_FILE = "rentals.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private RentalManager() {
        rentals = new ArrayList<>();
        loadRentals();
    }

    public static RentalManager getInstance() {
        if (instance == null) {
            instance = new RentalManager();
        }
        return instance;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
        saveRentals();
    }

    public List<Rental> getActiveRentals() {
        return rentals.stream()
                .filter(Rental::isActive)
                .toList();
    }

    public List<Rental> getAllRentals() {
        return new ArrayList<>(rentals);
    }

    public void returnCar(Rental rental, LocalDate returnDate) {
        rental.setReturnDate(returnDate);
        rental.setActive(false);
        saveRentals();
    }

    private void saveRentals() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RENTAL_FILE))) {
            for (Rental rental : rentals) {
                writer.println(String.format("%s|%d|%s|%s|%s|%s|%b",
                        rental.getCarModel(),
                        rental.getPrice(),
                        rental.getCustomerName(),
                        rental.getContactNumber(),
                        rental.getRentalDate().format(DATE_FORMATTER),
                        rental.getReturnDate() != null ? rental.getReturnDate().format(DATE_FORMATTER) : "null",
                        rental.isActive()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRentals() {
        File file = new File(RENTAL_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    String carModel = parts[0];
                    int price = Integer.parseInt(parts[1]);
                    String customerName = parts[2];
                    String contactNumber = parts[3];
                    LocalDate rentalDate = LocalDate.parse(parts[4], DATE_FORMATTER);

                    Rental rental = new Rental(carModel, price, customerName, contactNumber, rentalDate);

                    if (!parts[5].equals("null")) {
                        rental.setReturnDate(LocalDate.parse(parts[5], DATE_FORMATTER));
                    }
                    rental.setActive(Boolean.parseBoolean(parts[6]));

                    rentals.add(rental);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}