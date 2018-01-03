package c4q.com.midprogramredo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.security.auth.login.LoginException;

public class LoopActivity extends AppCompatActivity {
    TextView progressText;
    Button activityButton;

    int num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);

        progressText = (TextView)findViewById(R.id.progress);
        activityButton= (Button)findViewById(R.id.login_activity_button);
        // Create an instance of Asyc class
        Async async = new Async();
        //Execute starts the code created in the Async class
        async.execute();

        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoopActivity.this, LoginActivity.class);
                startActivity(intent);


            }
        });


    }





    public class Async extends AsyncTask<Integer, Integer, Integer> {


        // On pre execute sets everything up before running
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        //Task being done in background. Done in background does not run on main thread.
        // the for loop iterates through up to 100000. publishProgress() shows each iteration in "%"
        @Override
        protected Integer doInBackground(Integer[] value) {

            int completed = 0;
            for (int i = num; i <100000 ; i++) {
                publishProgress(i);
                completed+=1 ;

            }
            return completed ;
        }

        //shows progress of the task being done. effects the ui but not a heavy strain,
        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);

            progressText.setText(" Loops Completed " + progress[0]);

        }


        //Shows final result of task
        // progressText is then set to the result of the number of times the task is done.
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            progressText.setText("I counted " + result);
        }
    }

}
