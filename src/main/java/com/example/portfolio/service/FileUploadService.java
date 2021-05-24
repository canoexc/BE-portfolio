package com.example.portfolio.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public interface FileUploadService {
    public String upload(MultipartFile uploadFile);
    public String download(String fileName, HttpServletResponse response) throws UnsupportedEncodingException;
    public String delete(String fileName);
}
