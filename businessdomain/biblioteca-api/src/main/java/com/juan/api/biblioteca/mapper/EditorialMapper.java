package com.juan.api.biblioteca.mapper;

import org.mapstruct.Mapper;

import com.juan.api.biblioteca.dto.EditorialDto;
import com.juan.api.biblioteca.entities.Editorial;

@Mapper(componentModel = "spring")
public interface EditorialMapper {
	
	EditorialDto editorialToEditorialDto(Editorial editorial);
	
	Editorial editorialDtoToEditorial(EditorialDto editorialDto);

}
