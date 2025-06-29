/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import enums.StatusAngsuranEnum;
import java.util.Date;
/**
 *
 * @author wtf
 */
public class AngsuranModel {
    private int id;
    private int idPinjaman;
    private String MetodePembayaran;
    private int idMetodePembayaran;
    private int angsuranKe;
    private int nominalAngsuran;
    private Date tanggalPembayaran;
    private String status;
    
    public AngsuranModel() {
    }

    public AngsuranModel(int id, int idPinjaman, String MetodePembayaran, int idMetodePembayaran, int angsuranKe, int nominalAngsuran, Date tanggalAngsuran, String status) {
        this.id = id;
        this.idPinjaman = idPinjaman;
        this.MetodePembayaran = MetodePembayaran;
        this.idMetodePembayaran = idMetodePembayaran;
        this.angsuranKe = angsuranKe;
        this.nominalAngsuran = nominalAngsuran;
        this.tanggalPembayaran = tanggalAngsuran;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPinjaman() {
        return idPinjaman;
    }

    public void setIdPinjaman(int idPinjaman) {
        this.idPinjaman = idPinjaman;
    }

    public String getMetodePembayaran() {
        return MetodePembayaran;
    }

    public void setMetodePembayaran(String MetodePembayaran) {
        this.MetodePembayaran = MetodePembayaran;
    }
    
    public int getIdMetodePembayaran() {
        return idMetodePembayaran;
    }

    public void setIdMetodePembayaran(int idMetodePembayaran) {
        this.idMetodePembayaran = idMetodePembayaran;
    }

    public int getAngsuranke() {
        return angsuranKe;
    }

    public void setAngsuranKe(int angsuranKe) {
        this.angsuranKe = angsuranKe;
    }

    public int getNominalAngsuran() {
        return nominalAngsuran;
    }

    public void setNominalAngsuran(int nominalAngsuran) {
        this.nominalAngsuran = nominalAngsuran;
    }

    public Date getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(Date tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(StatusAngsuranEnum status) {
        this.status = status.getLabel();
    }
}
