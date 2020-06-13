package org.spring.web.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExceptionUtil {

    public static String getExceptionAllInfo(Exception e){
        String errStr = "";
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(out);
            e.printStackTrace(pout);
            errStr = new String(out.toByteArray());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return errStr;
    }
}
