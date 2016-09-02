package loan.advisor.lukekramer.assignment6.clientapi;

import android.os.AsyncTask;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import loan.advisor.lukekramer.assignment6.BuildConfig;
import loan.advisor.lukekramer.assignment6.entity.Person;
import loan.advisor.lukekramer.assignment6.repository.person.Impl.PersonRepositoryImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class GetPerson extends AsyncTask<Void, Void, String> {
    String email;
    String income;
    String userid;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getIncome() {
        return this.income;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    protected String doInBackground(Void... params) {
        MultiValueMap requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        RestTemplate restTemplate = new RestTemplate();
        Map map = new HashMap();
        map.put(PersonRepositoryImpl.COLUMN_ID, getUserid());
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        HttpEntity<Person> requestEntity = restTemplate.exchange("http://148.100.4.108:8080/person/{id}", HttpMethod.GET, new HttpEntity(requestHeaders), Person.class, map);
        setIncome(((Person) requestEntity.getBody()).getIncome() + BuildConfig.FLAVOR);
        setEmail(((Person) requestEntity.getBody()).getEmailAddress());
        return null;
    }
}
