package com.cl.java.jdk8;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BiFunctionTest {

	public static void main(String[] args) {
		BiFunction<String, List<Article>,List<Article>> byAuthor = 
				(name, articles) -> articles.stream().filter((article) -> name.equals(article.getAuthor())).collect(Collectors.toList());
				
		BiFunction<String, List<Article>,List<Article>> byTag = 
				(tag, articles) -> articles.stream().filter((article) -> article.getTag().contains(tag)).collect(Collectors.toList());
				
		Function<List<Article>, List<Article>> sortByDate = (articles) -> articles.stream().sorted(
				(x,y) -> y.getPublishDate().compareTo(x.getPublishDate())
		).collect(Collectors.toList());
		
		Function<List<Article>, Optional<Article>> fisrt = (a) -> a.stream().findFirst();
		
		//组合
		Function<List<Article>, Optional<Article>> newest = fisrt.compose(sortByDate);
		
		BiFunction<String, List<Article>, Optional<Article>> newestByAuthor = byAuthor.andThen(newest);
	}
}
class Article{
	private String author;
	private String tag;
	private Date publishDate;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	
}
