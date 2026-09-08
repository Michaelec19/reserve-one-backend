package com.reserveone.lanhua.modules.user_information.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_information")
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_information")
    private Integer idUserInformation;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "number_dni", length = 20)
    private String numberDni;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "user_phone", length = 20)
    private String userPhone;

    @Column(name = "contact_name", length = 50)
    private String contactName;

    @Column(name = "kinship", length = 20)
    private String kinship;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "EPS", length = 20)
    private String eps;

    @Column(name = "rh", length = 10)
    private String rh;

    @Lob
    @Column(name = "medic_conditions", columnDefinition = "TEXT")
    private String medicConditions;

    @Lob
    @Column(name = "document_url", columnDefinition = "TEXT")
    private String documentUrl;

    @Lob
    @Column(name = "eps_url", columnDefinition = "TEXT")
    private String epsUrl;

    @Column(name = "date_eps")
    private LocalTime dateEps;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public UserInformation() {}

    // Getters y Setters
    public Integer getIdUserInformation() { return idUserInformation; }
    public void setIdUserInformation(Integer idUserInformation) { this.idUserInformation = idUserInformation; }

    public Integer getIdUser() { return idUser; }
    public void setIdUser(Integer idUser) { this.idUser = idUser; }

    public String getNumberDni() { return numberDni; }
    public void setNumberDni(String numberDni) { this.numberDni = numberDni; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }

    public String getKinship() { return kinship; }
    public void setKinship(String kinship) { this.kinship = kinship; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public String getEps() { return eps; }
    public void setEps(String eps) { this.eps = eps; }

    public String getRh() { return rh; }
    public void setRh(String rh) { this.rh = rh; }

    public String getMedicConditions() { return medicConditions; }
    public void setMedicConditions(String medicConditions) { this.medicConditions = medicConditions; }

    public String getDocumentUrl() { return documentUrl; }
    public void setDocumentUrl(String documentUrl) { this.documentUrl = documentUrl; }

    public String getEpsUrl() { return epsUrl; }
    public void setEpsUrl(String epsUrl) { this.epsUrl = epsUrl; }

    public LocalTime getDateEps() { return dateEps; }
    public void setDateEps(LocalTime dateEps) { this.dateEps = dateEps; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
