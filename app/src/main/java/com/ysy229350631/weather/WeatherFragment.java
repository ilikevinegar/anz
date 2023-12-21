package com.ysy229350631.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherFragment extends Fragment {
    private AppCompatSpinner citySpinner;
    private TextView temperatureView;
    private TextView humidityView;
    private TextView windView;
    private TextView aqiView;
    private RecyclerView futureWeatherRecyclerView;
    private CityViewModel cityViewModel;
    private WeatherViewModel weatherViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        citySpinner = view.findViewById(R.id.city_name);
        temperatureView = view.findViewById(R.id.wt_temp);
        humidityView = view.findViewById(R.id.wt_humi);
        aqiView = view.findViewById(R.id.wt_aqi);
        futureWeatherRecyclerView = view.findViewById(R.id.future_weather);
        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        loadCities();
        setupFutureWeatherRecyclerView();
        return view;
    }
    private void loadCities() {
        cityViewModel.getCityItemData().observe(getViewLifecycleOwner(), cities -> {
            Object City = new Object();
            List<String> cityNames = cities.stream().limit(10).map(City::setCityItemData).collect(Collectors.toList());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, cityNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            citySpinner.setAdapter(adapter);
        });
    }
    private void setupFutureWeatherRecyclerView() {
        futureWeatherRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        futureWeatherRecyclerView.setAdapter(new WeatherAdapter(new ArrayList<>()));
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = (String) parent.getItemAtPosition(position);
                updateWeatherData(selectedCity);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle any situation where no item is selected
            }
        });
    }
    private void updateWeatherData(String city) {
        weatherViewModel.getWeatherItemData(city).observe(getViewLifecycleOwner(), weather -> {
            temperatureView.setText(String.format("%s°C", weather.getWttemp()));
            humidityView.setText(String.format("湿度: %s%%", weather.getHumidity()));
            aqiView.setText(String.format("空气: %s", weather.getAqi()));
            updateFutureWeather(weather.getFutureWeather());
        });
    }
    private void updateFutureWeather(List<WeatherItem> futureWeather) {
        WeatherAdapter adapter = (WeatherAdapter) futureWeatherRecyclerView.getAdapter();
        if (adapter != null) {
            adapter.setWeatherData(futureWeather);
        }
    }
    private class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder> {
        private List<WeatherItem> mWeathers;
        public WeatherAdapter(List<WeatherItem> weathers) {
            mWeathers = weathers;
        }
        @NonNull
        @Override
        public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.activity_main, parent, false);
            return new WeatherHolder(v);
        }
        @Override
        public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {
            holder.bindWeather(mWeathers.get(position));
        }
        @Override
        public int getItemCount() {
            return mWeathers.size();
        }
        public void setWeatherData(List<WeatherItem> futureWeather) {
        }
    }
    private class WeatherHolder extends RecyclerView.ViewHolder {
        private TextView mWtTemp;
        private TextView mWtTemp1;
        private TextView mWtTemp2;
        private TextView mWtHumi;
        private TextView mWtNm;
        private TextView mWtAqi;
        public WeatherHolder(@NonNull View itemView) {
            super(itemView);
            mWtTemp = itemView.findViewById(R.id.wt_temp);
            mWtTemp1 = itemView.findViewById(R.id.wt_temp1);
            mWtTemp2 = itemView.findViewById(R.id.wt_temp2);
            mWtHumi = itemView.findViewById(R.id.wt_humi);
            mWtNm = itemView.findViewById(R.id.wt_name);
            mWtAqi = itemView.findViewById(R.id.wt_aqi);
        }
        public void bindWeather(WeatherItem weather) {
            mWtTemp.setText(weather.getWttemp());
            mWtTemp1.setText(weather.getWttemp1());
            mWtTemp2.setText(weather.getWttemp2());
            mWtHumi.setText(weather.getWthumi());
            mWtNm.setText(weather.getWtnm());
            mWtAqi.setText(weather.getAqi());
        }
    }
    private class CityHolder extends RecyclerView.ViewHolder {
        private TextView mCityName;
        public CityHolder(@NonNull View itemView) {
            super(itemView);
            mCityName = itemView.findViewById(R.id.city_name);
        }
        public void bindCity(CityItem city) {
            mCityName.setText(city.getCitynm());
        }
    }
    private class CityAdapter extends RecyclerView.Adapter<WeatherHolder> {
        private List<CityItem> mCitys;
        public CityAdapter(List<CityItem> citys) {
            mCitys = citys;
        }
        @NonNull
        @Override
        public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.activity_main, parent, false);
            return new CityHolder(v);
        }
        @Override
        public void onBindViewHolder(@NonNull CityHolder holder, int position) {
            holder.bindCity(mCitys.get(position));
        }
        @Override
        public int getItemCount() {
            return mCitys.size();
        }
    }
}

/**import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class WeatherFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private WeatherViewModel mWeatherViewModel;
    private CityViewModel mCityViewModel;
    private AppCompatSpinner mCitySpinner;
    private ArrayAdapter<String> mCityAdapter;
    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        mRecyclerView = v.findViewById(R.id.future_weather);
        mCitySpinner = v.findViewById(R.id.city_name);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCityAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        mCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCitySpinner.setAdapter(mCityAdapter);
        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherViewModel = new ViewModelProvider(getActivity()).get(WeatherViewModel.class);
        mCityViewModel = new ViewModelProvider(getActivity()).get(CityViewModel.class);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWeatherViewModel.getWeatherItemData().observe(getViewLifecycleOwner(), new
                Observer<List<WeatherItem>>() {
                    @Override
                    public void onChanged(List<WeatherItem> weathers) {
                        mRecyclerView.setAdapter(new WeatherAdapter(weathers));
                    }
                });
        mCityViewModel.getCityItemData().observe(getViewLifecycleOwner(), new Observer<List<CityItem>>() {
            @Override
            public void onChanged(List<CityItem> cityItems) {
                mCityAdapter.clear();
                for (CityItem cityItem : cityItems) {
                    mCityAdapter.add(cityItem.getCitynm());
                }
            }
        });
    }
    private class WeatherHolder extends RecyclerView.ViewHolder {
        private TextView mWtTemp;
        private TextView mWtTemp1;
        private TextView mWtTemp2;
        private TextView mWtHumi;
        private TextView mWtNm;
        private TextView mWtAqi;
        public WeatherHolder(@NonNull View itemView) {
            super(itemView);
            mWtTemp = itemView.findViewById(R.id.wt_temp);
            mWtTemp1 = itemView.findViewById(R.id.wt_temp1);
            mWtTemp2 = itemView.findViewById(R.id.wt_temp2);
            mWtHumi = itemView.findViewById(R.id.wt_humi);
            mWtNm = itemView.findViewById(R.id.wt_name);
            mWtAqi = itemView.findViewById(R.id.wt_aqi);
        }
        public void bindWeather(WeatherItem weather) {
            mWtTemp.setText(weather.getWttemp());
            mWtTemp1.setText(weather.getWttemp1());
            mWtTemp2.setText(weather.getWttemp2());
            mWtHumi.setText(weather.getWthumi());
            mWtNm.setText(weather.getWtnm());
            mWtAqi.setText(weather.getAqi());
        }
    }
    private class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder> {
        private List<WeatherItem> mWeathers;
        public WeatherAdapter(List<WeatherItem> weathers) {
            mWeathers = weathers;
        }
        @NonNull
        @Override
        public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.activity_main, parent, false);
            return new WeatherHolder(v);
        }
        @Override
        public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {
            holder.bindWeather(mWeathers.get(position));
        }
        @Override
        public int getItemCount() {
            return mWeathers.size();
        }
    }
}**/
