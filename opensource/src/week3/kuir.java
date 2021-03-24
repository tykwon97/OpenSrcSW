package week3;

//import week3.makeCollection;
//import week3.makeKeyword;

public class kuir {

	public static void main(String[] args) {

		// == : 문자열이 기본형인 경우에만 제대로 비교된다. 참조형 형식으로 만들어지면 비교가 되지 않는다.
		//equals : String 클래스의 메소드, 기본형 형식이든 참조형 형식이든 비교 가능
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
				System.out.println("잘못 입력하셨습니다.");
			}
		}catch(NullPointerException e) {
			System.out.println("NullPointerException 발생");
		}

	}

}
