package com.example.offerdaysongs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SongRightsDTO {
    Long id;
    RecordingDto recording;
    CompanyDto company;
    Double price;
}
