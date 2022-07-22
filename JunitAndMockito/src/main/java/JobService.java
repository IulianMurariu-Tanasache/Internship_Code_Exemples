import java.util.Optional;

public interface JobService {

    Optional<String> findCurrentJobPosition(Person person);

    default boolean assignJobPosition(Person person, String jobPosition) {
        if(!findCurrentJobPosition(person).isPresent()) {
            person.setJobPosition(jobPosition);

            return true;
        } else {
            return false;
        }
    }

    default boolean personIsEntitledToUnemploymentSupport(Person person) {
        return findCurrentJobPosition(person).isEmpty() || findCurrentJobPosition(person).get()
            .equals("");
    }
}