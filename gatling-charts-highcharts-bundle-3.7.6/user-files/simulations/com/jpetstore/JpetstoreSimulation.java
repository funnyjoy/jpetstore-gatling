package com.jpetstore;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class JpetstoreSimulation extends Simulation {

	{
		FeederBuilder<String> feeder = csv("jpetstore_users.csv").eager().shuffle().circular();

		HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080")
				.inferHtmlResources(AllowList(), DenyList())
				.acceptHeader(
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
				.acceptEncodingHeader("gzip, deflate").acceptLanguageHeader("ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.userAgentHeader(
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36");

		Map<CharSequence, String> headers_0 = new HashMap<>();
		headers_0.put("Proxy-Connection", "keep-alive");
		headers_0.put("Upgrade-Insecure-Requests", "1");

		Map<CharSequence, String> headers_2 = new HashMap<>();
		headers_2.put("Accept", "*/*");
		headers_2.put("Proxy-Connection", "keep-alive");

		Map<CharSequence, String> headers_5 = new HashMap<>();
		headers_5.put("Accept", "application/json, text/javascript, */*; q=0.01");
		headers_5.put("Proxy-Connection", "keep-alive");
		headers_5.put("X-Requested-With", "XMLHttpRequest");

		Map<CharSequence, String> headers_14 = new HashMap<>();
		headers_14.put("Accept", "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8");
		headers_14.put("Proxy-Connection", "keep-alive");

		Map<CharSequence, String> headers_17 = new HashMap<>();
		headers_17.put("Cache-Control", "max-age=0");
		headers_17.put("Origin", "http://localhost:8080");
		headers_17.put("Proxy-Connection", "keep-alive");
		headers_17.put("Upgrade-Insecure-Requests", "1");

		Map<CharSequence, String> headers_35 = new HashMap<>();
		headers_35.put("Accept", "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8");
		headers_35.put("If-Modified-Since", "Fri, 22 Apr 2022 01:13:39 GMT");
		headers_35.put("Proxy-Connection", "keep-alive");

		ScenarioBuilder scn = scenario("JpetstoreSimulation")
				.exec(http("request_0").get("/").headers(headers_0)
						.resources(http("request_1").get("/catalog/").headers(headers_0),
								http("request_2").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(2)
				.exec(http("request_3").get("/catalog/viewCategory?categoryId=FISH").headers(headers_0)
						.resources(http("request_4").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
								http("request_5").get("/categories/FISH?_=1650854400576").headers(headers_5),
								http("request_6").get("/categories/FISH/products?_=1650854400577").headers(headers_5)))
				.pause(1)
				.exec(http("request_7").get("/catalog/viewProduct?productId=FI-FW-01").headers(headers_0)
						.resources(http("request_8").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
								http("request_9").get("/products/FI-FW-01?_=1650854402187").headers(headers_5),
								http("request_10").get("/products/FI-FW-01/items?_=1650854402188").headers(headers_5)))
				.pause(2)
				.exec(http("request_11").get("/catalog/viewItem?itemId=EST-4").headers(headers_0).resources(
						http("request_12").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
						http("request_13").get("/items/EST-4?_=1650854405078").headers(headers_5),
						http("request_14").get("/resources/images/fish3.gif").headers(headers_14)))
				.pause(1)
				.exec(http("request_15").get("/cart/addItemToCart?workingItemId=EST-4").headers(headers_0)
						.resources(http("request_16").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(5)
				.exec(http("request_17").post("/cart/updateCartQuantities").headers(headers_17)
						.formParam("quantity[EST-4]", "20")
						.resources(http("request_18").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(4)
				.exec(http("request_19").get("/catalog/viewCategory?categoryId=FISH").headers(headers_0).resources(
						http("request_20").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
						http("request_21").get("/categories/FISH/products?_=1650854418261").headers(headers_5),
						http("request_22").get("/categories/FISH?_=1650854418260").headers(headers_5)))
				.pause(1)
				.exec(http("request_23").get("/catalog/viewProduct?productId=FI-FW-02").headers(headers_0)
						.resources(http("request_24").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
								http("request_25").get("/products/FI-FW-02?_=1650854420911").headers(headers_5),
								http("request_26").get("/products/FI-FW-02/items?_=1650854420912").headers(headers_5)))
				.pause(3)
				.exec(http("request_27").get("/cart/addItemToCart?workingItemId=EST-21").headers(headers_0)
						.resources(http("request_28").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(3)
				.exec(http("request_29").post("/cart/updateCartQuantities").headers(headers_17)
						.formParam("quantity[EST-4]", "20").formParam("quantity[EST-21]", "5")
						.resources(http("request_30").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(2)
				.exec(http("request_31").get("/order/newOrderForm").headers(headers_0)
						.resources(http("request_32").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(1).feed(feeder)
				.exec(http("request_33").post("/account/signon").headers(headers_17)
						.formParam("j_username", "#{j_username}").formParam("j_password", "#{j_password}")
						.formParam("submit", "Login")
						.resources(http("request_34").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
								http("request_35").get("/resources/images/banner_dogs.gif").headers(headers_35)))
				.pause(4)
				.exec(http("request_36").post("/order/newOrder").headers(headers_17).formParam("cardType", "Visa")
						.formParam("creditCard", "999 9999 9999 9999").formParam("expiryDate", "12/03")
						.formParam("billToFirstName", "ABC").formParam("billToLastName", "XYX")
						.formParam("billAddress1", "901 San Antonio Road").formParam("billAddress2", "MS UCUP02-206")
						.formParam("billCity", "Palo Alto").formParam("billState", "CA").formParam("billZip", "94303")
						.formParam("billCountry", "USA").formParam("_shippingAddressRequired", "on")
						.formParam("shipToFirstName", "ABC").formParam("shipToLastName", "XYX")
						.formParam("shipAddress1", "901 San Antonio Road").formParam("shipAddress2", "MS UCUP02-206")
						.formParam("shipCity", "Palo Alto").formParam("shipState", "CA").formParam("shipZip", "94303")
						.formParam("shipCountry", "USA").formParam("newOrder", "Continue")
						.resources(http("request_37").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(1)
				.exec(http("request_38").post("/order/newOrder").headers(headers_17).formParam("billToFirstName", "ABC")
						.formParam("billToLastName", "XYX").formParam("billAddress1", "901 San Antonio Road")
						.formParam("billAddress2", "MS UCUP02-206").formParam("billCity", "Palo Alto")
						.formParam("billState", "CA").formParam("billZip", "94303").formParam("billCountry", "USA")
						.formParam("shipToFirstName", "ABC").formParam("shipToLastName", "XYX")
						.formParam("shipAddress1", "901 San Antonio Road").formParam("shipAddress2", "MS UCUP02-206")
						.formParam("shipCity", "Palo Alto").formParam("shipState", "CA").formParam("shipZip", "94303")
						.formParam("shipCountry", "USA").formParam("cardType", "Visa")
						.formParam("creditCard", "999 9999 9999 9999").formParam("expiryDate", "12/03")
						.formParam("confirmed", "true")
						.resources(http("request_39").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(2)
				.exec(http("request_40").get("/account/editAccountForm").headers(headers_0)
						.resources(http("request_41").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(5)
				.exec(http("request_42").get("/account/signoff").headers(headers_0)
						.resources(http("request_43").get("/catalog/").headers(headers_0),
								http("request_44").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(3)
				.exec(http("request_45").get("/account/signonForm").headers(headers_0)
						.resources(http("request_46").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(1)
				.exec(http("request_47").post("/account/signon").headers(headers_17)
						.formParam("j_username", "#{j_username}").formParam("j_password", "#{j_password}")
						.formParam("submit", "Login").resources(http("request_48").get("/catalog/").headers(headers_0),
								http("request_49").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(7)
				.exec(http("request_50").get("/catalog/viewCategory?categoryId=FISH").headers(headers_0).resources(
						http("request_51").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
						http("request_52").get("/categories/FISH/products?_=1650854468048").headers(headers_5),
						http("request_53").get("/categories/FISH?_=1650854468047").headers(headers_5)))
				.pause(1)
				.exec(http("request_54").get("/catalog/viewProduct?productId=FI-FW-02").headers(headers_0)
						.resources(http("request_55").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
								http("request_56").get("/products/FI-FW-02?_=1650854470348").headers(headers_5),
								http("request_57").get("/products/FI-FW-02/items?_=1650854470349").headers(headers_5)))
				.pause(1)
				.exec(http("request_58").get("/catalog/viewItem?itemId=EST-20").headers(headers_0).resources(
						http("request_59").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
						http("request_60").get("/items/EST-20?_=1650854472355").headers(headers_5),
						http("request_61").get("/resources/images/fish2.gif").headers(headers_14)))
				.pause(1)
				.exec(http("request_62").get("/catalog/viewCategory?categoryId=DOGS").headers(headers_0)
						.resources(http("request_63").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
								http("request_64").get("/categories/DOGS/products?_=1650854474357").headers(headers_5),
								http("request_65").get("/categories/DOGS?_=1650854474356").headers(headers_5),
								http("request_66").get("/catalog/viewProduct?productId=K9-CW-01").headers(headers_0),
								http("request_67").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
								http("request_68").get("/products/K9-CW-01?_=1650854475380").headers(headers_5),
								http("request_69").get("/products/K9-CW-01/items?_=1650854475381").headers(headers_5)))
				.pause(1)
				.exec(http("request_70").get("/catalog/viewItem?itemId=EST-27").headers(headers_0).resources(
						http("request_71").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
						http("request_72").get("/items/EST-27?_=1650854476776").headers(headers_5),
						http("request_73").get("/resources/images/dog4.gif").headers(headers_14)))
				.pause(1)
				.exec(http("request_74").get("/catalog/viewCategory?categoryId=REPTILES").headers(headers_0).resources(
						http("request_75").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
						http("request_76").get("/categories/REPTILES/products?_=1650854478343").headers(headers_5),
						http("request_77").get("/categories/REPTILES?_=1650854478342").headers(headers_5)))
				.pause(1)
				.exec(http("request_78").get("/catalog/viewProduct?productId=RP-SN-01").headers(headers_0)
						.resources(http("request_79").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
								http("request_80").get("/products/RP-SN-01?_=1650854480487").headers(headers_5),
								http("request_81").get("/products/RP-SN-01/items?_=1650854480488").headers(headers_5)))
				.pause(2)
				.exec(http("request_82").get("/cart/addItemToCart?workingItemId=EST-11").headers(headers_0)
						.resources(http("request_83").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(3)
				.exec(http("request_84").post("/cart/updateCartQuantities").headers(headers_17)
						.formParam("quantity[EST-11]", "1")
						.resources(http("request_85").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2),
								http("request_86").get("/order/newOrderForm").headers(headers_0),
								http("request_87").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(1)
				.exec(http("request_88").post("/order/newOrder").headers(headers_17).formParam("cardType", "Visa")
						.formParam("creditCard", "999 9999 9999 9999").formParam("expiryDate", "12/03")
						.formParam("billToFirstName", "ABC").formParam("billToLastName", "XYX")
						.formParam("billAddress1", "901 San Antonio Road").formParam("billAddress2", "MS UCUP02-206")
						.formParam("billCity", "Palo Alto").formParam("billState", "CA").formParam("billZip", "94303")
						.formParam("billCountry", "USA").formParam("_shippingAddressRequired", "on")
						.formParam("shipToFirstName", "ABC").formParam("shipToLastName", "XYX")
						.formParam("shipAddress1", "901 San Antonio Road").formParam("shipAddress2", "MS UCUP02-206")
						.formParam("shipCity", "Palo Alto").formParam("shipState", "CA").formParam("shipZip", "94303")
						.formParam("shipCountry", "USA").formParam("newOrder", "Continue")
						.resources(http("request_89").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(1)
				.exec(http("request_90").post("/order/newOrder").headers(headers_17).formParam("billToFirstName", "ABC")
						.formParam("billToLastName", "XYX").formParam("billAddress1", "901 San Antonio Road")
						.formParam("billAddress2", "MS UCUP02-206").formParam("billCity", "Palo Alto")
						.formParam("billState", "CA").formParam("billZip", "94303").formParam("billCountry", "USA")
						.formParam("shipToFirstName", "ABC").formParam("shipToLastName", "XYX")
						.formParam("shipAddress1", "901 San Antonio Road").formParam("shipAddress2", "MS UCUP02-206")
						.formParam("shipCity", "Palo Alto").formParam("shipState", "CA").formParam("shipZip", "94303")
						.formParam("shipCountry", "USA").formParam("cardType", "Visa")
						.formParam("creditCard", "999 9999 9999 9999").formParam("expiryDate", "12/03")
						.formParam("confirmed", "true")
						.resources(http("request_91").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)))
				.pause(8)
				.exec(http("request_92").get("/account/signoff").headers(headers_0).resources(
						http("request_93").get("/catalog/").headers(headers_0),
						http("request_94").get("/webjars/jquery/3.5.1/jquery.min.js").headers(headers_2)));

//		setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
//		setUp(scn.injectOpen(rampUsers(20).during(20))).protocols(httpProtocol);
		setUp(scn.injectClosed(
		  rampConcurrentUsers(1).to(20).during(60),
		  constantConcurrentUsers(20).during(300),
		  rampConcurrentUsers(20).to(1).during(60)
		)).protocols(httpProtocol);
	}
}
