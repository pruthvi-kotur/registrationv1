package com.api1.Controller;

import com.api1.Service.RegistrationService;
import com.api1.entity.Registration;
import com.api1.payload.RegistrationDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getallregistrations()
  {
      List<RegistrationDto> dtos = registrationService.getallreg();
      return new ResponseEntity<>(dtos,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> createRegistrations(@Valid @RequestBody RegistrationDto registrationdto, BindingResult result)
  {
      if(result.hasErrors())
      {
          return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
      }
      RegistrationDto dto = registrationService.createreg(registrationdto);
      return new ResponseEntity<>(dto,HttpStatus.CREATED);
  }

  @DeleteMapping
  public ResponseEntity<String> deleteRegistrations(@RequestParam long id)
  {
      registrationService.delereg(id);
      return new ResponseEntity<>("Record deleted",HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Registration> updateRegistrations(@PathVariable Long id, @RequestBody Registration registration)
  {
       Registration savedEntity= registrationService.updatereg(id,registration);
       return new ResponseEntity<>(savedEntity,HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RegistrationDto> getRegistrationsById(@PathVariable Long id)
  {
      RegistrationDto dto=registrationService.getregbyid(id);
      return new ResponseEntity<>(dto,HttpStatus.OK);
  }
}
