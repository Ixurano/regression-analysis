package fi.arcada.android;

public class RegressionLine {
    //metod variabler i klassen
    double k;
    double m;
    double x;
    double correlationCoefficient;
    String getCorrelationGrade;
    //konstruktor metoden
    public RegressionLine(double[] xVals, double[] yVals) {
        calculate(xVals, yVals);
    }
    //kalkyl metoden för allt calQk, calQm och calQcorrelation
    private void calculate(double[] xVals, double[] yVals) {
        int n;
        double sigmaX = 0;
        double sigmaY = 0;
        double xY = 0;
        double xSquared = 0;
        double ySquared = 0;
        double sigmaXtot;
        double sigmaYtot;
        //storleken av datan
        n = xVals.length;
        //for-loop för att få summa av x,y samt x och y i kvadrat samt summan av x och y i kvadrat
        for (int i = 0; i < xVals.length; i++) {

            sigmaX = sigmaX + xVals[i];

            sigmaY = sigmaY + yVals[i];

            xY = xY + (xVals[i] * yVals[i]);

            xSquared = xSquared + Math.pow(xVals[i], 2);
            ySquared = ySquared + Math.pow(yVals[i], 2);
        }
        sigmaXtot = Math.pow(sigmaX, 2);
        sigmaYtot = Math.pow(sigmaY, 2);

        k = ((n * xY) - (sigmaX * sigmaY)) / ((n * xSquared) - sigmaXtot);
        m = (sigmaY / n) - (k * (sigmaX / n));
        correlationCoefficient = ((n * xY) - (sigmaX * sigmaY)) / Math.sqrt(((n * xSquared) - sigmaXtot) * ((n*ySquared)- sigmaYtot));
    }
    //getter för X
    public double getX(Double yValue) {
        x = (yValue - m) / k;

        return x;

    }

    public double getCorrelationCoefficient() {
        return correlationCoefficient;
    }
    //metod för få graden IF kan göras om till switch om man vill
    public String getCorrelationGrade() {
        if (correlationCoefficient == 1 || correlationCoefficient == -1 ){
            getCorrelationGrade = "Perfekt";
        }
        else if (correlationCoefficient >=0.75 || correlationCoefficient <1 || correlationCoefficient >= -0.75 || correlationCoefficient > -1){
            getCorrelationGrade = "Hög";
        }
        else if (correlationCoefficient >=0.25 || correlationCoefficient < 0.75 || correlationCoefficient >= -0.25 || correlationCoefficient > -0.75){
            getCorrelationGrade = "Måttlig";
        }
        else if (correlationCoefficient > 0.00 || correlationCoefficient < 0.25 || correlationCoefficient >= 0 || correlationCoefficient > -0.25){
            getCorrelationGrade = "låg";
        }
        else  if (correlationCoefficient == 0){
            getCorrelationGrade = "Ingen korrelation";
        }

        return getCorrelationGrade;
    }

}
