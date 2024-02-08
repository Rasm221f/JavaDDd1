package JSONex;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
public class Account {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Address address;
    private AccountDetails account;
}

