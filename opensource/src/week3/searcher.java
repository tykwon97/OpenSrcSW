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
		//word="��鿡�� ��, �и�, ������ �ִ�";
		KeywordList kl = ke.extractKeyword(word,true);

		String[] arr = new String[kl.size()]; //�ش� �̸�
		int[] num = new int[kl.size()]; // ������ Ƚ��
		double[] weight = new double[kl.size()]; // ����ġ
		for(int i=0;i<kl.size();i++)
		{
			Keyword kwrd = kl.get(i);
			//System.out.print(kwrd.getString() + ":" + kwrd.getCnt()+"#");
			String str = (kwrd.getString() + ":" + kwrd.getCnt()+"#");
			arr[i]=kwrd.getString();
			num[i]=kwrd.getCnt();
			//System.out.println(str);
		}
		//System.out.println(Arrays.toString(arr)); //query ���¼�
		//System.out.println(Arrays.toString(num)); //�ش� ���¼� ����
		//System.out.println();
		int size = kl.size();
		CalcSim2(addr,size,arr,num);

	}

	//����ϴ� �޼ҵ�
	public void CalcSim2(String addr, int size,String[] arr, int[]num) throws IOException, ClassNotFoundException
	{
		
	}
}