package loan.advisor.lukekramer.assignment6.clientapi;

import android.os.AsyncTask;
import java.util.Collections;
import loan.advisor.lukekramer.assignment6.entity.Result;
import loan.advisor.lukekramer.assignment6.entity.Result.Builder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class LoanResultPost extends AsyncTask<Void, Void, String> {
    String loanid;
    String outcome;
    String userid;

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLoanid() {
        return this.loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public String getOutcome() {
        return this.outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    protected String doInBackground(Void... params) {
        String uri = "http://10.0.0.13:8080/result/";
        Result standardResult = new Builder().ClientID(Long.parseLong(getUserid())).LoanID(Long.parseLong(getLoanid())).Status(getOutcome()).Date(null).Build();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity requestEntity = new HttpEntity(standardResult, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        ResponseEntity<Result> result = restTemplate.exchange("http://10.0.0.13:8080/result/", HttpMethod.POST, requestEntity, Result.class, new Object[0]);
        return null;
    }
}
