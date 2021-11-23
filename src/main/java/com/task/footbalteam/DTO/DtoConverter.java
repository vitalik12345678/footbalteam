package com.task.footbalteam.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class DtoConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public DtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T, D extends Convetible> D convertToEntity(T dto, D entity) {
        return modelMapper.map(dto, (Type) entity.getClass());
    }


    public <T, D extends Convetible> T convertToDto(D entity, Type dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }


    public <T, D extends Convetible> D convertFromDtoToDto(T dtoConverted, D dtoConverting) {
        return modelMapper.map(dtoConverted, (Type) dtoConverting.getClass());
    }
}
