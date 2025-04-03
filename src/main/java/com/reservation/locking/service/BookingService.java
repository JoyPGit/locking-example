package com.reservation.locking.service;

import com.reservation.locking.entity.BusDetails;
import com.reservation.locking.entity.Ticket;
import com.reservation.locking.exception.NoSeatsAvailableException;
import com.reservation.locking.repository.BusRepository;
import com.reservation.locking.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    private BusRepository busRepository;
    private TicketRepository ticketRepository;

    @Autowired
    BookingService(BusRepository busRepository, TicketRepository ticketRepository){
        this.busRepository = busRepository;
        this.ticketRepository = ticketRepository;
    }


    /**
     * fetch bus details by id,
     * check capacity
     * sleep will make both methods hit db at the same time
     */
    @Transactional
    public void bookTicket() throws NoSeatsAvailableException, InterruptedException { //
        Optional<BusDetails> busDetails = this.busRepository.findWithLockById(52L);
        if(busDetails.isPresent()){

            this.saveTicket("John", "Allen",
                    "male", busDetails.get()); // enum

            Thread.sleep(1000);
        }
    }

    @Transactional
    public void bookTicket1() throws NoSeatsAvailableException, InterruptedException { //
        Optional<BusDetails> busDetails = this.busRepository.findWithLockById(52L);
        if(busDetails.isPresent()){

            this.saveTicket("Mary", "Allen",
                    "female", busDetails.get()); // enum

            Thread.sleep(1000);
        }
    }

    /**
     * handle throws
     * @param firstName
     * @param lastName
     * @param busDetails
     * @throws NoSeatsAvailableException
     */
    private void saveTicket(String firstName, String lastName, String gender,
                            BusDetails busDetails) throws NoSeatsAvailableException {
        if(busDetails.getCapacity() <= busDetails.getTickets().size()){
            throw new NoSeatsAvailableException();
        }

        Ticket ticket = new Ticket();
        ticket.setFirstName(firstName);
        ticket.setLastName(lastName);
        ticket.setGender(gender);
        ticket.setBusDetails(busDetails);

        busDetails.addTicket(ticket);
        ticketRepository.save(ticket);
    }
}
