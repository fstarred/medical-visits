package it.stellato.cgm.dao;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import it.stellato.cgm.entity.Visit;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VisitRepository implements PanacheMongoRepository<Visit> {

    public Visit findById(String id){
        return findById(new ObjectId(id));
    }

    public Visit add(Visit visit) {
        persistOrUpdate(visit);
        return visit;
    }

}
