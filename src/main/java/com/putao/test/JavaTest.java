package com.putao.test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static java.util.Arrays.sort;

public class JavaTest {

    public static void printX(){
        for(int i= 1;i<=9;i++){
            for(int j = 1;j<=9; j++){
                if(j<=i){
                    System.out.print(i+"*" +j +"=" +i*j+"\t");
                }
                if(i==j){
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        printX();
    }

}
