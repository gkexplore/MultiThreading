package com.gk.report;

import com.gk.writer.TextFileWritter;

import java.io.IOException;

/**
 * Created by karthik on 12/22/2016.
 */
public class ReportGenerator extends Thread {

    private String fileName;
    private TextFileWritter textFileWritter = null;
    private boolean recordWritten = true;
    private boolean terminateFlag = false;
    private String record = "";

    public ReportGenerator(String fileName, String header){
        this.record = header;
       this.fileName = fileName;
       this.textFileWritter = new TextFileWritter(fileName);
       this.textFileWritter.initializeWritter();
       this.textFileWritter.writeData(this.record);
    }

    @Override
    public void run(){
        try {
            synchronized (this){
                while(!terminateFlag){
                    try{
                        if (!isRecordWritten()) {
                            textFileWritter.writeData(this.record);
                        }
                        this.setRecordWritten(true);
                        this.wait();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Thread is stopped!!!");
        }finally {
            System.out.println("Thread Exit success");
            try {
                this.textFileWritter.closeWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized boolean isRecordWritten() {
        return recordWritten;
    }

    public synchronized void setRecordWritten(boolean recordWritten) {
        this.recordWritten = recordWritten;
    }

    public synchronized boolean isRecordSaved(){
        return (this.getState()== Thread.State.WAITING&&this.isRecordWritten());
    }

    public synchronized void terminateThread() throws InterruptedException {
        this.terminateFlag = true;
        Thread.sleep(1000);
        this.notify();
    }

    public synchronized void writeNextRecord(String record){
        synchronized (this){
            try{
                this.record = record;
                this.notify();
                this.recordWritten = false;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
