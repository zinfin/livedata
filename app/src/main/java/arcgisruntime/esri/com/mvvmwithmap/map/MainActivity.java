package arcgisruntime.esri.com.mvvmwithmap.map;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;

import arcgisruntime.esri.com.mvvmwithmap.R;
import arcgisruntime.esri.com.mvvmwithmap.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MapViewModel mapViewModel;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding =  DataBindingUtil.setContentView(this, R.layout.activity_main);
        mapView = findViewById(R.id.mapview);
        binding.setLifecycleOwner(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        }

        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);

        mapViewModel.getMap().observe(this, new Observer<ArcGISMap>() {
            @Override
            public void onChanged(@Nullable ArcGISMap arcGISMap) {
                MutableLiveData<ArcGISMap> mutableLiveData = mapViewModel.getMap();
                ArcGISMap map = mutableLiveData.getValue();
                binding.mapview.setMap(map);
            }
        });

        Observer<Boolean> allowedObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                binding.btnAddFeature.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            }
        };


        mapViewModel.getAllowAddFeature().observe(this, allowedObserver);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                mapViewModel.getAllowAddFeature().setValue(true);
            }
        }, 10000);

    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mapView.setMap(null);
    }
}
