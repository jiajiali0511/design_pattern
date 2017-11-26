package com.fresh.design.pattern.strategy;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by ljiajiali on 17-11-16.
 */
public class CalPriceFactory {

    private static final String CAL_PRICE_PACKAGE = "com.fresh.design.pattern.strategy";
    private ClassLoader classLoader = getClass().getClassLoader();
    private List<Class<? extends CalPrice>> calPriceList;

    public CalPrice createCalPrice(Custom custom) {
        SortedMap<Integer, Class<? extends CalPrice>> sortedMap = Maps.newTreeMap();
        for (Class<? extends CalPrice> clazz : calPriceList) {
            Annotation annotation = handleAnnotation(clazz);
            if (annotation == null) {
                continue;
            }
            if (annotation instanceof TotalValidRegion) {
                TotalValidRegion totalValidRegion = (TotalValidRegion) annotation;
                if (new BigDecimal(totalValidRegion.value().min()).compareTo(custom.getTotalAmount()) <= 0
                        && new BigDecimal(totalValidRegion.value().max()).compareTo(custom.getTotalAmount()) > 0) {
                    sortedMap.put(totalValidRegion.value().order(), clazz);
                }
            } else if (annotation instanceof OnceValidRegion) {
                OnceValidRegion onceValidRegion = (OnceValidRegion) annotation;
                if (new BigDecimal(onceValidRegion.value().min()).compareTo(custom.getTotalAmount()) <= 0
                        && new BigDecimal(onceValidRegion.value().max()).compareTo(custom.getTotalAmount()) > 0) {
                    sortedMap.put(onceValidRegion.value().order(), clazz);
                }
            }
        }
        try {
            return CallPriceProxy.getCallPriceProxy(sortedMap);
        } catch (Exception e) {
            throw new RuntimeException("策略获得失败");
        }
    }

    private Annotation handleAnnotation(Class<? extends CalPrice> clazz) {
        TotalValidRegion totalValidRegion = clazz.getAnnotation(TotalValidRegion.class);
        OnceValidRegion onceValidRegion = clazz.getAnnotation(OnceValidRegion.class);
        if (totalValidRegion == null && onceValidRegion == null) {
            return null;
        }
        return totalValidRegion == null ? onceValidRegion : totalValidRegion;
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
