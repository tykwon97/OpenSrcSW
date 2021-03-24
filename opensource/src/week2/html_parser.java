package week2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader; 
//import java.io.FileReader; 
import java.io.IOException;
import java.io.InputStreamReader;

public class html_parser {

	public static void main(String[] args) throws IOException {
		try {
			 File dir = new File("../week2/html_example");//검색할 디렉토리
			 File files[] = dir.listFiles();
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// docs 엘리먼트
			Document docu = docBuilder.newDocument();
			docu.setXmlStandalone(true); //standalone="no" 를 없애준다.

			Element docs = docu.createElement("docs");
			docu.appendChild(docs);
			for(int i=0;i<files.length;i++) {
				
				// doc 엘리먼트
				Element doc = docu.createElement("doc");
				docs.appendChild(doc);
				
				// 속성값 정의 (id:1)
				String StrNum = Integer.toString(i);
				doc.setAttribute("id", StrNum);

				// title 엘리먼트
				Element title = docu.createElement("title");
				// body 엘리먼트
				Element body = docu.createElement("body");

				//File file = new File("./week2/html_example/떡.html");
				File file = new File(""+files[i]);
				if(file.exists()) 
				{
					//BufferedReader inFile = new BufferedReader(new FileReader(file)); 
					BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
					String sLine = null; 
					while( (sLine = inFile.readLine()) != null ) {

						if(sLine.indexOf("<title>") != -1)
						{
							String[] split1 = sLine.split("<title>");
							String[] split2 = split1[1].split("</title>");
							//System.out.println(split2[0]);
							title.appendChild(docu.createTextNode(split2[0]));
						}
						
						if(sLine.indexOf("<p>") != -1)
						{
							String[] split1 = sLine.split("<p>");
							String[] split2 = split1[1].split("</p>");
							//System.out.println(split2[0]);
							body.appendChild(docu.createTextNode(split2[0]));
						}
					}
				}
				doc.appendChild(title);
				doc.appendChild(body);

				//doc = docu.createElement("doc");
				docs.appendChild(doc);
			}
			// XML 파일로 쓰기
			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); //정렬 스페이스4칸
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //들여쓰기
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); //doc.setXmlStandalone(true); 했을때 붙어서 출력되는부분 개행

			DOMSource source = new DOMSource(docu);
			StreamResult result = new StreamResult(new FileOutputStream(new File("../week2/collection.xml")));

			transformer.transform(source, result);

			System.out.println("=========END=========");

		}catch (Exception e){
			e.printStackTrace();
		}
 		
	}
}

