/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;

import models.AngsuranModel;
import repository.AngsuranDAO;
/**
 *
 * @author wtf
 */
public class AngsuranController {
    public static List<AngsuranModel> getAllAngsuranByIdPinjaman(int idPinjaman) {
        return AngsuranDAO.getByPinjamanId(idPinjaman);
    }
}
