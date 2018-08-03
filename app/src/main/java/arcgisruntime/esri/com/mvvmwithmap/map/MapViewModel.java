package arcgisruntime.esri.com.mvvmwithmap.map;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalItem;

/* Copyright 2016 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For additional information, contact:
 * Environmental Systems Research Institute, Inc.
 * Attn: Contracts Dept
 * 380 New York Street
 * Redlands, California, USA 92373
 *
 * email: contracts@esri.com
 *
 */
public class MapViewModel extends ViewModel{

    private MutableLiveData<ArcGISMap> map;
    private MutableLiveData<String> mapTitle;
    private MutableLiveData<Boolean> allowAddFeature;


    public MapViewModel() {
        allowAddFeature = new MutableLiveData<>();
        allowAddFeature.setValue(false);
    }

    public MutableLiveData<ArcGISMap> getMap() {
        if (map == null) {
            // Inject Portal and item id
            PortalItem item = new PortalItem(new Portal("https://www.arcgis.com"), "fcc7fc65bb96464c9c0986576c119a92");
            map = new MutableLiveData<ArcGISMap>();
            ArcGISMap arcGISMap = new ArcGISMap(item);
            map.postValue(arcGISMap);
        }
        return map;
    }

    public void setMap(MutableLiveData<ArcGISMap> arcGISMap) {
        map = arcGISMap;

    }


    public MutableLiveData<String> getMapTitle(){
        return mapTitle;
    }

    public void setMapTitle(MutableLiveData<String> title) {
        mapTitle = title;

    }

    public MutableLiveData<Boolean> getAllowAddFeature()
    {
        return allowAddFeature;
    }


    public void setAllowAddFeature(MutableLiveData<Boolean> allowed) {
        allowAddFeature = allowed;

    }


}
