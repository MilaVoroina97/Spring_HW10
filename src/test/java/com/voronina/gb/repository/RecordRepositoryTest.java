package com.voronina.gb.repository;

import com.voronina.gb.model.Record;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RecordRepositoryTest {

    @Autowired
    private RecordRepository recordRepository;

    @Test
    void findRecordByName() {
        Record record1 = new Record();
        record1.setId(1L);
        record1.setName("Work");
        record1.setContent("We have important meeting today");
        record1.setCreatedDate("12.01.2023");

        Record record2 = new Record();
        record2.setId(2L);
        record2.setName("Home");
        record2.setContent("Don't forget to clean fridge!");
        record2.setCreatedDate("08.08.2019");


        recordRepository.save(record1);
        recordRepository.save(record2);

        String name = "Work";
        List<Record> resultList = recordRepository.findRecordByName(name);

        assertThat(resultList).isNotNull();
        assertThat(resultList.size()).isEqualTo(1);



    }
}
