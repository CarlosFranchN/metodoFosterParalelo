/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;

import java.util.Random;

public class Foster {
    

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        SomaElementosArray();
        System.out.println();
        somaArrayParalelo();
    }

    public static int[] gerarArrayAleatorio(int N) {
        Random random = new Random();
        int[] array = new int[N];

        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(100);  // Gera números aleatórios de 0 a 99
        }

        return array;
    }
    public static  int[] gerarArray(int N){
        int[] array =  new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i+1;
        }
        return array;
    }

    public static void  SomaElementosArray(){
        int tamanhoArray = 10;
        // int[] meuArray = {1,2,3,4,5,6,7,8,9,10};
        int[] meuArray = gerarArray(1000000000);
        int soma = 0;
        long inicio = System.nanoTime();
        for (int elemento: meuArray){
            soma+= elemento;
        }
        System.out.println("A soma dos elementos do array é: " + soma);
        long fim  = System.nanoTime();
        System.out.println("Tempo é: "+ (fim - inicio));
    }

    public static void somaArrayParalelo(){
        // int[] meuArray = {1,2,3,4,5,6,7,8,9,10};
        int[] meuArray = gerarArray(1000000000);


        int nThreads = 2;

        int subArraySize = meuArray.length/nThreads;
        Thread[] threads = new Thread[nThreads];
        int[] partialSums = new int[nThreads];

        for (int i = 0; i < nThreads; i++) {
            final int start = i*subArraySize;
            final int end = (i == nThreads-1)? meuArray.length: (i + 1)*subArraySize;

            final int threadIndex = i;
            threads[i] = new Thread(()->{
                int sum = 0;
                for (int j = start; j < end; j++) {
                    sum+= meuArray[j];
                }
                partialSums[threadIndex] = sum;
            });

            threads[i].start();}
        long inicio = System.nanoTime();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        int totalSum = 0;
        for (int partialSum : partialSums) {
            totalSum+=partialSum;
        }
        System.out.println("A soma dos elementos do array é: " + totalSum);
        long fim  = System.nanoTime();
        System.out.println("Tempo é: "+ (fim - inicio));


        }
    }

