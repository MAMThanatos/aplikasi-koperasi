/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author wtf
 */

public class SimpananModel {
    private int id;
    private int idNasabah;
    private int nominal;
    private Date tanggalUangMasuk;

    public SimpananModel() {
    }

    public SimpananModel(int id, int idNasabah, int nominal, Date tanggalUangMasuk) {
        this.id = id;
        this.idNasabah = idNasabah;
        this.nominal = nominal;
        this.tanggalUangMasuk = tanggalUangMasuk;
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

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public Date getTanggalUangMasuk() {
        return tanggalUangMasuk;
    }

    public void setTanggalUangMasuk(Date tanggalUangMasuk) {
        this.tanggalUangMasuk = tanggalUangMasuk;
    }
}
