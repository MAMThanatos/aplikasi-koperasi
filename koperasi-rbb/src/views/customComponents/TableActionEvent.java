/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.customComponents;

/**
 *
 * @author wtf
 */
public interface TableActionEvent {
    public void onAccept(int row);
    public void onRefuse(int row);
    public void onDetail(int row);
}
