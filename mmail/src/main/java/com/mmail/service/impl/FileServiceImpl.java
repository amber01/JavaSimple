package com.mmail.service.impl;

import com.google.common.collect.Lists;
import com.mmail.service.IFileService;
import com.mmail.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    //上传问之后返回文件名
    public String upload(MultipartFile file,String path){
        //124.jpg
        String fileName = file.getOriginalFilename(); //文件名
        //文件后缀名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1); //截取之后获取的是jpg
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs(); //mkdirs文件目录有多级
        }
        File targetFile = new File(path,uploadFileName);

        try {
            file.transferTo(targetFile);
            //文件已经上传成功了

            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上

            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }

        //A:abc.jpg
        //B:abc.jpg
        return targetFile.getName();
    }
}
