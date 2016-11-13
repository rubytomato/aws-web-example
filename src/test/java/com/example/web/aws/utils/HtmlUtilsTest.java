package com.example.web.aws.utils;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.UriUtils;

public class HtmlUtilsTest {

	@Test
	public void test1() {
		String str = "<div class=\"container-fluid\"><div class=\"row\"><div class=\"col-md-12 well\"><h2>タイトル</h2></div></div></div>";

		String esc = HtmlUtils.htmlEscape(str);
		System.out.println(esc);

		esc = HtmlUtils.htmlEscapeDecimal(str);
		System.out.println(esc);

		esc = HtmlUtils.htmlEscapeHex(str);
		System.out.println(esc);


		String uesc = HtmlUtils.htmlUnescape("&lt;div class=&quot;container-fluid&quot;&gt;&lt;div class=&quot;row&quot;&gt;&lt;div class=&quot;col-md-12 well&quot;&gt;&lt;h2&gt;タイトル&lt;/h2&gt;&lt;/div&gt;&lt;/div&gt;&lt;/div&gt;");
		System.out.println(uesc);

		uesc = HtmlUtils.htmlUnescape("&#60;div class=&#34;container-fluid&#34;&#62;&#60;div class=&#34;row&#34;&#62;&#60;div class=&#34;col-md-12 well&#34;&#62;&#60;h2&#62;タイトル&#60;/h2&#62;&#60;/div&#62;&#60;/div&#62;&#60;/div&#62;");
		System.out.println(uesc);

		uesc = HtmlUtils.htmlUnescape("&#x3c;div class=&#x22;container-fluid&#x22;&#x3e;&#x3c;div class=&#x22;row&#x22;&#x3e;&#x3c;div class=&#x22;col-md-12 well&#x22;&#x3e;&#x3c;h2&#x3e;タイトル&#x3c;/h2&#x3e;&#x3c;/div&#x3e;&#x3c;/div&#x3e;&#x3c;/div&#x3e;");
		System.out.println(uesc);

	}

	@Test
	public void test2() throws Exception {
		String uri = "http://user:password@www.example.com:9000/contents/detail.action?qid=1&kw=battery#section7";
		System.out.println(UriUtils.encodeScheme(uri,"UTF-8"));
		System.out.println(UriUtils.encodeAuthority(uri,"UTF-8"));

		System.out.println(UriUtils.encodeHost("localhost","UTF-8"));
		System.out.println(UriUtils.encodePort("9000","UTF-8"));
		System.out.println(UriUtils.encodePath("/contents/detail.action","UTF-8"));
		System.out.println(UriUtils.encodeQuery("?qid=1&kw=バッテリー","UTF-8"));
		System.out.println(UriUtils.encodeFragment("#section7","UTF-8"));

		System.out.println(UriUtils.extractFileExtension(uri));

		System.out.println(UriUtils.encode(uri, StandardCharsets.UTF_8.name()));
		System.out.println(UriUtils.decode("http%3A%2F%2Fuser%3Apassword%40www.example.com%3A9000%2Fcontents%2Fdetail.action%3Fqid%3D1%26kw%3Dbattery%23section7","UTF-8"));
	}

	private static final String ENC = "UTF-8";

	@Test
	public void encodePathSegment() throws UnsupportedEncodingException {
		assertEquals("Invalid encoded result", "foobar", UriUtils.encodePathSegment("foobar", ENC));
		assertEquals("Invalid encoded result", "%2Ffoo%2Fbar", UriUtils.encodePathSegment("/foo/bar", ENC));
	}

}
