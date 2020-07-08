
/**
 *
 * @author Aşkın Kadir Çekim
 */
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

public class SMSActivate {

    private String API_KEY;
    private String API_URL = "https://sms-activate.ru/stubs/handler_api.php";

    public SMSActivate(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String getBalance() throws UnsupportedEncodingException, ParseException {
        HttpPost post = new HttpPost(API_URL);
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("api_key", API_KEY));
        urlParameters.add(new BasicNameValuePair("action", "getBalance"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);

            JSONObject jsonObject = new JSONObject("{" + EntityUtils.toString(response.getEntity()) + "}");
            return jsonObject.get("ACCESS_BALANCE").toString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Map<String, Object> getAllNumbers(int country) throws UnsupportedEncodingException, ParseException {
        HttpPost post = new HttpPost(API_URL);
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("api_key", API_KEY));
        urlParameters.add(new BasicNameValuePair("action", "getNumbersStatus"));
        urlParameters.add(new BasicNameValuePair("country", String.valueOf(country)));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);

            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            return jsonObject.toMap();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String[] orderNumber(int country, String service) throws UnsupportedEncodingException, ParseException {
        HttpPost post = new HttpPost(API_URL);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

        urlParameters.add(new BasicNameValuePair("api_key", API_KEY));
        urlParameters.add(new BasicNameValuePair("action", "getNumber"));
        urlParameters.add(new BasicNameValuePair("service", service));
        urlParameters.add(new BasicNameValuePair("country", String.valueOf(country)));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);

            String result = EntityUtils.toString(response.getEntity());
            result = result.substring(14);

            return result.split(":");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStatus(String accessId) throws UnsupportedEncodingException, ParseException {
        HttpPost post = new HttpPost(API_URL);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

        urlParameters.add(new BasicNameValuePair("api_key", API_KEY));
        urlParameters.add(new BasicNameValuePair("action", "getStatus"));
        urlParameters.add(new BasicNameValuePair("id", accessId));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);

            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
