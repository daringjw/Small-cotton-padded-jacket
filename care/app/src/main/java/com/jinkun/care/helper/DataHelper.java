package com.jinkun.care.helper;

import com.jinkun.care.model.entity.DetailDiseaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by coderwjq on 2017/8/18 10:22.
 * @Desc
 */

public class DataHelper {

    public static List<DetailDiseaseEntity> diseaseArrayTransToList(String[] names) {
        ArrayList<DetailDiseaseEntity> datas = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            datas.add(new DetailDiseaseEntity(names[i], null, false));
        }
        return datas;
    }
}
