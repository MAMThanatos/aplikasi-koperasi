/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.MetodePembayaranModel;
import repository.metodePembayaranDAO;

/**
 *
 * @author wtf
 */
public class PembayaranController {
    public static List<MetodePembayaranModel> getAllMetodePembayaran() {
        return metodePembayaranDAO.getAll();
    }
}
