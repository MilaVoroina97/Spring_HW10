package com.voronina.gb.controller;

import com.voronina.gb.model.Record;
import com.voronina.gb.service.RecordService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final Counter addRecordCounter = Metrics.counter("add_record_count");
    private final Timer findAllRecordsTimer = Metrics.timer("find_records_timer");

    @GetMapping("/records")
    public String getAll(Model model) {

        findAllRecordsTimer.baseTimeUnit();
        List<Record> records = recordService.getAll();

        model.addAttribute("records", records);
        return "records-list";
    }

    @GetMapping("/record-create")
    public String createRecordForm(Record record) {
        addRecordCounter.increment();
        return "record-create";
    }

    @PostMapping("/record-create")
    public String createRecord(Record record) {
        recordService.createRecord(record);
        return "redirect:/records";
    }

    @GetMapping("/record-delete/{id}")
    public String deleteRecord(@PathVariable("id") Long id) {
        recordService.deleteRecord(id);
        return "redirect:/records";
    }

    @GetMapping("/record-update/{id}")
    public String getOneRecord(@PathVariable("id") Long id, Model model) {
        Record record = recordService.getRecordById(id);
        model.addAttribute("record", record);
        return "record-update";
    }

    @PostMapping("/record-update")
    public String updateRecord(Record record) {
        recordService.updateRecord(record);
        return "redirect:/records";
    }

    @GetMapping("/find-records-by-name")
    public String findActsByPeriod(Model model) {
        List<Record> records = recordService.findRecordByName("Work");
        model.addAttribute("records", records);
        return "record-list";
    }
}
