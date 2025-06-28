/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseConnection;
import enums.StatusPinjamanEnum;
import java.util.Date;

/**
 *
 * @author wtf
 */
public class PinjamanModel {
    private int id;
    private int idNasabah;
    private String namaNasabah;
    private int nominalPinjaman;
    private int tenor;
    private String keterangan;
    private Date tanggalPengajuan;
    private String status;
    private Date tanggalStatus;
 
    public PinjamanModel() {
    }

    public PinjamanModel(int id, int idNasabah, int nominalPinjaman, int tenor, String keterangan, Date tanggalPengajuan) {
        this.id = id;
        this.idNasabah = idNasabah;
        this.nominalPinjaman = nominalPinjaman;
        this.tenor = tenor;
        this.keterangan = keterangan;
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(int idNasabah) {
        this.idNasabah = idNasabah;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }
    
    

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getNominalPinjaman() {
        return nominalPinjaman;
    }

    public void setNominalPinjaman(int nominalPinjaman) {
        this.nominalPinjaman = nominalPinjaman;
    }

    public Date getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(Date tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(StatusPinjamanEnum status) {
        this.status = status.getLabel();
    }

    public Date getTanggalStatus() {
        return tanggalStatus;
    }

    public void setTanggalStatus(Date tanggalStatus) {
        this.tanggalStatus = tanggalStatus;
    }
}
