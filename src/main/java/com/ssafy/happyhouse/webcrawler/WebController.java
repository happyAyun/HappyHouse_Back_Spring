package com.ssafy.happyhouse.webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	// ResponseEntity<Map<String, Object>>
	@GetMapping("/craw")
	public ResponseEntity<String> naverCraw() throws Exception {

		System.out.println("들어옴.");
		String clientId = "Mo2LMrzNFOfh5U68idjp"; // 애플리케이션 클라이언트 아이디값"
		String clientSecret = "jscnM3_wXR"; // 애플리케이션 클라이언트 시크릿값"

		String text = null;
		try {
			text = URLEncoder.encode("아파트", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text; // json 결과
		// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
		// // xml 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);

		System.out.println(responseBody);
		return new ResponseEntity<String>(responseBody, HttpStatus.OK);
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (

		IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

//	@GetMapping("/hi")
//	public void hi() {
//		System.out.println("hi");
//	}

//	public static HashMap<String, String> map;

	// @SuppressWarnings("deprecation")
//	@GetMapping("/craw")
//	public ResponseEntity<Map<String, Object>> startCrawl() throws IOException {
//
//		System.out.println("들어옴");
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
//		Date currentTime = new Date();
//
//		String dTime = formatter.format(currentTime);
//		String e_date = dTime;
//
//		currentTime.setDate(currentTime.getDate() - 1);
//		String s_date = formatter.format(currentTime);
//
//		String query = "아파트";
//		String s_from = s_date.replace(".", "");
//		String e_to = e_date.replace(".", "");
//		int page = 1;
//		ArrayList<String> al1 = new ArrayList<>();
//		ArrayList<String> al2 = new ArrayList<>();
//		ArrayList<String> newsList = new ArrayList<>();
//
////		Map<Object, Object> resultMap = new HashMap<>();
//
//		while (page < 20) {
//			String address = "https://search.naver.com/search.naver?where=news&query=" + query + "&sort=1&ds=" + s_date
//					+ "&de=" + e_date + "&nso=so%3Ar%2Cp%3Afrom" + s_from + "to" + e_to + "%2Ca%3A&start="
//					+ Integer.toString(page);
//			Document rawData = Jsoup.connect(address).timeout(5000).get();
//			System.out.println(address);
//			Elements blogOption = rawData.select("div[class=\"news_area\"]");
////			Elements blogOption = rawData.select("dl dt");
//			String realURL = "";
//			String realTITLE = "";
//
////			for (Element option : blogOption) {
//			for (int i = 0; i < blogOption.size(); i++) {
//				Element option = blogOption.get(i);
//				realURL = option.select("a").attr("href");
//				realTITLE = option.select("a").attr("title");
//				System.out.println(realURL.toString());
//				System.out.println(realTITLE);
//				Map<String, Object> news = new HashMap<>();
////				news.put("url", realURL);
////				news.put("title", realTITLE);
////				Map<Integer, Object> set = new HashMap<>();
////				resultMap.put(i, news);
//				al1.add(realURL);
//				al2.add(realTITLE);
//			}
//			page += 10;
//		}
//		System.out.println(al1);
//		System.out.println(al2);
//		Map<String, Object> resultMap = new HashMap<>();
////		resultMap.put("urls", al1);
//		resultMap.put("titles", al2);
//		HttpStatus status = HttpStatus.OK;
//		System.out.println(status);
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
////		return "news/news";
//
//	}

}