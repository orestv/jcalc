package org.orestv.jcalc;


import org.apache.commons.math3.complex.Complex;

public class ElectroDynamicsCalculator {
    private final int COUNT_I = 3;
    private final int COUNT_S = 4;
    private final int COUNT_K = 2;
    private final int COUNT_N = 2;

    private double t_i;
    private Material material;

    private double[][][] a = new double[3][4][2];

    private double[] d = new double[8];
    private double[] p = new double[2];

    private double[][][] A_k = new double[2][2][COUNT_K];
    private double[][] A_i = new double[COUNT_I][COUNT_N];

    private double[][][] C = new double[COUNT_I][COUNT_K][COUNT_N];

    private Complex[][][] B = new Complex[COUNT_I][6][COUNT_N];

    private double[] beta = new double[2];
    private double omega = 6.28 * 10000;

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

        calculateConstants();
        calculate_a();
        calculate_p();
        calculate_A_k();
        calculate_A_i();
        calculate_C();
        calculate_B();
    }

    private void calculateConstants() {
        beta[0] = 6.9 / t_i;
        beta[1] = 2 * beta[0];
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

    private void calculate_A_i() {
        for (int i = 0; i < COUNT_I; i++)
            for (int n = 0; n < COUNT_N; n++)
                A_i[i][n] = a[i][2][n] + a[i][3][n];
    }

    private void calculate_C() {
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 2; k++)
                for (int n = 0; n < 2; n++)
                    C[i][k][n] = a[i][0][n] * (A_k[0][0][k] + A_k[0][1][k]) +
                                 a[i][1][n] * (A_k[1][0][k] + A_k[1][1][k]);
    }

    private void calculate_B() {
        for (int i = 0; i < COUNT_I; i++)
            for (int n = 0; n < COUNT_N; n++) {

                B[i][0][n] = (new Complex(-C[i][0][n]).divide(new Complex(0, -omega).add(beta[0] + p[0]))).add(A_i[i][n]);
                B[i][0][n] = B[i][0][n].add(new Complex(-C[i][1][n]).divide(new Complex(0, -omega).add(beta[0] + p[1]))).add(A_i[i][n]);

//                B[i][1][n] =

            }
    }

    private void calculate_p() {
        double a = 1, b = -(d[0] + d[5]), c = d[0]*d[5] - d[1]*d[4];
        double D = b*b - 4*a*c;
        p[0] = (- b - Math.sqrt(D)) / (2 * a);
        p[1] = (- b + Math.sqrt(D)) / (2*a);
    }
}
