package com.coviam.metadata.controller.filter;

import com.coviam.metadata.services.ProgramServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sorting")
@CrossOrigin
public class SortingController {

    @Autowired
    private ProgramServices programServices;


    @GetMapping("/getAllProgramVideoSortByTime")
    public ResponseEntity<?> getAllProgramVideoSortByTime(
            @RequestParam(name = "programType") String type,
            @RequestParam(name = "sortType") String sortType,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        if (type.equals("single"))
            if (sortType.equals("older"))
                return ResponseEntity.ok(programServices.getAllSingleVideoProgramSortByOldestFirst(pageNumber, pageSize));
            else
                return ResponseEntity.ok(programServices.getAllSingleVideoProgramSortByNewestFirst(pageNumber, pageSize));

        else if (type.equals("multi"))
            if (sortType.equals("older"))
                return ResponseEntity.ok(programServices.getAllMultiVideoProgramSortByOldestFirst(pageNumber, pageSize));
            else
                return ResponseEntity.ok(programServices.getAllMultiVideoProgramSortByNewestFirst(pageNumber, pageSize));


        else if (sortType.equals("older"))
            return ResponseEntity.ok(programServices.getAllSeasonalVideoProgramSortByOldestFirst(pageNumber, pageSize));
        else
            return ResponseEntity.ok(programServices.getAllSeasonalVideoProgramSortByNewestFirst(pageNumber, pageSize));
    }


    @GetMapping("/getAllProgramVideoSortByName")
    public ResponseEntity<?> getAllProgramVideoSortByName(
            @RequestParam(name = "programType") String type,
            @RequestParam(name = "sortType") String sortType,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {

        if (type.equals("single"))
            if (sortType.equals("desc"))
                return ResponseEntity.ok(programServices.getAllSingleVideoProgramSortByNameDesc(pageNumber, pageSize));
            else
                return ResponseEntity.ok(programServices.getAllSingleVideoProgramSortByNameAsc(pageNumber, pageSize));


        else if (type.equals("multi"))
            if (sortType.equals("desc"))
                return ResponseEntity.ok(programServices.getAllMultiVideoProgramSortByNameDesc(pageNumber, pageSize));
            else
                return ResponseEntity.ok(programServices.getAllMultiVideoProgramSortByNameAsc(pageNumber, pageSize));


        else if (type.equals("seasonal"))
            if (sortType.equals("desc"))
                return ResponseEntity.ok(programServices.getAllSingleVideoProgramSortByNameDesc(pageNumber, pageSize));
            else
                return ResponseEntity.ok(programServices.getAllSingleVideoProgramSortByNameAsc(pageNumber, pageSize));


        return ResponseEntity.ok("Invalid type or sortBy parameter!" + sortType + " " + type);

    }
}






















/*@GetMapping(value = "/getAllSingleVideoProgramSortByNameAsc")
    public ResponseEntity<Page<Program>> getAllSingleVideoProgramSortByNameAsc(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(programServices.getAllSingleVideoProgramSortByNameAsc(pageNumber, pageSize));
    }

    //sort in descending order by program name
    @GetMapping(value = "/getAllSingleVideoProgramSortByNameDesc")
    public ResponseEntity<Page<Program>> getAllSingleVideoProgramSortByNameDesc(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(programServices.getAllSingleVideoProgramSortByNameDesc(pageNumber, pageSize));
    }

    //Sort by newestFirst
    @GetMapping(value = "/getAllSingleVideoProgramSortByNewestFirst")
    public ResponseEntity<Page<Program>> getAllSingleVideoProgramSortByNewestFirst(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(programServices.getAllSingleVideoProgramSortByNewestFirst(pageNumber, pageSize));
    }

    //==========================================================
    //multiple Video Program
    //==========================================================
    //added by Sunil
    //sort in ascending order by program name
    @GetMapping(value = "/getAllMultiVideoProgramSortByNameAsc")
    public ResponseEntity<Page<Program>> getAllMultiVideoProgramSortByNameAsc(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(programServices.getAllMultiVideoProgramSortByNameAsc(pageNumber, pageSize));
    }

    //sort in descending order by program name
    @GetMapping(value = "/getAllMultiVideoProgramSortByNameDesc")
    public ResponseEntity<Page<Program>> getAllMultiVideoProgramSortByNameDesc(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(programServices.getAllMultiVideoProgramSortByNameDesc(pageNumber, pageSize));
    }
*/




/*//Sort by newest program First
    @GetMapping(value = "/getAllMultiVideoProgramSortByNewestFirst")
    public ResponseEntity<Page<Program>> getAllMultiVideoProgramSortByNewestFirst(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(programServices.getAllMultiVideoProgramSortByNewestFirst(pageNumber, pageSize));
    }

    //==========================================================
    //Seasonal Video Program
    //==========================================================
    //getAllSeasonalVideoProgram
    //added by Sunil
    //sort in ascending order by program name
    @GetMapping(value = "/getAllSeasonalVideoProgramSortByNameAsc")
    public ResponseEntity<Page<Program>> getAllSeasonalVideoProgramSortByNameAsc(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(programServices.getAllSeasonalVideoProgramSortByNameAsc(pageNumber, pageSize));
    }

    //sort in descending order by program name
    @GetMapping(value = "/getAllSeasonalVideoProgramSortByNameDesc")
    public ResponseEntity<Page<Program>> getAllSeasonalVideoProgramSortByNameDesc(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(programServices.getAllSeasonalVideoProgramSortByNameDesc(pageNumber, pageSize));
    }

    //Sort by newestFirst
    @GetMapping(value = "/getAllSeasonalVideoProgramSortByNewestFirst")
    public ResponseEntity<Page<Program>> getAllSeasonalVideoProgramSortByNewestFirst(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(programServices.getAllSeasonalVideoProgramSortByNewestFirst(pageNumber, pageSize));
    }*/