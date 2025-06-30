/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.customComponents;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author wtf
 */
public class TableActionCellEditorNasabah extends DefaultCellEditor {
    private TableActionEventNasabah ev;
    
    public TableActionCellEditorNasabah(TableActionEventNasabah ev) {
        super(new JCheckBox());
        this.ev = ev;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionNasabah action = new PanelActionNasabah();
        
        String status = table.getValueAt(row, 5).toString();
        action.setStatus(status);
        
        action.initEvent(ev, row);
        action.setBackground(table.getSelectionBackground());
        
        return action;
    }
   
}
