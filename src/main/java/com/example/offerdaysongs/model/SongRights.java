package com.example.offerdaysongs.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SongRights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recording_id", insertable = true, updatable = true)
    Recording recording;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = true, updatable = true)
    Company company;
    Double price;
}
