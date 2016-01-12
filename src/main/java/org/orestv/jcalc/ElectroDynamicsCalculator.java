package org.orestv.jcalc;


public class ElectroDynamicsCalculator {
    private double t_i;
    private Material material;

    private double[] d = new double[8];
    private double[] p = new double[2];

    private double[][][] A_k = new double[2][2][2];

    ElectroDynamicsCalculator(double t_i, Material material) {
        this.t_i = t_i;
        this.material = material;
        calculateInitials();
    }

    public double H(double r, double t, int n) {
        // todo: implement
        return 0;
    }

    private void calculateInitials() {
        // todo: implement

        for (double d_: d)
            System.out.println(d_);

        calculate_a();
        calculate_p();
        calculate_A_k();
    }

    private void calculate_a() {
        // todo: implement
    }

    private void calculate_A_k() {
        for (int k = 0; k < 2; k++) {
            A_k[0][0][k] = ((p[k] - d[5]) * d[2] + d[1]*d[6]) / (2*p[k] - (d[0] + d[5]));
            A_k[1][0][k] = ((p[k] - d[0]) * d[6] + d[2]*d[4]) / (2*p[k] - (d[0] + d[6]));
            A_k[0][1][k] = ((p[k] - d[5]) * d[3] + d[1]*d[7]) / (2*p[k] - (d[0] + d[5]));
            A_k[1][1][k] = ((p[k] - d[0]) * d[7] + d[3]*d[4]) / (2*p[k] - (d[0] + d[5]));
        }
    }

    private void calculate_p() {
        double a = 1, b = -(d[0] + d[5]), c = d[0]*d[5] - d[1]*d[4];
        double D = b*b - 4*a*c;
        p[0] = (- b - Math.sqrt(D)) / (2 * a);
        p[1] = (- b + Math.sqrt(D)) / (2*a);
    }
}
