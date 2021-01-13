package com.example.elasticsearch_api;

import com.alibaba.fastjson.JSON;
import com.example.elasticsearch_api.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.SearchOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ElasticsearchApiApplicationTests {

	@Autowired
	private RestHighLevelClient restHighLevelClient;

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateIndex() throws IOException {

		CreateIndexRequest request = new CreateIndexRequest("network");

		CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

		System.out.print(response);
	}

	@Test
	void testExistIndex()throws Exception{
		GetIndexRequest request = new GetIndexRequest("ems");
		boolean flag = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);

		System.out.print(flag);
	}

	@Test
	void testDeleteIndex() throws Exception{
		DeleteIndexRequest request = new DeleteIndexRequest("network");
		AcknowledgedResponse delete = restHighLevelClient.indices().delete(request,RequestOptions.DEFAULT);

		System.out.print(delete);
	}

	@Test
	void testAdd() throws Exception{
		User user = new User(13,"yx6","6.6.6.6");
		IndexRequest request = new IndexRequest("network");

		request.id("13");

		request.source(JSON.toJSONString(user), XContentType.JSON);

		IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);

		System.out.println(response.toString());
		System.out.println(response.status());
	}

	@Test
	void testISExists() throws Exception{
		GetRequest request = new GetRequest("network","1");

		request.fetchSourceContext(new FetchSourceContext(false));
		request.storedFields("_none_");

		boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
	}

	@Test
	void testGetData () throws Exception{
		GetRequest request = new GetRequest("network","1");
		GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
		System.out.println(response.getSourceAsString());
		System.out.println(response);
	}

	@Test
	void testUpdateData() throws Exception{
		UpdateRequest request = new UpdateRequest("network","1");
		request.timeout("1s");

		User user = new User(1,"yx1","1.1.1.1");
		request.doc(JSON.toJSONString(user),XContentType.JSON);

		UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);

		System.out.println(response.status());
		System.out.println(response);
	}

	void testDeleteData() throws Exception{
		DeleteRequest request = new DeleteRequest("network","1");
		request.timeout("1s");

		DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
		System.out.println(response.status());
		System.out.println(response);
	}

	@Test
	void testBathData() throws Exception{
		BulkRequest request = new BulkRequest();
		request.timeout("10s");

		List<User> users = new ArrayList<>();
		users.add(new User(14,"yx6","6.6.6.6"));
		users.add(new User(15,"yx7","7.7.7.7"));
		users.add(new User(16,"yx8","8.8.8.8"));

		for (int i = 0; i < users.size(); i++){
			request.add(
					new IndexRequest("network").id(""+(i + 1)).source(JSON.toJSONString(users.get(i)), XContentType.JSON)
			);
		}
		BulkResponse bulkResponse = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
		System.out.println(bulkResponse.hasFailures());

	}

	@Test
	void testSearch() throws Exception{
		SearchRequest request = new SearchRequest("network");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("ip","7.7.7.7");
		searchSourceBuilder.query(termQueryBuilder);

		request.source(searchSourceBuilder);

		SearchResponse searchResponse = restHighLevelClient.search(request,RequestOptions.DEFAULT);
		System.out.println(JSON.toJSONString(searchResponse.getHits()));
		System.out.println("===============");

		for (SearchHit documentFields : searchResponse.getHits().getHits()){
			System.out.println(documentFields.getSourceAsMap());
		}
	}

	@Test
	void testAllSearch() throws Exception{
		//1、创建requst对象
		SearchRequest request = new SearchRequest("network");
		//2、创建查询条件
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
		searchSourceBuilder.query(queryBuilder);
		//默认10条
		searchSourceBuilder.size(20);
		request.source(searchSourceBuilder);

		SearchResponse searchResponse = restHighLevelClient.search(request,RequestOptions.DEFAULT);
		System.out.println(JSON.toJSONString(searchResponse.getHits()));
		System.out.println("===============");

		int count = 0;
		for (SearchHit documentFields : searchResponse.getHits().getHits()){
			count++;
			System.out.println(documentFields.getSourceAsMap());
		}
		System.out.println(count);
		System.out.println(searchResponse.getHits().getTotalHits());
	}
}
