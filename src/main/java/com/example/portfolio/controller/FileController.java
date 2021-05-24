package com.example.portfolio.controller;
import cn.hutool.json.JSONObject;
import com.example.portfolio.common.lang.Result;
import com.example.portfolio.service.FileUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("upload")
    public List<Map> upload(@RequestBody List<MultipartFile> files) {
        List<Map> resultList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        if (files != null) {
            for (MultipartFile file : files) {
                map = new HashMap<>();
                String returnFileUrl = fileUploadService.upload(file);
                if (returnFileUrl.equals("error")) {
                    map.put("error", "文件上传失败1！");
                    resultList.add(map);
                }
                map.put("success", "文件上传成功！");
                map.put("returnFileUrl", returnFileUrl);
                resultList.add(map);
            }
        } else {
            map.put("error", "文件上传失败2！");
            resultList.add(map);
        }
        return resultList;
    }

    @GetMapping("download")
    public Result downloadFile(String fileName,HttpServletResponse response) throws Exception{
        String status = fileUploadService.download(fileName, response);
        if (status.equals("error")){
            return Result.success("error");
        } else {
            return Result.success("success");
        }
    }
}
