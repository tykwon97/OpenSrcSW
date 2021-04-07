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
		//System.out.println(Arrays.toString(arr));
		//System.out.println(Arrays.toString(num));
		//System.out.println();


		FileInputStream fileStream = new FileInputStream(addr);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();

		//System.out.println("�о�� ��ü�� type -> "+ object.getClass());

		HashMap hashMap = (HashMap)object;
		double[] total = new double[5];
		for(int i=0;i<kl.size();i++)
		{
			double[] value = (double[])hashMap.get(arr[i]);
			if(value == null) {
				//System.out.println("�ؽ��ʿ� �������� �ʽ��ϴ�. -> "+ arr[i]);
			}else {
				//System.out.println("�ؽ��ʿ� ���� -------------------------------> "+ arr[i]);
				//System.out.println(Arrays.toString(value));
				for(int k=0;k<value.length;k++){
					{
						if(value[k]==0.0) {
							total[0]=total[0]+(value[k+1]*num[i]);
						}else if(value[k]==1.0) {
							total[1]=total[1]+(value[k+1]*num[i]);
						}else if(value[k]==2.0) {
							total[2]=total[2]+(value[k+1]*num[i]);
						}else if(value[k]==3.0) {
							total[3]=total[3]+(value[k+1]*num[i]);
						}else if(value[k]==4.0) {
							total[4]=total[4]+(value[k+1]*num[i]);
						}else
						{
							System.out.println("���� �߻�");
						}
						k++;//2�� ����
					}
				}
			}
		}
		//System.out.println("total[0] : "+Math.round(total[0]*100)/100.0);
		//System.out.println("total[1] : "+Math.round(total[1]*100)/100.0);
		//System.out.println("total[2] : "+Math.round(total[2]*100)/100.0);
		//System.out.println("total[3] : "+Math.round(total[3]*100)/100.0);
		//System.out.println("total[4] : "+Math.round(total[4]*100)/100.0);

		String[] name = new String[5];
		int n=0;
		String address = addr + "/../collection.xml"; 
		//System.out.println(address);
		File file = new File(address);//�˻��� ���丮
		BufferedReader File = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
		String Line = null; 
		while( (Line = File.readLine()) != null ) {
			if(Line.indexOf("<title>") != -1) {
				String[] split1 = Line.split("<title>");
				String[] split2 = split1[1].split("</title>");
				name[n]=split2[0];
				n++;
			}
		}
		//System.out.println(Arrays.toString(name));
		
		String[] max = new String[5];
		String tmp1;
		double tmp2;
		for(int i=0;i<total.length;i++)
		{
			for(int j=i+1;j<total.length;j++)
			{
				if(total[j]>total[i])
				{
					tmp1 = name[j];
					name[j] = name [i];
					name[i]= tmp1;
					
					tmp2 = total[j];
					total[j] = total [i];
					total[i]= tmp2;
				}
			}
		}
		//System.out.println(Arrays.toString(name));
		//System.out.println(Arrays.toString(total));
		System.out.println();
		System.out.println("1���� : "+name[0]);
		System.out.println("2���� : "+name[1]);
		System.out.println("3���� : "+name[2]);
	}

	//����ϴ� �޼ҵ�
	public void CalcSim()
	{

	}

}