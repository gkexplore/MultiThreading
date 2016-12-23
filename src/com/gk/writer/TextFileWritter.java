package com.gk.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by karthik on 12/22/2016.
 */
public class TextFileWritter {
        private String fileName;
        private BufferedWriter bufferedWriter = null;
        private FileWriter fileWriter = null;
        public TextFileWritter(String fileName){
            this.fileName = fileName;
        }

        public void initializeWritter(){
            try {
                this.fileWriter = new FileWriter(new File(this.fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.bufferedWriter = new BufferedWriter(this.fileWriter);
        }

        public synchronized void writeData(String data){
            try {
                this.bufferedWriter.write(data);
                this.bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void closeWriter() throws IOException {
            this.bufferedWriter.close();
            this.fileWriter.close();
        }
    }
