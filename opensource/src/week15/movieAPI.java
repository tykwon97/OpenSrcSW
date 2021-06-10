package week15;

//���̹� �˻� API ���� - blog �˻�
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser; 
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//console encoing utf-8�� ��ȯ�ؾߵ�
//JSON�Ľ� ���̺귯�� �߰����־�ߵ�

public class movieAPI { //ApiExamSearchBlog {

public static void main(String[] args) throws IOException, ParseException {
	String ID = null;
	String PassWord = null;
	
	//���� ��ü ����
    File file = new File("..\\week15\\NaverAPI.txt");
    if(file.exists()) { 
    	BufferedReader inFile = new BufferedReader(new FileReader(file)); 
    	String sLine = null; 
    	
    	int num=0;
    	while( (sLine = inFile.readLine()) != null ) {
    		if(num++==0)
    			ID = sLine;
    		else 
    			PassWord = sLine;
    	}
    }

//    System.out.println(ID); //�о���� ���ڿ��� ��� �մϴ�. 
//    System.out.println(PassWord); //�о���� ���ڿ��� ��� �մϴ�. 
    
   String clientId = ID; //���ø����̼� Ŭ���̾�Ʈ ���̵�"
   String clientSecret = PassWord; //���ø����̼� Ŭ���̾�Ʈ ��ũ����"

   String text = null;
   try {
       text = URLEncoder.encode("�����", "UTF-8"); //�׸����丮 -> query �˻��� 
   } catch (UnsupportedEncodingException e) {
       throw new RuntimeException("�˻��� ���ڵ� ����",e);
   }

   String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text;    // json ���
   //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml ���

   Map<String, String> requestHeaders = new HashMap<>();
   requestHeaders.put("X-Naver-Client-Id", clientId);
   requestHeaders.put("X-Naver-Client-Secret", clientSecret);
   String responseBody = get(apiURL,requestHeaders);

   //System.out.println(responseBody);
  
   JSONParser jsonParser = new JSONParser();
   //JSONParse�� json�����͸� �־� �Ľ��� ���� JSONObject�� ��ȯ�Ѵ�.
   JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
   //JSONObject���� PersonsArray�� get�Ͽ� JSONArray�� �����Ѵ�.
   JSONArray infoArray = (JSONArray) jsonObject.get("items");

   for(int i = 0; i<infoArray.size(); i++) {
  	 System.out.println("=item_"+i+"===========================================");
  	 JSONObject itemObject = (JSONObject) infoArray.get(i);
  	 System.out.println("title:\t"+itemObject.get("title"));
  	 System.out.println("subtitle:\t"+itemObject.get("subtitle"));
  	 System.out.println("director:\t"+itemObject.get("director"));
  	 System.out.println("actor:\t"+itemObject.get("actor"));
  	 System.out.println("userRating:\t"+itemObject.get("userRating"));
   }
}

private static String get(String apiUrl, Map<String, String> requestHeaders){
   HttpURLConnection con = connect(apiUrl);
   try {
       con.setRequestMethod("GET");
       for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
           con.setRequestProperty(header.getKey(), header.getValue());
       }

       int responseCode = con.getResponseCode();
       if (responseCode == HttpURLConnection.HTTP_OK) { // ���� ȣ��
           return readBody(con.getInputStream());
       } else { // ���� �߻�
           return readBody(con.getErrorStream());
       }
   } catch (IOException e) {
       throw new RuntimeException("API ��û�� ���� ����", e);
   } finally {
       con.disconnect();
   }
}

private static HttpURLConnection connect(String apiUrl){
   try {
       URL url = new URL(apiUrl);
       return (HttpURLConnection)url.openConnection();
   } catch (MalformedURLException e) {
       throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�. : " + apiUrl, e);
   } catch (IOException e) {
       throw new RuntimeException("������ �����߽��ϴ�. : " + apiUrl, e);
   }
}

private static String readBody(InputStream body){
   InputStreamReader streamReader = new InputStreamReader(body);

   try (BufferedReader lineReader = new BufferedReader(streamReader)) {
       StringBuilder responseBody = new StringBuilder();

       String line;
       while ((line = lineReader.readLine()) != null) {
           responseBody.append(line);
       }

       return responseBody.toString();
   } catch (IOException e) {
       throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
   }
}



}