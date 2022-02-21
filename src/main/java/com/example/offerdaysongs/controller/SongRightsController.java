package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.CompanyDto;
import com.example.offerdaysongs.dto.RecordingDto;
import com.example.offerdaysongs.dto.SingerDto;
import com.example.offerdaysongs.dto.SongRightsDTO;
import com.example.offerdaysongs.dto.requests.CreateSongRightsRequest;
import com.example.offerdaysongs.dto.requests.UpdateSongRightsRequest;
import com.example.offerdaysongs.model.SongRights;
import com.example.offerdaysongs.service.SongRightsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/songrights")
public class SongRightsController {
    private static final String ID = "id";
    private static final String COMPANY_NAME = "companyName";
    private final SongRightsService songRightsService;

    public SongRightsController(SongRightsService songRightsService) {
        this.songRightsService = songRightsService;
    }

    @GetMapping("/{id:[\\d]+}")
    public SongRightsDTO get(@PathVariable(ID) long id) {
        var songRights = songRightsService.getById(id);
        return convertToDto(songRights);
    }

    @GetMapping("/company/{companyName}")
    public List<SongRightsDTO> getSongRightsByCompany(@PathVariable(COMPANY_NAME) String companyName) {
        return songRightsService.getSongRightsByCompany(companyName).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/period")
    public List<SongRightsDTO> getSongRightsByPeriod(@RequestParam String dateBegin, @RequestParam String dateEnd) {
        return songRightsService.getSongRightsByPeriod(dateBegin, dateEnd).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    public List<SongRightsDTO> getAll() {
        return songRightsService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public SongRightsDTO crate(@RequestBody CreateSongRightsRequest request) {
        return convertToDto(songRightsService.create(request));
    }

    @PutMapping("/")
    public SongRightsDTO update(@RequestBody UpdateSongRightsRequest request){
        return convertToDto(songRightsService.update(request));
    }

    private SongRightsDTO convertToDto(SongRights songRights) {
        var company = songRights.getCompany();
        CompanyDto companyDto = company != null ? new CompanyDto(company.getId(), company.getName()) : null;
        var recording = songRights.getRecording();
        if (recording != null) {
            var singer = recording != null ? recording.getSinger() : null;
            RecordingDto recordingDto = new RecordingDto(recording.getId(),
                    recording.getTitle(),
                    recording.getVersion(),
                    recording.getReleaseTime(),
                    singer != null ? new SingerDto(singer.getId(), singer.getName()) : null);
            return new SongRightsDTO(songRights.getId(), recordingDto, companyDto, songRights.getPrice());
        } else {
            return new SongRightsDTO(songRights.getId(), null, companyDto, songRights.getPrice());
        }
    }
}
