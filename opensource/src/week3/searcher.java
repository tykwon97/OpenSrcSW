package week3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

public class searcher {
	@SuppressWarnings({"rawtypes","unchecked","nls"})
	public void search(String addr, String type, String word) throws IOException, ClassNotFoundException {
		//System.out.println(addr+ type+ word);

		//init KeywordExtractor
		KeywordExtractor ke = new KeywordExtractor();
		//extract Keywords
		//word="라면에는 면, 분말, 스프가 있다";
		KeywordList kl = ke.extractKeyword(word,true);

		String[] arr = new String[kl.size()]; //해당 이름
		int[] num = new int[kl.size()]; // 나오는 횟수
		double[] weight = new double[kl.size()]; // 가중치
		for(int i=0;i<kl.size();i++)
		{
			Keyword kwrd = kl.get(i);
			//System.out.print(kwrd.getString() + ":" + kwrd.getCnt()+"#");
			String str = (kwrd.getString() + ":" + kwrd.getCnt()+"#");
			arr[i]=kwrd.getString();
			num[i]=kwrd.getCnt();
			//System.out.println(str);
		}
		//System.out.println(Arrays.toString(arr)); //query 형태소
		//System.out.println(Arrays.toString(num)); //해당 형태소 갯수
		//System.out.println();
		int size = kl.size();
		CalcSim2(addr,size,arr,num);

	}

	//계산하는 메소드
	public void CalcSim2(String addr, int size,String[] arr, int[]num) throws IOException, ClassNotFoundException
	{
		
	}
}