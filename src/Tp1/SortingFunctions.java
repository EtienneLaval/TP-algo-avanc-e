package Tp1;

//import java.util.concurrent.Callable;

import com.sun.deploy.util.ArrayUtil;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingFunctions {
    public static Long[][] getTimedFunction (int arraymin, int arraymax, double step){
        Long[][] timeArray = new Long[4][100];
        int increment = 0;
        for (int i = arraymin; i< arraymax; i = (int)Math.round(i*step)){
            int[] array = RandomData.generate1d(i,0,500);
            long begin;
            long end;

            begin = System.currentTimeMillis();
            SelectionSort(array);
            end = System.currentTimeMillis();
            timeArray[0][increment] = (long) Math.floor((end-begin)*0.01);


            begin = System.currentTimeMillis();
            BubleSort(array);
            end = System.currentTimeMillis();
            timeArray[1][increment] = end-begin;

            begin = System.currentTimeMillis();
            MergeSort (array,0, array.length);
            end = System.currentTimeMillis();
            timeArray[2][increment] = end-begin;

             begin = System.currentTimeMillis();
            QuickSort (array,0, array.length);
             end = System.currentTimeMillis();
            timeArray[3][increment] = end-begin;


//            MergeSort (array,0, array.length);
//            begin = System.currentTimeMillis();
//            Dichotomy(array,100,0,array.length);
//            end = System.currentTimeMillis();
//            timeArray[0][increment] = end-begin;
//

            increment++;
        }
        return timeArray;
    }


    public static void Dichotomy (int[] array, int value, int begin, int end){
        if (end-begin<1){
            int middle= begin + (end-begin)/2;
            if (array[middle] < value) {// si c'est exact, c'est bon mais le resultat ne nous intéresse pas
                Dichotomy(array,value,middle+1,end);
            } else if(array[middle] > value) {
                Dichotomy(array,value,begin,middle-1);
            }
        }
        if(array[begin] == value){
            System.out.println(" Found !");
        }//de même nous ne cherchons pas ici à connaître la réponse
    }

    public static void FindSmallest (int[] array){
        int[] sorted = array;
        int smallest = array[0];
        List< Integer > index = new ArrayList<Integer>() ;

        for (int i = 0; i<array.length; i++){
            if (array[i] < smallest){
                smallest = array[i];
                index.clear();
                index.add(i);
            }
            else if (array[i] == smallest){
                index.add(i);
            }
        }
    } // je ne retourne rien, pour ne pas alouer des resources inutiles

    public static void swap(int[] data, int i, int j){
        int tmp= data[i];
        data[i]= data[j];
        data[j]= tmp;
    }

    public static int minimumIndex(int[] data, int begin, int end){
        int res= begin;
        for(int i=begin+1; i != end; ++i){
            if(data[i] < data[res]){
                res= i;
            }
        }
        return res;
    }

    public static void SelectionSort (int[] data){
        if(data.length < 2){return;}
        for(int i=0; i != data.length-1; ++i){
            swap(data, i, minimumIndex(data, i, data.length));
        }
    }

    public static void BubleSort(int[] data){
        if(data.length < 2){return;}
        boolean hadToSwap= false;
        do{
            hadToSwap=false;
            for(int i= 0; i != data.length-1; ++i){
                if(data[i] > data[i+1]){
                    swap(data, i, i+1);
                    hadToSwap= true;
                } }
        }while(hadToSwap);



    }
    public static void MergeSort(int[] data, int begin, int end){
        if((end-begin) < 2){return;}
        int middle= (end+begin)/2;
        MergeSort(data, begin, middle);
        MergeSort(data, middle, end);
        MergeSorted(data, begin, middle, end);
    }
    public static void MergeSorted(int data[], int begin, int middle, int end){

        int[] tmp= new int[middle-begin];
        System.arraycopy(data, begin, tmp, 0, tmp.length);
        int i=0, j=middle, dest=begin;
        while((i< tmp.length) && (j<end)){
            data[dest++] = (tmp[i] < data[j]) ? tmp[i++] : data[j++] ;
        }
        while(i < tmp.length){
            data[dest++]= tmp[i++];
        }
    }


    public static int partition(int[] data, int begin, int end, int pivotIdx){
        swap(data, pivotIdx, --end);
        pivotIdx= end;
        int pivot= data[pivotIdx];
        //invariant is that everything before begin is known to be < pivot
        // and everything after end is known to be >= pivot
        while(begin != end){
            if(data[begin] >= pivot){
                swap(data, begin, --end);
            }else{
                ++begin;
            }
        }
        swap(data, pivotIdx, begin);
        return begin;
    }
    public static void QuickSort(int[] data, int begin, int end){
             if((end-begin) < 2){ return; }
             int m= partition(data, begin, end, (end+begin)/2);
             QuickSort(data, begin, m);
            QuickSort(data, m+1, end); // +1 for convergence }
        }
}
