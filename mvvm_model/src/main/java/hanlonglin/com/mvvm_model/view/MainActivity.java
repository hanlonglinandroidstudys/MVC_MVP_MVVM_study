package hanlonglin.com.mvvm_model.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import hanlonglin.com.mvvm_model.R;
import hanlonglin.com.mvvm_model.databinding.ActivityMainBinding;
import hanlonglin.com.mvvm_model.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    MainViewModel mainViewModel=new MainViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        ActivityMainBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setViewModel(mainViewModel);
        mainViewModel.onResetSelect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_top,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_reset:
                Log.e(TAG,"点击重置");
                mainViewModel.onResetSelect();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
