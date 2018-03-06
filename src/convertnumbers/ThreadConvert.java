/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertnumbers;

import convertnumbers.core.ExchangeNumberType;
import convertnumbers.core.ReadWriteFile;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiena
 */
public class ThreadConvert extends Thread {
        String fileContent;
        String[] s;
        int length, runner = 0, _persent;
        DefaultTableModel model;
        StringBuilder[] sb;
        long runtime;

        ThreadConvert(DefaultTableModel model, String pathFile) {
            this.model = model;
            this.fileContent = ReadWriteFile.Read(pathFile);
            this.s = this.fileContent.split(" ");
            this.length = this.s.length;
            _persent = this.length / 100;
            if(this.length > 1000)
                sb = new StringBuilder[(int) Math.ceil(this.length/1000) + 1000];
            else sb = new StringBuilder[1];
            
            for (int i = 0; i < sb.length; i++) {
                sb[i] = new StringBuilder();
            }
        }

        public float getProcessStatus(){
            return runner/_persent;
        }
        
        public long getRuntime(){
            return runtime;
        }

        public void run() {
            int jumper = 1;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < this.length; i++) {
               //model.addRow(new Object[]{ExchangeNumberType.convertNumber(s[i], 10, 2).toString(), s[i].toString()});
               
               sb[jumper-1].append(ExchangeNumberType.convertNumber(s[i], 10, 2));
               runner = i;
               
               if(i / jumper == 1000){
                   jumper ++;
               }
            }
            long finishTime = System.currentTimeMillis();
            runtime = finishTime - startTime;
        }
     }
