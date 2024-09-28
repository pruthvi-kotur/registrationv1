package com.api1.Service;

import com.api1.Repository.RegistrationRepository;
import com.api1.entity.Registration;
import com.api1.exceptions.ResourceNotFound;
import com.api1.payload.RegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
  private RegistrationRepository registrationRepository;
  private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getallreg()
  {
      List<Registration> registrations = registrationRepository.findAll();
      List<RegistrationDto> dtos = registrations.stream().map(r -> maptodto(r)).collect(Collectors.toList());
      return dtos;
  }

    public RegistrationDto createreg(RegistrationDto registrationdto) {

        Registration regentity = maptoentity(registrationdto);
        Registration saved = registrationRepository.save(regentity);
        RegistrationDto dto = maptodto(saved);
        return dto;
    }

    public void delereg(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updatereg(Long id,Registration registartion) {
        Registration reg = registrationRepository.findById(id).get();
        reg.setName(registartion.getName());
        reg.setEmail(registartion.getEmail());
        reg.setMobile(registartion.getMobile());
        Registration savedreg = registrationRepository.save(reg);
        return savedreg;
    }





    Registration maptoentity(RegistrationDto dto)
    {
        Registration registration=modelMapper.map(dto,Registration.class);
       /* Registration registration=new Registration();
        registration.setName(dto.getName());
        registration.setEmail(dto.getEmail());
        registration.setMobile(dto.getMobile());*/
        return registration;
    }

    RegistrationDto maptodto(Registration reg)
    {
        RegistrationDto dto=modelMapper.map(reg,RegistrationDto.class);
        /*RegistrationDto dto=new RegistrationDto();
        dto.setName(reg.getName());
        dto.setEmail(reg.getEmail());
        dto.setMobile(reg.getMobile());*/
        return dto;
    }


    public RegistrationDto getregbyid(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(()->new ResourceNotFound("recored not found with id ="+id));
        return maptodto(registration);
    }
}
