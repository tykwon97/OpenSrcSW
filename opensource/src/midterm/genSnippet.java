package midterm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class genSnippet {

	public void keyword(String addr,String key) throws IOException {
		
		int num = 0;
		int n;
		int[] arr = new int[5];
		String[] arr1 = new String[10];
		File file = new File("C:/study/SimpleIR/input.txt");//검색할 디렉토리
		BufferedReader File = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
		String Line = null; 
		while( (Line = File.readLine()) != null ) {
			n=0;
			
			//for(String wo : Line) {
			//	String[] separate = wo.split(" ");
			
			
			
				String[] split = Line.split(" ");
				System.out.println(split[0]);
				arr1[n++]=split[0];
				//arr[num++]=arr[num++]+1;//겹치는 숫자
				//if(split[0]==null)
				//	break;
			
		}
		
		

	}
}
