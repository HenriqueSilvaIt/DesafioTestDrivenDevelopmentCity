package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {


    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {

        List<City> list  = repository.findAll(Sort.by("name"));

        return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());


    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City entity = new City();
       entity.setId(dto.getId());
       entity.setName(dto.getName());

       entity = repository.save(entity);

       return new CityDTO(entity);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

     if(!repository.existsById(id) ) {
         throw new ResourceNotFoundException("Id não existe");
     }
     try {
         repository.deleteById(id);
     } catch (DataIntegrityViolationException e ){

    throw new DatabaseException("Falha de inetegridade referencial");

     }

    }

}
