package com.skypro.starbank.model;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
}
