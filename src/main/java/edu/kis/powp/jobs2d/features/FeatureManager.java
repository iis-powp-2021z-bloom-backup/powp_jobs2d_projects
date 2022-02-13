package edu.kis.powp.jobs2d.features;

import java.util.ArrayList;
import java.util.List;

public class FeatureManager {

    private final static List<FeatureInterface> featureList = new ArrayList<>();

    public static void setupFeatures() {
        for (FeatureInterface feature : featureList) {
            feature.setup();
        }
    }

    public static void addFeature(FeatureInterface newFeature) {
        featureList.add(newFeature);
    }

    public static List<FeatureInterface> getList() {
        return featureList;
    }
}
