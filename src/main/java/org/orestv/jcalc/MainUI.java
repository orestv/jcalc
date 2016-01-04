package org.orestv.jcalc;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class MainUI extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JPanel paramsPanel;
    private JPanel chartPanel;

    private JSpinner ctl_t_i;
    private JComboBox<Material> ctl_materials;

    private JLabel ctl_sigma = new JLabel();
    private JLabel ctl_mu = new JLabel();
    private JLabel ctl_nu = new JLabel();
    private JLabel ctl_k = new JLabel();
    private JLabel ctl_lambda = new JLabel();
    private JLabel ctl_E = new JLabel();
    private JLabel ctl_alpha = new JLabel();
    private JLabel ctl_rho = new JLabel();
    private JLabel ctl_sigma_t = new JLabel();

    public MainUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        this.setSize(new Dimension(800, 600));

        MigLayout mainLayout = new MigLayout(
                "debug",
                "[8cm]5[grow]",
                "[grow]"
        );
        mainPanel = new JPanel(mainLayout);

        MigLayout paramsLayout = new MigLayout(
                "wrap,fillx",
                "[grow][right][right]",
                ""
        );
        paramsPanel = new JPanel(paramsLayout);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0.0001, 0.0001, 1, 0.0001);
        ctl_t_i = new JSpinner(spinnerModel);

        JSpinner.NumberEditor editor = (JSpinner.NumberEditor)ctl_t_i.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(4);
        Dimension d = ctl_t_i.getPreferredSize();
        d.width = 85;
        ctl_t_i.setPreferredSize(d);

        ctl_materials = new JComboBox<>(Material.values());

        paramsPanel.add(new JLabel("tᵢ ="), "");
        paramsPanel.add(ctl_t_i, "skip");

        paramsPanel.add(new JLabel("Матеріал"));
        paramsPanel.add(ctl_materials, "skip");

        paramsPanel.add(new JLabel("К-т електропровідності σ"));
        paramsPanel.add(ctl_sigma);
        paramsPanel.add(new JLabel("1/Ом·м"));

        paramsPanel.add(new JLabel("Магнітна проникливість μ"));
        paramsPanel.add(ctl_mu);
        paramsPanel.add(new JLabel("Гн/м"));

        paramsPanel.add(new JLabel("К-т Пуассона υ"));
        paramsPanel.add(ctl_nu);
        paramsPanel.add(new JLabel(""));

        paramsPanel.add(new JLabel("К-т температуропровідності κ"));
        paramsPanel.add(ctl_k);
        paramsPanel.add(new JLabel("м²/с"));

        paramsPanel.add(new JLabel("К-т теплопровідності λ"));
        paramsPanel.add(ctl_lambda);
        paramsPanel.add(new JLabel("Вт/(м·К)"));

        paramsPanel.add(new JLabel("Модуль Юнга Е"));
        paramsPanel.add(ctl_E);
        paramsPanel.add(new JLabel("Н/м²"));

        paramsPanel.add(new JLabel("К-т лінійного розширення α"));
        paramsPanel.add(ctl_alpha);
        paramsPanel.add(new JLabel("1/К"));

        paramsPanel.add(new JLabel("Густина ρ"));
        paramsPanel.add(ctl_rho);
        paramsPanel.add(new JLabel("кг/м³"));

        paramsPanel.add(new JLabel("Границя текучості σₜ"));
        paramsPanel.add(ctl_sigma_t);
        paramsPanel.add(new JLabel("МПа"));

        updateMaterialParams((Material) ctl_materials.getSelectedItem());

        ctl_materials.addActionListener(this);

        MigLayout chartLayout = new MigLayout();
        chartPanel = new JPanel(chartLayout);

        mainPanel.add(paramsPanel, "grow");
        mainPanel.add(chartPanel);
        this.add(mainPanel);
    }

    private void updateMaterialParams(Material material) {
        ctl_sigma.setText(Double.toString(material.sigma));
        ctl_mu.setText(Double.toString(material.mu));
        ctl_nu.setText(Double.toString(material.nu));
        ctl_k.setText(Double.toString(material.k));
        ctl_lambda.setText(Double.toString(material.lambda));
        ctl_E.setText(Double.toString(material.E));
        ctl_alpha.setText(Double.toString(material.alpha));
        ctl_rho.setText(Double.toString(material.rho));
        ctl_sigma_t.setText(Double.toString(material.sigma_t));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ctl_materials) {
            updateMaterialParams((Material) ctl_materials.getSelectedItem());
        }
    }
}
