package com.shixzh.bcms.framework.annotation;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomizedAnnotationTest {
    @Test
    public void testUseCase() {
        List<Integer> idList = new ArrayList<>();
        Collections.addAll(idList, 47, 48, 67);
        trackUseCase(idList, PasswordUtils.class);

    }

    public void trackUseCase(List<Integer> list, Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            UseCase useCase = method.getAnnotation(UseCase.class);
            if (useCase != null) {
                System.out.println("id: " + useCase.id() + ", description: " + useCase.description());
                list.remove(new Integer(useCase.id()));
            }
        }

        for (Integer id : list) {
            System.out.println("Warning: Missing use case - " + id);
        }
    }
}
