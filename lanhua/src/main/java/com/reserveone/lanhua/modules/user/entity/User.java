package com.reserveone.lanhua.modules.user.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table(name = "users")
@Data 

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idRol;

    @Column(name = "name_user", nullable = false, length = 40)
    private String nameUser;

    @Column(name = "last_name_user", nullable = false, length = 40)
    private String lastNameUser;

    @Column(name = "email_user", nullable = false, length = 100)
    private String emailUser;

    @Column(name = "password_user", nullable = false, length = 255)
    private String passwordUser;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }
}
