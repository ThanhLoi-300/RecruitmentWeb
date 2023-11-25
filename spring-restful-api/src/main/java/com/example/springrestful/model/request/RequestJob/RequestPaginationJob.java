package com.example.springrestful.model.request.RequestJob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPaginationJob {
    private int limit;
    private int offset;
}
