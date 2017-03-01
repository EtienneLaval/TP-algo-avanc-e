package Tp1;

/**
 * Created by etien on 08/02/2017.
 */
//public class ViewController {
//}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;



public class ViewController extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Les tris");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Taille de tableau");
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Algorythmes de tri");

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();

        series1.setName("Selection Sort");
        series2.setName("Bubble Sort");
        series3.setName("Merge Sort");
        series4.setName("Quick Sort");



        int arraymin = 1000;
        int arraymax = 400000;
        double step = 2;


        Long[][] timingSmallest = SortingFunctions.getTimedFunction(arraymin,arraymax,step);
        System.out.println (timingSmallest[0][0]);
        for (int i = 0; i<9;i++){
            series1.getData().add(new XYChart.Data(arraymin*Math.pow(step,i), timingSmallest[0][i]));
        }
        for (int i = 0; i<9;i++){
            series2.getData().add(new XYChart.Data(arraymin*Math.pow(step,i), timingSmallest[1][i]));
        }
        for (int i = 0; i<9;i++){
            series3.getData().add(new XYChart.Data(arraymin*Math.pow(step,i), timingSmallest[2][i]));
        }
        for (int i = 0; i<9;i++){
            series4.getData().add(new XYChart.Data(arraymin*Math.pow(step,i), timingSmallest[3][i]));
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1, series2, series3, series4);
//        lineChart.getData().addAll(series1);

        stage.setScene(scene);
        stage.show();

    }

}