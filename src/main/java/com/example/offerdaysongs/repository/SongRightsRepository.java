package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.dto.SongRightDTO;
import com.example.offerdaysongs.model.SongRights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SongRightsRepository extends JpaRepository<SongRights, Long>, JpaSpecificationExecutor<SongRights> {
    @Query(value = "select sr.id, sr.recording_id, sr.company_id, sr.price " +
            "       from song_rights sr " +
            "       join company  c on c.id = sr.company_id " +
            "       where " +
            "       c.name like :companyName", nativeQuery = true)
    List<SongRights> findByCompany(@Param("companyName") String companyName);

    @Query(value = "select sr.id, sr.recording_id, sr.company_id, sr.price " +
            "            from song_rights sr " +
            "            join recording r on r.id = sr.recording_id " +
            "            where " +
            "            r.release_time between :date_begin and :date_end", nativeQuery = true)
    List<SongRights> findByPeriod(@Param("date_begin") String date_begin, @Param("date_end") String date_end);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query(value = "update SONG_RIGHTS set RECORDING_ID =:recording_id, COMPANY_ID = :company_id, " +
            "       PRICE = :price where ID =:id", nativeQuery = true)
    void updateSongRights(@Param("id") long id,
                          @Param("recording_id") long recordingId,
                          @Param("company_id") long companyId,
                          @Param("price") double price);

    @Query(value = "SELECT r.title, s.name AS singer, isnull(c.name,'') AS company, isnull(sr.price,0)" +
            "       FROM RECORDING r" +
            "       left join SONG_RIGHTS  sr on sr.RECORDING_ID = r.ID" +
            "       left join COMPANY c on c.ID = sr.COMPANY_ID " +
            "       left join SINGER s on s.ID = r.SINGER_ID" +
            "       where r.TITLE = :title",nativeQuery = true)
    List<SongRightDTO> getSongRight (@Param("title") String songTitle);
}
