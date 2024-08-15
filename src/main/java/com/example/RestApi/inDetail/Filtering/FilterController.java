package com.example.RestApi.inDetail.Filtering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {

    @GetMapping(path = "/filtering")
    public List<SomeBean> getBeans(){
        return Arrays.asList(
                new SomeBean("f1","f2","f3"),
                new SomeBean("f4","f5","f6")
        );
    }

    @GetMapping(path = "dynamic-filtering")
    public MappingJacksonValue getFilteredBeans(){
        SomeBean someBean = new SomeBean(
                "field1","field2","field3");

        MappingJacksonValue mj = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept(
                        "field1","field2"
                );
        FilterProvider filters = new SimpleFilterProvider().
                addFilter("Bean-Filter",filter);
        mj.setFilters(filters);
        return mj;
    }
}
