package loan.advisor.lukekramer.assignment6.clientapi;

import android.os.AsyncTask;
import java.util.Collections;
import loan.advisor.lukekramer.assignment6.BuildConfig;
import loan.advisor.lukekramer.assignment6.entity.Login;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class GetAllLogins extends AsyncTask<Void, Void, String> {
    String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    protected String doInBackground(Void... params) {
        String uri = "http://148.100.4.108:8080/logins/";
        MultiValueMap requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        HttpEntity<Login[]> requestEntity = restTemplate.exchange("http://148.100.4.108:8080/logins/", HttpMethod.GET, new HttpEntity(requestHeaders), Login[].class, new Object[0]);
        String testString = BuildConfig.FLAVOR;
        for (Login id : (Login[]) requestEntity.getBody()) {
            testString = testString + id.getId() + "-";
        }
        setId(testString);
        return null;
    }
}
