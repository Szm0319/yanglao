package com.example.yanglao.entity;

import lombok.Data;

@Data
public class File {
    private int cai_id;
    private String filename;
    private byte[] filedata;
}