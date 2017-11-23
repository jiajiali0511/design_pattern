package com.fresh.design.pattern.strategy;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by ljiajiali on 17-11-16.
 */
public class CalPriceFactory {

    private static final String CAL_PRICE_PACKAGE = "com.fresh.design.pattern.strategy";
    private ClassLoader classLoader = getClass().getClassLoader();
    private List<Class<? extends CalPrice>> calPriceList;

    public CalPrice createCalPrice(Custom custom) {
        for (Class<? extends CalPrice> clazz : calPriceList) {
            TotalValidRegion validRegion = handleAnnotation(clazz);
            if (validRegion == null) {
                continue;
            }
            if (new BigDecimal(validRegion.min()).compareTo(custom.getTotalAmount()) <= 0 && new BigDecimal(validRegion.max()).compareTo(custom.getTotalAmount()) > 0) {
                try {
                    return clazz.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("策略获得失败");
                }
            }
        }
        throw new RuntimeException("策略获得失败");
    }

    private TotalValidRegion handleAnnotation(Class<? extends CalPrice> clazz) {
        return clazz.getAnnotation(TotalValidRegion.class);
    }

    private CalPriceFactory() {
        init();
    }

    private void init() {
        calPriceList = Lists.newArrayList();
        File[] resource = getResources();
        Class<CalPrice> calPriceClazz = null;
        try {
            calPriceClazz = (Class<CalPrice>) classLoader.loadClass(CalPrice.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("未找到策略接口");
        }
        for (int i = 0; i < resource.length; i++) {
            try {
                Class<?> clazz = classLoader.loadClass(CAL_PRICE_PACKAGE + "." + resource[i].getName().replace(".class", ""));
                if (CalPrice.class.isAssignableFrom(clazz) && clazz != calPriceClazz) {
                    calPriceList.add((Class<? extends CalPrice>) clazz);
                }
            } catch (ClassNotFoundException e) {

            }
        }
    }

    private File[] getResources() {
        try {
            File file = new File(classLoader.getResource(CAL_PRICE_PACKAGE.replace(".", "/")).toURI());
            return file.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    if (pathname.getName().endsWith(".class")) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException("未找到策略源");
        }
    }

    public static CalPriceFactory getInstance() {
        return CalPriceFactoryInstance.instance;
    }

    private static class CalPriceFactoryInstance {
        private static CalPriceFactory instance = new CalPriceFactory();
    }

}
