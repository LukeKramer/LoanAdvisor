package loan.advisor.lukekramer.assignment6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonRegister(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterClient.class));
    }

    public void buttonLogin(View view) throws InterruptedException {
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    public void exit(View view) {
        finish();
    }
}
