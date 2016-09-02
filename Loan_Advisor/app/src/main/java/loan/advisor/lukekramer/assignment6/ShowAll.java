package loan.advisor.lukekramer.assignment6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.HashSet;
import java.util.Set;
import loan.advisor.lukekramer.assignment6.entity.Person;
import loan.advisor.lukekramer.assignment6.repository.person.Impl.PersonRepositoryImpl;
import loan.advisor.lukekramer.assignment6.repository.tables.CreateTables;

public class ShowAll extends AppCompatActivity {
    private ArrayAdapter adapter;
    private ListView listView;
    private String[] names;
    private Set<Person> personSet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        new CreateTables(getApplicationContext()).createTables();
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl(getApplicationContext());
        this.personSet = new HashSet();
        this.personSet = personRepository.findAll();
        if (this.personSet.size() > 0) {
            this.names = new String[this.personSet.size()];
            int i = 0;
            for (Person firstName : this.personSet) {
                this.names[i] = firstName.getFirstName();
                i++;
            }
            this.adapter = new ArrayAdapter(this, 17367043, this.names);
            this.listView = (ListView) findViewById(R.id.listView);
            this.listView.setAdapter(this.adapter);
            return;
        }
        Toast.makeText(this, "No Data", 0).show();
    }

    public void returntoHome(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
