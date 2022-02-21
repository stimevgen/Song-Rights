package com.example.offerdaysongs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.JoinColumn;

@Data
@AllArgsConstructor
public class SongRightDTO {
    String title;
    String singer;
    String company;
    Double price;
}
