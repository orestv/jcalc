package org.orestv.jcalc;

public enum Material {
    STEEL (0.135 * Math.pow(10, 7), 12.57 * Math.pow(10, -7), 0.283, 0.422 * Math.pow(10, -5),
            0.167 * Math.pow(10, 2), 0.192 * Math.pow(10, 12), 0.000017, 7850, 300),
    COPPER(0.588 * Math.pow(10, 8), 12.57 * Math.pow(10, -7), 0.35, 0.118 * Math.pow(10, -3),
            0.406 * Math.pow(10, 3), 0.129 * Math.pow(10, 12), 0.0000178, 8900, 70),
    ALUMINIUM(0.363 * Math.pow(10, 8), 12.57 * Math.pow(10, -7), 0.34, 0.847 * Math.pow(10, -4),
            0.209 * Math.pow(10, 3), 0.71 * Math.pow(10, 11), 0.0000229, 2670, 30);

    public double sigma;
    public double mu;
    public double nu;
    public double k;
    public double lambda;
    public double E;
    public double alpha;
    public double rho;
    public double sigma_t;

    Material(double sigma, double mu, double nu, double k,
             double lambda, double E, double alpha, double rho, double sigma_t) {
        this.sigma = sigma;
        this.mu = mu;
        this.nu = nu;
        this.k = k;
        this.lambda = lambda;
        this.E = E;
        this.alpha = alpha;
        this.rho = rho;
        this.sigma_t = sigma_t;
    }

    @Override
    public String toString() {
        switch (this) {
            case STEEL:
                return "Сталь";
            case COPPER:
                return "Мідь";
            case ALUMINIUM:
                return "Алюміній";
            default:
                return "N/A";
        }
    }
}
