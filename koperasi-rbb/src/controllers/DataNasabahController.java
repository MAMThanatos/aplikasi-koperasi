/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.NasabahModel;
        
/**
 *
 * @author wtf
 */
public class DataNasabahController {
    public static String[][] getAllNasabah() {
        return NasabahModel.getAllNasabah();
    }
}
