package com.works.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String title;

    @Column(length = 500)
    String detail;

    @Column(length = Integer.MAX_VALUE)
    int noteDay;

}
