package week3;

import java.io.IOException;

public class kuir {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// == : 문자열이 기본형인 경우에만 제대로 비교된다. 참조형 형식으로 만들어지면 비교가 되지 않는다.
		//equals : String 클래스의 메소드, 기본형 형식이든 참조형 형식이든 비교 가능
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
				System.out.println("잘못 입력하셨습니다.");
			}
		}catch(NullPointerException e) {
			System.out.println("NullPointerException 발생");
		}

	}

}
