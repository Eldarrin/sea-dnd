package io.eldarrin.seadnd.common.wrapper;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoRepositoryWrapper {

	private final MongoClient client;

	private static final Logger logger = LoggerFactory.getLogger(MongoRepositoryWrapper.class);

	// TODO set config
	public MongoRepositoryWrapper(Vertx vertx, JsonObject config) {
		logger.info("starting");
		this.client = MongoClient.createShared(vertx, config);
		logger.info("starting done");
	}

	protected void upsertSingle(JsonObject document, String collection, Handler<AsyncResult<String>> resultHandler) {
		client.save(collection, document, resultHandler);
	}

	/*
	protected Future<List<JsonObject>> selectDocuments(String collection, JsonObject query) {
		Future<List<JsonObject>> future = Future.future();
		client.find(collection, query, res -> {
			if (res.succeeded()) {
				future.complete(res.result());
			} else {
				future.fail(res.cause());
			}
		});

		return future;
	}
	*/

	// TODO swap for JSON Query
	protected Promise<JsonObject> retrieveDocument(String collection, Integer score, Integer percentageScore) {
		Promise<JsonObject> promise = Promise.promise();
		JsonObject query = new JsonObject()
				.put("score", score)
				.put("percentageScore", percentageScore);
		client.find(collection, query, res -> {
			if (res.succeeded()) {
				for (JsonObject json : res.result()) {
					promise.complete(json);
				}
			} else {
				promise.fail(res.cause());
			}
		});
		return promise;
	}

	/*
	protected Future<Optional<JsonObject>> removeDocument(String collection, String id) {
		Future<Optional<JsonObject>> future = Future.future();
		client.removeDocument(collection, new JsonObject().put("_ID", id), res -> {
			if (res.succeeded()) {
				future.complete(Optional.of(res.result().toJson().put("deleted", true)));
			} else {
				future.fail(res.cause());
			}
		});
		
		
		return future;
	}
	*/
}
