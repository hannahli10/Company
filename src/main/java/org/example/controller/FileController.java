package org.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/file")
public class FileController {
    private Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired
//    private FileService fileService;

    @RequestMapping(value = "", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file){
        logger.info("test file name:"+ file.getOriginalFilename());
        return null;
    }
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String getObject(@RequestParam("filename")String s3Key){
//        return fileService.getFileUrl(s3Key);
//    }
}
