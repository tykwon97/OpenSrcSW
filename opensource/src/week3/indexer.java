package week3;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Arrays;

class indexer {
	
	@SuppressWarnings ( {"rawtypes", "unchecked", "nls" })
	
	public void invert(String addr) throws IOException, ClassNotFoundException {
		
		FileOutputStream fileStream1 = new FileOutputStream("C:/study/SimpleIR/week3/index.post");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileStream1);
		
		HashMap<String, double[]> food = new HashMap<String, double[]>();
		
		File file = new File(addr);//검색할 디렉토리
		BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
		String sLine = null; 
		int num = 0;
		while( (sLine = inFile.readLine()) != null ) {
			if(sLine.indexOf("<title>") != -1)
				num ++; //title개수 -> 반복 횟수
		}
		
		int i=0;
		BufferedReader File = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
		String Line = null; 
		
		while( (Line = File.readLine()) != null ) {
			if(file.exists()) {
				if(Line.indexOf("<body>") != -1)
				{	
					//System.out.println("i는 = "+i);
					String[] split1 = Line.split("<body>");
					String[] split2 = split1[1].split("</body>");
					String[] word = split2[0].split("#");
			
					//System.out.println(word);
					for(String wo : word) {
						String[] separate = wo.split(":");
						//System.out.println(separate[0] + "  " +separate[1]);
						//System.out.println(wo);
						
						double[] arr = new double[10];
						arr[0]=0.0;
						arr[2]=1.0;
						arr[4]=2.0;
						arr[6]=3.0;
						arr[8]=4.0;
						
						if (food.containsKey(separate[0]))
						{
							arr = food.get(separate[0]);
							arr[i*2+1]=Double.parseDouble(separate[1]);
						}else
							arr[i*2+1]=Double.parseDouble(separate[1]);
							
						food.put(separate[0],arr);
						//System.out.print(separate[0]+" : ");
						//System.out.println(Arrays.toString(arr));

					}
					i++;
					//System.out.println(split2[0]);
					//food.put( i++, split2[0]);
					
				} //if
			}//if

		} //while
		

		
		
		for(String key : food.keySet()){
            double[] value = food.get(key);
            
            double n1,n2,n3,n4,n5;
            double total = 0;
            if(value[1]>0)
            	total++;
            if(value[3]>0)
            	total++;
            if(value[5]>0)
            	total++;
            if(value[7]>0)
            	total++;
            if(value[9]>0)
            	total++;
            
            n1=value[1] * Math.log(num/total);
            n2=value[3] * Math.log(num/total);
            n3=value[5] * Math.log(num/total);
            n4=value[7] * Math.log(num/total);
            n5=value[9] * Math.log(num/total);

            value[1]=Math.round(n1*100)/100.0;
            value[3]=Math.round(n2*100)/100.0;
            value[5]=Math.round(n3*100)/100.0;
            value[7]=Math.round(n4*100)/100.0;
            value[9]=Math.round(n5*100)/100.0;
            
            int n=0;
            int j=0;
            double[] arr = new double[(int) total*2];
            while(n<total*2)
            {
            	if(value[j*2+1]>0)
            	{
            		arr[n]=value[j*2];
            		arr[n+1]=value[j*2+1];
            		n=n+2;
            	}
            	j++;
            }
            food.put(key,arr);
            //value[1]=Double.toString(n1);
            double[] val = food.get(key);
            //System.out.println(key+" : "+Arrays.toString(val));
            
        }
 
		objectOutputStream.writeObject(food); //04.01수정 (바뀐 사항을 저장 못함...)
		objectOutputStream.close();
		
		//System.out.println("=========END=========");
		
		for(String a : food.keySet()){ //저장된 key값 확인
			double ar[] = food.get(a);
		    System.out.println("[Key]:" + a + " [Value]:" +Arrays.toString(ar));
		}
		
	}
}
