package com.github.confluent.kafka.connect.smt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        String s = "0002-11-20 10:01:02.0";


        Date dateF = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss.S").parse(returnFormattedDate(s));
        System.out.println(dateF.toInstant().toEpochMilli());


    }


    private static String returnFormattedDate(String s){
        String src_dtf = "nndd-MM-yy hh:mm:ss.S";;
        String dt_sep = "-";
        String tm_sep = " ";
        int d_idx = 0;
        int m_idx = 0;
        int yt_idx = 0;
        int idx = 0;

        String[] dtf = src_dtf.split(dt_sep);
        int i = 0;
        for (String part:dtf) {
            if(part.contains("d")) {
                idx = part.indexOf('d');
                d_idx = i;
            }
            else if(part.contains("y")){
                yt_idx = i;
            }
            else if(part.contains("M")){
                m_idx = i;
            }
            i++;
        }

        String[] dt_split = s.split(dt_sep);
        String[] tm_split = dt_split[yt_idx].split(tm_sep);
        String yrtm = (tm_split[0].length()<4)? 20+tm_split[0]:tm_split[0];
        String newDt = dt_split[d_idx].substring(idx) + "-" + dt_split[m_idx] + "-" + yrtm + " " + tm_split[1];
        if(tm_split.length > 2){
            newDt = dt_split[d_idx].substring(idx) + "-" + dt_split[m_idx] + "-" + yrtm + " " + tm_split[1] + " " + tm_split[2];
        }
        return newDt;
    }
}
