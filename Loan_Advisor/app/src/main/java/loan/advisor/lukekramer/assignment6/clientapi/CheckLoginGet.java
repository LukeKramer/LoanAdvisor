package loan.advisor.lukekramer.assignment6.clientapi;

import android.content.Context;
import android.os.AsyncTask;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import loan.advisor.lukekramer.assignment6.entity.Login;
import loan.advisor.lukekramer.assignment6.repository.person.Impl.PersonRepositoryImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class CheckLoginGet extends AsyncTask<Void, Void, String> {
    private Context context;
    String id;
    String password;
    String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getId() {
        return this.id;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    protected String doInBackground(Void... params) {
        MultiValueMap requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        RestTemplate restTemplate = new RestTemplate();
        Map map = new HashMap();
        map.put(PersonRepositoryImpl.COLUMN_ID, getId());
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        HttpEntity<Login> requestEntity = restTemplate.exchange("http://148.100.4.108:8080/login/{id}", HttpMethod.GET, new HttpEntity(requestHeaders), Login.class, map);
        setPassword(((Login) requestEntity.getBody()).getPassword());
        setUsername(((Login) requestEntity.getBody()).getUsername());
        return null;
    }
}
