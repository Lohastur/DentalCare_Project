package com.pabloreyes.clinic.model.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @SequenceGenerator(name = "sec_user", sequenceName = "sec_user", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_user")
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id")
//    private Role role;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
