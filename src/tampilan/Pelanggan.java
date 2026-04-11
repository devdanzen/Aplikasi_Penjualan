package tampilan;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import koneksi.koneksi;

public class Pelanggan extends JFrame {

    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;

    // Komponen GUI
    private JLabel lblJudul, lblId, lblNama, lblJenis, lblTelp, lblAlamat, lblCari, lblData;
    private JTextField txtid, txtnm, txttelp, txtcari;
    private JTextArea txtalamat;
    private JRadioButton rlaki, rperempuan;
    private ButtonGroup buttonGroup1;
    private JButton bsimpan, bubah, bhapus, bbatal, bkeluar, bcari;
    private JTable tblplgn;
    private JScrollPane scrollPane;

    public Pelanggan() {
        initComponents();
        kosong();
        aktif();
        datatable();
    }

    private void initComponents() {
        // Setup JFrame
        setTitle("Data Pelanggan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550);
        setLayout(null);

        // Judul
        lblJudul = new JLabel("Data Pelanggan");
        lblJudul.setBounds(220, 10, 200, 30);
        add(lblJudul);

        // ID Pelanggan
        lblId = new JLabel("ID Pelanggan");
        lblId.setBounds(20, 50, 120, 25);
        add(lblId);
        txtid = new JTextField();
        txtid.setBounds(150, 50, 200, 25);
        add(txtid);

        // Nama Pelanggan
        lblNama = new JLabel("Nama Pelanggan");
        lblNama.setBounds(20, 90, 120, 25);
        add(lblNama);
        txtnm = new JTextField();
        txtnm.setBounds(150, 90, 200, 25);
        add(txtnm);

        // Jenis Kelamin
        lblJenis = new JLabel("Jenis Kelamin");
        lblJenis.setBounds(20, 130, 120, 25);
        add(lblJenis);
        rlaki = new JRadioButton("Laki-Laki");
        rlaki.setBounds(150, 130, 90, 25);
        add(rlaki);
        rperempuan = new JRadioButton("Perempuan");
        rperempuan.setBounds(250, 130, 100, 25);
        add(rperempuan);
        buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(rlaki);
        buttonGroup1.add(rperempuan);

        // No Telepon
        lblTelp = new JLabel("No. Telepon");
        lblTelp.setBounds(20, 170, 120, 25);
        add(lblTelp);
        txttelp = new JTextField();
        txttelp.setBounds(150, 170, 200, 25);
        add(txttelp);

        // Alamat
        lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(20, 210, 120, 25);
        add(lblAlamat);
        txtalamat = new JTextArea();
        txtalamat.setBounds(150, 210, 200, 60);
        add(txtalamat);

        // Tombol
        bsimpan = new JButton("Simpan");
        bsimpan.setBounds(20, 290, 80, 25);
        add(bsimpan);
        bubah = new JButton("Ubah");
        bubah.setBounds(110, 290, 80, 25);
        add(bubah);
        bhapus = new JButton("Hapus");
        bhapus.setBounds(200, 290, 80, 25);
        add(bhapus);
        bbatal = new JButton("Batal");
        bbatal.setBounds(290, 290, 80, 25);
        add(bbatal);
        bkeluar = new JButton("Keluar");
        bkeluar.setBounds(380, 290, 80, 25);
        add(bkeluar);

        // Cari
        lblData = new JLabel("Data Pelanggan");
        lblData.setBounds(20, 330, 120, 25);
        add(lblData);
        txtcari = new JTextField();
        txtcari.setBounds(150, 330, 150, 25);
        add(txtcari);
        bcari = new JButton("Cari");
        bcari.setBounds(310, 330, 80, 25);
        add(bcari);

        // Tabel
        tblplgn = new JTable();
        scrollPane = new JScrollPane(tblplgn);
        scrollPane.setBounds(20, 370, 550, 130);
        add(scrollPane);

        // Action Listeners
        bsimpan.addActionListener(e -> bsimpanActionPerformed());
        bubah.addActionListener(e -> bubahActionPerformed());
        bhapus.addActionListener(e -> bhapusActionPerformed());
        bbatal.addActionListener(e -> bbatalActionPerformed());
        bkeluar.addActionListener(e -> bkeluarActionPerformed());
        bcari.addActionListener(e -> bcariActionPerformed());
        tblplgn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblplgnMouseClicked();
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });
    }

    protected void aktif() {
        txtid.requestFocus();
    }

    protected void kosong() {
        txtid.setText("");
        txtnm.setText("");
        txttelp.setText("");
        txtalamat.setText("");
        txtcari.setText("");
        buttonGroup1.clearSelection();
    }

    protected void datatable() {
        Object[] Baris = {"ID Pelanggan", "Nama", "Jenis Kelamin", "No. Telepon", "Alamat"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = txtcari.getText();
        try {
            String sql = "SELECT * FROM pelanggan where id like '%" + cariitem + "%' or nmplg like '%" + cariitem + "%' order by id asc";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tabmode.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5)
                });
            }
            tblplgn.setModel(tabmode);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" + e);
        }
    }

    private void bsimpanActionPerformed() {
        String jenis = null;
        if (rlaki.isSelected()) {
            jenis = "Laki-Laki";
        } else if (rperempuan.isSelected()) {
            jenis = "Perempuan";
        }
        String sql = "insert into pelanggan values (?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtid.getText());
            stat.setString(2, txtnm.getText());
            stat.setString(3, jenis);
            stat.setString(4, txttelp.getText());
            stat.setString(5, txtalamat.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");
            kosong();
            txtid.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan" + e);
        }
        datatable();
    }

    private void bubahActionPerformed() {
        String jenis = null;
        if (rlaki.isSelected()) {
            jenis = "Laki-Laki";
        } else if (rperempuan.isSelected()) {
            jenis = "Perempuan";
        }
        try {
            String sql = "update pelanggan set nmplg=?,jenis=?,telepon=?,alamat=? where id='" + txtid.getText() + "'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnm.getText());
            stat.setString(2, jenis);
            stat.setString(3, txttelp.getText());
            stat.setString(4, txtalamat.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil diubah");
            kosong();
            txtid.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal diubah" + e);
        }
        datatable();
    }

    private void bhapusActionPerformed() {
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "konfirmasi dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "delete from pelanggan where id ='" + txtid.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasil dihapus");
                kosong();
                txtid.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "data gagal dihapus" + e);
            }
            datatable();
        }
    }

    private void bbatalActionPerformed() {
        kosong();
        datatable();
    }

    private void bkeluarActionPerformed() {
        dispose();
    }

    private void bcariActionPerformed() {
        datatable();
    }

    private void tblplgnMouseClicked() {
        int bar = tblplgn.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        txtid.setText(a);
        txtnm.setText(b);
        if ("Laki-Laki".equals(c)) {
            rlaki.setSelected(true);
        } else {
            rperempuan.setSelected(true);
        }
        txttelp.setText(d);
        txtalamat.setText(e);
    }

    private void txtcariKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            datatable();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Pelanggan().setVisible(true));
    }
}
