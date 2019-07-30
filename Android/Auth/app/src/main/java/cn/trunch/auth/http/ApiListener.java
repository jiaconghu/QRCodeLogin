package cn.trunch.auth.http;

/**
 * API调结果用监听接口
 */
public interface ApiListener {

    void success(Api api);

    void failure(Api api);
}
