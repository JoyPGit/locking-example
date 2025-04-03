package com.reservation.locking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ticket_details") //
public class Ticket {
    @Id
    @GeneratedValue //
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY) //
    @JoinColumn(name = "bus_id")
    private BusDetails busDetails; // why not bus id?

}
