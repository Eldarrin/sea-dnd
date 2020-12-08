package io.eldarrin.seadnd.common.wrapper;

import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

public class MySqlRepositoryWrapper {

    protected final MySQLPool pool;

    public MySqlRepositoryWrapper(Vertx vertx, JsonObject config) {
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(config.getInteger("port", 3306))
                .setHost(config.getString("host", "mysql"))
                .setDatabase(config.getString("database"))
                .setUser(config.getString("username"))
                .setPassword(config.getString("password"));

        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);

        pool = MySQLPool.pool(vertx, connectOptions, poolOptions);
    }

    protected Promise<Void> execute(String sql) {
        Promise<Void> promise = Promise.promise();

        pool.query(sql)
                .execute(ar -> {
                    if (ar.succeeded()) {
                        RowSet<Row> result = ar.result();
                        promise.complete();
                        System.out.println("Got " + result.size() + " rows ");
                    } else {
                        promise.fail(ar.cause().getCause());
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                    // Now close the pool
                    //pool.close();
                });
        return promise;
    }

    protected void insert() {
        pool
                .preparedQuery("INSERT INTO users (first_name, last_name) VALUES (?, ?)")
                .execute(Tuple.of("Julien", "Viet"), ar -> {
                    if (ar.succeeded()) {
                        RowSet<Row> rows = ar.result();
                        System.out.println(rows.rowCount());
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                });
    }

    protected Promise<RowSet<Row>> retrieveAll(String sql) {
        Promise<RowSet<Row>> promise = Promise.promise();
        pool.query(sql)
            .execute(ar -> {
                if (ar.succeeded()) {
                    RowSet<Row> result = ar.result();
                    promise.complete(result);
                    System.out.println("Got " + result.size() + " rows ");
                } else {
                    promise.fail(ar.cause().getCause());
                    System.out.println("Failure: " + ar.cause().getMessage());
                }
                // Now close the pool
                //pool.close();
            });
        return promise;
    }

}
