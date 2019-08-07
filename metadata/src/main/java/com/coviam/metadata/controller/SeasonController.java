package com.coviam.metadata.controller;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.SeasonRequest;
import com.coviam.metadata.dto.response.SeasonResponse;
import com.coviam.metadata.entity.Season;
import com.coviam.metadata.services.SeasonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/metadata")
@CrossOrigin
public class SeasonController {

    @Autowired
    private SeasonServices seasonServices;

    @PutMapping(value = "/addSeason")
    public ResponseEntity<Season> addSeason(@RequestBody SeasonRequest seasonRequest) {
        return ResponseEntity.ok(seasonServices.addSeason(seasonRequest));
    }

    @PostMapping(value = "/deleteSeasonById")
    public ResponseEntity<Boolean> deleteSeasonById(@RequestBody DeleteRequest deleteRequest) {

        return ResponseEntity.ok(seasonServices.deleteSeasonById(deleteRequest));
    }

    @GetMapping(value = "/getSeasonById")
    public ResponseEntity<?> getSeasonById(@RequestParam(name = "seasonId") String seasonId) {

        Season season = seasonServices.getSeasonById(seasonId);
        return ResponseEntity.ok(season);
    }


    // todo fix this
    @GetMapping(value = "/getSeasonsByProgramId")
    public ResponseEntity<?> getSeasonByProgramId(
            @RequestParam(name = "programId") String programId,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        Page<Season> seasonPage = seasonServices.getSeasonsByProgramId(programId, pageNumber, pageSize);

        return ResponseEntity.ok(seasonPage);
//        if (seasonPage.isEmpty()) {
//            return new ResponseEntity<Page<Season>>(seasonPage, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAllSeasonalVideo")
    public ResponseEntity<Page<Season>> getAllSeasonalVideo(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(seasonServices.getAllSeasons(pageNumber, pageSize));
    }

    @GetMapping("/getAllMultiVideo")
    public ResponseEntity<Page<Season>> getAllMultiVideo(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        return ResponseEntity.ok(seasonServices.getAllMultiVideo(pageNumber, pageSize));
    }

    @RequestMapping(path = "/addSeasonInBulk", method = RequestMethod.POST)
    public ResponseEntity<List<SeasonResponse>> addSeasonByBulk(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String uploadingDir = System.getProperty("user.dir") + "/src/FileUpload/";
        File file = new File(uploadingDir + multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        List<SeasonResponse> seasonResponseList = seasonServices.addSeasonByBulkUpload(file);
        file.delete();
        return ResponseEntity.ok(seasonResponseList);
    }
}
