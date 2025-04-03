package com.reservation.locking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "bus_details") //
public class BusDetails {
    @Id
    @GeneratedValue
    private Long id; // Long vs long
    private String number;
    private LocalDateTime departureTime;
    private int capacity;

    @OneToMany(mappedBy = "busDetails")
    private Set<Ticket> tickets;

    public void addTicket(Ticket ticket){
        ticket.setBusDetails(this);
        getTickets().add(ticket);
    }
}
