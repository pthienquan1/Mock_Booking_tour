package com.vti.booking_tour.controller;

import com.vti.booking_tour.entities.Booking;
import com.vti.booking_tour.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by quan0
 * Date 8/31/2023 - 7:12 PM
 * Description: ...
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingService.findAllBookingActive();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookById(@PathVariable Long id){
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking){
        Booking createdBooking = bookingService.insert(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking){
        Optional<Booking> existingBook = bookingService.getBookingById(id);
        if(existingBook.isPresent()){
            booking.setId(id);
            Booking bookingUpdate = bookingService.update(booking);
            return ResponseEntity.ok(bookingUpdate);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable Long id){
        Optional<Booking> existingBook = bookingService.getBookingById(id);
        if(existingBook.isPresent()){
            bookingService.delete(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


}
