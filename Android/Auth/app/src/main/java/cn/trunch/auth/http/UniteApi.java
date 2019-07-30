package cn.trunch.auth.http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class UniteApi extends Api {

    private String jsonCode;
    private JSONObject jsonData;

    private String url;

    public UniteApi(String url, HashMap<String, String> paramsMap) {
        this.url = url;
        setParamsMap(paramsMap);
    }

    public String getJsonCode() {
        return jsonCode;
    }

    public JSONObject getJsonData() {
        return jsonData;
    }

    @Override
    protected void parseCode(JSONObject jsonObject) {
        this.jsonCode = jsonObject.optString("code");
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        this.jsonData = jsonObject.getJSONObject("data");
    }

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    protected boolean isBackToUiThread() {
        return true;
    }
}
