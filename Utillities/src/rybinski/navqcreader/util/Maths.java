/*
 * Decompiled with CFR 0_118.
 */
package rybinski.navqcreader.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Locale;

public class Maths {

    public static String formatDouble(double d, int noDecimals) {
        Locale.setDefault(Locale.US);
        String str = "###,##0.00";
        StringBuilder format = new StringBuilder();
        format.append(str);
        for (int i = 0; i < noDecimals; ++i) {
            format.append("0");
        }
        DecimalFormat df = new DecimalFormat(format.toString());
        System.out.println(df.format(d));
        return df.format(d);
    }

    public static double computeAverage(List list) {
        double value = 0.0;
        double sum = 0.0;
        int count = 0;
        String element;
        do {
            list.remove("NaN");
        } while (list.contains("NaN"));
        for (int i = 0; i < list.size(); ++i) {
            element = list.get(i).toString();
            if (element.equals("NaN")) {
                continue;
            }
            value = Double.valueOf(element);
            sum += value;
            ++count;
        }
        return sum / (double) count;
    }

    public static double computeMin(List list) {
        double minValue = 0.0;
        do {
            list.remove("NaN");
        } while (list.contains("NaN"));
        for (int i = 0; i < list.size(); ++i) {
            String element = list.get(i).toString();
            double value = Double.valueOf(element);
            minValue = i == 0 ? value : Math.min(minValue, value);
        }
        return minValue;
    }

    public static double computeMax(List list) {
        double maxValue = 0.0;
        do {
            list.remove("NaN");
        } while (list.contains("NaN"));
        for (int i = 0; i < list.size(); ++i) {
            String element = list.get(i).toString();
            double value = Double.valueOf(element);
            maxValue = i == 0 ? value : Math.max(maxValue, value);
        }
        return maxValue;
    }

    public static double computeAverageNew(List list) {
        List<Double> listDouble = new ArrayList<>();
        do {
            list.remove("NaN");
        } while (list.contains("NaN"));
        for (int i = 0; i < list.size(); ++i) {
            String element = list.get(i).toString();
            double value = Double.valueOf(element);
            listDouble.add(value);

            // maxValue = i == 0 ? value : Math.max(maxValue, value);
        }
        DoubleSummaryStatistics stats;
        stats = listDouble.stream().mapToDouble((x) -> x).summaryStatistics();
        return stats.getAverage();
//return maxValue;
    }
}
