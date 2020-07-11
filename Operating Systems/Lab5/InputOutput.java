package Task5;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class InputOutput {
    private static XYDataset createDataset(Algorithms algorithm, Simulator simulator) {
        DefaultXYDataset ds = new DefaultXYDataset();
        ArrayList<Double> meanLoad = simulator.getMeanLoad().get(algorithm);
        ArrayList<Double> medianLoad = simulator.getMedianLoad().get(algorithm);
        ArrayList<Integer> loadRequests = simulator.getLoadRequests().get(algorithm);
        ArrayList<Integer> migrated = simulator.getMigrated().get(algorithm);
        ArrayList<Double> ticks = new ArrayList<>();
        for (int i = 0; i < medianLoad.size(); i++) {
            ticks.add((double) i);
        }
        double[] meanLoadFlat = new double[meanLoad.size()];
        double[] meanFlat = new double[medianLoad.size()];
        double[] loadRequestsFlat = new double[loadRequests.size()];
        double[] migratedFlat = new double[migrated.size()];
        double[] ticksFlat = new double[ticks.size()];
        for (int i = 0; i < meanLoad.size(); i++) {
            meanLoadFlat[i] = meanLoad.get(i);
        }
        for (int i = 0; i < medianLoad.size(); i++) {
            meanFlat[i] = medianLoad.get(i);
        }
        for (int i = 0; i < loadRequests.size(); i++) {
            loadRequestsFlat[i] = (double) loadRequests.get(i);
        }
        for (int i = 0; i < migrated.size(); i++) {
            migratedFlat[i] = (double) migrated.get(i);
        }
        for (int i = 0; i < ticks.size(); i++) {
            ticksFlat[i] = ticks.get(i);
        }
        double[][] meanLoadSeries = {ticksFlat, meanLoadFlat};
        double[][] meanSeries = {ticksFlat, meanFlat};
        double[][] requestsSeries = {ticksFlat, loadRequestsFlat};
        double[][] migrationSeries = {ticksFlat, migratedFlat};
        ds.addSeries("Mean load", meanLoadSeries);
        ds.addSeries("Median load", meanSeries);
        ds.addSeries("Requests", requestsSeries);
        ds.addSeries("Migrations", migrationSeries);
        return ds;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        GetValues dialog = new GetValues();
        dialog.pack();
        dialog.setVisible(true);
        int n = dialog.getN();
        int p = dialog.getP();
        int r = dialog.getR();
        int z = dialog.getZ();
        int maxLoad = dialog.getMaxLoad();
        int howMany = dialog.getHowMany();
        int ticks = dialog.getTicks();
        ArrayList<Process> processes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < howMany; i++) {
            int load = random.nextInt(maxLoad) + 1;
            int spawn = random.nextInt(ticks) + 1;
            int duration = random.nextInt(ticks) + 1;
            processes.add(new Process(load, spawn, duration));
        }
        //noinspection unchecked
        Collections.sort(processes);
        Simulator simulator = new Simulator(p, r, z, n, processes);
        simulator.simulate();
        XYDataset randDS = createDataset(Algorithms.RAND, simulator);
        XYDataset findDS = createDataset(Algorithms.FIND, simulator);
        XYDataset seizeDS = createDataset(Algorithms.SEIZE, simulator);
        JFreeChart randChart = ChartFactory.createXYLineChart("Algorithm 1 (random)", "ticks", "y", randDS, PlotOrientation.VERTICAL, true, true, false);
        JFreeChart findChart = ChartFactory.createXYLineChart("Algorithm 2 (find)", "ticks", "y", findDS, PlotOrientation.VERTICAL, true, true, false);
        JFreeChart seizeChart = ChartFactory.createXYLineChart("Algorithm 3 (seize)", "ticks", "y", seizeDS, PlotOrientation.VERTICAL, true, true, false);
        for (int i = 0; i < 4; i++) {
            randChart.getXYPlot().getRenderer().setSeriesStroke(i, new BasicStroke(2.5f));
            findChart.getXYPlot().getRenderer().setSeriesStroke(i, new BasicStroke(2.5f));
            seizeChart.getXYPlot().getRenderer().setSeriesStroke(i, new BasicStroke(2.5f));
        }
        ChartPanel randPanel = new ChartPanel(randChart);
        ChartPanel findPanel = new ChartPanel(findChart);
        ChartPanel seizePanel = new ChartPanel(seizeChart);
        DisplayCharts charts = new DisplayCharts(randPanel, findPanel, seizePanel);
        charts.pack();
        charts.setVisible(true);
    }
}
