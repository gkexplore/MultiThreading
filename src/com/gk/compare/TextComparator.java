package com.gk.compare;

import java.util.Comparator;

/**
 * Created by karthik on 12/22/2016.
 */
public class TextComparator implements Comparator<String> {
    private int keyLength;
    public TextComparator(int keyLength){
      this.keyLength = keyLength;
    }

    @Override
    public int compare(String sourceObject, String targetObject) {
        String[] firstKeyArray = sourceObject.toString().split("\\|",-1);
        String[] secondKeyArray = targetObject.toString().split("\\|", -1);
        int retval = 0;
        try{
        for(int i=0;i<keyLength;i++){
          retval = firstKeyArray[i].compareTo(secondKeyArray[i]);
          if(retval==0){
              continue;
          }else{
              return retval;
          }
        }}catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }
}
