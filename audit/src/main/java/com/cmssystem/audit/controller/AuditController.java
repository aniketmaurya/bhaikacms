package com.cmssystem.audit.controller;

import com.cmssystem.audit.dto.AddAuditDto;
import com.cmssystem.audit.dto.AddAuditResponseDto;
import com.cmssystem.audit.dto.AuditDto;
import com.cmssystem.audit.dto.AuditPageDto;
import com.cmssystem.audit.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/audit")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AuditController {

    @Autowired
    private AuditService auditService;

    /**
     * @param addAuditDto entry for audit table
     * @return boolean added,string message
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addAudit")
    public ResponseEntity<?> addAudit(@RequestBody AddAuditDto addAuditDto) {
        AddAuditResponseDto response = auditService.addAudit(addAuditDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param auditPageDto pagenumber and count in page
     * @return auditdto audit data to be displayed
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getAllAudits")
    public ResponseEntity<?> getAllAudits(@RequestBody AuditPageDto auditPageDto) {
        List<AuditDto> response = auditService.getAllAudits(auditPageDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRecentAudits")
    public ResponseEntity<?> getRecentAudits(@RequestParam(value = "size") Integer size) {
        List<AuditDto> response = auditService.getRecentAudits(size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    public EmployeeDto getEmployeeByID(@RequestParam(value = "employeeId") String ID) {
//        Optional<Employee> employee = employeeService.getEmployeeByID(ID);
//        if (employee.isPresent()) {
//            EmployeeDto employeeDto = new EmployeeDto();
//            BeanUtils.copyProperties(employee.get(), employeeDto);
//            return employeeDto;
//        }
//        return null;
//    }

}
