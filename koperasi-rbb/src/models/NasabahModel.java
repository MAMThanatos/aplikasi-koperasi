/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import enums.StatusNasabahEnum;

/**
 *
 * @author wtf
 */

public class NasabahModel {
    private int id;
    private String nama;
    private String username;
    private String hashedPassword;
    private int idJabatan;
    private String jabatan;
    private String status;

    public NasabahModel() {
    }

    public NasabahModel(int id, String nama, String username, String hashedPassword, int idJabatan, String jabatan, String status) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.idJabatan = idJabatan;
        this.jabatan = jabatan;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(int idJabatan) {
        this.idJabatan = idJabatan;
    }
    
    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(StatusNasabahEnum status) {
        this.status = status.getLabel();
    }
}
