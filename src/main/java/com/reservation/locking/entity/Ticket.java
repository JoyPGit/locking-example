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
    // it references the id of the parent table by default, if id in bus is renamed
    // to bus_id, a new col bus_id is created along with id and bus_id is considered the
    // primary key
    // works both ways with or w/o renaming???

}
