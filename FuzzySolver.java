package mathcomp.oletsky.fuzzylogic;

public class FuzzySolver {

    public static double[][] minImpl(double[] aSet, double[] bSet) {
        int m = aSet.length;
        int n = bSet.length;
        double[][] res = new double[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                res[i][j] = Math.min(aSet[i], bSet[j]);
            }
        return res;
    }

    public static double[][] implLucasz(double[] aSet, double[] bSet) {
        int m = aSet.length;
        int n = bSet.length;
        double[][] res = new double[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                res[i][j] = Math.min(1., 1.-aSet[i]+bSet[j]);
            }
        return res;
    }

    public static double[][] implClassic(double[] aSet, double[] bSet) {
        int m = aSet.length;
        int n = bSet.length;
        double[][] res = new double[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                res[i][j] = Math.max(1.-aSet[i],bSet[j]);
            }
        return res;
    }

    public static double[] minMaxComp(double[] aFact, double[][] impl) {
        int m = aFact.length;
        int m1 = impl.length;
        if (m!=m1) throw new RuntimeException("Dimensions don't match");
        int n = impl[0].length;
        double[][] auxil = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                auxil[i][j] = Math.min(aFact[i], impl[i][j]);
            }
        double[] res = new double[n];

        for (int j=0; j<n; j++) {
            double mx=auxil[0][j];
            for(int i=1; i<m; i++) {
                if (auxil[i][j]>mx) mx=auxil[i][j];
            }
            res[j]=mx;
        }

        return res;
    }

    public static double weightCenter (double[] bas, double[] fuz) {
        int n = bas.length;
        int n1 = fuz.length;
        if (n!=n1) throw new RuntimeException("Vector dimensions don't match");
        double s1 = 0;
        for (int i=0; i<n; i++) {
            s1+=bas[i]*fuz[i];
        }
        double s2 = 0;
        for (int i=0; i<n; i++) {
            s2+=fuz[i];
        }
        return s1/s2;

    }

    public static double getFuzzySolution(double[] aSet,
                                          double[] aFact,
                                          double[] bSet,
                                          double[] basicMarks) {
        double[][] minimpl = minImpl(aSet,bSet);
        double[] bRes = minMaxComp(aFact, minimpl);
        double b = weightCenter(basicMarks,bRes);
        return b;
    }

}
