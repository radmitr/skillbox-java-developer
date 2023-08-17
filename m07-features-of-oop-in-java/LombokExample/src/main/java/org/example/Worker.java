package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(exclude = "workStart")
@AllArgsConstructor
public class Worker {

    private String name;
    private Integer salary;
    private Date workStart;
}
