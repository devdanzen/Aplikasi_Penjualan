/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tampilan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author Danish
 */
public class Barang extends javax.swing.JFrame {

    private static final java.util.logging.Logger LOG =
            java.util.logging.Logger.getLogger(Barang.class.getName());

    private final Connection conn = new koneksi().connect();
    private DefaultTableModel tableModel;

    /**
     * Creates new form Barang
     */
    public Barang() {
        initComponents();
        setTitle("Data Barang");
        setupTable();
        muatData("");
    }

    private void setupTable() {
        tableModel = new DefaultTableModel(
                new String[]{"Kode", "Nama Barang", "Jenis", "Harga Beli", "Harga Jual"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tblbrg.setModel(tableModel);
        tblbrg.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblbrg.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblbrg.getColumnModel().getColumn(1).setPreferredWidth(160);
        tblbrg.getColumnModel().getColumn(2).setPreferredWidth(80);
    }

    private void muatData(String keyword) {
        tableModel.setRowCount(0);
        String sql = "SELECT kd_barang, nm_brg, jenis, hargabeli, hargajual "
                   + "FROM barang WHERE kd_barang LIKE ? OR nm_brg LIKE ? "
                   + "ORDER BY kd_barang";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getString("kd_barang"),
                        rs.getString("nm_brg"),
                        rs.getString("jenis"),
                        rs.getLong("hargabeli"),
                        rs.getLong("hargajual")
                    });
                }
            }
        } catch (SQLException ex) {
            pesanError("Gagal memuat data: " + ex.getMessage());
        }
    }

    private void resetForm() {
        txtkode.setText("");
        txtnama.setText("");
        cbjenis.setSelectedIndex(0);
        txthbeli.setText("");
        txthjual.setText("");
        txtcari.setText("");
        tblbrg.clearSelection();
        txtkode.requestFocus();
    }

    private boolean inputValid() {
        if (txtkode.getText().trim().isEmpty()) {
            return peringatan("Kode Barang wajib diisi");
        }
        if (txtnama.getText().trim().isEmpty()) {
            return peringatan("Nama Barang wajib diisi");
        }
        try {
            Long.parseLong(txthbeli.getText().trim());
            Long.parseLong(txthjual.getText().trim());
        } catch (NumberFormatException ex) {
            return peringatan("Harga harus berupa angka");
        }
        return true;
    }

    private boolean peringatan(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Peringatan", JOptionPane.WARNING_MESSAGE);
        return false;
    }

    private void pesanError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblJudul = new javax.swing.JLabel();
        lblKode = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblJenis = new javax.swing.JLabel();
        lblHBeli = new javax.swing.JLabel();
        lblHJual = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        cbjenis = new javax.swing.JComboBox<>();
        txthbeli = new javax.swing.JTextField();
        txthjual = new javax.swing.JTextField();
        bsimpan = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bbatal = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbrg = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("Data Barang");

        lblKode.setText("Kode Barang");
        lblNama.setText("Nama Barang");
        lblJenis.setText("Jenis Barang");
        lblHBeli.setText("Harga Beli");
        lblHJual.setText("Harga Jual");

        cbjenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Makanan", "Minuman" }));

        bsimpan.setText("Simpan");
        bsimpan.addActionListener(this::bsimpanActionPerformed);

        bubah.setText("Ubah");
        bubah.addActionListener(this::bubahActionPerformed);

        bhapus.setText("Hapus");
        bhapus.addActionListener(this::bhapusActionPerformed);

        bbatal.setText("Batal");
        bbatal.addActionListener(this::bbatalActionPerformed);

        bkeluar.setText("Keluar");
        bkeluar.addActionListener(this::bkeluarActionPerformed);

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        bcari.setText("Cari");
        bcari.addActionListener(this::bcariActionPerformed);

        tblbrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbrgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbrg);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKode)
                            .addComponent(lblNama)
                            .addComponent(lblJenis)
                            .addComponent(lblHBeli)
                            .addComponent(lblHJual))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkode, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addComponent(txtnama)
                            .addComponent(cbjenis, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthbeli)
                            .addComponent(txthjual)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bsimpan)
                        .addGap(8, 8, 8)
                        .addComponent(bubah)
                        .addGap(8, 8, 8)
                        .addComponent(bhapus)
                        .addGap(8, 8, 8)
                        .addComponent(bbatal)
                        .addGap(8, 8, 8)
                        .addComponent(bkeluar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(bcari)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblJudul)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKode)
                    .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJenis)
                    .addComponent(cbjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHBeli)
                    .addComponent(txthbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHJual)
                    .addComponent(txthjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsimpan)
                    .addComponent(bubah)
                    .addComponent(bhapus)
                    .addComponent(bbatal)
                    .addComponent(bkeluar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        if (!inputValid()) return;
        String sql = "INSERT INTO barang (kd_barang, nm_brg, jenis, hargabeli, hargajual) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, txtkode.getText().trim());
            ps.setString(2, txtnama.getText().trim());
            ps.setString(3, (String) cbjenis.getSelectedItem());
            ps.setLong(4, Long.parseLong(txthbeli.getText().trim()));
            ps.setLong(5, Long.parseLong(txthjual.getText().trim()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data barang berhasil disimpan");
            muatData("");
            resetForm();
        } catch (SQLIntegrityConstraintViolationException ex) {
            pesanError("Kode barang sudah terpakai");
        } catch (SQLException ex) {
            pesanError("Gagal simpan: " + ex.getMessage());
        }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        if (tblbrg.getSelectedRow() < 0) {
            peringatan("Pilih baris dari tabel terlebih dahulu");
            return;
        }
        if (!inputValid()) return;
        String sql = "UPDATE barang SET nm_brg=?, jenis=?, hargabeli=?, hargajual=? WHERE kd_barang=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, txtnama.getText().trim());
            ps.setString(2, (String) cbjenis.getSelectedItem());
            ps.setLong(3, Long.parseLong(txthbeli.getText().trim()));
            ps.setLong(4, Long.parseLong(txthjual.getText().trim()));
            ps.setString(5, txtkode.getText().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data barang berhasil diubah");
            muatData("");
            resetForm();
        } catch (SQLException ex) {
            pesanError("Gagal ubah: " + ex.getMessage());
        }
    }//GEN-LAST:event_bubahActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        if (txtkode.getText().trim().isEmpty()) {
            peringatan("Pilih baris dari tabel terlebih dahulu");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this,
                "Hapus barang " + txtnama.getText() + "?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (ok != JOptionPane.YES_OPTION) return;
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM barang WHERE kd_barang=?")) {
            ps.setString(1, txtkode.getText().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data barang berhasil dihapus");
            muatData("");
            resetForm();
        } catch (SQLException ex) {
            pesanError("Gagal hapus: " + ex.getMessage());
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        resetForm();
        muatData("");
    }//GEN-LAST:event_bbatalActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        dispose();
    }//GEN-LAST:event_bkeluarActionPerformed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        muatData(txtcari.getText().trim());
    }//GEN-LAST:event_bcariActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        muatData(txtcari.getText().trim());
    }//GEN-LAST:event_txtcariKeyReleased

    private void tblbrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbrgMouseClicked
        int row = tblbrg.getSelectedRow();
        if (row < 0) return;
        txtkode.setText(tableModel.getValueAt(row, 0).toString());
        txtnama.setText(tableModel.getValueAt(row, 1).toString());
        cbjenis.setSelectedItem(tableModel.getValueAt(row, 2).toString());
        txthbeli.setText(tableModel.getValueAt(row, 3).toString());
        txthjual.setText(tableModel.getValueAt(row, 4).toString());
    }//GEN-LAST:event_tblbrgMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            LOG.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Barang().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbjenis;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHBeli;
    private javax.swing.JLabel lblHJual;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblNama;
    private javax.swing.JTable tblbrg;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txthbeli;
    private javax.swing.JTextField txthjual;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtnama;
    // End of variables declaration//GEN-END:variables
}
