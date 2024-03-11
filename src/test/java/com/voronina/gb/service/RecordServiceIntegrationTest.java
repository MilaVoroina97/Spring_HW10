package com.voronina.gb.service;

import com.voronina.gb.model.Record;
import com.voronina.gb.repository.RecordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RecordServiceIntegrationTest {

    @MockBean
    private RecordRepository recordRepository;
    @Autowired
    private RecordService recordService;

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

        List<Record> recordList = new ArrayList<>();
        recordList.add(record1);
        recordList.add(record2);

        given(recordRepository.findAll()).willReturn(recordList);

        recordService.findRecordByName("Work");

        verify(recordRepository).findRecordByName("Work");
    }
}
