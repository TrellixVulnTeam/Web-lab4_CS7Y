package com.labs.backend.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseData {
    public boolean success;
    public String info;
    public String token;
}
