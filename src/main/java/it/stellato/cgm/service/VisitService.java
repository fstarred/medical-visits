package it.stellato.cgm.service;

import it.stellato.cgm.dao.VisitRepository;
import it.stellato.cgm.entity.Visit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class VisitService {

    @Inject
    VisitRepository repository;

    public Visit get(final String id) {
        return repository.findById(id);
    }

    public Visit add(Visit input) {
        return repository.add(input);
    }

}
