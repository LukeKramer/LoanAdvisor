package loan.advisor.lukekramer.assignment6.clientapi;

import android.os.AsyncTask;
import java.util.Collections;
import loan.advisor.lukekramer.assignment6.entity.Person;
import loan.advisor.lukekramer.assignment6.entity.Person.Builder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestPerson extends AsyncTask<Void, Void, String> {
    private String email;
    private String fname;
    private String id;
    private long income;
    private String lname;
    private String number;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public long getuserIncome() {
        return this.income;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }

    public String getNumber() {
        return this.number;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setuserIncome(long income) {
        this.income = income;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    protected String doInBackground(Void... params) {
        String uri = "http://148.100.4.108:8080/person/";
        Person person = new Builder().FirstName(getFname()).Email(getEmail()).Income(getuserIncome()).LastName(getLname()).PhoneNumber(getNumber()).build();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity requestEntity = new HttpEntity(person, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        setId(restTemplate.exchange("http://148.100.4.108:8080/person/", HttpMethod.POST, requestEntity, Person.class, new Object[0]).getHeaders().getFirst(HttpHeaders.LOCATION));
        return null;
    }
}
