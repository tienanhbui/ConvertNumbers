/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertnumbers;

import convertnumbers.core.ConvertNumberTypeWithLinkedList;
import convertnumbers.core.ConvertNumberTypeWithStack;
import convertnumbers.core.ReadWriteFile;
import javax.swing.JFileChooser;

/**
 *
 * @author tiena
 */
public class ThreadConvert extends Thread {
        String fileContent, splitChar;
        String[] source;
        int length, runner = 0, _persent, numTab, numberType;
        StringBuilder[] stringBuilder_bin, stringBuilder_Hex, stringBuilder_Oct, stringBuilder_Dec;
        long runtime;
        boolean run = false;

        ThreadConvert(String pathSource, int numTab, int numberType, String splitChar) {
            this.numTab = numTab;
            this.numberType = numberType;
            this.splitChar = splitChar;
            
            if(numTab == 0){
                this.fileContent = pathSource;
            }else{
                this.fileContent = ReadWriteFile.Read(pathSource);
            }

            if(this.fileContent.length() > 0 )run = true;
            this.source = this.fileContent.split(splitChar);

            this.length = this.source.length;
            _persent = (int) Math.ceil(this.length / 100);
            if(_persent == 0) _persent = 1;
            
            if(this.length > 1000){
                stringBuilder_bin = new StringBuilder[(int) Math.ceil(this.length/1000) + 1];
                stringBuilder_Hex = new StringBuilder[(int) Math.ceil(this.length/1000) + 1];
                stringBuilder_Oct = new StringBuilder[(int) Math.ceil(this.length/1000) + 1];
                stringBuilder_Dec = new StringBuilder[(int) Math.ceil(this.length/1000) + 1];
            }else{
                stringBuilder_bin = new StringBuilder[1];
                stringBuilder_Hex = new StringBuilder[1];
                stringBuilder_Oct = new StringBuilder[1];
                stringBuilder_Dec = new StringBuilder[1];
            }
            
            for (int i = 0; i < stringBuilder_bin.length; i++) {
                stringBuilder_bin[i] = new StringBuilder();
                stringBuilder_Hex[i] = new StringBuilder();
                stringBuilder_Oct[i] = new StringBuilder();
                stringBuilder_Dec[i] = new StringBuilder();
                
            }
            
            // unset var not use
            switch(numberType){
                case 2 : stringBuilder_bin = null; break;
                case 8 : stringBuilder_Oct = null;  break;
                case 16 : stringBuilder_Hex = null; break;
                default: stringBuilder_Dec = null;
            }
        }

        public float getProcessStatus(){
            if(run)
            return runner/_persent;
            else return 100;
        }
        
        public long getRuntime(){
            return runtime;
        }
        
        public boolean getStatus(){
            return run;
        }
        
        public StringBuilder[] getResult(int numberType){
            StringBuilder[] res;
            switch(numberType){
                case 2 :  res = stringBuilder_bin; break;
                case 8 : res = stringBuilder_Oct;  break;
                case 16 : res = stringBuilder_Hex; break;
                default: res = stringBuilder_Dec;
            }
            return res;
        }
        
        public String[] getSourceData(){
            return this.source;
        }

        public void run() {
            int jumper = 1;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < this.length; i++) {
                try {
                    if(stringBuilder_bin != null)
                    stringBuilder_bin[jumper - 1].append(ConvertNumberTypeWithLinkedList.convertNumber(source[i], numberType, 2)).append(" ");
                    
                    if(stringBuilder_Hex != null)
                    stringBuilder_Hex[jumper - 1].append(ConvertNumberTypeWithLinkedList.convertNumber(source[i], numberType, 16)).append(" ");
                    
                    if(stringBuilder_Oct != null)
                    stringBuilder_Oct[jumper - 1].append(ConvertNumberTypeWithLinkedList.convertNumber(source[i], numberType, 8)).append(" ");
           
                   
                    if(stringBuilder_Dec != null)
                    stringBuilder_Dec[jumper - 1].append(ConvertNumberTypeWithLinkedList.convertNumber(source[i], numberType, 10)).append(" ");
                    
                } catch (Exception e) {
                    System.out.println( "Lỗi" + e);
                }
               runner = i;
               
               if((i + 1) / jumper == 1000){
                   jumper ++;
               }
            }
            run = false;
            long finishTime = System.currentTimeMillis();
            runtime = finishTime - startTime;
        }
     }
