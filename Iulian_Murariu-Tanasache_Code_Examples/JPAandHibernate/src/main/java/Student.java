import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="STUDENT", schema="JPA_LEARN")
@Data // not recommended for jpa?
@RequiredArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Table -> bad | Identity -> auto increment in mysql | sequence -> is best but not supported by mysql
    private Long id = null;

    @NonNull
    @Column(name="name", length=50, nullable=false, unique=true)
    private String name;

    @NonNull
    private int year;
    @NonNull
    private String major;
    @NonNull
    private LocalDate date_of_birth;

    @NonNull
    @Enumerated(EnumType.STRING) // enum will be converted to its name
    private Gender gender;

    @Transient // this will not be included in the table as a column
    private int age;
}
