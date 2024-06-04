package com.ecommerce.RestEcommerce.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String email;
    private String name;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;
}
