package it.stellato.cgm.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.smallrye.common.constraint.NotNull;
import it.stellato.cgm.type.VisitReason;
import it.stellato.cgm.type.VisitType;
import org.hibernate.validator.constraints.Length;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDateTime;

@MongoEntity(collection = "visits")
public class Visit {

    @NotNull
    @JsonbDateFormat("yyyy-MM-dd HH:mm")
    private LocalDateTime time;
    @Length(max = 200)
    private String familyTree;

    VisitReason reason;

    VisitType type;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getFamilyTree() {
        return familyTree;
    }

    public void setFamilyTree(String familyTree) {
        this.familyTree = familyTree;
    }

    public VisitReason getReason() {
        return reason;
    }

    public void setReason(VisitReason reason) {
        this.reason = reason;
    }

    public VisitType getType() {
        return type;
    }

    public void setType(VisitType type) {
        this.type = type;
    }
}
