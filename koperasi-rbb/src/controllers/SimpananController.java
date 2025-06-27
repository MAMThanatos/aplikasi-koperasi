/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.Date;
import models.SimpananModel;
        
/**
 *
 * @author wtf
 */
public class SimpananController {
    public static boolean insertSimpanan(int idNasabah, int nominalSimpanan, Date tglUangMasuk) {
        return SimpananModel.insertSimpanan(idNasabah, nominalSimpanan, tglUangMasuk);
    }
}
