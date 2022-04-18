package com.etnetera.hr.repository;


import com.etnetera.hr.entity.JsFrameworkEntity;


import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkRepositoryTest {

    @Autowired
    private JavaScriptFrameworkRepository underTest;

    @Test
    public void itShouldSaveJSFramework() {
        //Given
        JsFrameworkEntity entity = new JsFrameworkEntity(1L, "Angular", "10.5", null, null);
        //When
        var actualSaveJSFramework = underTest.save(entity);
        //Then
        var optionalJsFrameworkEntity = underTest.findById(1L);
        assertThat(optionalJsFrameworkEntity).isPresent()
                .hasValueSatisfying(jsFrameworkEntity ->
                        assertThat(jsFrameworkEntity.getId()).isEqualTo(entity.getId()))
                .hasValueSatisfying(jsFrameworkEntity ->
                        assertThat(jsFrameworkEntity.getName()).isEqualTo(entity.getName()));

    }

    @Test
    public void itShouldFindAllByName() {
        //Given
        JsFrameworkEntity entity = new JsFrameworkEntity(1L, "Angular", "10.5", null, null);
        JsFrameworkEntity entity2 = new JsFrameworkEntity(2L, "Angular", "10.4", null, null);
        JsFrameworkEntity entity3 = new JsFrameworkEntity(3L, "React", "10.4", null, null);
        //When
        underTest.save(entity);
        underTest.save(entity2);
        underTest.save(entity3);
        //Then
        var optionalJsFrameworkEntity = underTest.findAllByName(entity.getName());
        Assertions.assertThat(optionalJsFrameworkEntity)
                .hasSize(2)
                .extracting(JsFrameworkEntity::getName)
                .containsExactlyInAnyOrder("Angular", "Angular");

    }
}
