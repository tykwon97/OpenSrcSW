package midterm;

import java.io.IOException;

//input.txt의 상대주소 처리를 못했습니다.
public class midterm {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		try {
			String addr = "../../../" + args[1];
			System.out.println(addr);
			genSnippet gs = new genSnippet();
			//System.out.println(args[0]);
			if(args[0].equals("-f")){
				//	../week2/html_example
				//	"C:/study/SimpleIR/week3/html_example"
				gs.keyword(args[1],args[3]);
				
			} 
			else{
				System.out.println("잘못 입력하셨습니다.");
			}
		}catch(NullPointerException e) {
			System.out.println("NullPointerException 발생");
		}
	}
}
