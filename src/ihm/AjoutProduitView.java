package ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.Produit;
import service.ServiceProduit;

public class AjoutProduitView extends JFrame {
	DefaultTableModel tm;

	JTextField jTextCategory;
	JTextField jTextDate;
	JLabel labelCategory;
	JLabel labelDate;
	JButton btnEnvoi;
	JButton btnAnnul;

	ServiceProduit serviceProduit;

	public AjoutProduitView() {

		labelCategory = new JLabel("Categorie");
		labelDate = new JLabel("Date_expi");
		jTextCategory = new JTextField(10);
		jTextDate = new JTextField(10);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

		JPanel panSaisieCat = new JPanel();
		JPanel panSaisieDate = new JPanel();

		panSaisieCat.setLayout(new FlowLayout());
		panSaisieCat.add(labelCategory);
		panSaisieCat.add(jTextCategory);
		panSaisieDate.setLayout(new FlowLayout());
		panSaisieDate.add(labelDate);
		panSaisieDate.add(jTextDate);

		JPanel panSaisi = new JPanel();
		panSaisi.setLayout(new GridLayout(2, 1));
		panSaisi.add(panSaisieCat);
		panSaisi.add(panSaisieDate);
		// panSaisie.add(jTable);
		JPanel panValid = new JPanel();
		panValid.setLayout(new FlowLayout());

		btnEnvoi = new JButton("Valider");
		btnAnnul = new JButton("Annuler");
		btnEnvoi.setSize(30, 30);
		btnAnnul.setSize(30, 30);

		panValid.add(btnEnvoi);
		panValid.add(btnAnnul);
		panValid.setBackground(Color.yellow);
		JPanel pHaut = new JPanel();
		pHaut.setLayout(new GridLayout(2, 2));
		pHaut.add(panSaisi);
		pHaut.add(panValid);
		this.add(pHaut);
		this.setResizable(false);
		btnAnnul.addActionListener((e) -> {
			jTextCategory.setText("");
			jTextDate.setText("");
		});

		btnEnvoi.addActionListener((e) -> {
			serviceProduit = new ServiceProduit();
			String cat = jTextCategory.getText();
			String date = jTextDate.getText();
			if (cat.isEmpty() || date.isEmpty()) {
				return;
			}
			String[] data = { cat, date };

			try {
				Produit p = new Produit(cat, sdf.parse(date));
				serviceProduit.ajoutProduit(p);
				JOptionPane.showMessageDialog(this, "Le produit a été rajouté avec succès");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

	}
}