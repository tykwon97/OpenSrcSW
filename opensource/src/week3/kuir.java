package week3;

//import week3.makeCollection;
//import week3.makeKeyword;

public class kuir {

	public static void main(String[] args) {

		// == : ���ڿ��� �⺻���� ��쿡�� ����� �񱳵ȴ�. ������ �������� ��������� �񱳰� ���� �ʴ´�.
		//equals : String Ŭ������ �޼ҵ�, �⺻�� �����̵� ������ �����̵� �� ����
		try {
			makeCollection mc = new makeCollection();
			makeKeyword mk = new makeKeyword();
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
			else{
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}catch(NullPointerException e) {
			System.out.println("NullPointerException �߻�");
		}

	}

}
