package prv.zielony.transaction.analyst.classical.dto;

import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * Created by zielony on 26.02.16.
 */
@Embeddable
public class PersonalDataDto {

    private String name;

    private String surname;

    private LocalDate birthDate;

    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
