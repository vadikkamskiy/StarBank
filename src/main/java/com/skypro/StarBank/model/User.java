package com.skypro.StarBank.model;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
}
