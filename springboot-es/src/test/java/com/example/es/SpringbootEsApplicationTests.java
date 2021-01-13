package com.example.es;

import com.example.es.beans.Book;
import com.example.es.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class SpringbootEsApplicationTests {

	//@Autowired
	//JestClient jestClient;

	@Autowired
	BookRepository bookRepository;

	@Test
	void contextLoads() {

		String source = "{\n" +
				"    \"first_name\" :  \"Douglas\",\n" +
				"    \"last_name\" :   \"Fir\",\n" +
				"    \"age\" :         37,\n" +
				"    \"about\":        \"I like to build cabinets\",\n" +
				"    \"interests\":  [ \"forestry\" ]\n" +
				"}";

		Index.Builder id = new Index.Builder(source).index("megacorp").type("employee").id("1");
		System.out.println(id);
	}

	@Test
	public void test2(){
		Book book = new Book(1, "西游记", "吴承恩");
		//bookRepository.index(book);
		List<Book> books = bookRepository.findByBookNameLike("游");

		for (Book b:books
				) {
			System.out.println(b);
		}
	}

}
