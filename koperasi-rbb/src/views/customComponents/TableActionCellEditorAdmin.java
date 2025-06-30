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
public class TableActionCellEditorAdmin extends DefaultCellEditor {
    private TableActionEventAdmin ev;
    
    public TableActionCellEditorAdmin(TableActionEventAdmin ev) {
        super(new JCheckBox());
        this.ev = ev;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionAdmin action = new PanelActionAdmin();
        
        String status = table.getValueAt(row, 6).toString();
        action.setStatus(status);
        
        action.initEvent(ev, row);
        action.setBackground(table.getSelectionBackground());
        
        return action;
    }
   
}
