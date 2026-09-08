package com.reserveone.lanhua.modules.user_information.dto;

import java.time.LocalTime;

public class UserInformationDTO {
    private Integer idUserInformation;
    private Integer idUser;
    private String numberDni;
    private String address;
    private String userPhone;
    private String contactName;
    private String kinship;
    private String contactPhone;
    private String eps;
    private String rh;
    private String medicConditions;
    private String documentUrl;
    private String epsUrl;
    private LocalTime dateEps;

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
}