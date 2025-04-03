package com.reservation.locking.controller;

import com.reservation.locking.entity.BusDetails;
import com.reservation.locking.repository.BusRepository;
import com.reservation.locking.service.BookingService;
import org.apache.commons.lang3.function.FailableRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController // = @Controller + @ResponseBody
@RequestMapping("/locking/api/v1")
public class BookingController {

    private BookingService bookingService;
    private BusRepository busRepository;

    @Autowired
    BookingController(BookingService bookingService, BusRepository repository){
        this.bookingService = bookingService;
        this.busRepository = repository;
    }

    @GetMapping("/bookTicket")
    public void bookTicket(){
        ExecutorService executorService = Executors.newFixedThreadPool(2); // fixed vs
        // cached
        executorService.execute(run(bookingService::bookTicket));
        executorService.execute(run(bookingService::bookTicket1));
        executorService.shutdown();
        // check why exception issue w/o method reference

    }

    @GetMapping("/addBus")
    public void addBus(@RequestParam String number, @RequestParam int capacity){
        BusDetails busDetails = new BusDetails();
        busDetails.setCapacity(capacity);
        busDetails.setNumber(number);
        busDetails.setDepartureTime(LocalDateTime.now());
        this.busRepository.save(busDetails);
    }

    private Runnable run(FailableRunnable<Exception> runnable){ //
        return () -> {
            try{
                runnable.run();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        };
    }
}
