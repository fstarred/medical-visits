package it.stellato.cgm.dao;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import it.stellato.cgm.entity.Patient;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@ApplicationScoped
public class PatientRepository implements PanacheMongoRepository<Patient> {

    public Patient findById(String id) {
        return findById(new ObjectId(id));
    }

    public Patient add(Patient patient) {
        persistOrUpdate(patient);
        return patient;
    }

    public Patient.Visit getVisit(final String patientId, int index) {
        return Optional.ofNullable(findById(new ObjectId(patientId)))
                .filter(o -> o.getVisits().size() > index)
                .map(patient -> {
                    Patient.Visit[] visits = new Patient.Visit[patient.getVisits().size()];
                    return patient.getVisits().toArray(visits)[index];
                })
                .orElseThrow(NotFoundException::new);
    }

    public Patient updateVisit(String patientId, Patient.Visit visit, int index) {
        return Optional.ofNullable(findById(new ObjectId(patientId)))
                .filter(o -> o.getVisits().size() > index)
                .map(patient -> {
                    Patient.Visit[] visits = new Patient.Visit[patient.getVisits().size()];
                    patient.getVisits().toArray(visits);
                    visits[index] = visit;
                    patient.setVisits(new HashSet<>(Arrays.asList(visits)));
                    update(patient);
                    return patient;
                }).orElseThrow(NotFoundException::new);
    }

    public Patient addVisit(String patientId, Patient.Visit visit) {
        return Optional.ofNullable(findById(new ObjectId(patientId)))
                .map(patient -> {
                    patient.getVisits().add(visit);
                    update(patient);
                    return patient;
                }).orElseThrow(NotFoundException::new);
    }
}