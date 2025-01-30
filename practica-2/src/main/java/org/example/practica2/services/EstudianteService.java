package org.example.practica2.services;

import org.example.practica2.model.Estudiante;
import org.example.practica2.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public void save(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    public Estudiante update(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public void delete(Long id) {
        estudianteRepository.deleteById(id);
    }

    public Estudiante findById(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

}
