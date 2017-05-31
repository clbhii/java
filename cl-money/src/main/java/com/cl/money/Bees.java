package com.cl.money;

import java.util.Map;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class Bees extends AbstractProduct{

	public Bees(Integer pagesize) {
		super(pagesize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(int page, Map<String, Double> map) {
		WebResource service = client.resource("http://api.app.hmyd.com:80/");
		service = service.path("app/item/v3/list");
		Builder header = service.header("hm-app", "HMA")
			.header("hm-app-v", "3.5.0")
			.header("hm-app-m", "Prod")
			.header("hm-app-c", "360")
			.header("hm-app-id", "Al3broCzqdEATxOtLrsuJy0yS9biB9U1eSlWtNMS")
			.header("hmyd-app-timestamp", "1484817735765").header("hmyd-app-sign", "fZdraGzI/+WMKdrmccMltX7QXBm1DggoWYUIRjQ/lxP0cm5gHKPrT2rN3qGO4mHQn88422Kqb2N9PQYFodcYuk19N0KiUJoSlEkM85hxh7Q/bDGDOeWemla+FvFL3i/E+2hW5dwMVGaOid/PPKTROU+Pv7qQ2jBQJ31MrQxwJPp78slB+r0bYwEXrTXY1XX9+FKjPskUnPi0DHYDAwzNC8iM3tvHO/AHWYXpE90zv1Gga67Z++36gsAWkIBPuogCREyryZeT3JTr73lD5kAegy74jIPi9rU0FgUzeuQaa30w0K7ClfHENEinGXM0CMhLFPXmQnrFZGb0vL9cBzxrwQ==");
		String resultJson = header.get(String.class);
		System.out.println(resultJson);
		
	}

	
}
