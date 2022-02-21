package com.example.offerdaysongs.service;

import com.example.offerdaysongs.dto.SongRightDTO;
import com.example.offerdaysongs.dto.requests.CreateSongRightsRequest;
import com.example.offerdaysongs.dto.requests.UpdateSongRightsRequest;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.model.Singer;
import com.example.offerdaysongs.model.SongRights;
import com.example.offerdaysongs.repository.*;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialJavaObject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongRightsService {
    private final SongRightsRepository songRightsRepository;
    private final CompanyRepository companyRepository;
    private final RecordingRepository recordingRepository;
    private final SingerRepository singerRepository;

    public SongRightsService(SongRightsRepository songRightsRepository, CompanyRepository companyRepository,
                             RecordingRepository recordingRepository, SingerRepository singerRepository) {
        this.songRightsRepository = songRightsRepository;
        this.companyRepository = companyRepository;
        this.recordingRepository = recordingRepository;
        this.singerRepository = singerRepository;
    }

    public List<SongRights> getAll() {
        return songRightsRepository.findAll();
    }

    public SongRights getById(Long id) {
        return songRightsRepository.getById(id);
    }

    private Recording createRecording(Recording recording) {
        var tmpRecording = new Recording();
        tmpRecording.setTitle(recording.getTitle());
        tmpRecording.setVersion(recording.getVersion());
        tmpRecording.setReleaseTime(recording.getReleaseTime());
        var singerDto = recording.getSinger();
        if (singerDto != null) {
            var singer = singerRepository.findById(singerDto.getId()).orElseGet(() -> {
                var temp = new Singer();
                temp.setName(singerDto.getName());
                return singerRepository.save(temp);
            });
            tmpRecording.setSinger(singer);
        }
        return recordingRepository.save(tmpRecording);
    }

    public List<SongRights> getSongRightsByCompany(String companyName) {
        return songRightsRepository.findByCompany(companyName);
    }

    public List<SongRights> getSongRightsByPeriod(String dateBegin, String dateEnd) {
        return songRightsRepository.findByPeriod(dateBegin, dateEnd);
    }

    @Transactional
    public SongRights create(CreateSongRightsRequest request) {
        SongRights songRights = new SongRights();
        songRights.setPrice(request.getPrice());
        var recordingDto = request.getRecording();
        if (recordingDto != null) {
            var recording = recordingRepository.findById(recordingDto.getId()).orElseGet(() -> createRecording(recordingDto));
            songRights.setRecording(recording);
        }
        var companyDto = request.getCompany();
        if (companyDto != null) {
            var company = companyRepository.findById(companyDto.getId()).orElseGet(() -> {
                var tmpCompany = new Company();
                tmpCompany.setName(companyDto.getName());
                return companyRepository.save(tmpCompany);
            });
            songRights.setCompany(company);
        }
        return songRightsRepository.save(songRights);
    }

    @Transactional
    public SongRights update(UpdateSongRightsRequest request) {
        SongRights songRights = songRightsRepository.getById(request.getId());
        if (songRights != null) {
            var companyDto = request.getCompany();
            var recordingDto = request.getRecording();
            songRightsRepository.updateSongRights(songRights.getId(), recordingDto.getId(), companyDto.getId(), request.getPrice());
        }
        return songRightsRepository.getById(request.getId());
    }

    public List<SongRightDTO> getSongRight(String songTitle) {
        return songRightsRepository.getSongRight(songTitle).stream()
                .map(rightViewDTO -> new SongRightDTO(rightViewDTO.getTitle(), rightViewDTO.getSinger(),
                        rightViewDTO.getCompany(), rightViewDTO.getPrice()))
                .collect(Collectors.toList());
    }

}
