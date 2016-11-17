package com.hitherejoe.androidboilerplate.data;

import com.hitherejoe.androidboilerplate.data.local.PreferencesHelper;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.data.model.Config;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.data.remote.ConfigService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class DataManager {

    private final AndroidBoilerplateService mAndroidBoilerplateService;
    private final ConfigService mConfigService;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(AndroidBoilerplateService watchTowerService,
                       PreferencesHelper preferencesHelper,
                       ConfigService configService) {
        mAndroidBoilerplateService = watchTowerService;
        mPreferencesHelper = preferencesHelper;
        mConfigService=configService;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<List<Character>> getCharacters(int[] ids) {
        List<Integer> characterIds = new ArrayList<>(ids.length);
        for (int id : ids) {
            characterIds.add(id);
        }
        return Observable.from(characterIds).concatMap(new Func1<Integer, Observable<Character>>() {
            @Override
            public Observable<Character> call(Integer integer) {
                return mAndroidBoilerplateService.getCharacter(integer);
            }
        }).toList();
    }

    public Observable<Config> getConfig() {
        Observable<Config> config =mConfigService.getConfig();
        return config;
    }
}
