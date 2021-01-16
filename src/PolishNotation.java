/*
 * Author:Kevin Hoang Dinh
 * Code test link: https://open.kattis.com/problems/polish
 * 
 * The problem is almost like a calculator but a bit more complex instead of syntax error
 * Basically instead of syntax error like calculator, you need to shorten the Notation as short as possible
 */

import java.util.Scanner;
import java.util.*;
public class PolishNotation{
    
	
	//Using stack to keep track between operands (value,number) and operators (+,*,power....) 
    private Stack<String> st1, st2;
    private Scanner lineScanner;
    
    //Need to check int for the possibility to shorten Notation
    public boolean checkInt(String exp){
        if(exp.matches("-?(0|[1-9]\\d*)"))
            return true;
        return false;
    }
    
    //Test only consist of 3 operators
    public boolean checkOperator(String exp){
        if(exp.equals("+") ||exp.equals("-") ||exp.equals("*"))
            return true;
        return false;
    }
    
    //function to calculate 2 operands with an operator
    public int calculation(String Op, String exp1, String exp2){
        int result = 0;
        int a = Integer.parseInt(exp1);
        int b = Integer.parseInt(exp2);
        switch(Op){
            case "+":
                result = a + b;
                return result;
            case "-":
                result = a - b;
                return result;
            case "*":
                result = a * b;
                return result;
            default:
            return 0;
        }
    }
    
    public String shortenPN(){
        String PN = "";
       
        while(!st1.empty()){
        	//begin to pop from the 1st stack
            String exp = st1.pop();
            //if it's an operator, pop 2 expression from 2nd stack, if 2nd stack has size 2 or more,
            //to check if those are operands
            if(checkOperator(exp) && st2.size()>= 2){
                String exp1 = st2.pop();
                String exp2 = st2.pop();
                if(checkInt(exp1)&&checkInt(exp2)){
                    st2.push(""+calculation(exp,exp1,exp2));
                } 
                //if there ain't suitable to shorten, push them back to 2nd stack
                else{
                    st2.push(exp2);
                    st2.push(exp1);
                    st2.push(exp);
                }
            }
            //not operator, push it to the 2nd stack.
            else{
                st2.push(exp);
            }
        }
        //After shorten the Notation, pop the sequences out and print them in main. 
        while(!st2.empty()){
            PN+= " ";
            PN+=st2.pop();
        }
        return PN;
    }
    
    public void reader(){
        int casenumber = 1;
        try (Scanner in = new Scanner(System.in)) {
            while(in.hasNextLine()){
                String  line = in.nextLine();
                lineScanner = new Scanner(line);
                st1 = new Stack<String>();
                st2 = new Stack<String>();
                //Put all the input in 1st stack
                while(lineScanner.hasNext())
                    st1.push(lineScanner.next());
                System.out.println("Case "+ casenumber+":" + shortenPN());
                casenumber++;
            }
        }
    }
    public static void main(String []args){
    	PolishNotation a = new PolishNotation(); 
        a.reader();
    }
}