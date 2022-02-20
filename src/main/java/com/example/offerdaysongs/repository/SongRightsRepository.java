package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.SongRights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SongRightsRepository extends JpaRepository<SongRights, Long>, JpaSpecificationExecutor<SongRights> {
    @Query(value = "select sr.id, sr.recording_id, sr.company_id, sr.price " +
            "       from song_rights sr " +
            "       join company  c on c.id = sr.company_id " +
            "       where " +
            "       c.name like :companyName",nativeQuery = true)
    List<SongRights> findByCompany(@Param("companyName")String companyName);

    @Query(value ="select sr.id, sr.recording_id, sr.company_id, sr.price " +
            "            from song_rights sr " +
            "            join recording r on r.id = sr.recording_id " +
            "            where " +
            "            r.release_time between :date_begin and :date_end",nativeQuery = true)
    List<SongRights> findByPeriod(@Param("date_begin")String date_begin, @Param("date_end")String date_end);
}