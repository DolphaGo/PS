package yogiyo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Entity
class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    @OneToMany(mappedBy="owner")
    private Set<Pet> pets = new HashSet<>();

    protected Person(){}

    public String getFirstName() {
        return firstName;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet){
        pet.setOwner(this);
        this.pets.add(pet);
    }

}

@Entity
class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person owner;

    protected Pet(){}

    public Pet(final String name, final Person owner){
        this.name = name;
        this.owner = owner;
    }

    public String getOwnerName() {
        return owner.getFirstName();
    }

    public Person getOwner(){
        return owner;
    }

    public void setOwner(Person owner){
        this.owner = owner;
    }

    public String getName() {
        return name;
    }
}

class PersonNotFoundException extends RuntimeException {

}

@Service
class PersonService {

    private final EntityManager entityManager;

    public PersonService(final EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public void addPet(Long personId, String petName) {
        Person person = entityManager.find(Person.class, personId);
        if(person == null){
            throw new PersonNotFoundException();
        }

        Pet pet = new Pet(petName, person);
        entityManager.persist(pet);
        person.addPet(pet);
    }
}
