package week3;

import java.io.IOException;

public class kuir {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// == : ���ڿ��� �⺻���� ��쿡�� ����� �񱳵ȴ�. ������ �������� ��������� �񱳰� ���� �ʴ´�.
		//equals : String Ŭ������ �޼ҵ�, �⺻�� �����̵� ������ �����̵� �� ����
		try {
			makeCollection mc = new makeCollection();
			makeKeyword mk = new makeKeyword();
			indexer index = new indexer();
			searcher sc = new searcher();

			if(args[0].equals("-c")){
				//	../week2/html_example
				//	"C:/study/SimpleIR/week3/html_example"
				mc.collection(args[1]);
			} 
			else if(args[0].equals("-k")){
				//	../week3/collection.xml
				//	"C:/study/SimpleIR/week3/collection.xml"
				mk.keyword(args[1]);
			} 
			else if(args[0].equals("-i")){
				//-i C:/study/SimpleIR/week3/index.xml
				index.invert(args[1]);
			} 
			else if(args[0].equals("-s")){
				//-s C:/study/SimpleIR/week3/index.post -q "query"
				sc.search(args[1],args[2],args[3]);
			} 
			else{
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}catch(NullPointerException e) {
			System.out.println("NullPointerException �߻�");
		}

	}

}
