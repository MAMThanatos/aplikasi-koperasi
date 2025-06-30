/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.nasabah.internalframes;

import controllers.AngsuranController;
import models.PinjamanModel;
import controllers.PinjamanController;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import models.AngsuranModel;
import views.customComponents.TableActionCellEditorNasabah;
import views.customComponents.TableActionCellRenderNasabah;
import views.customComponents.TableActionEventNasabah;

/**
 *
 * @author Aziz
 * @author wtf
 */

public class PinjamanView extends javax.swing.JInternalFrame {
    private int idNasabah;
    
    /**
     * Creates new form PinjamanView
     * @param idNasabah
     */
    
    public PinjamanView(int idNasabah) {
        this.idNasabah = idNasabah;
        
        initComponents();
        loadPinjaman();
        renderPanelAction();
    }

    private void loadPinjaman() {
        List<PinjamanModel> pinjamanList = PinjamanController.getAllPinjamanByid(idNasabah);
        
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }         
        };
        
        model.addColumn("ID");
        model.addColumn("Nominal");
        model.addColumn("Alasan");
        model.addColumn("Tenor (Bulan)");
        model.addColumn("Tanggal Pengajuan");
        model.addColumn("Status");
        model.addColumn("Aksi");
        
        for (PinjamanModel pinjaman : pinjamanList) {
            Object[] rowData = new Object[8];
            rowData[0] = pinjaman.getId();
            rowData[1] = pinjaman.getNominalPinjaman();
            rowData[2] = pinjaman.getKeterangan();
            rowData[3] = pinjaman.getTenor();
            rowData[4] = pinjaman.getTanggalPengajuan();
            rowData[5] = pinjaman.getStatus();

            model.addRow(rowData);
        }
        
        jTable1.setModel(model);
    }
    
    private void renderPanelAction() {
        TableActionEventNasabah ev = new TableActionEventNasabah() {
            @Override
            public void onRefuse(int row) {
                String idStr = jTable1.getValueAt(row, 0).toString();
                int IdPinjaman = Integer.parseInt(idStr);
                
                boolean success = PinjamanController.batalkanPinjaman(IdPinjaman);
                
                if(!success) {
                    JOptionPane.showMessageDialog(views.nasabah.internalframes.PinjamanView.this, "Gagal Mengubah Status!");
                }
                
                loadPinjaman();
                renderPanelAction();
            }

            @Override
            public void onDetail(int row) {
                String id = jTable1.getValueAt(row, 0).toString();
                String nominal = jTable1.getValueAt(row, 1).toString();
                String alasan = jTable1.getValueAt(row, 2).toString();
                String tenor = jTable1.getValueAt(row, 3).toString();
                String tanggal = jTable1.getValueAt(row, 4).toString();
                String status = jTable1.getValueAt(row, 5).toString();

                String detail = String.format(
                    "ID: %s\nNominal: %s\nTenor: %s bulan\nTanggal Pengajuan: %s\nStatus: %s\n\nKeterangan:\n%s",
                    id, nominal, tenor, tanggal, status, alasan
                );

                JTextArea textArea = new JTextArea(detail);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setEditable(false);
                textArea.setCaretPosition(0);
                JScrollPane detailScroll = new JScrollPane(textArea);
                
                javax.swing.JPanel panel = new javax.swing.JPanel();
                panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
                panel.add(detailScroll);

                if (status.equalsIgnoreCase("DISETUJUI")) {
                    detailScroll.setPreferredSize(new java.awt.Dimension(580, 150));
                    
                    JTable table = new JTable();
                    DefaultTableModel model = new DefaultTableModel(
                        new Object[]{"ID", "Angsuran Ke", "Tagihan", "Tanggal Pembayaran", "Status"}, 0
                    );

                    List<AngsuranModel> angsuranList = AngsuranController.getAllAngsuranByIdPinjaman(Integer.parseInt(id));
                    for (AngsuranModel angsuran : angsuranList) {
                        model.addRow(new Object[]{
                            angsuran.getId(),
                            angsuran.getAngsuranke(),
                            angsuran.getNominalAngsuran(),
                            angsuran.getTanggalPembayaran()!= null ? angsuran.getTanggalPembayaran().toString() : "-",
                            angsuran.getStatus()
                        });
                    }

                    table.setModel(model);
                    table.setEnabled(false);
                    JScrollPane tableScroll = new JScrollPane(table);
                    tableScroll.setPreferredSize(new java.awt.Dimension(580, 200));

                    panel.add(javax.swing.Box.createVerticalStrut(10));
                    panel.add(tableScroll);
                } else {
                    detailScroll.setPreferredSize(new java.awt.Dimension(580, 300));

                }

                // Tampilkan dialog
                JOptionPane.showOptionDialog(
                    views.nasabah.internalframes.PinjamanView.this,
                    panel,
                    "Detail Pinjaman",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{"tutup"},
                    "tutup"
                );
            }
        };
                
        jTable1.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRenderNasabah());
        jTable1.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditorNasabah(ev));
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Riwayat Pinjaman");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nominal", "Alasan", "Tenor (Bulan)", "Status", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Ajukan Pinjaman");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JTextField nominalField = new JTextField();
        JSpinner tenorSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 60, 1));
        JTextArea alasanArea = new JTextArea(5, 25);
        alasanArea.setLineWrap(true);
        alasanArea.setWrapStyleWord(true);

        tenorSpinner.setPreferredSize(new Dimension(80, tenorSpinner.getPreferredSize().height));
        
        JScrollPane scrollAlasan = new JScrollPane(alasanArea);
        scrollAlasan.setPreferredSize(new Dimension(300, 100));
        
        JPanel panel = new JPanel(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5); 
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(new JLabel("Nominal Pinjaman"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(nominalField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Tenor"), gbc);
        
        JPanel tenorPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        tenorPanel.add(tenorSpinner);
        tenorPanel.add(new JLabel(" Bulan"));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tenorPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panel.add(new JLabel("Alasan Pengajuan"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(scrollAlasan, gbc);

        Object[] options = {"Ajukan", "Batal"};
        int result = JOptionPane.showOptionDialog(
            this,
            panel,
            "Ajukan Pinjaman",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int nominal = Integer.parseInt(nominalField.getText().trim());
                int tenor = (Integer) tenorSpinner.getValue();
                String alasan = alasanArea.getText().trim();

                if (nominal <= 0 || tenor <= 0) {
                    throw new IllegalArgumentException("Isi semua field dengan benar.");
                }

                boolean success = PinjamanController.ajukanPinjaman(idNasabah, nominal, tenor, alasan); 

                if (success) {
                    JOptionPane.showMessageDialog(this, "Pengajuan berhasil!");
                    loadPinjaman();
                } else {
                    JOptionPane.showMessageDialog(this, "Pengajuan gagal!");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nominal harus berupa angka.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
