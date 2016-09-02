package loan.advisor.lukekramer.assignment6.clientapi;

import android.os.AsyncTask;
import java.util.Collections;
import loan.advisor.lukekramer.assignment6.entity.Login;
import loan.advisor.lukekramer.assignment6.entity.Login.Builder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class PersonLoginPost extends AsyncTask<Void, Void, String> {
    private long clientid;
    private String id;
    private String password;
    private String username;

    public void setId(String id) {
        this.id = id;
    }

    public void setClientid(long clientid) {
        this.clientid = clientid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public long getClientid() {
        return this.clientid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    protected String doInBackground(Void... params) {
        String uri = "http://148.100.4.108:8080/login/";
        Login login = new Builder().Userid(getClientid()).Username(getUsername()).Password(getPassword()).build();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity requestEntity = new HttpEntity(login, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        setId(restTemplate.exchange("http://148.100.4.108:8080/login/", HttpMethod.POST, requestEntity, Login.class, new Object[0]).getHeaders().getFirst(HttpHeaders.LOCATION));
        return null;
    }
}
