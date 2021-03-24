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

			// docs ������Ʈ
			Document docu = docBuilder.newDocument();
			docu.setXmlStandalone(true); //standalone="no" �� �����ش�.

			Element docs = docu.createElement("docs");
			docu.appendChild(docs);

			int num = 0;
			File file = new File(addr);//�˻��� ���丮
			BufferedReader File = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
			String Line = null; 
			while( (Line = File.readLine()) != null ) {
				if(Line.indexOf("<title>") != -1)
					num ++; //title���� -> �ݺ� Ƚ��
			}

			// doc ������Ʈ
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

						// �Ӽ��� ���� (id:1)
						String StrNum = Integer.toString(i++);
						doc.setAttribute("id", StrNum);

						// title ������Ʈ
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
						
						// body ������Ʈ
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

			// XML ���Ϸ� ����
			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); //���� �����̽�4ĭ
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //�鿩����
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); //doc.setXmlStandalone(true); ������ �پ ��µǴºκ� ����
			DOMSource source = new DOMSource(docu);
			StreamResult result = new StreamResult(new FileOutputStream(new File("../week3/index.xml")));

			transformer.transform(source, result);

			System.out.println("=========END=========");
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
