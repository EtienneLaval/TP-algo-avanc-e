package Tp1;

import java.util.Random;

public class RandomData {

    public static int[] generate1d(float nbVals, int min, int max){
        int roundNbVals = (int)Math.round(nbVals);
        int[] res= new int[roundNbVals];
        Random generator = new Random();
        for(int i=0; i != roundNbVals; ++i){
            res[i]= (int)((generator.nextLong()% ((long)max-min))+min);
        }
        return res;

    }
//         System.out.println ("yo");
}
// swing
// gaffe a pas computer les tableaux sans les avoir créé au préalable si non leur création entrere dans le calcul de temps