package com.coworking.service;

import com.coworking.model.Spaces;
import com.coworking.repository.SpacesRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {
    private final SpacesRepository spacesRepository;

    public AdminService(SpacesRepository spacesRepository) {
        this.spacesRepository = spacesRepository;
    }

    public void addSpace(String type, double price) {
        Spaces space = new Spaces(type, price);
        spacesRepository.save(space);
    }

    public void deleteSpace(int id) {
        spacesRepository.deleteById(id);
    }

    public List<Spaces> getAllSpaces() {
        return spacesRepository.findAll();
    }
}
