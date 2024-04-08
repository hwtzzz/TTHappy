package com.hwt.tthappy.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

    //直接复制字段值
    public static <TSource, TDestination> TDestination copyProperties(TSource source, TDestination destination) {
        BeanUtils.copyProperties(source, destination);
        return destination;
    }

    /**
     * 将一个List对象转换为另一个List对象
     *
     * @param sourceList       原List
     * @param destinationClass 目标类型
     * @param <TSource>        原始list的对象类型
     * @param <TDestination>   目标list的对象类型
     * @return 目标类型的List
     */
    public static <TSource, TDestination> List<TDestination> mapToList(List<TSource> sourceList, Class<TDestination> destinationClass) {
        if (sourceList.isEmpty() || sourceList == null) {
            return null;
        }
        List<TDestination> tList = new ArrayList<>();
        for (TSource f : sourceList) {
            TDestination t = mapToModel(f, destinationClass);
            tList.add(t);
        }
        return tList;
    }


    /**
     * 将一个对象转换为另一个对象
     *
     * @param Source           原始对象
     * @param destinationClass 目标对象类型
     * @param <TSource>        原始对象类型
     * @param <TDestination>   目标对象类型
     * @return 目标类型的对象
     */
    public static <TSource, TDestination> TDestination mapToModel(TSource Source, Class<TDestination> destinationClass) {
        TDestination model = null;
        if (Source == null || destinationClass == null) {
            return null;
        }
        try {
            model = destinationClass.newInstance();
            BeanUtils.copyProperties(Source, model);
        } catch (InstantiationException e) {
            throw new UserFriendlyException("MapToModel : 实例化异常！" + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new UserFriendlyException("MapToModel : 安全权限异常！" + e.getMessage());
        }
        return model;
    }

    /**
     * 将实体分页数据转换为Dto分页数据
     *
     * @param pageSource 原始分页结果
     * @param tClass   要转换的Dto类型
     * @return Dto类型的分页结果
     */
    public static <TEntity, TDto> IPage<TDto> PageMapToDto(IPage<TEntity> pageSource, Class<TDto> tClass) {
        try {
            // 创建Page对象，实际上是一个ArrayList类型的集合
            if (pageSource != null) {

                IPage<TDto> page = new Page<>(pageSource.getCurrent(), pageSource.getSize());
                page.setTotal(pageSource.getTotal());
                List<TEntity> records = pageSource.getRecords();
                List<TDto> list = new ArrayList<>();
                for (TEntity record : records) {
                    if (record != null) {
                        //TDto newV = dtoClass.newInstance();
                        // 把原对象数据拷贝到新的对象
                        TDto dto = tClass.newInstance();
                        BeanUtils.copyProperties(record, dto);
                        list.add(dto);
                    }
                }
                page.setRecords(list);
                page.setTotal(pageSource.getTotal());
                return page;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Page<>(1, 0, 0);
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将对象装换为MultiValueMap
     *
     * @param bean
     * @return
     */
    public static <T> MultiValueMap<String, Object> beanToMultiValueMap(T bean) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.add(key + "", beanMap.get(key));
            }
        }
        return map;
    }
}