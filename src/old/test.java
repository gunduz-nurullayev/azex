package old;

import az.test.domain.FlexibleList;
import az.test.domain.FlexibleListItem;
import az.test.repository.CommonDao;
import az.test.repository.implementation.CommonDaoImpl;
import az.test.service.CommonService;
import az.test.service.implementation.CommonServiceImpl;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class test implements TestInterface {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Sozu daxil edin");
        String a=sc.nextLine();
            int count=0;
            boolean result=false;
        for (int i = 0; i <a.length() ; i++) {
            if(a.charAt(i)==a.charAt(a.length()-i-1)){
                count++;
                if(a.length()/2==count){
                     result=true;
                     break;
                }
            }
        }
        System.out.println("Result: "+result);
        main(args);
    }
}
