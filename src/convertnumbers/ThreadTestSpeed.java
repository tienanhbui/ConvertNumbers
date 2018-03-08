/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertnumbers;

import convertnumbers.core.ConvertNumberTypeWithLinkedList;
import convertnumbers.core.ConvertNumberTypeWithStack;
import convertnumbers.core.InitData;
import convertnumbers.core.ReadWriteFile;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiena
 */
public class ThreadTestSpeed extends Thread{
    int lengthFile, loopPerRow, totalLoop, runner;
    float persent;
    StringBuilder[] stringBuilder;
    boolean running;
    DefaultTableModel modelTest;
    public long[] resTimesStack, resTimesLinked;

    public ThreadTestSpeed(int lengthFile, int loopPerRow, int totalLoop, DefaultTableModel modelTest) {
        this.lengthFile = lengthFile;
        this.loopPerRow = loopPerRow;
        this.totalLoop = totalLoop;
        this.modelTest = modelTest;
        this.persent = (float) totalLoop/100;
        
        resTimesStack = new long[loopPerRow *totalLoop];
        resTimesLinked = new long[loopPerRow *totalLoop];;
    }
    
    public boolean running(){
        return running;
    }
    
    public int getProcess(){
        return (int) (runner / this.persent);
    }
    
    @Override
    public void run() {
        running = true;
        int timeCount = 0;
        for (int i = 0; i < this.totalLoop; i++) {
            InitData.insertData(this.lengthFile);
            String resdt = ReadWriteFile.Read("data.dat");
            String[] source = resdt.split(" ");
            if(this.lengthFile > 1000){
                stringBuilder = new StringBuilder[(int) Math.ceil(this.lengthFile/1000) + 1];
            }else{
                stringBuilder= new StringBuilder[1];
            }

            for (int j = 0; j < stringBuilder.length; j++) {
                stringBuilder[j] = new StringBuilder();
            }
            
            for (int looper = 0; looper < loopPerRow; looper++) {
                long runTimeStack, runTimeLinkedList;
                int jumper = 1;
                long startTime = System.currentTimeMillis();
                for (int z = 0; z < this.lengthFile; z++) {
                    try {
                        stringBuilder[jumper - 1].append(ConvertNumberTypeWithLinkedList.convertNumber(source[z], 10, 2)).append(" ");

                    } catch (Exception e) {
                        System.out.println( "Lỗi" + e);
                    }
                   if((i + 1) / jumper == 1000){
                       jumper ++;
                   }
                }
                long finishTime = System.currentTimeMillis();
                runTimeLinkedList =finishTime - startTime;
                resTimesLinked[timeCount] = runTimeLinkedList;

                jumper = 1;
                startTime = System.currentTimeMillis();
                for (int z = 0; z < this.lengthFile; z++) {
                    try {
                        stringBuilder[jumper - 1].append(ConvertNumberTypeWithStack.convertNumber(source[z], 10, 2) + " ");

                    } catch (Exception e) {
                        System.out.println( "Lỗi" + e);
                    }
                   if((i + 1) / jumper == 1000){
                       jumper ++;
                   }
                }
                finishTime = System.currentTimeMillis();
                runTimeStack =finishTime - startTime;
                resTimesStack[timeCount] = runTimeStack;
                
                timeCount ++;
                modelTest.insertRow(0, new Object[]{runTimeStack, runTimeLinkedList, Math.abs(runTimeStack - runTimeLinkedList), ((runTimeStack - runTimeLinkedList) > 0)? "Linked List" : "Stack" });
            }
            runner = i;
        }
        running = false;
    }
    
}
