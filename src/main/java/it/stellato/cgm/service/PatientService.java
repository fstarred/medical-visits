package it.stellato.cgm.service;

import it.stellato.cgm.dao.PatientRepository;
import it.stellato.cgm.entity.Patient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PatientService {

    @Inject
    PatientRepository repository;

    public Patient get(final String id) {
        return repository.findById(id);
    }

    public Patient add(Patient patient) {
        return repository.add(patient);
    }

    public Patient.Visit getVisit(final String id, final int index) {
        return repository.getVisit(id, index);
    }

    public Patient addVisit(final String id, final Patient.Visit input) {
        return repository.addVisit(id, input);
    }

    public Patient updateVisit(final String id, final Patient.Visit input, int index) {
        return repository.updateVisit(id, input, index);
    }
}
