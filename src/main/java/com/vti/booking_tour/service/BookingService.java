package com.vti.booking_tour.service;

import com.vti.booking_tour.entities.Booking;
import com.vti.booking_tour.models.BookingStatus;
import com.vti.booking_tour.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by quan0
 * Date 8/29/2023 - 9:59 PM
 * Description: ...
 */
@Service
public class BookingService {
    final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking insert(Booking booking) {

        booking.setCreatedDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.BOOKING_PENDING);
        return bookingRepository.save(booking);
    }

    public Booking update(Booking booking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(booking.getId());
        if ( optionalBooking.isPresent() ) {
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking delete(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if ( optionalBooking.isPresent() ) {
            Booking foundBooking = optionalBooking.get();
            if ( foundBooking.getStatus() == BookingStatus.BOOKING_PENDING ) {
                foundBooking.setStatus(BookingStatus.BOOKING_CANCEL);
                return bookingRepository.save(foundBooking);
            }
        }
        return null;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> findAllBookingActive() {
        return bookingRepository.findAll().stream().filter
                (booking -> booking.isActive() == true).collect(Collectors.toList());
    }

    public List<Booking> findAllBookingCancel() {

        return bookingRepository.findAll().stream().filter
                (booking-> booking.isCancel() == true).collect(Collectors.toList());
    }


    public List<Booking> findAllBookingFinish() {

        return bookingRepository.findAll().stream().filter
                (booking-> booking.isFinish() == true).collect(Collectors.toList());
    }

    public Optional<Booking> getBookingById(Long id){
        return bookingRepository.findById(id);
    }
}
