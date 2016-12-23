package com.gk.main;

import com.gk.compare.TextComparator;
import com.gk.report.ReportGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthik on 12/22/2016.
 */
public class CompareText {
    public static void main(String args[]) throws InterruptedException {
        ArrayList<String> sourceRecordList = new ArrayList<String>();
        ArrayList<String> targetRecordList = new ArrayList<String>();

            sourceRecordList.add("200 | 300 | 500");
            sourceRecordList.add("200 | 400 | 500");
            sourceRecordList.add("200 | 500 | 600");


            targetRecordList.add("200 | 300 | 500");
            targetRecordList.add("200 | 400 | 600");
            targetRecordList.add("200 | 500 | 500");

        TextComparator comparator = new TextComparator(3);
        int compareFlag = 0;
        String[] reportTypes = new String[]{"missingTarget", "missMatch","missingSource"};
        List<ReportGenerator> reportGeneratorList = new ArrayList<ReportGenerator>();
        for(int i=0;i<reportTypes.length;i++){
            ReportGenerator reportGenerator = new ReportGenerator(reportTypes[i]+".txt",reportTypes[i]);
            reportGeneratorList.add(reportGenerator);
            reportGenerator.start();
        }
        ReportGenerator reportGeneratorObj = null;
        for(int i=0;i<sourceRecordList.size();i++){
            compareFlag = comparator.compare(sourceRecordList.get(i), targetRecordList.get(i));
            if(compareFlag==0){
               reportGeneratorObj = reportGeneratorList.get(1);
               if(reportGeneratorObj.isRecordSaved()){
                   reportGeneratorObj.writeNextRecord("miss match "+i);
               }
            }else if(compareFlag<0){
                reportGeneratorObj = reportGeneratorList.get(0);
                if(reportGeneratorObj.isRecordSaved()){
                    reportGeneratorObj.writeNextRecord("missing target "+i);
                }
            }else{
                reportGeneratorObj = reportGeneratorList.get(2);
                if(reportGeneratorObj.isRecordSaved()){
                    reportGeneratorObj.writeNextRecord("missing source "+i);
                }
            }
        }

        for(int i=0;i<reportTypes.length;i++){
            ReportGenerator reportGenerator = reportGeneratorList.get(i);
            reportGenerator.terminateThread();
        }

    }
}
