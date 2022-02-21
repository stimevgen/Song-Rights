package com.example.offerdaysongs;

import com.example.offerdaysongs.controller.CompanyController;
import com.example.offerdaysongs.controller.RecordingController;
import com.example.offerdaysongs.controller.SingerController;
import com.example.offerdaysongs.controller.SongRightsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class OfferDaySongsApplicationTests {
    @Autowired
    private CompanyController companyController;
    @Autowired
    private RecordingController recordingController;
    @Autowired
    private SingerController singerController;

    @Autowired
    private SongRightsController songRightsController;

    @Transactional
    @Test
    void contextLoads() {
        System.out.println(companyController.getAll());
        System.out.println(recordingController.getAll());
        System.out.println(singerController.getAll());
        System.out.println(songRightsController.getAll());

        }
    }
