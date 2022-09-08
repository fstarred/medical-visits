package it.stellato.cgm.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.smallrye.common.constraint.NotNull;
import it.stellato.cgm.type.VisitReason;
import it.stellato.cgm.type.VisitType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@MongoEntity(collection = "patients")
public class Patient {

    @BsonId
    @JsonbProperty("_id")
    private ObjectId id;

    @NotEmpty
    @Length(min = 2)
    private String name;
    @NotEmpty
    @Length(min = 2)
    private String surname;

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate birthDate;
    @NotEmpty
    @Size(min = 9, max = 9)
    private String socialSecurityNumber;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private Set<Visit> visits = new HashSet<>();

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public static class Visit {

        @NotNull
        @JsonbDateFormat("yyyy-MM-dd HH:mm")
        private LocalDateTime time;
        @Length(max = 200)
        private String familyTree;

        private VisitReason reason;

        private VisitType type;

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
}
