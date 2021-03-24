package week3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class makeKeyword {

	public void keyword(String addr) {

		try {	

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// docs 엘리먼트
			Document docu = docBuilder.newDocument();
			docu.setXmlStandalone(true); //standalone="no" 를 없애준다.

			Element docs = docu.createElement("docs");
			docu.appendChild(docs);

			int num = 0;
			File file = new File(addr);//검색할 디렉토리
			BufferedReader File = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
			String Line = null; 
			while( (Line = File.readLine()) != null ) {
				if(Line.indexOf("<title>") != -1)
					num ++; //title개수 -> 반복 횟수
			}

			// doc 엘리먼트
			Element doc = docu.createElement("doc");
			docs.appendChild(doc);

			//BufferedReader inFile = new BufferedReader(new FileReader(file)); 
			BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
			String sLine = null; 

			int i=0;

			while( (sLine = inFile.readLine()) != null ) {
				if(file.exists()) {
					if(sLine.indexOf("<title>") != -1)
					{
						String[] split1 = sLine.split("<title>");
						String[] split2 = split1[1].split("</title>");

						// 속성값 정의 (id:1)
						String StrNum = Integer.toString(i++);
						doc.setAttribute("id", StrNum);

						// title 엘리먼트
						Element title = docu.createElement("title");

						title.appendChild(docu.createTextNode(split2[0]));
						doc.appendChild(title);
					}


					String str = null;
					if(sLine.indexOf("<body>") != -1)
					{
						//init KeywordExtractor
						KeywordExtractor ke = new KeywordExtractor();
						//extract Keywords
						KeywordList kl = ke.extractKeyword(sLine,true);
						
						// body 엘리먼트
						Element body = docu.createElement("body");
						//String[] split1 = sLine.split("<body>");
						//String[] split2 = split1[1].split("</body>");
						//System.out.println(split2[0]);

						
						//print result
						for( int j=0; j<kl.size();j++ )
						{
							Keyword kwrd = kl.get(j);
							//System.out.print(kwrd.getString() + ":" + kwrd.getCnt()+"#");
							str = (kwrd.getString() + ":" + kwrd.getCnt()+"#");
							body.appendChild(docu.createTextNode(str));
							doc.appendChild(body);
						} //for
					} //if
				}//if

			} //while
			//doc = docu.createElement("doc");
			docs.appendChild(doc);

			// XML 파일로 쓰기
			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); //정렬 스페이스4칸
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //들여쓰기
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); //doc.setXmlStandalone(true); 했을때 붙어서 출력되는부분 개행
			DOMSource source = new DOMSource(docu);
			StreamResult result = new StreamResult(new FileOutputStream(new File("../week3/index.xml")));

			transformer.transform(source, result);

			System.out.println("=========END=========");
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
