package com.miz.java;

public class test {

    public static void selectSort(int[] a){
        for (int i = 0; i < a.length-1; i++){
            int min = i;
            for (int j = i + 1; j < a.length; j++){
                if (a[j] < a[min]){
                    min = j;
                }
            }
            if (min != i){
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1,6,34,7,2,5};
        selectSort(a);
    }
}
