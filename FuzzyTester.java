package mathcomp.oletsky.fuzzylogic;

import java.util.Arrays;

public class FuzzyTester {
    public static void main(String[] args) {
        double[] basicA = {3., 6., 9., 12., 18., 21., 27.};
        double[] basicB = {59., 72., 84., 91., 96., 100.};
        //double[] badA = {1., 1., 0.2, 0., 0., 0.};
        double[] goodA = {0., 0.2, 0.7, 1., 0.6, 0.2, 0.};

        double[] aSet = {0., 0.1, 0.4, 0.6, 0.8, 1., 1.};;
        double[] aFact = goodA;
        double[] bSet = {0., 0.2, 0.4, 0.7, 0.9, 1.};

        double[][] minimpl = FuzzySolver.minImpl(aSet,bSet);
        double[] bRes = FuzzySolver.minMaxComp(aFact, minimpl);
        System.out.println(Arrays.toString(bRes));
        double b = FuzzySolver.getFuzzySolution(aSet, aFact, bSet, basicB);
        System.out.println("Result of defuzzification:");
        System.out.println(b);

    }

}
